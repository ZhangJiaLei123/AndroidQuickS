package com.blxt.quickpermission;

import android.Manifest;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.blxt.quickview.ActivityStytle;

import java.util.LinkedList;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

/**
 * 权限申请
 */
public class BasePermissionActivity extends Activity {

    private static final String TAG = "BasePermissionActivity";
    private boolean isDefaultDialog = true;
    private static final int REQUST_CODE = 101;
    private String[] mPermissions = new String[]{

            // Manifest.permission.SYSTEM_ALERT_WINDOW, //  悬浮窗
             Manifest.permission.RECORD_AUDIO,          //  音频录制
            Manifest.permission.MODIFY_AUDIO_SETTINGS,  //  音频录制
            Manifest.permission.WRITE_SETTINGS,         //  SD卡读写
            //Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA                  //  相机
            //, Manifest.permission.TYPE_APPLICATION_OVERLAY
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 隐藏导航栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout._permission_layout);
        initPermission();

        ActivityStytle.setNavigationBar(this, GONE);
        ActivityStytle.statusBarHide(this);

    }

    public Context getContext(){
        return this;
    }

    /***
     * 初始化权限
     * @return
     */
    boolean initPermission(){
        Log.i(TAG,"检查权限");


        boolean isPermission = false;
        for(String s : mPermissions){
            if(!isPermissionGrant(s)){
                isPermission = true;
                break;
            }
        }

        if(isPermission)
        {
            checkPermission();
        }

        return false;
    }

    /**
     * 检查 是否 有 xx权限
     * @param permission
     * @return
     */
    public boolean isPermissionGrant(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    /**
     * 发起请求权限
     * @return
     */
    public boolean checkPermission() {


        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }

        List<String> permissionToRequestList = new LinkedList<String>();
        for (String permission : mPermissions) {
            if(checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                permissionToRequestList.add(permission);
            }
        }
        String[] permissionToRequest = (String[])permissionToRequestList.toArray(new String[permissionToRequestList.size()]);
        if(permissionToRequest.length > 0){
            requestPermissions(permissionToRequest, REQUST_CODE);
            if (mCallback != null) {
                mCallback.onRequest();
            }
        } else {
            if (mCallback != null) {
                mCallback.onGranted();
            }
        }

        return false;
    }


    private PermissionCheckCallback mCallback = new PermissionCheckCallback() {
        @Override
        public void onRequest() {
            Log.i(TAG,"onRequest");
        }

        @Override
        public void onGranted() {
            Log.i(TAG,"onGranted");
        }

        @Override
        public void onGrantSuccess() {
            Log.i(TAG,"授权成功");
        }

        @Override
        public void onGrantFail() {
            Log.i(TAG,"授权失败");
            Toast.makeText(getContext(), "请打开必要权限", Toast.LENGTH_SHORT)
                    .show();
        }
    };



    /**
     * 权限结果回调
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUST_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (mCallback != null) {
                        mCallback.onGrantSuccess();
                    }
                    Log.v(TAG, "获取到权限" + grantResults[0]);
                } else {
                    Log.v(TAG, "未检测到权限");
                    if (isDefaultDialog) {
                      //  popupWarningDialog();
                        break;
                    }
                    if (mCallback != null) {
                        mCallback.onGrantFail();
                    }
                }
                break;
            default:
                break;
        }


        for(String s : mPermissions){
            if(!isPermissionGrant(s)){
                Log.v(TAG, "有权限" + s);
            }
            else{
                Log.v(TAG, "没有权限" + s);
            }
        }
        Log.v(TAG, "检查完毕");
    }


    /**
     * 结果回调接口
     */
    public interface PermissionCheckCallback {
        void onRequest();
        void onGranted();
        void onGrantSuccess();
        void onGrantFail();
    }


}
