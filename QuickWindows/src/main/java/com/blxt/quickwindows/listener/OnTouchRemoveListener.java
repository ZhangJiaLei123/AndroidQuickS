package com.blxt.quickwindows.listener;

import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * view的移动监听,拖动
 */
public class OnTouchRemoveListener implements View.OnTouchListener {

    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;

    public OnTouchRemoveListener(WindowManager windowManager, WindowManager.LayoutParams layoutParams) {
        this.windowManager = windowManager;
        this.layoutParams = layoutParams;
    }

    private int x;
    private int y;

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = (int) event.getRawX();
                y = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int nowX = (int) event.getRawX();
                int nowY = (int) event.getRawY();
                int movedX = nowX - x;
                int movedY = nowY - y;
                x = nowX;
                y = nowY;
                layoutParams.x = layoutParams.x + movedX;
                layoutParams.y = layoutParams.y + movedY;
                windowManager.updateViewLayout(view, layoutParams);
                break;
            default:
                break;
        }
        return false;
    }
}
