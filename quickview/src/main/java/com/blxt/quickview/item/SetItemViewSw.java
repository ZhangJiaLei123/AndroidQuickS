package com.blxt.quickview.item;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.blxt.quickview.R;


/**
 * 设置页面的item
 * @author Zhang
 */
public class SetItemViewSw extends BaseSetItemView implements CompoundButton.OnCheckedChangeListener{


    private Switch aSwitch; // 右边箭头

    public SetItemViewSw(Context context, String title) {
        this(context, title, "false");
    }


    public SetItemViewSw(Context context, String title, String hint) {
        super(context);

        instance = this;
        LayoutInflater.from(getContext()).inflate(R.layout._item_set__sw,this);

        iv_bottom =findViewById(R.id._item_bottom);
        iv_logo =findViewById(R.id._item_title_ic);
        tv_title =findViewById(R.id._item_title);
        tv_hint = findViewById(R.id._item_tip);
        aSwitch = findViewById(R.id._item_sw); //

        setTitle(title);
        setHint("");
        setTextOff("关");
        setTextOn("开");
        initview();
        intiValue(hint);

        String value = getValue();
        if(value.equals("true") || value.equals("TRUE")){
            aSwitch.setChecked(true);
        }
        else{
            aSwitch.setChecked(false);
        }

        addListener();

    }


    public void intiValue(String value){
        String tmp = getValue();
        if(tmp == null || tmp.length() == 0){
            tmp = "true";
        }

        if(tmp.equals("true") || tmp.equals("TRUE")){
            aSwitch.setChecked(true);
            value = "true";
        }
        else{
            aSwitch.setChecked(false);
            value = "false";
        }
        saveValue(value);
    }


    private void addListener(){
        aSwitch.setOnCheckedChangeListener(this);
        //this.setOnClickListener(this);
    }

    public void setTextOff(String textOff){
        aSwitch.setTextOff(textOff);
    }

    public void setTextOn(String textOn){
        aSwitch.setTextOn(textOn);
    }

    public void setEnabled(boolean isbootom){
        aSwitch.setEnabled(isbootom);
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        boolean fal = true;
        if(clickListener != null){
            fal = clickListener.onClickSetItem(instance);
        }

        saveValue(b + "");
    }
}
