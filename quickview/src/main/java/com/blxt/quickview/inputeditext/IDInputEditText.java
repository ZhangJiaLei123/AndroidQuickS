package com.blxt.quickview.inputeditext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blxt.quickview.R;

/**
 * 用户名输入EditText
 */
public class IDInputEditText extends LinearLayout {

    private ImageView IvLog;
    private EditText editText;


    View view = null;

    public IDInputEditText(Context context) {
        super(context);
        initUI();
    }

    public IDInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI();
    }

    public IDInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI();
    }

    @SuppressLint("NewApi")
    public IDInputEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initUI();
    }


    public void initUI(){
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.___input_user_name,this);
            IvLog = view.findViewById(R.id.__iv_log);
            IvLog.setBackgroundResource(R.drawable.__id_input);
            editText = view.findViewById(R.id.__et_input);
            editText.clearFocus();
            editText.setInputType(EditorInfo.TYPE_CLASS_PHONE); //设置输入格式:只能输入数字
            setHint("请输入工号");
        }
      //  editText.clearFocus();
    }

    public String getText(){
        return editText.getText().toString();
    }

    public void setText(int str){
        editText.setText(str);
    }

    public void setText(String str){
        editText.setText(str);
    }

    public void setHint(String str){
        editText.setHint(str);
    }
    public void setHint(int str){
        editText.setHint(str);
    }
}
