package com.blxt.quickview.inputeditext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.method.DigitsKeyListener;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blxt.quickview.R;


public class IDInputEditText
        extends LinearLayout {
    private ImageView IvLog;
    private EditText editText;
    View view = null;
    String regular;

    public IDInputEditText(Context context) {
        super(context);


        this.regular = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        initUI();
    }

    public IDInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.regular = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        initUI();
    }

    public IDInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.regular = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        initUI();
    }

    @SuppressLint({"NewApi"})
    public IDInputEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.regular = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        initUI();
    }

    public void initUI() {
        if (this.view == null) {
            this.view = LayoutInflater.from(getContext()).inflate(R.layout.___input_user_name, (ViewGroup) this);
            this.IvLog = (ImageView) this.view.findViewById(R.id.__iv_log);
            this.IvLog.setBackgroundResource(R.drawable.__id_input);
            this.editText = (EditText) this.view.findViewById(R.id.__et_input);
            this.editText.clearFocus();
            this.editText.setInputType(96);

            this.editText.setKeyListener((KeyListener) new DigitsKeyListener() {
                public int getInputType() {
                    return 96;
                }


                protected char[] getAcceptedChars() {
                    char[] ac = IDInputEditText.this.regular.toCharArray();
                    return ac;
                }
            });


            setHint("请输入用户名");
        }
    }


    public String getText() {
        if (this.editText.getText() == null) {
            return "";
        }
        return this.editText.getText().toString();
    }


    public void setText(int str) {
        this.editText.setText(str);
    }


    public void setText(String str) {
        this.editText.setText(str);
    }


    public void setHint(String str) {
        this.editText.setHint(str);
    }


    public void setHint(int str) {
        this.editText.setHint(str);
    }


    public void setEnable(boolean b) {
        this.editText.setEnabled(b);
    }
}
