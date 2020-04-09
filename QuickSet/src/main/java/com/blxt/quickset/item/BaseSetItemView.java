package com.blxt.quickset.item;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class BaseSetItemView
        extends LinearLayout {
    protected static SharedPreferences sharedPreferences = null;

    SharedPreferences getSP(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(context.getPackageName() + "_preferences", 0);
        }
        return sharedPreferences;
    }


    protected BaseSetItemView instance;

    protected ImageView iv_logo;
    protected TextView tv_title;
    protected TextView tv_hint;
    protected ImageView iv_bottom;
    protected List<View> subView = new ArrayList<>();

    private CharSequence tipText = null;
    private String spKey = null;

    public BaseSetItemView(Context context) {
        super(context);


        this.clickListener = null;
        sharedPreferences = getSP(context);
    }

    OnClickListenerCallBack clickListener;

    protected void initview() {
        int visibility = getVisibility();
        if (this.subView != null)
            setSubViewVisibility(visibility);
    }

    public BaseSetItemView setTitle(String title) {
        this.tv_title.setText(title);
        return this;
    }

    public String getTitle() {
        return this.tv_title.getText().toString();
    }

    public BaseSetItemView setTitleImage(int res) {
        this.iv_logo.setBackgroundResource(res);
        return this;
    }

    public BaseSetItemView setHint(String tipText) {
        this.tipText = tipText;
        this.tv_hint.setText(tipText);
        return this;
    }

    public BaseSetItemView setHint(int tipText) {
        this.tv_hint.setText(tipText);
        this.tipText = this.tv_hint.getText();
        return this;
    }

    public BaseSetItemView setHint(CharSequence tipText, String vueal) {
        this.tipText = tipText;
        this.tv_hint.setText(tipText);
        return this;
    }

    public String getHintText() {
        return this.tv_hint.getText().toString();
    }

    public BaseSetItemView setSubView(View view) {
        this.subView.add(view);
        return this;
    }

    public void setOnClickListener(OnClickListenerCallBack clickListener) {
        this.clickListener = clickListener;
    }

    public View getView() {
        return (View) this;
    }

    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (this.subView != null)
            setSubViewVisibility(visibility);
    }

    protected void setSubViewVisibility(int visibility) {
        for (int i = 0; i < this.subView.size(); i++)
            ((View) this.subView.get(i)).setVisibility(visibility);
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public boolean saveValue(String value) {
        if (sharedPreferences != null) {
            String key = getKey();
            if (key == null || key.length() <= 0)
                return false;
            sharedPreferences.edit().putString(getKey(), value).commit();
            return true;
        }
        return false;
    }

    public String getValue() {
        return sharedPreferences.getString(getKey(), "null");
    }

    public BaseSetItemView setSpKey(String key) {
        this.spKey = key;
        return this;
    }

    public String getKey() {
        String key = null;
        if (this.spKey != null) {
            key = this.spKey;
        } else if (this.tv_title.getText() != null) {
            key = this.tv_title.getText().toString();
        }
        if ((key == null || key.trim().length() <= 0) && getTag() != null)
            key = getTag().toString();
        if (key == null || key.trim().length() <= 0)
            key = getId() + "";
        return key;
    }

    public static interface OnClickListenerCallBack {
        boolean onClickSetItem(View param1View);
    }
}

