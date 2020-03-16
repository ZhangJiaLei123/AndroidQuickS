package com.blxt.quicklog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;

import com.blxt.qfile.QZip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QLog
{
  public static long MAX_LNEG = 10485760L;

  public static class PATH
  {
    public static String SDPath = Environment.getExternalStorageDirectory().getPath();

    public static String getAppFilesPath(Context context) { return context.getFilesDir().getPath(); }

    public static String getAppCachePath(Context context) { return context.getCacheDir().getPath(); }

    public static String getAppCacheLogPath(Context context) { return getAppCachePath(context) + "/log.txt"; }
  }

  static String logFilePath = "";
  static File file = null;

  public static boolean isSave = true;
  public static String CODE = "UTF-8";
  static final String DEFAULT_TAG = "Q-LOG";

  public QLog(@NonNull String defaultpath) {
    logFilePath = defaultpath;
    file = new File(logFilePath);
    File filePath = new File(file.getParent());
    if (!filePath.exists()) {
      filePath.mkdirs();
    }
  }

  public QLog(Context context) { this(PATH.getAppCacheLogPath(context)); }

  public QLog setMaxSize(long MAX_LNEG) {
    QLog.MAX_LNEG = MAX_LNEG;
    return this;
  }

  public static void i(Object... msg) {
    Mlg mlg = new Mlg(msg);

    saveLog(String.format("[info]  %s: %s", new Object[] { mlg.tag, mlg.str }));
    Log.i(mlg.tag, mlg.str);
  }

  public static void d(Object... msg) {
    Mlg mlg = new Mlg(msg);

    saveLog(String.format("[debug] %s: %s", new Object[] { mlg.tag, mlg.str }));
    Log.d(mlg.tag, mlg.str);
  }

  public static void e(Object... msg) {
    Mlg mlg = new Mlg(msg);

    saveLog(String.format("[error] %s: %s", new Object[] { mlg.tag, mlg.str }));
    Log.e(mlg.tag, mlg.str);
  }

  public static void w(Object... msg) {
    Mlg mlg = new Mlg(msg);

    saveLog(String.format("[worry] %s: %s", new Object[] { mlg.tag, mlg.str }));
    Log.w(mlg.tag, mlg.str);
  }

  public static void f(String format, Object... msg) {
    Mlg mlg = new Mlg(format, msg);
    saveLog(String.format("[info] %s: %s", new Object[] { mlg.tag, mlg.str }));
    Log.i(mlg.tag, mlg.str);
  }

  static class Mlg
  {
    String tag = "Q-LOG";
    String str = "";

    Mlg(String format, Object... msg) {
      if (msg.length == 1) {
        this.str = String.format(format, msg);
      } else {

        this.tag = getObjStr(msg[0]);
        Object[] msgN = new Object[msg.length - 1];
        for (int i = 1; i < msgN.length; i++) {
          msgN[i - 1] = msg[i];
        }

        this.str = String.format(format, msgN);
      }
    }

    Mlg(Object... msg) {
      if (msg.length == 1) {
        this.str = getObjStr(msg[0]);
      } else {

        this.tag = getObjStr(msg[0]);
        for (int i = 1; i < msg.length - 1; i++) {
          this.str += getObjStr(msg[i]) + ",";
        }
        this.str += getObjStr(msg[msg.length - 1]);
      }
    }

    private String getObjStr(Object obj) {
      if (obj == null) {
        return "null";
      }

      if (obj instanceof Exception) {
        return QLog.getException((Exception)obj);
      }
      if (obj instanceof Byte) {
        return String.format("%X", new Object[] { obj });
      }
      if (obj instanceof Short) {
        return String.format("%X", new Object[] { obj });
      }
      if (obj instanceof Integer) {
        return String.format("%d", new Object[] { obj });
      }
      if (obj instanceof Long) {
        return String.format("%d", new Object[] { obj });
      }
      if (obj instanceof Float) {
        return String.format("%f", new Object[] { obj });
      }
      if (obj instanceof Double) {
        return String.format("%f", new Object[] { obj });
      }
      if (obj instanceof Character) {
        return String.format("%c", new Object[] { obj });
      }
      if (obj instanceof String) {
        return (String)obj;
      }
      if (obj instanceof Class) {
        return ((Class)obj).getClass().getName();
      }
      if (obj instanceof Boolean) {
        return "" + obj.toString();
      }
      if (obj instanceof String[]) {
        String _S = "";
        for (String s : (String[])obj) {
          _S = _S + s + ",";
        }
        return _S;
      }

      return obj.toString();
    }
  }

  public static void saveLog(String msg) {
    if (isSave) {
      addFile(file,
              String.format("%s %s\r\n", new Object[] {
                      getTimeStr("<MM-dd HH:mm:ss.S> "), msg
              }));
    }
  }

  public static String getTimeStr(String format) {
    Date currentTime = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat(format);
    String dateString = formatter.format(currentTime);
    return dateString;
  }

  public static synchronized boolean addFile(@NonNull File filepath, @NonNull String content) {
    FileOutputStream fos = null;
    OutputStreamWriter osw = null;
    try {
      if (!filepath.exists()) {
        filepath.createNewFile();
        fos = new FileOutputStream(filepath);
      } else {

        fos = new FileOutputStream(filepath, true);
      }

      osw = new OutputStreamWriter(fos, CODE);

      osw.write(content);

      osw.close();
    } catch (Exception e) {
      return false;
    } finally {
      if (fos != null) {
        try {
          fos.close();
          osw.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    long size = filepath.length();
    if (size > MAX_LNEG) {

      File zip = new File(filepath.getParent() + "/" + getNameWithoutFormat(filepath.getPath()) + ".zip");

      if (zip.exists()) {
        zip.delete();
      }
      try {
        QZip.zipFile(file, zip);
      } catch (Exception e) {
        e.printStackTrace();
      }
      file.delete();
    }

    return true;
  }

  public static String getException(Exception e) {
    Writer writer = null;
    PrintWriter printWriter = null;
    try {
      writer = new StringWriter();
      printWriter = new PrintWriter(writer);
      e.printStackTrace(printWriter);
      return writer.toString();
    } finally {
      try {
        if (writer != null)
          writer.close();
        if (printWriter != null)
          printWriter.close();
      } catch (IOException iOException) {}
    }
  }

  public static String getNameWithoutFormat(String filePath) {
    if (filePath.isEmpty()) {
      return "";
    }
    int point = filePath.lastIndexOf('.');
    return filePath.substring(filePath.lastIndexOf(File.separator) + 1, point);
  }

  public static long getLogSize() {
    if (file == null || !file.exists()) {
      return 0L;
    }
    return file.length();
  }

  public static synchronized boolean delLogFile() {
    if (file == null || !file.exists()) {
      return false;
    }
    try {
      file.delete();
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public static String openLogFolder(Context context) {
    Intent shareIntent = new Intent("android.intent.action.SEND");
    shareIntent.putExtra("android.intent.extra.STREAM",
            (Parcelable)Uri.fromFile(file));
    shareIntent.setType("text/plain");
    context.startActivity(Intent.createChooser(shareIntent, "打开为："));

    return file.getPath();
  }
}
