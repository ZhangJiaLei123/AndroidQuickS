package com.blxt.androidquicks;

import android.Manifest;
import android.os.Bundle;

import com.blxt.quickpermission.BasePermissionActivity;
import com.blxt.quickpermission.PermissionInfo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class PermissionActivity extends BasePermissionActivity implements BasePermissionActivity.PermissionCallBack {

    List<PermissionInfo> permissionInfos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = "权限";
        message = "此软件需要必要的权限才可正常工作,详情请参见用户协议";

        PermissionInfo p1 = new PermissionInfo(Manifest.permission.WRITE_EXTERNAL_STORAGE, "基础文件读写");
        PermissionInfo p2 = new PermissionInfo(Manifest.permission.CALL_PHONE, "电话");
        PermissionInfo p3 = new PermissionInfo(Manifest.permission.CAMERA, "相机");
        PermissionInfo p4 = new PermissionInfo(Manifest.permission.SYSTEM_ALERT_WINDOW, "悬浮窗");

        setpPermissionCallBack(this);

         if(myRequetPermission(p1, p2, p3, p4)){

         }
    }

    @Override
    public boolean SupPermiss() {
        return false;
    }

    @Override
    public boolean permissFinish() {
        return false;
    }
}

