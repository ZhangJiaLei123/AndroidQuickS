package com.blxt.quickview.item;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.blxt.quicktools.system.Brightness;
import com.blxt.quickview.R;
import com.blxt.quickview.dialog.BaseSeekbarDialog;
import com.blxt.quickview.dialog.BrightnessDialog;





public class SetBrighView
  extends BaseSetItemView
{
  private ImageView iv_right;
  Activity activity;

  public SetBrighView(Activity activity, String title) {
    super((Context)activity);
    this.activity = activity;

    this.instance = this;
    LayoutInflater.from(getContext()).inflate(R.layout.__item_set__simple, (ViewGroup)this);

    this.iv_bottom = findViewById(R.id._item_bottom);
    this.iv_logo = (ImageView)findViewById(R.id._item_title_ic);
    this.tv_title = (TextView)findViewById(R.id._item_title);
    this.tv_hint = (TextView)findViewById(R.id._item_tip);
    this.iv_right = (ImageView)findViewById(R.id._item_iright);

    setTitle(title);
    initSubView();

    this.tv_hint.setText("" + Brightness.getBrightness(getContext()));

    setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View view) {
            boolean fal = true;
            if (SetBrighView.this.clickListener != null) {
              fal = SetBrighView.this.clickListener.onClickSetItem((View)SetBrighView.this.instance);
            }
            if (fal) {
              BrightnessDialog brightnessDialog = new BrightnessDialog(SetBrighView.this.getActivity());
              brightnessDialog.setBackCancelable(new BaseSeekbarDialog.SeekBarCallBack()
                  {
                    public void onProgressChanged(SeekBar seekBar, int i)
                    {
                      SetBrighView.this.tv_hint.setText("" + i);
                      SetBrighView.this.saveValue("" + i);
                    }
                  });
              brightnessDialog.show();
            }
          }
        });
  }




  public void setEnabled(boolean isbootom) { setEnabled(isbootom); }




  public Activity getActivity() { return this.activity; }
}
