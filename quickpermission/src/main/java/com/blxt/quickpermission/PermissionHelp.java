package com.blxt.quickpermission;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.PermissionChecker;

/**
 * 权限帮助工具类,
 * 在这里实现权限检查等工具
 */
public class PermissionHelp {

    /**
     * 权限检查.判断是否有该权限
     * @param context
     * @param permiss
     * @return true 有权限 false 没有权限
     */
    public static boolean check(Context context,
                                @PermissionChecker.PermissionResult String ... permiss){
        boolean fal = false;

        return fal;
    }

    /**
     * 悬浮窗权限申请
     * @param context
     * @return
     */
    public static boolean makeWindows(Context context){
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(context)) { //若没有权限，提示获取.
                Log.i("BasePermissionActivity", "" + "没有悬浮窗");
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                Toast.makeText(context,"需要取得权限以使用悬浮窗",Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
                return false;
            }
            return true;
        }
        return true;
    }

    public static boolean makeNoti(Context context){
        // 通知栏使用权
        String string = null;
        if(!isPermissionOpen(context)){
            openPermissionSetting(context);
            return false;
        }
        return true;
    }

    /**
     * 系统设置权
     * @param activity
     * @return
     */
    public static boolean makeSysyemSet(Activity activity){
        //申请android.permission.WRITE_SETTINGS权限的方式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //如果当前平台版本大于23平台
            if (!Settings.System.canWrite(activity)) {
                //如果没有修改系统的权限这请求修改系统的权限
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + activity.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivityForResult(intent, 0);
                return false;
            } else {
                //有了权限，你要做什么呢？具体的动作
                return true;

            }
        }
        return true;
    }

    /**
     * 检查权限
     * @param context
     * @return
     */
    public static boolean isPermissionOpen(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return NotificationManagerCompat.from(context).getImportance() != NotificationManager.IMPORTANCE_NONE;
        }
        return NotificationManagerCompat.from(context).areNotificationsEnabled();
    }

    /**
     * 通知栏使用权
     * @param context
     */
    public static void openPermissionSetting(Context context) {
        try {
            Intent localIntent = new Intent();
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //直接跳转到应用通知设置的代码：
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                localIntent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                localIntent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
                context.startActivity(localIntent);
                return;
            }
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                localIntent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                localIntent.putExtra("app_package", context.getPackageName());
                localIntent.putExtra("app_uid", context.getApplicationInfo().uid);
                context.startActivity(localIntent);
                return;
            }
            if (android.os.Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
                localIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                localIntent.addCategory(Intent.CATEGORY_DEFAULT);
                localIntent.setData(Uri.parse("package:" + context.getPackageName()));
                context.startActivity(localIntent);
                return;
            }

            //4.4以下没有从app跳转到应用通知设置页面的Action，可考虑跳转到应用详情页面,

            if (Build.VERSION.SDK_INT >= 9) {
                localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
                context.startActivity(localIntent);
                return;
            }

            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" cxx   pushPermission 有问题");
        }
    }
}



