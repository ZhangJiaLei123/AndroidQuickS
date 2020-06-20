package com.blxt.qusb4a;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;

import java.util.List;

public abstract class UsbHelper {

    private String USB_PERMISSION = "blxt.usb.permission";
    private PendingIntent mPrtPermissionIntent; //获取外设权限的意图
    Context context;
    /** 设备列表*/
    List<UsbDevice> devices;

    public UsbHelper(Context context) {
        this.context = context;
        register();
    }

    /**
     * 刷新
     */
    public void refresh() {
        devices = UsbTools.getDevices(context);
       // QLog.i("USB", devices);
       // for (UsbDevice device : devices) {
        //    QLog.i("USB", device.getDeviceName());
       // }
    }

    public UsbManager getUsbManager() {
        return (UsbManager) context.getSystemService(Context.USB_SERVICE);
    }

    public List<UsbDevice> getDevices() {
        return devices;
    }

    /**
     * 请求权限：一般来说有弹框
     */
    public void requestNormalPermission(UsbManager usbManager, UsbDevice device) {
        if (!usbManager.hasPermission(device)) {
         //   QLog.d("USB没有权限，试着请求它");
            usbManager.requestPermission(device, mPrtPermissionIntent);// will recall mReceiver
        } else {
         //   QLog.d("USB已授予权限，连接usb");
        }
    }

    /**
     * 注册广播
     */
    private void register() {
        //注册在此service下的receiver的监听的action
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        intentFilter.addAction(UsbManager.ACTION_USB_ACCESSORY_ATTACHED);
        intentFilter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        intentFilter.addAction(USB_PERMISSION);
        context.registerReceiver(usbReceiver, intentFilter);//注册receiver
        //通知监听外设权限注册状态
        //PendingIntent：连接外设的intent
        //ask permission
        mPrtPermissionIntent = PendingIntent.getBroadcast(context, 0, new Intent(USB_PERMISSION), 0);
    }

    /**
     * 设备插拔广播接收
     */
    private BroadcastReceiver usbReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            String action = intent.getAction();

            // USB注册动作
            if (USB_PERMISSION.equals(action)) {
                {
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        if ((UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE) != null) {
                         //   QLog.d("请求dev权限并授予pemission权限后");
                        } else {
                         //   QLog.d("USB外设意外消失");
                        }
                    } else {
                      //  QLog.d("USB权限注册失败");
                    }
                     onUsbPermissionDenied();
                }
            }
            // USB拔插动作
            else if (UsbManager.ACTION_USB_ACCESSORY_ATTACHED.equals(action)
                    || UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
               // QLog.d("USB 插入...");
                onUsbConnected();
            } else if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
              //  QLog.d("USB 拔出...");
                onDeviceNotFound();
            }
        }
    };

    protected abstract void onUsbConnected();
    protected abstract void onUsbPermissionDenied();
    protected abstract void onDeviceNotFound();

}
