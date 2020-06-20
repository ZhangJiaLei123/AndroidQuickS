package com.blxt.qusb4a;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Handler;
import android.os.Message;

import com.blxt.qusb4a.util.ComBean;
import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;
import com.hoho.android.usbserial.util.SerialInputOutputManager;

import java.io.IOException;

public abstract class UsbController2 {
    private static final int WRITE_WAIT_MILLIS = 2000;
    private static final int READ_WAIT_MILLIS = 2000;

    private UsbDeviceConnection usbDeviceConnection = null;
    private UsbManager usbManager;
    UsbDevice device;
    UsbSerialPort mUsbSerialPort;
    SerialInputOutputManager mSerialIoManager;

    private boolean isConnect; // 连接标记

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    public UsbController2(UsbManager usbManager, UsbDevice device) {
        this.usbManager = usbManager;
        this.device = device;
    }

    /**
     * usb设备的连接
     */
    public boolean connect() {

        usbDeviceConnection = null;
        usbDeviceConnection = usbManager.openDevice(device);
        UsbSerialDriver driver = UsbSerialProber.getDefaultProber().probeDevice(device);
        mUsbSerialPort = driver.getPorts().get(0);
        if (usbDeviceConnection == null) {
            return false;
        } else { // 连接成功
           if(!setConnectionParam()){
               return false;
           }
        }
        mSerialIoManager = new SerialInputOutputManager(mUsbSerialPort, mListener);
        Thread thread = new Thread(mSerialIoManager);
        thread.start();
        isConnect = true;
        return true;
    }

    /**
     * 是否连接
     * @return
     */
    public boolean isConnect(){
        return isConnect;
    }

    /**
     * 断开连接
     *
     * @return
     */
    public boolean disconnect() {
        stopIoManager();
        isConnect = false;
        if (mUsbSerialPort != null) {
            try {
                mUsbSerialPort.close();
            } catch (IOException e) {
                return false;
            } finally {
                mUsbSerialPort = null;
            }
        }
        return true;
    }

    private void stopIoManager() {
        if (mSerialIoManager != null) {
            mSerialIoManager.stop();
            mSerialIoManager = null;
        }
    }

    /**
     * 设置通讯参数
     */
    private boolean setConnectionParam() {
        try {
            if (device.getInterfaceCount() > 0) {

                mUsbSerialPort.open(usbDeviceConnection);
                mUsbSerialPort.setParameters(19200, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);

//                StringBuilder sb = new StringBuilder();
//
//                sb.append("\n CD  - Carrier Detect = " + mUsbSerialPort.getCD());
//                sb.append("\n CTS - Clear To Send = " + mUsbSerialPort.getCTS());
//                sb.append("\n DSR - Data Set Ready = " + mUsbSerialPort.getDSR());
//                sb.append("\n DTR - Data Terminal Ready = " + mUsbSerialPort.getDTR());
//                sb.append("\n DSR - Data Set Ready = " + mUsbSerialPort.getDSR());
//                sb.append("\n RI  - Ring Indicator = " + mUsbSerialPort.getRI());
//                sb.append("\n RTS - Request To Send = " + mUsbSerialPort.getRTS());

                try {
                    mUsbSerialPort.setDTR(true);
                } catch (IOException x) {
                   // QLog.e("IOException DTR: " + x.getMessage());
                    return false;
                }
                try {
                    mUsbSerialPort.setRTS(true);
                } catch (IOException x) {
                    return false;
                   // QLog.e("IOException RTS: " + x.getMessage());
                }
            }
            //无通讯接口
            else {
               // QLog.e("该USB无通讯接口");
                return false;
            }


        } catch (IOException e) {
           // QLog.e("Error setting up device: " + e.getMessage());
            try {
                if(mUsbSerialPort != null){
                    mUsbSerialPort.setDTR(false);
                    mUsbSerialPort.setRTS(false);
                    mUsbSerialPort.close();
                }
            } catch (IOException e2) {
            }
            mUsbSerialPort = null;
            return false;
        }

        return true;
    }

