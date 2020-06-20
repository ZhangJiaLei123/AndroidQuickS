package com.blxt.quicklog;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.LENGTH_SHORT;

public class CrashHandler
        implements Thread.UncaughtExceptionHandler {
    static final String TAG = "CrashHandler";
    private static CrashHandler INSTANCE;// CrashHandler实例

    CrashCallBack crashCallBack = null;
    Context context;

    static File fileCrash;

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashHandler getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CrashHandler(context);
        }
        return INSTANCE;
    }


    /**
     * 保证只有一个CrashHandler实例
     */
    private CrashHandler(Context context) {
        this(context, "/CrashLog.log");
        Thread.setDefaultUncaughtExceptionHandler(this);// 设置该CrashHandler为程序的默认处理器
    }

    public CrashHandler(Context context, String fileName) {
        this(context.getCacheDir().getPath(), fileName);
        this.context = context;
    }

    public CrashHandler(String logFilePath, String fileName) {
        File filePath = new File(logFilePath);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        fileCrash = new File(logFilePath + fileName);
    }

    /**
     * 当UncaughtException发生时会转入该重写的方法来处理
     */
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {

      if (throwable == null)
        return ;

        handleException(throwable);
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex 异常信息
     *           如果处理了该异常信息;否则返回false.
     */
    public boolean handleException(Throwable ex) {

        new Thread() {
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "很抱歉,程序出现异常", LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        // 收集设备参数信息
        Map<String, String> info = collectDeviceInfo(context);

        // 保存日志文件
        boolean isSave = true;
        if (this.crashCallBack != null) {
            isSave = this.crashCallBack.onCrash(ex);
        }
        if (isSave) {
            saveCrashInfo2File(ex, info);
        }

        return true;
    }

    /**
     * 收集设备参数信息
     *
     * @param context
     */
    public Map<String, String> collectDeviceInfo(Context context) {
        Map<String, String> info = new HashMap<String, String>();// 用来存储设备信息和异常信息

        try {
            PackageManager pm = context.getPackageManager();// 获得包管理器
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_ACTIVITIES);// 得到该应用的信息，即主Activity
            if (pi != null) {
                String versionName = pi.versionName == null ? "null"
                        : pi.versionName;
                String versionCode = pi.versionCode + "";
                info.put("versionName", versionName);
                info.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Field[] fields = Build.class.getDeclaredFields();// 反射机制
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                info.put(field.getName(), field.get("").toString());
                Log.d(TAG, field.getName() + ":" + field.get(""));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return info;
    }


    /**
     * 保存异常信息
     *
     * @param ex
     * @return
     */
    private String saveCrashInfo2File(Throwable ex, Map<String, String> info) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : info.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\r\n");
        }
        Writer writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        ex.printStackTrace(pw);
        Throwable cause = ex.getCause();
        // 循环着把所有的异常信息写入writer中
        while (cause != null) {
            cause.printStackTrace(pw);
            cause = cause.getCause();
        }
        pw.close();// 记得关闭
        String result = writer.toString();
        sb.append(result);
        // 保存文件
        String time = QLog.getTimeStr("yyyyMMddHHmmss");
        QLog.e(new Object[]{"CrashHandler", "产生致命异常,见日志:", time, ex.getMessage()});
        ex.printStackTrace();
        QLog.addFile(fileCrash,
                String.format("\r\n============= %s ============\r\n " +
                                "%s =============================\r\n",
                        new Object[]{
                                time, sb.toString()}));
        return null;
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

    public void setCrashCallBack(CrashCallBack crashCallBack) {
        this.crashCallBack = crashCallBack;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static interface CrashCallBack {
        boolean onCrash(Throwable param1Throwable);
    }

}
