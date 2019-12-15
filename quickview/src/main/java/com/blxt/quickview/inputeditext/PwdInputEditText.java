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
    private EditText EtInput;
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
            EtInput = view.findViewById(R.id.__et_input);
            IvEye = view.findViewById(R.id._iv_eye);
            EtInput.clearFocus();
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
      //  EtInput.clearFocus();
    }

    private void setPwdModel(Boolean fal){
        if(fal){
            IvEye.setBackgroundResource(R.drawable.__eye_on);
            EtInput.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        else{
            IvEye.setBackgroundResource(R.drawable.__eye_off);
            EtInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }


    public String getText(){
        return EtInput.getText().toString();
    }

    public void setText(int str){
        EtInput.setText(str);
    }

    public void setText(String str){
        EtInput.setText(str);
    }

    public void setHint(int str){
        EtInput.setHint(str);
    }
    public void setHint(String str){
        EtInput.setHint(str);
    }
}