    /**
     * 发送数据
     *
     * @param data
     * @return
     */
    public boolean sendData(byte[] data) {
        if (mUsbSerialPort == null) {
            return false;
        }
        try {
            mUsbSerialPort.write(data, WRITE_WAIT_MILLIS);
            onDataSend(new ComBean("", data, data.length));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * USB 数据监听
     */
    private final SerialInputOutputManager.Listener mListener =
            new SerialInputOutputManager.Listener() {
                final int RunSlepTime = 5; // 延时ms
                private ReadRunnable runnable = new ReadRunnable();
                //第一次运行线程时设置成true
                private boolean beginning = false;
                /***
                 * 是否启用实时处理,实时处理会导致超过32字节的包被拆包
                 */
                private boolean bRealTime = false;


                @Override
                public void onRunError(Exception e) {
                    // 后面需要添加异常处理
                   // QLog.e("接收USB数据异常");
                }

                @Override
                public void onNewData(final byte[] data) {

                    int size = data.length;

                    if (bRealTime) { // 实时处理数据,使用包长度不超过32字节的
                        ComBean ComRecData = new ComBean(device.getDeviceName(), data, size);
                        onDataReceived(ComRecData);
                    } else { // 长包断包处理
                        //临时数组,将缓冲区buffer中的有效数据读取出来,临时数据长度就是接收到的数据长度。
                        byte[] temp = new byte[size];
                        System.arraycopy(data, 0, temp, 0, size);
                        //具体注释见init方法
                        runnable.put(temp, size);
                        //如果程序第一次运行
                        if (!beginning) {
                            //运行runnable,只在第一次执行,如果重复执行虽不会抛出异常,但是也无法正常执行功能
                            handler.post(runnable);
                            beginning = false;
                        }
                    }

                    if (bRealTime) {
                        try {
                            Thread.sleep(RunSlepTime);//延时ms
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            };

    /**
     * USB 数据接收 断包处理
     */
    public class ReadRunnable implements Runnable {
        private byte[] lastBuffer;
        int time = 0;
        int timeCheak = 20; // 每个 timeSleep 毫秒检查一次缓存中是否接收到消息，
        int timeOut = 50; // 如果 timeOut 毫秒内没有接收到下条消息则认为本次消息传递完成。
        boolean work = true;
        private int lastBufferLength;

        //断包处理逻辑包含其中
        public void put(byte[] buffer, int size) {

            if (lastBuffer == null) {
                lastBuffer = buffer;
            } else {
                lastBufferLength = lastBuffer.length;
                byte[] temp = new byte[lastBufferLength + size];
                //先拷贝之前的数据
                System.arraycopy(lastBuffer, 0, temp, 0, lastBufferLength);
                //再拷贝刚接收到的数据
                System.arraycopy(buffer, 0, temp, lastBufferLength, size);
                lastBuffer = null;
                lastBuffer = temp;
                temp = null;
            }
            work = true;
            time = 0;
        }

        public void reStart() {
            work = true;
            time = 0;
        }

        public void stop() {
            work = false;
            time = 0;
        }

        //接收完成后重置完整消息缓冲区
        public void reset() {
            work = false;
            time = 0;
            lastBuffer = null;
        }

        /**
         * 每个 timeSleep 毫秒检查一次缓存中是否接收到消息，
         * 如果 timeOut 毫秒内没有接收到下条消息则认为本次消息传递完成。
         */
        @Override
        public void run() {
            while (work) {
                try {
                    Thread.sleep(timeCheak);
                    time += timeCheak;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (time >= timeOut) {
                    byte[] finalBuffer = lastBuffer;
                    reset();
                    //业务处理方法
                    ComBean ComRecData = new ComBean(device.getDeviceName(), finalBuffer, finalBuffer.length);
                    onDataReceived(ComRecData);
                }
            }

        }
    }

    //----------------数据收发回调
    protected abstract void onDataReceived(ComBean ComRecData);

    protected abstract void onDataSend(ComBean ComRecData);
}
