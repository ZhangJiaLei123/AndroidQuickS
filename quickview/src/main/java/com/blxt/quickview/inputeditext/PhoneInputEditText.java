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
public class PhoneInputEditText extends LinearLayout {

    private ImageView IvLog;
    private EditText editText;


    View view = null;

    public PhoneInputEditText(Context context) {
        super(context);
        initUI();
    }

    public PhoneInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI();
    }

    public PhoneInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI();
    }

    @SuppressLint("NewApi")
    public PhoneInputEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initUI();
    }


    public void initUI(){
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.___input_user_name,this);
            IvLog = view.findViewById(R.id.__iv_log);
            IvLog.setBackgroundResource(R.drawable.__ic_phone);
            editText = view.findViewById(R.id.__et_input);
            editText.clearFocus();
            setHint("请输入电话号码");
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(13)}); //设置限制长度，多了输
            editText.setInputType(EditorInfo.TYPE_CLASS_PHONE); //设置输入格式:只能输入数字
            //关键部分:自动分隔手机号码通过addTextChangedListener()实现
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                    if (charSequence == null || charSequence.length() == 0)
                        return;
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < charSequence.length(); i++) {
                        if (i != 3 && i != 8 && charSequence.charAt(i) == ' ') {
                            continue;
                        } else {
                            stringBuilder.append(charSequence.charAt(i));
                            if ((stringBuilder.length() == 4 || stringBuilder.length() == 9)
                                    && stringBuilder.charAt(stringBuilder.length() - 1) != ' ') {
                                stringBuilder.insert(stringBuilder.length() - 1, ' ');
                            }
                        }
                    }
                    if (!stringBuilder.toString().equals(charSequence.toString())) {
                        int index = start + 1;
                        if (stringBuilder.charAt(start) == ' ') {
                            if (before == 0) {
                                index++;
                            } else {
                                index--;
                            }
                        } else {
                            if (before == 1) {
                                index--;
                            }
                        }
                        editText.setText(stringBuilder.toString());
                        editText.setSelection(index);
                    }
                }
                @Override
                public void afterTextChanged(Editable s) {
                }
            });
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
