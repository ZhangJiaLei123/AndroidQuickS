package com.blxt.quickview;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import static android.view.View.GONE;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

/**
 * Activity风格,
 * Activity的状态栏\导航栏\虚拟按键处理
 * 沉浸式处理
 */
public class ActivityStytle {

    /**
     * 隐藏导航栏,在etContentView(R.layout.)前使用
     * @param activity
     */
    public static void hintTitleBar(Activity activity){
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);

    }

    /**
     * 设置导航栏可视性
     * @param activity
     * @param
     */
    public static void setNavigationBar(Activity activity, int visible){
        View decorView = activity.getWindow().getDecorView();
        //显示NavigationBar
        if (GONE == visible){
            int option = SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            decorView.setSystemUiVisibility(option);
        }
    }

    /**
     * 设置Activity的导航栏隐藏
     * @param activity
     */
    public static void statusBarHide(Activity activity){
        // 代表 5.0 及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            ActionBar actionBar = activity.getActionBar();
            if(actionBar != null){
                actionBar.hide();
            }
            return;
        }

        // versionCode > 4.4  and versionCode < 5.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

    }

}
