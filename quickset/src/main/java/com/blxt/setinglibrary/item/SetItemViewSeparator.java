package com.blxt.setinglibrary.item;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blxt.quickset.R;


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
        ItemTitle.setText(title);
    }
}
