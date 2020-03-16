 package com.blxt.quicklog;

import android.content.Context;
import androidx.annotation.NonNull;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class CrashHandler
  implements Thread.UncaughtExceptionHandler
{
  static final String TAG = "CrashHandler";
  static File fileCrash;
  CrashCallBack crashCallBack = null;

  public CrashHandler(Context context, String fileName) { this(context.getCacheDir().getPath(), fileName); }

  public CrashHandler(String logFilePath, String fileName) {
    File filePath = new File(logFilePath);
    if (!filePath.exists()) {
      filePath.mkdirs();
    }

    fileCrash = new File(logFilePath + fileName);
  }

  public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
    boolean isSave = true;
    if (this.crashCallBack != null) {
      isSave = this.crashCallBack.onCrash(throwable);
    }

    if (!isSave) {
      return;
    }

    String time = QLog.getTimeStr("yyyyMMddHHmmss");
    QLog.e(new Object[] { "CrashHandler", "产生致命异常,见日志:", time, throwable.getMessage() });
    throwable.printStackTrace();
    QLog.addFile(fileCrash,
        String.format("\r\n============= %s ============\r\n %s =============================\r\n", new Object[] {


            time, toString(throwable)
          }));
  }

  private String toString(Throwable ex) {
    Writer writer = new StringWriter();
    PrintWriter printWriter = new PrintWriter(writer);
    ex.printStackTrace(printWriter);
    Throwable exCause = ex.getCause();
    while (exCause != null) {
      exCause.printStackTrace(printWriter);
      exCause = exCause.getCause();
    }
    printWriter.close();
    return writer.toString();
  }

  public void setCrashCallBack(CrashCallBack crashCallBack) { this.crashCallBack = crashCallBack; }

  public static interface CrashCallBack {
    boolean onCrash(Throwable param1Throwable);
  }
}
