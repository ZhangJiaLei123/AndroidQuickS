package com.blxt.quickview.dialog;

import android.content.Context;
import android.os.Bundle;
import android.widget.SeekBar;

import com.blxt.quicktools.system.VoiceTools;
import com.blxt.quickview.R;


/**
 * 声音设置对话框
 */
public class VoiceDialog extends BaseSeekbarDialog implements SeekBar.OnSeekBarChangeListener {


    public VoiceDialog( Context context) {
        super(context,  R.style.DialogTheme);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initUI();
    }

    @Override
    protected void initUI(){
         seekBar.setMax(VoiceTools.getMax(getContext()));
         seekBar.setProgress(VoiceTools.getVolume(getContext()));
         seekBar.setOnSeekBarChangeListener(this);
         title.setText("声音调节");
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        VoiceTools.setVolume(getContext(), i);
        if(callBack != null){
            callBack.onProgressChanged(seekBar, VoiceTools.getVolume(getContext()));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
