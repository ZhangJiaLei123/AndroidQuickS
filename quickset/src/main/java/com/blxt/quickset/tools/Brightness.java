package com.blxt.quickset.tools;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;

/**
 * 屏幕亮度设置工具
 */
public class Brightness {

    /**
     * 自动调节屏幕亮度
     */
    public final static int MODEL_AUTOMATIC = Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
    /**
     * 手动调节屏幕亮度
     */
    public final static int MODEL_MANUAL = Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL;

    /**
     * 设置当前屏幕亮度的模式
     * MODEL_AUTOMATIC 为自动调节屏幕亮度
     * MODEL_MANUAL 为手动调节屏幕亮度
     */
    public static void setScreenMode(Context context, int paramInt) {
        try {
            Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, paramInt);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }


    /**
     * 获取屏幕亮度值
     * 屏幕最大亮度为255。
     * 屏幕最低亮度为0。
     * 屏幕亮度值范围必须位于：0～255
     *
     * <uses-permission android:name="android.permission.WRITE_SETTINGS"/>
     * @param context
     * @return
     */
    public static  int getBrightness(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        int defVal = 125;
        return Settings.System.getInt(contentResolver,
                Settings.System.SCREEN_BRIGHTNESS, defVal);
    }

    /**
     * 保存当前的屏幕亮度值，并使之生效
     */
    public static  void setBrightness(Activity context, int paramInt) {
        setScreenMode(context, MODEL_MANUAL);
        Window localWindow = context.getWindow();
        WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
        localLayoutParams.screenBrightness = paramInt / 255.0F;
        localWindow.setAttributes(localLayoutParams);

        setBrightness(context, paramInt);
    }
    /**
     * 设置当前屏幕亮度值 0--255
     */
    public static  void setBrightness(Context context,int paramInt) {
        setScreenMode(context, MODEL_MANUAL);
        try {
            Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, paramInt);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

}
