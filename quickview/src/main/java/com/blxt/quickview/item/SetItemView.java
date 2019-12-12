package com.blxt.quickview.item;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.blxt.quickview.R;

/**
 * 设置页面的item
 * @author Zhang
 */
public class SetItemView extends BaseSetItemView {


    private ImageView iv_right; // 右边箭头

    public SetItemView(Context context, String title){
        this(context, title, "");
    }

    public SetItemView(Context context, String title, String value) {
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
        intiValue(value);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickListener != null){
                    clickListener.onClickSetItem(instance);
                }

            }
        });
    }

    protected void intiValue(String value){
        String tmp = getValue();
        if(tmp == null || tmp.length() == 0){
            saveValue(value);
        }
        else{
            value = tmp;
        }
        tv_hint.setText(value);
    }



    public void setEnabled(boolean isbootom){
        this.setEnabled(isbootom);
    }



}
