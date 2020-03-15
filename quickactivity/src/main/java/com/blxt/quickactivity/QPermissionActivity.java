 package com.blxt.quickactivity;

import android.app.Activity;
import android.app.AppOpsManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.provider.Settings;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.blxt.quicklog.QLog;
import com.blxt.quickpermission.permission.Action;
import com.blxt.quickpermission.permission.AndPermission;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


public abstract class QPermissionActivity
  extends QBaseActivity
{
  private static final String TAG = "QPermissionActivity";
  protected boolean FAL_PERMISSIONS_CHAEK_WINDOWS = false;
  protected boolean FAL_PERMISSIONS_CHAEK_NOTIFICATION = false;
  protected boolean FAL_PERMISSIONS_CHAEK_SYSTEM = false;
  protected boolean FAL_PERMISSIONS_CHAEK_APP_AUTO_RUN = false;
  protected boolean FAL_PERMISSIONS_CHAEK_ADMIN = false;
  protected PermissionCallBack callBack = null;

  protected void onResume() {
    super.onResume();
    doCheckPermission((Activity)this);
    if (this.FAL_PERMISSIONS_CHAEK_WINDOWS) {
      doWindowsCheck((Context)this);
    }
    if (this.FAL_PERMISSIONS_CHAEK_NOTIFICATION) {
      doNotificationCheck((Activity)this);
    }
    if (this.FAL_PERMISSIONS_CHAEK_SYSTEM) {
      doSystemCheck((Activity)this);
    }
    if (this.FAL_PERMISSIONS_CHAEK_APP_AUTO_RUN) {
      jumpStartInterface((Context)this);
    }
  }













  protected String[] permissions = new String[] { "android.permission.READ_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO", "com.android.voicemail.permission.ADD_VOICEMAIL", "android.permission.WRITE_SETTINGS", "android.permission.MODIFY_AUDIO_SETTINGS", "android.permission.SYSTEM_ALERT_WINDOW", "android.permission.ACCESS_FINE_LOCATION" };













  protected void setPermissions(@NonNull String... permissions) { this.permissions = permissions; }






  protected void doCheckPermission(final Activity activity) {
    if (this.permissions == null) {
      return;
    }

    AndPermission.with(activity)
      .runtime()
      .permission(this.permissions)
      .onGranted(new Action()
        {
          public void onAction(Object data) {
            if (QPermissionActivity.this.callBack != null) QPermissionActivity.this.callBack.onCheckPermissionResult(true);


          }
        }).onDenied(new Action()
        {
          public void onAction(Object data) {
            if (QPermissionActivity.this.callBack != null) QPermissionActivity.this.callBack.onCheckPermissionResult(false);
            Uri packageURI = Uri.parse("package:" + activity.getPackageName());
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", packageURI);
            intent.addFlags(268435456);
            activity.startActivity(intent);
          }
        }).start();
  }






  protected void doNotificationCheck(Activity activity) {
    if (Build.VERSION.SDK_INT <= 19) {

      if (this.callBack != null) this.callBack.onNotificationResult(true);

      return;
    }
    boolean enabled = isNotificationEnabled((Context)this);

    if (enabled) {
      if (this.callBack != null) this.callBack.onNotificationResult(true);

      return;
    }
    if (this.callBack != null) this.callBack.onNotificationResult(false);





    Intent localIntent = new Intent();

    if (Build.VERSION.SDK_INT >= 21) {
      localIntent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
      localIntent.putExtra("app_package", activity.getPackageName());
      localIntent.putExtra("app_uid", (activity.getApplicationInfo()).uid);
    } else {

      localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
      localIntent.addCategory("android.intent.category.DEFAULT");
      localIntent.setData(Uri.parse("package:" + activity.getPackageName()));
    }
    activity.startActivity(localIntent);
  }







  protected boolean doWindowsCheck(Context context) {
    if (Build.VERSION.SDK_INT >= 23 &&
      !Settings.canDrawOverlays(context)) {
      if (this.callBack != null) this.callBack.onWindowsCheckResult(false);
      Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION");
      Toast.makeText(context, "需要取得权限以使用悬浮窗", 0).show();
      context.startActivity(intent);
      return false;
    }

    if (this.callBack != null) this.callBack.onWindowsCheckResult(true);
    return true;
  }
  protected static interface PermissionCallBack {
    void onCheckPermissionResult(boolean param1Boolean);
    void onNotificationResult(boolean param1Boolean);
    void onWindowsCheckResult(boolean param1Boolean);
    void onSystemCheckResult(boolean param1Boolean);
    void onDevicePolicyManager(boolean param1Boolean); }

  protected void checkDevicePolicyManager(Context context, Class<?> broadcastReceiverClass) {
    DevicePolicyManager policyManager = (DevicePolicyManager)context.getSystemService("device_policy");

    ComponentName adminReceiver = new ComponentName(context, broadcastReceiverClass);

    boolean isActive = policyManager.isAdminActive(adminReceiver);

    if (this.callBack != null) this.callBack.onDevicePolicyManager(isActive);

    if (isActive) {
      return;
    }

    QLog.e(new Object[] { this, "请激活设备管理器" });
    Intent intent = new Intent("android.app.action.ADD_DEVICE_ADMIN");
    intent.putExtra("android.app.extra.DEVICE_ADMIN", (Parcelable)adminReceiver);
    intent.setFlags(268435456);

    intent.putExtra("android.app.extra.ADD_EXPLANATION", "请激活设备管理器");
    context.startActivity(intent);
  }










  protected boolean doSystemCheck(Activity activity) {
    if (Build.VERSION.SDK_INT >= 23)
    {
      if (!Settings.System.canWrite((Context)activity)) {

        Intent intent = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS");
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        intent.addFlags(268435456);
        activity.startActivityForResult(intent, 0);
        if (this.callBack != null) this.callBack.onSystemCheckResult(false);
        return false;
      }
    }
    if (this.callBack != null) this.callBack.onSystemCheckResult(true);
    return true;
  }







  @RequiresApi(api = 19)
  protected boolean isNotificationEnabled(Context context) {
    String CHECK_OP_NO_THROW = "checkOpNoThrow";
    String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

    AppOpsManager mAppOps = (AppOpsManager)context.getSystemService("appops");

    ApplicationInfo appInfo = context.getApplicationInfo();
    String pkg = context.getApplicationContext().getPackageName();
    int uid = appInfo.uid;

    Class<?> appOpsClass = null;
    try {
      appOpsClass = Class.forName(AppOpsManager.class.getName());
      Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, new Class[] { int.class, int.class, String.class });

      Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);

      int value = ((Integer)opPostNotificationValue.get(Integer.class)).intValue();
      return (((Integer)checkOpNoThrowMethod.invoke(mAppOps, new Object[] { Integer.valueOf(value), Integer.valueOf(uid), pkg })).intValue() == 0);
    }
    catch (Exception e) {
      e.printStackTrace();

      return false;
    }
  }



































  public void jumpStartInterface(Context context) {
    Intent intent = new Intent();
    try {
      intent.addFlags(268435456);
      ComponentName componentName = null;
      if (getMobileType().equals("Xiaomi")) {
        componentName = new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity");
      }
      else if (getMobileType().equals("Letv")) {
        intent.setAction("com.letv.android.permissionautoboot");
      } else if (getMobileType().equals("samsung")) {


        componentName = ComponentName.unflattenFromString("com.samsung.android.sm/.app.dashboard.SmartManagerDashBoardActivity");
      } else if (getMobileType().equals("HUAWEI")) {

        componentName = ComponentName.unflattenFromString("com.huawei.systemmanager/.startupmgr.ui.StartupNormalAppListActivity");
      }
      else if (getMobileType().equals("vivo")) {
        componentName = ComponentName.unflattenFromString("com.iqoo.secure/.safeguard.PurviewTabActivity");
      } else if (getMobileType().equals("Meizu")) {

        componentName = ComponentName.unflattenFromString("com.meizu.safe/.permission.SmartBGActivity");
      } else if (getMobileType().equals("OPPO")) {
        componentName = ComponentName.unflattenFromString("com.oppo.safe/.permission.startup.StartupAppListActivity");
      } else if (getMobileType().equals("ulong")) {
        componentName = new ComponentName("com.yulong.android.coolsafe", ".ui.activity.autorun.AutoRunListActivity");

      }
      else if (Build.VERSION.SDK_INT >= 9) {
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
      } else if (Build.VERSION.SDK_INT <= 8) {
        intent.setAction("android.intent.action.VIEW");
        intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
        intent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
      }

      intent.setComponent(componentName);
      context.startActivity(intent);
      if (getMobileType().equals("Xiaomi")) {
        showtip();
      }
      if (getMobileType().equals("samsung"));


    }
    catch (Exception e) {
      Log(new Object[] { "QPermissionActivity", "未知型号的授权" });
      intent = new Intent("android.settings.SETTINGS");
      context.startActivity(intent);
    }
  }


  private void showtip() {}

  private static String getMobileType() { return Build.MANUFACTURER; }
}
