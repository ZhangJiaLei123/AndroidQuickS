package com.blxt.quickview.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.blxt.quicktools.system.VoiceTools;
import com.blxt.quickview.R;
import com.blxt.quickview.dialog.BaseSeekbarDialog;
import com.blxt.quickview.dialog.VoiceDialog;







public class SetVoiceView
  extends BaseSetItemView
{
  private ImageView iv_right;

  public SetVoiceView(Context context, String title) {
    super(context);

    this.instance = this;
    LayoutInflater.from(getContext()).inflate(R.layout.__item_set__simple, (ViewGroup)this);

    this.iv_bottom = findViewById(R.id._item_bottom);
    this.iv_logo = (ImageView)findViewById(R.id._item_title_ic);
    this.tv_title = (TextView)findViewById(R.id._item_title);
    this.tv_hint = (TextView)findViewById(R.id._item_tip);
    this.iv_right = (ImageView)findViewById(R.id._item_iright);

    setTitle(title);
    initSubView();

    this.tv_hint.setText("" + VoiceTools.getVolume(getContext()));

    setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View view) {
            boolean fal = true;
            if (SetVoiceView.this.clickListener != null) {
              fal = SetVoiceView.this.clickListener.onClickSetItem((View)SetVoiceView.this.instance);
            }
            if (fal) {
              VoiceDialog voiceDialog = new VoiceDialog(SetVoiceView.this.getContext());
              voiceDialog.setBackCancelable(new BaseSeekbarDialog.SeekBarCallBack()
                  {
                    public void onProgressChanged(SeekBar seekBar, int i)
                    {
                      SetVoiceView.this.tv_hint.setText("" + i);
                      SetVoiceView.this.saveValue("" + i);
                    }
                  });
              voiceDialog.show();
            }
          }
        });
  }




  public void setEnabled(boolean isbootom) { setEnabled(isbootom); }
}
