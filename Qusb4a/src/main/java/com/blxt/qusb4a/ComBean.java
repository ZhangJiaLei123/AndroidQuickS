package com.blxt.qusb4a;


/**
 * 串口数据
 */
public class ComBean {
	/** 接收串口 */
	public String sComPort = "";
    /** 接收数据 */
    public byte[] bRec = null;
	/** 接收时间 */
    public long iRectime = 0;

    public ComBean(String sPort, byte[] buffer, int size) {
        sComPort = sPort;
        bRec = new byte[size];
        for (int i = 0; i < size; i++) {
			bRec[i] = buffer[i];
		}
		iRectime = new java.util.Date().getTime();
    }
}