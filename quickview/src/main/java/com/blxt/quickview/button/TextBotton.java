package com.blxt.quickview.button;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;

import com.blxt.quickview.R;

/**
 * 文本选择按钮
 */
public class TextBotton extends Button {

    int textColorOn= getResources().getColor(R.color.btn_text_on);
    int textColorOff = getResources().getColor(R.color.btn_text_off);

    int resIdOn = R.drawable.__roll;
    int resIdOff = R.drawable.__roll_off;

    private boolean isCheck = false;

    public TextBotton(Context context) {
        super(context);
        //setCheck(isCheck);
    }

    public TextBotton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public TextBotton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
        setCheck(isCheck);
    }

    @SuppressLint("ResourceType")
    private void init(AttributeSet attrs){

        textColorOn = getTextColors().getDefaultColor();

    }

    /**
     * 设置是否选择
     */
    public void setCheck(boolean isCheck){
        this.isCheck = isCheck;

        int resId = resIdOn;
        if(isCheck){
            resId = resIdOn;
            setTextColor(textColorOn);
        }
        else{
            resId = resIdOff;
            setTextColor(textColorOff);
        }
        setBtnBack(resId);

    }

    /**
     * 设置按钮图片
     * @param resId
     */
    private void setBtnBack(int resId){
        Drawable drawable = getResources().getDrawable(resId);
        // 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),drawable.getMinimumHeight());
        setCompoundDrawables(null, null, null, drawable);
    }


}
