package com.blxt.androidquicks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blxt.androidquicks.fragment.SettingFragment;
import com.blxt.quickpermission.PermissionHelp;
import com.blxt.quickview.dialog.BrightnessDialog;


public class MainActivity extends AppCompatActivity {

    static Activity activity;

    public static void start(Context context) {
        if(activity == null){
            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                    | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        SettingFragment settingFragment = SettingFragment.newInstance();
        changeFragment(settingFragment);
        PermissionHelp.makeNoti(this);
        startActivity(new Intent(this, PermissionActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activity = null;
    }

    public void onClickTest(View view){
        BrightnessDialog brightnessDialog = new BrightnessDialog( this);
        brightnessDialog.show();
        brightnessDialog.setValue(255);
    }

    private void changeFragment(Fragment fragment){
        //实例化碎片管理器对象
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        //选择fragment替换的部分
        ft.replace(R.id.cl_content,fragment);
        ft.commit();
    }

}
