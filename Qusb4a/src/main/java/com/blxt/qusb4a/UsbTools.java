package com.blxt.qusb4a;

import android.app.PendingIntent;
import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;

import com.blxt.qusb4a.driver.UsbSerialDriver;
import com.blxt.qusb4a.driver.UsbSerialProber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class UsbTools {
    UsbManager usbManager;
    public UsbTools(Context context){
        usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
    }
    /**
     * 判断对应 USB 设备是否有权限
     */
    public boolean hasPermission(UsbDevice device) {
        return usbManager.hasPermission(device);
    }

    /**
     * 请求获取指定 USB 设备的权限
     */
    public void requestPermission(UsbDevice device ,PendingIntent pi) {
        if (device != null) {
            if (usbManager.hasPermission(device)) {
               // QLog.i("已经获取到权限");
            } else {
                if (pi != null) {
                    usbManager.requestPermission(device, pi);
                  //  QLog.i("请求USB权限");
                } else {
                  //  QLog.i("请注册USB广播");
                }
            }
        }
    }

    public List<UsbDevice> getDeviceList() {
        HashMap<String, UsbDevice> deviceList = usbManager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
        List<UsbDevice> usbDevices = new ArrayList<>();
        while (deviceIterator.hasNext()) {
            UsbDevice device = deviceIterator.next();
            usbDevices.add(device);
           // QLog.e("获取USB设备列表",   device.getDeviceName());
        }
        return usbDevices;
    }

    public static List<UsbDevice> getDevices(Context context)
    {
        UsbManager usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        UsbSerialProber usbDefaultProber = UsbSerialProber.getDefaultProber();
        int i = 0;
        List<UsbDevice> devices = new ArrayList<>();
        for(UsbDevice device : usbManager.getDeviceList().values()) {
            UsbSerialDriver driver = usbDefaultProber.probeDevice(device);
            if(driver != null) {
                devices.add(device);
            }
        }
        return devices;
    }

    public static UsbDevice getDevice(List<UsbDevice> devices, String name)
    {
        for(UsbDevice device : devices){
            if(device != null && name.endsWith(device.getDeviceName())){
                return device;
            }
        }
        return null;
    }

    public UsbDevice getUsbDevice(int vendorId, int productId) {
        HashMap<String, UsbDevice> deviceList = usbManager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
        while (deviceIterator.hasNext()) {
            UsbDevice device = deviceIterator.next();
            if (device.getVendorId() == vendorId && device.getProductId() == productId) {
                //QLog.e("USBUtil", "getDeviceList: " + device.getDeviceName());
                return device;
            }
        }
      //  QLog.e("获取USB设备", "没有对应的设备");
        return null;
    }
}
