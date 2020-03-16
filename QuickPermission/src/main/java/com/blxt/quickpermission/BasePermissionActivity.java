package com.blxt.quickpermission;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.blxt.quickview.ActivityStytle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.view.View.GONE;

/**
 * 权限申请
 */
public class BasePermissionActivity extends Activity {

    private static final int NOT_NOTICE = 2;//如果勾选了不再询问
    private AlertDialog alertDialog;
    private AlertDialog mDialog;
    /**
     * 需要申请的权限数量
     */
    private int numberPermiss = 0;
    private int supNumberPermiss = 0;

    protected PermissionInfo permissInfo = new PermissionInfo(Manifest.permission.WRITE_EXTERNAL_STORAGE, "基础文件读写");
    /**
     *  特殊权限处理
     */
    String[] superPermiss = null;
    /**
     * 对话框镖旗
     */
    protected String title = "权限申请";
    /**
     * 权限提示内容
     */
    protected String message = "点击允许才可以使用我们的app哦";
    /**
     * 提示框logo
     */
    protected int titleIcoResID = R.drawable.ic_permiss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 隐藏导航栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout._permission_layout);

        ActivityStytle.setNavigationBar(this, GONE);
        ActivityStytle.statusBarHide(this);

    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i("BasePermissionActivity", "onResume");
        if (numberPermiss <= 0 && superPermiss != null && superPermiss.length > 0){
            //由于不知道是否选择了允许所以需要再次判断
            if (permissionCallBack != null){
                if(permissionCallBack.supPermiss()){
                    myRequetPermission();
                }
            }
            else{
                finish();
            }

        }
    }

    public void onPermissApply(View view){
        finish();
    }

    long lastTime = 0;
    /**
     * 拦截Activity的finish
     */
    @Override
    public void finish() {
        Calendar calendar = Calendar.getInstance();
        long time = calendar.getTimeInMillis();

        // 处理重复跳转特俗权限的问题
        if(time - lastTime < 500){
            return;
        }
        lastTime = time;
       if(numberPermiss <= 0 && supNumberPermiss <= 0){
           onSuperfinish();
           return;
       }

       if(makeSuperPermiss()){
           onSuperfinish();
           return;
       }
    }

    /**
     * 退出此Activity
     */
    public void onSuperfinish(){
        boolean fal = true;
        if(permissionCallBack != null){
            fal = permissionCallBack.permissFinish();
        }
        if (fal){
            super.finish();
        }
    }

    /**
     * 特殊权限处理
     * @return
     */
    public boolean makeSuperPermiss(){
        for(String s : superPermiss){
            // 悬浮窗
            if(s.equals(Manifest.permission.SYSTEM_ALERT_WINDOW)){
                if (Build.VERSION.SDK_INT >= 23) {
                    if (!Settings.canDrawOverlays(this)) { //若没有权限，提示获取.
                        Log.i("BasePermissionActivity", "" + "没有悬浮窗");
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                        Toast.makeText(this,"需要取得权限以使用悬浮窗",Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                    else{
                        supNumberPermiss--;
                    }
                }
                else{
                    supNumberPermiss--;
                }
            }
            // 系统设置
            if(s.equals(Manifest.permission.WRITE_SETTINGS)){
                //申请android.permission.WRITE_SETTINGS权限的方式
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //如果当前平台版本大于23平台
                    if (!Settings.System.canWrite(this)) {
                        //如果没有修改系统的权限这请求修改系统的权限
                        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                        intent.setData(Uri.parse("package:" + getPackageName()));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivityForResult(intent, 0);
                    } else {
                        //有了权限，你要做什么呢？具体的动作

                    }
                }
            }

        }
        if(supNumberPermiss == 0 ){
            super.finish();
            return true;
        }
        return false;
    }


    protected boolean myRequetPermission(PermissionInfo ...permissionInfo) {
        List<String> permissionls = new ArrayList<>();
        List<String> superPermisss = new ArrayList<>();
        boolean isPermiss = true;
        // 权限检查
        for (PermissionInfo p : permissionInfo) {
            // 悬浮窗等特殊权限需要做特殊处理
            if(p.permission.equals(Manifest.permission.SYSTEM_ALERT_WINDOW)){
                superPermisss.add(p.permission);
            }
            else if(p.equals(Manifest.permission.WRITE_SETTINGS)){
                superPermisss.add(p.permission);
            }
            else{
                if (ContextCompat.checkSelfPermission(this, p.permission) != PERMISSION_GRANTED) {
                    permissionls.add(p.permission);
                    isPermiss = false;
                }
            }
        }

        // 记录特殊权限
        supNumberPermiss = superPermisss.size();
        superPermiss = superPermisss.toArray(new String[supNumberPermiss]);

        // 权限申请
        if (!isPermiss) {
            numberPermiss = permissionls.size();
            String[] permissions = permissionls.toArray(new String[numberPermiss]);
            ActivityCompat.requestPermissions(this, permissions, 1);
            return false;
        }
         else {
            finish();
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1)
        {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PERMISSION_GRANTED) {//选择了“始终允许”
                    //Toast.makeText(this, "" + "权限" + permissions[i] + "申请成功", Toast.LENGTH_SHORT).show();
                    Log.i("BasePermissionActivity", "" + "权限" + permissions[i] + "申请成功");
                    // 一直到所有权限盛情成功后,才允许退出
                    numberPermiss--;
                    if(numberPermiss <= 0){
                        numberPermiss--;
                        finish();
                    }
                } else {
                    Log.i("BasePermissionActivity", "" + "权限" + permissions[i] + "申请失败");
                    // 选择不允许
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])){//用户选择了禁止不再询问

                        if(mDialog == null){
                            AlertDialog.Builder builder = new AlertDialog.Builder(BasePermissionActivity.this);
                            builder.setTitle(title)
                                    .setMessage(message)
                                    .setIcon(titleIcoResID)
                                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface arg0, int arg1) {
                                            //("您点击了取消按钮");
                                            // finish();
                                            mDialog = null;
                                        }
                                    })
                                    .setPositiveButton("去允许", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            if (mDialog != null && mDialog.isShowing()) {
                                                mDialog.dismiss();
                                                mDialog = null;
                                            }
                                            // 去权限申请页
                                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                            Uri uri = Uri.fromParts("package", getPackageName(), null);//注意就是"package",不用改成自己的包名
                                            intent.setData(uri);
                                            startActivityForResult(intent, NOT_NOTICE);
                                        }
                                    });
                            mDialog = builder.create();
                            mDialog.setCanceledOnTouchOutside(false);
                            mDialog.show();
                        }



                    }else {//选择禁止
                        Log.i("BasePermissionActivity", "" + "权限" + permissions[0] + "申请失败");

                        if(alertDialog == null){
                            AlertDialog.Builder builder = new AlertDialog.Builder(BasePermissionActivity.this);
                            builder.setTitle(title)
                                    .setMessage(message)
                                    .setIcon(titleIcoResID)
                                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface arg0, int arg1) {
                                            //("您点击了取消按钮");
                                            //finish();
                                            alertDialog = null;

                                        }
                                    })
                                    .setPositiveButton("去允许", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            if (alertDialog != null && alertDialog.isShowing()) {
                                                alertDialog.dismiss();
                                                alertDialog = null;
                                            }
                                            // 再次申请
                                            ActivityCompat.requestPermissions(BasePermissionActivity.this,
                                                    new String[]{permissInfo.permission}, 1);
                                        }
                                    });
                            alertDialog = builder.create();
                            alertDialog.setCanceledOnTouchOutside(false);
                            alertDialog.show();
                        }

                    }

                }
            }
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==NOT_NOTICE){
            myRequetPermission(permissInfo);//由于不知道是否选择了允许所以需要再次判断
        }
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();

    }

    public void setpPermissionCallBack(PermissionCallBack permissionCallBack){
        this.permissionCallBack = permissionCallBack;
    }

    PermissionCallBack permissionCallBack = null;
    public interface PermissionCallBack{
        /**
         * 发现没有特殊权限
         * @return
         */
        boolean supPermiss();

        /**
         * 结束
         * @return
         */
        boolean permissFinish();
    }

}
