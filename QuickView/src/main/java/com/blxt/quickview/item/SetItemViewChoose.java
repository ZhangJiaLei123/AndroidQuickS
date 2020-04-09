package com.blxt.quickview.item;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blxt.quickview.R;
import com.blxt.quickview.dialog.ActionSheetDialog;


public class SetItemViewChoose
        extends BaseSetItemView
        implements View.OnClickListener {
    private ImageView iv_right;
    private int selectId = 0;
    private String[] selectItems = new String[]{""};


    public SetItemViewChoose(Context context) {
        super(context);
    }


    public SetItemViewChoose(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.__item_set__simple, (ViewGroup) this);
        init();
    }


    private void init() {
        if (this.instance != null) {
            return;
        }
        this.instance = this;

        this.iv_right = (ImageView) findViewById(R.id._item_iright);

        loadStyle();
        addListener();

        initSubView();


        intiValue(getHintText());
    }


    protected void addListener() {
        this.iv_right.setOnClickListener(this);
        this.tv_hint.setOnClickListener(this);
    }


    public void onClick(View v) {
        if (this.clickListener != null && isEnabled()) {
            chooseRfidOutTime(getTitle(), this.selectItems);
            this.clickListener.onClickSetItem((View) this.instance);
        }
    }


    public int getSelectId() {
        return this.selectId;
    }


    public String getSelectItem() {
        return this.selectItems[this.selectId];
    }


    public SetItemViewChoose setSelectItems(String[] selectItems) {
        this.selectItems = selectItems;
        return this;
    }


    public void chooseRfidOutTime(String title, String... items) {
        (new ActionSheetDialog(getContext()))
                .builder()
                .setTitle(title)
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .setCallBack(new ActionSheetDialog.DialogCallBack() {
                    public void OnDismiss(Object obj) {
                        SetItemViewChoose.this.selectId = ((Integer) obj).intValue();
                        SetItemViewChoose.this.selectId = SetItemViewChoose.this.selectId - 1;


                        if (SetItemViewChoose.this.clickListener != null) {
                            SetItemViewChoose.this.clickListener.onClickSetItem((View) SetItemViewChoose.this.instance);
                        }
                    }
                }).addSheetItem(ActionSheetDialog.SheetItemColor.Blue, items)
                .show();
    }
}
