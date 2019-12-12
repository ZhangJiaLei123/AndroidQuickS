package com.blxt.quickview.item;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.blxt.quicktools.system.VoiceTools;
import com.blxt.quickview.R;
import com.blxt.quickview.dialog.BaseSeekbarDialog;
import com.blxt.quickview.dialog.VoiceDialog;


/**
 * 设置声音
 * @author Zhang
 */
public class SetVoiceView extends BaseSetItemView  {


    private ImageView iv_right; // 右边箭头


    public SetVoiceView(Context context, String title) {
        super(context);

        instance = this;
        LayoutInflater.from(getContext()).inflate(R.layout._item_set__simple,this);

        iv_bottom =findViewById(R.id._item_bottom);
        iv_logo =findViewById(R.id._item_title_ic);
        tv_title =findViewById(R.id._item_title);
        tv_hint = findViewById(R.id._item_tip);
        iv_right = findViewById(R.id._item_iright); //

        setTitle(title);
        initview();

        tv_hint.setText("" + VoiceTools.getVolume(getContext()));

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean fal = true;
                if(clickListener != null){
                    fal = clickListener.onClickSetItem(instance);
                }
                if (fal){
                    VoiceDialog voiceDialog = new VoiceDialog(getContext());
                    voiceDialog.setBackCancelable(new BaseSeekbarDialog.SeekBarCallBack(){

                        @Override
                        public void onProgressChanged(SeekBar seekBar, int i) {
                            tv_hint.setText("" + i);
                        }
                    });
                    voiceDialog.show();
                }

            }
        });
    }


    public void setEnabled(boolean isbootom){
        this.setEnabled(isbootom);
    }


}
