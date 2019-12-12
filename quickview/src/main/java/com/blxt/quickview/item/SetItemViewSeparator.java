package com.blxt.quickview.item;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blxt.quickview.R;


/**
 * 设置页面的item 的分隔符
 * @author Zhang
 */
public class SetItemViewSeparator extends LinearLayout {

    private TextView ItemTitle;


    public SetItemViewSeparator(Context context, String title) {
        super(context);
        LayoutInflater.from(getContext()).inflate(R.layout._item_set_separator,this);

        ItemTitle = findViewById(R.id._item_title);
        if(title != null){
            ItemTitle.setText(title);
        }
        else{ // 移除所有视图
            ItemTitle.setVisibility(GONE);
            removeAllViews();

            LayoutParams layout_923 = new LayoutParams(LayoutParams.MATCH_PARENT,2);

            setLayoutParams(layout_923);

        }


    }
}
