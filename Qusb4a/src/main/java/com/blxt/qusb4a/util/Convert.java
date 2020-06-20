package com.blxt.qusb4a.util;


/**
 * @author benjaminwan
 *数据转换工具
 */
public class Convert {
	//-------------------------------------------------------
	// 判断奇数或偶数，位运算，最后一位是1则为奇数，为0是偶数
	static public int isOdd(int num)
	{
		return num & 0x1;
	}
	//-------------------------------------------------------
	// Hex字符串转int
	static public int HexToInt(String inHex)
	{
		return Integer.parseInt(inHex, 16);
	}
	//-------------------------------------------------------
	// Hex字符串转byte
	static public byte HexToByte(String inHex)
	{
		return (byte)Integer.parseInt(inHex,16);
	}
	//-------------------------------------------------------
	// 1字节转2个Hex字符
	static public String Byte2Hex(Byte inByte)
	{
		return String.format("%02x", inByte).toUpperCase();
	}
	//-------------------------------------------------------
	// 字节数组转转hex字符串
	static public String ByteArrToHex(byte[] inBytArr)
	{
		StringBuilder strBuilder=new StringBuilder();
		int j=inBytArr.length;
		for (int i = 0; i < j; i++)
		{
			strBuilder.append(Byte2Hex(inBytArr[i]));
			strBuilder.append(" ");
		}
		return strBuilder.toString();
	}
	//-------------------------------------------------------
	// 字节数组转转hex字符串，可选长度
	static public String ByteArrToHex(byte[] inBytArr,int offset,int byteCount)//
	{
		StringBuilder strBuilder=new StringBuilder();
		int j=byteCount;
		for (int i = offset; i < j; i++)
		{
			strBuilder.append(Byte2Hex(inBytArr[i]));
		}
		return strBuilder.toString();
	}
	//-------------------------------------------------------
	// 转hex字符串转字节数组
	static public byte[] HexToByteArr(String inHex)//hex字符串转字节数组
	{
		int hexlen = inHex.length();
		byte[] result;
		if (isOdd(hexlen)==1)
		{//奇数
			hexlen++;
			result = new byte[(hexlen/2)];
			inHex="0"+inHex;
		}else {//偶数
			result = new byte[(hexlen/2)];
		}
		int j=0;
		for (int i = 0; i < hexlen; i+=2)
		{
			result[j]=HexToByte(inHex.substring(i,i+2));
			j++;
		}
		return result;
	}
	/**
	 * int到byte[] 由高位到低位
	 * @param i 需要转换为byte数组的整行值。
	 * @return byte数组
	 */
	public static byte[] intToByteArray(int i) {
		byte[] result = new byte[4];
		result[0] = (byte)((i >> 24) & 0xFF);
		result[1] = (byte)((i >> 16) & 0xFF);
		result[2] = (byte)((i >> 8) & 0xFF);
		result[3] = (byte)(i & 0xFF);
		return result;
	}

	/**
	 * byte[]转int
	 * @param bytes 需要转换成int的数组
	 * @return int值
	 */
	public static int byteArrayToInt(byte[] bytes) {
		int value=0;
		for(int i = 0; i < bytes.length; i++) {
			int shift= (3-i) * 8;
			value +=(bytes[i] & 0xFF) << shift;
		}
		return value;
	}

}