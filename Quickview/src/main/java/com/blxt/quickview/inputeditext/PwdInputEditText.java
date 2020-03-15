package com.blxt.quickview.inputeditext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blxt.quickview.R;

/**
 * 用户名输入EditText
 */
public class PwdInputEditText extends LinearLayout {

    private View view = null;
    private ImageView IvLog;
    private EditText editText;
    private ImageView IvEye;
    boolean isEyes = false;

    public PwdInputEditText(Context context) {
        super(context);
        initUI();
    }

    public PwdInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI();
    }

    public PwdInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI();
    }

    @SuppressLint("NewApi")
    public PwdInputEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initUI();
    }


    public void initUI(){
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.___input_user_pwd,this);
            IvLog = view.findViewById(R.id.__iv_log);
            editText = view.findViewById(R.id.__et_input);
            IvEye = view.findViewById(R.id._iv_eye);
            editText.clearFocus();
            isEyes = false;
            setPwdModel(isEyes);
            IvEye.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    isEyes = !isEyes;
                    setPwdModel(isEyes);
                }
            });
        }
      //  editText.clearFocus();
    }

    private void setPwdModel(Boolean fal){
        if(fal){
            IvEye.setBackgroundResource(R.drawable.__eye_on);
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        else{
            IvEye.setBackgroundResource(R.drawable.__eye_off);
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }


    public String getText(){
        if(editText.getText() == null){
            return "";
        }
        return editText.getText().toString();
    }

    public void setText(int str){
        editText.setText(str);
    }

    public void setText(String str){
        editText.setText(str);
    }

    public void setHint(int str){
        editText.setHint(str);
    }
    public void setHint(String str){
        editText.setHint(str);
    }

    public void setEnable(boolean b){
        editText.setEnabled(b);
    }

}
