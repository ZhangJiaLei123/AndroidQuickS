package com.blxt.quickview.inputeditext;

import android.annotation.SuppressLint;
import android.content.Context;
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
public class UserInputEditText extends LinearLayout {

    private ImageView IvLog;
    private EditText editText;


    View view = null;

    public UserInputEditText(Context context) {
        super(context);
        initUI();
    }

    public UserInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI();
    }

    public UserInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI();
    }

    @SuppressLint("NewApi")
    public UserInputEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initUI();
    }


    public void initUI(){
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.___input_user_name,this);
            IvLog = view.findViewById(R.id.__iv_log);
            IvLog.setBackgroundResource(R.drawable.__ic_input_user);
            editText = view.findViewById(R.id.__et_input);
            editText.clearFocus();
        }
      //  editText.clearFocus();
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

    public void setEnable(boolean b){
        editText.setEnabled(b);
    }
}
