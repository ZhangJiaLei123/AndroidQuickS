package com.blxt.qusb4a;


import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;

/**
 * USB控制类
 */
public abstract class UsbController extends UsbController2 {

    public UsbController(UsbManager usbManager, UsbDevice device) {
        super(usbManager, device);
    }
}
