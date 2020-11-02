package com.blxt.quickwindows.listener;

import android.app.Service;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.annotation.LayoutRes;

public abstract class FloatingWindowsService extends Service {

    protected WindowManager windowManager;
    protected WindowManager.LayoutParams layoutParams;

    @Override
    public void onCreate() {
        super.onCreate();
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        layoutParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }

        layoutParams.format = PixelFormat.RGBA_8888;
        layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//        layoutParams.width = 500;
//        layoutParams.height = 500;
//        layoutParams.x = 300;
//        layoutParams.y = 300;

    }

    /**
     * 视图布局绑定
     * @param context
     * @param resource
     * @param root
     * @return
     */
    protected View loadWindowLayout(Context context, @LayoutRes int resource, ViewGroup root) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) { // 没有权限
                return null;
            }
        }
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View displayView = layoutInflater.inflate(resource, root);

        return displayView;
    }

    /**
     * 显示视图
     * @param view
     */
    public void onDisplay(View view){
        windowManager.addView(view, layoutParams);
    }
}
