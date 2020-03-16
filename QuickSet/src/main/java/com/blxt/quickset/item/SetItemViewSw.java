 package com.blxt.quickset.item;

 import android.content.Context;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.CompoundButton;
 import android.widget.ImageView;
 import android.widget.Switch;
 import android.widget.TextView;
 import com.blxt.quickset.R;




 public class SetItemViewSw
   extends BaseSetItemView
   implements CompoundButton.OnCheckedChangeListener
 {
   private Switch aSwitch;

   public SetItemViewSw(Context context, String title) {
     super(context);

     this.instance = this;
     LayoutInflater.from(getContext()).inflate(R.layout._item_set__sw, (ViewGroup)this);

     this.iv_bottom = (ImageView)findViewById(R.id._item_bottom);
     this.iv_logo = (ImageView)findViewById(R.id._item_title_ic);
     this.tv_title = (TextView)findViewById(R.id._item_title);
     this.tv_hint = (TextView)findViewById(R.id._item_tip);
     this.aSwitch = (Switch)findViewById(R.id._item_sw);

     setTitle(title);
     setHint("");
     setTextOff("关");
     setTextOn("开");
     initview();

     String value = getValue();
     if (value.equals("true") || value.equals("TRUE")) {
       this.aSwitch.setChecked(true);
     } else {

       this.aSwitch.setChecked(false);
     }

     addListener();
   }



   private void addListener() { this.aSwitch.setOnCheckedChangeListener(this); }




   public void setTextOff(String textOff) { this.aSwitch.setTextOff(textOff); }



   public void setTextOn(String textOn) { this.aSwitch.setTextOn(textOn); }



   public void setEnabled(boolean isbootom) { this.aSwitch.setEnabled(isbootom); }




   public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
     boolean fal = true;
     if (this.clickListener != null) {
       fal = this.clickListener.onClickSetItem((View)this.instance);
     }

     saveValue(b + "");
   }
 }

