package com.blxt.quickview.editext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatEditText;

import com.blxt.quickview.R;


public class ClearEditText
        extends AppCompatEditText
        implements TextWatcher {
    private Drawable mClearDrawable;

    public ClearEditText(Context context) {
        this(context, null);
    }


    public ClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 16842862);
    }


    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @SuppressLint({"NewApi"})
    private void init() {
        this.mClearDrawable = getCompoundDrawables()[2];
        if (this.mClearDrawable == null) {
            this.mClearDrawable = getResources().getDrawable(R.mipmap.close);
        }

        this.mClearDrawable.setBounds(0, 0, this.mClearDrawable.getIntrinsicWidth(), this.mClearDrawable.getIntrinsicHeight());
        setBackground(getResources().getDrawable(R.mipmap.sousuok));

        addTextChangedListener(this);
    }


    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }


    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }


    public void afterTextChanged(Editable s) {
        String text = getText().toString().trim();
        if (TextUtils.isEmpty(text)) {

            setCompoundDrawables(null, null, null, null);
        } else {
            setCompoundDrawables(null, null, this.mClearDrawable, null);
        }
    }


    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == 1 &&
                getCompoundDrawables()[2] != null) {

            boolean touchable = (event.getX() > (getWidth() - getTotalPaddingRight()) && event.getX() < (getWidth() - getPaddingRight()));
            if (touchable) {
                setText("");
            }
        }

        return super.onTouchEvent(event);
    }
}

