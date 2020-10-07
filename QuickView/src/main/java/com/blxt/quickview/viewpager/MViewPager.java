package com.blxt.quickview.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;


public class MViewPager
        extends ViewPager {
    private boolean isScroll = true;

    public MViewPager(@NonNull Context context) {
        super(context);
    }


    public MViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public boolean onTouchEvent(MotionEvent arg0) {
        if (this.isScroll) {
            return false;
        }
        return super.onTouchEvent(arg0);
    }


    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (this.isScroll) {
            return false;
        }
        return super.onInterceptTouchEvent(arg0);
    }


    public void setScroll(boolean isScroll) {
        this.isScroll = isScroll;
    }
}

