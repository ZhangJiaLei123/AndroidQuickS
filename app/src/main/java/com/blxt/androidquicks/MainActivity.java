package com.blxt.androidquicks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SettingFragment settingFragment = SettingFragment.newInstance();
        changeFragment(settingFragment);

        startActivity(new Intent(this, WelcomeActivity.class));
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
