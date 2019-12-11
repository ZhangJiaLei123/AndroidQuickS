package com.blxt.quickset.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;


import com.blxt.quickset.R;
import com.blxt.quicktools.system.Brightness;

/**
 * 亮度设置对话框
 */
public class BrightnessDialog extends BaseSeekbarDialog implements SeekBar.OnSeekBarChangeListener {

    Activity activity;

    public BrightnessDialog(Activity activity) {
        super(activity,  R.style.DialogTheme2);
        this.activity =  activity;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initUI();
    }

    @Override
    protected void initUI(){
        int max = 255;

        seekBar.setMax(max);
        seekBar.setProgress(Brightness.getBrightness(getActivity()));
        seekBar.setOnSeekBarChangeListener(this);

        title.setText("亮度调节");
    }

    public void setValue(int value){
        seekBar.setProgress(value);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        // 没有权限的话,就无法生效
        boolean fal = Brightness.checkPermiss(getActivity());
        if(fal){
            Brightness.setBrightness(getContext(), i);
            if(callBack != null){
                callBack.onProgressChanged(seekBar, Brightness.getBrightness(getContext()));
            }
        }
        else{
            Toast.makeText(getContext(), "没有权限", Toast.LENGTH_LONG);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public Activity getActivity(){
        return this.activity;
    }

}
