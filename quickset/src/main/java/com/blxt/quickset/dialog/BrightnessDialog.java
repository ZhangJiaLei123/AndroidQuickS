package com.blxt.quickset.dialog;

import android.content.Context;
import android.os.Bundle;
import android.widget.SeekBar;

import androidx.annotation.NonNull;

import com.blxt.quickset.tools.Brightness;

/**
 * 亮度设置对话框
 */
public class BrightnessDialog extends BaseSeekbarDialog implements SeekBar.OnSeekBarChangeListener {


    public BrightnessDialog(@NonNull Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initUI();
    }

    @Override
    protected void initUI(){
         seekBar.setProgress(Brightness.getBrightness(getContext()));
         seekBar.setOnSeekBarChangeListener(this);

         title.setText("亮度调节");
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        Brightness.setBrightness(getContext(), i);
        if(callBack != null){
            callBack.onProgressChanged(seekBar, Brightness.getBrightness(getContext()));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
