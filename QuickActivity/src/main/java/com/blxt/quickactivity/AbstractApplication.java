package com.blxt.quickactivity;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Environment;
import android.os.Process;

import com.blxt.quicklog.QLog;

import java.io.File;

public abstract class AbstractApplication
        extends Application {
    // static final String TAG = "AbstractApplication";
    protected QAppConfig appConfig;
    protected String APP_SD_PATH = null;

    protected String APP_FILE_PATH = null;

    protected String APP_CACHE_PATH = null;

    protected boolean isFirstRun = true;

    public static boolean isDebug = true;

    protected AbstractApplication() {
        isDebug = true;
    }

    public void onCreate() {
        super.onCreate();
        this.appConfig = QAppConfig.getInstance((Context) this);
        this.isFirstRun = this.appConfig.getBoolean("isFirstRun", this.isFirstRun).booleanValue();
        this.APP_SD_PATH = Environment.getExternalStorageDirectory().getPath() + "/Android/data/" + getPackageName();

        File file = new File(this.APP_SD_PATH);
        if (!file.exists()) {
            file.mkdirs();
            QLog.i(new Object[]{"AbstractApplication", "创建文件夹", file.getPath()});
        }

        this.APP_FILE_PATH = getFilesDir().getPath();
        this.APP_CACHE_PATH = getCacheDir().getPath();
    }

    protected void setFirstRun(Boolean isFirstRun) {
        this.isFirstRun = isFirstRun.booleanValue();
        this.appConfig.putBoolean("", isFirstRun.booleanValue());
    }

    public QAppConfig getAppConfig() {
        return this.appConfig;
    }


    /**
     * 退出程序
     */
    public void exit() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    public void onRestart() {
        Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());

        intent.putExtra("REBOOT", "reboot");
        PendingIntent restartIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, Intent.FILL_IN_ACTION);
        AlarmManager mgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000L, restartIntent);
        Process.killProcess(Process.myPid());
    }


//    /**
//     * 是否是Debug
//     * @param context
//     * @return
//     */
//    public boolean isDebugApp(Context context) {
//        try {
//            ApplicationInfo info = context.getApplicationInfo();
//            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
//        } catch (Exception x) {
//            return false;
//        }
//    }


}
