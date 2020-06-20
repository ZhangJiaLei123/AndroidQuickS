package com.blxt.quickview.button;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;

import com.blxt.quickview.AttributeHelper;
import com.blxt.quickview.R;


@SuppressLint({"AppCompatCustomView"})
public class TextBotton
        extends Button {
    AttributeHelper attributeHelper = null;

    int textColorOn = getResources().getColor(R.color.btn_text_on);
    int textColorOff = getResources().getColor(R.color.btn_text_off);

    int resIdOn = R.drawable.__roll;
    int resIdOff = R.drawable.__roll_off;

    int locationDef = 2;

    private boolean isCheck = false;


    public TextBotton(Context context) {
        super(context);
    }


    public TextBotton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public TextBotton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
        setCheck(this.isCheck);
    }

    @SuppressLint({"ResourceType"})
    private void init(AttributeSet attrs) {
        if (this.attributeHelper != null) {
            return;
        }
        this.attributeHelper = new AttributeHelper(getContext(), attrs);

        this.textColorOn = getTextColors().getDefaultColor();
        this.textColorOff = getHintTextColors().getDefaultColor();
        setCheck(false);
    }


    public void setCheck(boolean isCheck) {
        this.isCheck = isCheck;

        int color = this.textColorOn;
        int image = this.resIdOn;
        if (!isCheck) {
            color = this.textColorOff;
            image = this.resIdOff;
        }

        setTextColor(color);
        setBtnBack(image);
    }


    public void setHintImageOn(int resId) {
        this.resIdOn = resId;
    }


    public void setHintImageOff(int resId) {
        this.resIdOff = resId;
    }


    public void setTextColorOn(int textColorOn) {
        this.textColorOn = textColorOn;
    }


    public void setTextColorOff(int textColorOff) {
        this.textColorOff = textColorOff;
    }


    public void setLocationDef(int locationDef) {
        this.locationDef = locationDef;
    }


    private void setBtnBack(int resId) {
        Drawable drawable = getResources().getDrawable(resId);

        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        setCompoundDrawables(null, null, null, drawable);
    }
}

