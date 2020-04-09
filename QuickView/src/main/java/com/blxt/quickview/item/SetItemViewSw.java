package com.blxt.quickview.item;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.blxt.quickview.R;


public class SetItemViewSw
        extends BaseSetItemView
        implements CompoundButton.OnCheckedChangeListener {
    final String ORIENTATION_VERTICAL = "vertical";
    final String ORIENTATION_HORIZONTAL = "horizontal";

    private Switch aSwitch;
    String orientation = "vertical";


    public SetItemViewSw(Context context) {
        super(context);
    }


    public SetItemViewSw(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.orientation = this.attributeHelper.getString("orientation", this.orientation);
        if (this.orientation.equals("vertical")) {
            LayoutInflater.from(getContext()).inflate(R.layout.__item_set__sw_v, (ViewGroup) this);
        } else if (this.orientation.equals("horizontal")) {
            LayoutInflater.from(getContext()).inflate(R.layout.__item_set__sw, (ViewGroup) this);
        }

        init();
    }


    private void init() {
        if (this.instance != null) {
            return;
        }
        this.instance = this;

        this.aSwitch = (Switch) findViewById(R.id._item_sw);

        boolean fal = this.attributeHelper.getBoolean("checked", false);
        this.aSwitch.setChecked(fal);

        loadStyle();
        addListener();

        initSubView();

        intiValue(getHintText());
    }


    private void addListener() {
        this.aSwitch.setOnCheckedChangeListener(this);
    }


    public void setTextOff(String textOff) {
        this.aSwitch.setTextOff(textOff);
    }


    public void setTextOn(String textOn) {
        this.aSwitch.setTextOn(textOn);
    }


    public void setEnabled(boolean isbootom) {
        this.aSwitch.setEnabled(isbootom);
    }


    public void setChecked(boolean checked) {
        this.aSwitch.setChecked(checked);
    }


    public boolean isChecked() {
        return this.aSwitch.isChecked();
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (this.clickListener != null)
            this.clickListener.onClickSetItem((View) this.instance);
    }
}
