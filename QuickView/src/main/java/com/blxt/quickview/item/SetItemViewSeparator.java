package com.blxt.quickview.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blxt.quickview.R;


public class SetItemViewSeparator
        extends LinearLayout {
    private TextView ItemTitle;

    public SetItemViewSeparator(Context context, String title, int colorResId) {
        super(context);
        LayoutInflater.from(getContext()).inflate(R.layout.__item_set_separator, (ViewGroup) this);

        this.ItemTitle = (TextView) findViewById(R.id._item_title);
        if (title != null) {
            this.ItemTitle.setText(title);
        } else {

            this.ItemTitle.setVisibility(GONE);
            removeAllViews();

            LinearLayout.LayoutParams layout_923 = new LinearLayout.LayoutParams(-1, 1);
            layout_923.leftMargin = 50;
            layout_923.rightMargin = 50;

            setLayoutParams((ViewGroup.LayoutParams) layout_923);
        }

        if (colorResId != 0) {
            setBackgroundResource(colorResId);
        }
    }

    public SetItemViewSeparator(Context context, String title) {
        this(context, title, 0);
    }
}
