package com.blxt.quickview.item;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blxt.quickview.R;


/**
 * 设置页面的item 的分隔符
 * @author Zhang
 */
public class SetItemViewSeparator extends LinearLayout {

    private TextView ItemTitle;

    /**
     *
     * @param context
     * @param title       标题
     * @param colorResId  背景颜色
     */
    public SetItemViewSeparator(Context context, String title, int colorResId) {
        super(context);
        LayoutInflater.from(getContext()).inflate(R.layout.__item_set_separator,this);

        ItemTitle = findViewById(R.id._item_title);
        if(title != null){
            ItemTitle.setText(title);
        }
        else{ // 移除所有视图
            ItemTitle.setVisibility(GONE);
            removeAllViews();

            LayoutParams layout_923 = new LayoutParams(LayoutParams.MATCH_PARENT,1);
            layout_923.leftMargin = 50;
            layout_923.rightMargin = 50;

            setLayoutParams(layout_923);
        }

        if(colorResId != 0){
            setBackgroundResource(colorResId);
        }
    }

    public SetItemViewSeparator(Context context, String title) {
        this(context, title, 0);
    }
}
