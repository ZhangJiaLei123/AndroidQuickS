package com.blxt.quickview.dialog;

import android.content.Context;
import android.os.Bundle;
import android.widget.SeekBar;
import com.blxt.quicktools.system.VoiceTools;
import com.blxt.quickview.R;






public class VoiceDialog
  extends BaseSeekbarDialog
  implements SeekBar.OnSeekBarChangeListener
{
  public VoiceDialog(Context context) { super(context, R.style.DialogTheme); }




  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    initUI();
  }


  protected void initUI() {
    this.seekBar.setMax(VoiceTools.getMax(getContext()));
    this.seekBar.setProgress(VoiceTools.getVolume(getContext()));
    this.seekBar.setOnSeekBarChangeListener(this);
    this.title.setText("声音调节");
  }


  public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
    VoiceTools.setVolume(getContext(), i);
    if (this.callBack != null)
      this.callBack.onProgressChanged(seekBar, VoiceTools.getVolume(getContext()));
  }

  public void onStartTrackingTouch(SeekBar seekBar) {}

  public void onStopTrackingTouch(SeekBar seekBar) {}
}

