package com.blxt.qusb4a;

/**
 * Created by KMS on 2016/8/23.
 */
public interface IUsbConnState {
    void onUsbConnected();

    void onUsbPermissionDenied();

    void onDeviceNotFound();

}
