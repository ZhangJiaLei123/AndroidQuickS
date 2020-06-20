package com.blxt.qconverter;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Converter
{
  public static char[] getChars(byte[] bytes) {
    Charset cs = Charset.forName("UTF-8");
    ByteBuffer bb = ByteBuffer.allocate(bytes.length);
    bb.put(bytes);
    bb.flip();
    CharBuffer cb = cs.decode(bb);
    return cb.array();
  }

  public static String byte2Str(byte[] bytes) {
    char[] a = getChars(bytes);
    return new String(a);
  }

  public static byte[] getBytes(char[] chars, String code) {
    Charset cs = Charset.forName(code);
    CharBuffer cb = CharBuffer.allocate(chars.length);
    cb.put(chars);
    cb.flip();
    ByteBuffer bb = cs.encode(cb);
    byte[] bytes = bb.array();
    
    int i = bytes.length;
    for (; i > 1 && bytes[i - 1] == 0; i--);
    byte[] bytes_new = new byte[i];
    System.arraycopy(bytes, 0, bytes_new, 0, i);
    return bytes_new;
  }

  public static String ToString(byte[] src, String format) {
    StringBuilder stringBuilder = new StringBuilder("");
    if (src == null || src.length <= 0) {
      return null;
    }
    for (int i = 0; i < src.length; i++) {
      stringBuilder.append(String.format(format, new Object[] { Byte.valueOf(src[i]) }));
    } 
    
    return stringBuilder.toString();
  }
  
  public static String ToString(int[] src, String format) {
    StringBuilder stringBuilder = new StringBuilder("");
    if (src == null || src.length <= 0) {
      return null;
    }
    for (int i = 0; i < src.length; i++) {
      stringBuilder.append(String.format(format, new Object[] { Integer.valueOf(src[i]) }));
    } 
    
    return stringBuilder.toString();
  }

  public static short[] ToShorts(String licString) {
    if (licString == null || licString.equals("")) {
      return null;
    }
    licString = licString.toUpperCase();
    int length = licString.length();
    char[] hexChars = licString.toCharArray();
    short[] date = new short[length];
    for (int i = 0; i < length; i++) {
      date[i] = (short)hexChars[i];
    }
    return date;
  }

  public static byte[] toBytes(String content) {
    String[] beans = content.split(" ");
    byte[] byteNum = new byte[beans.length];
    
    int index = 0;
    for (String bean : beans) {
      if (bean != null) {

        
        bean = bean.trim();
        if (bean.length() > 0)
        {
          
          byteNum[index++] = Byte.parseByte(bean, 16); } 
      } 
    }  return byteNum;
  }
  
  public static byte[] toBytes(long num) {
    byte[] byteNum = new byte[8];
    for (int ix = 0; ix < 8; ix++) {
      int offset = 64 - (ix + 1) * 8;
      byteNum[ix] = (byte)(int)(num >> offset & 0xFFL);
    } 
    return byteNum;
  }
  
  public static long toLong(byte[] byteNum) {
    long num = 0L;
    for (int ix = 0; ix < 8; ix++) {
      num <<= 8L;
      num |= (byteNum[ix] & 0xFF);
    } 
    return num;
  }

  public static byte toByte(int num) { return (byte)(num & 0xFF); }

  public static int toInt(byte byteNum) { return (byteNum > 0) ? byteNum : (128 + 128 + byteNum); }

  public static String getTimeStr(String format) {
    Date currentTime = new Date();
    return getTimeStr(currentTime, format);
  }

  public static String getTimeStr(Date currentTime, String format) {
    SimpleDateFormat formatter = new SimpleDateFormat(format);
    String dateString = formatter.format(currentTime);
    return dateString;
  }
}
 