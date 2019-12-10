package com.blxt.quickset.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.blxt.quickset.R;
import com.blxt.quickset.tools.Brightness;

/**
 * 精度条设置对话框
 */
public class BaseSeekbarDialog extends Dialog {

    protected SeekBar seekBar;
    protected TextView title;

    protected boolean iscancelable = true;//控制点击dialog外部是否dismiss
    protected boolean isBackCancelable = true;//控制返回键是否dismiss

    SeekBarCallBack callBack = null;

    public BaseSeekbarDialog(@NonNull Context context) {
        super(context, R.style.DialogTheme);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._dialog_brightness);


        title = findViewById(R.id._dialog_title);
        seekBar = findViewById(R.id._dialog_seekBar);

        setCancelable(iscancelable);//点击外部不可dismiss
        setCanceledOnTouchOutside(isBackCancelable);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
    }

    protected void initUI(){
    }

    public void setBackCancelable(SeekBarCallBack callBack){
        this.callBack = callBack;
    }
    public interface SeekBarCallBack{
        void onProgressChanged(SeekBar seekBar, int i) ;
    }


}
