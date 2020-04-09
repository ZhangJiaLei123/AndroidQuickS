package com.blxt.quickset.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blxt.quickset.R;


public class SetItemViewSeparator
        extends LinearLayout {
    private TextView ItemTitle;

    public SetItemViewSeparator(Context context, String title) {
        super(context);
        LayoutInflater.from(getContext()).inflate(R.layout._item_set_separator, (ViewGroup) this);

        this.ItemTitle = (TextView) findViewById(R.id._item_title);
        this.ItemTitle.setText(title);
    }
}
