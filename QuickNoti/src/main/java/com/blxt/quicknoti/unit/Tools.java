package com.blxt.quicknoti.unit;

import android.content.Context;
import android.content.pm.ApplicationInfo;

public class Tools {

    /**
     * 判断是否为Debug版本
     * @param context
     * @return
     */
    public static boolean isDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
