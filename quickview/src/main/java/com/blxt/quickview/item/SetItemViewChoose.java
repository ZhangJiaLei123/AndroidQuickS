package com.blxt.quickview.item;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.blxt.quickview.R;
import com.blxt.quickview.dialog.ActionSheetDialog;

/**
 * 提供选择的设置item
 * @author Zhang
 */
public class SetItemViewChoose extends SetItemView {

    private int chooseId = 0;
    private String chooseItems[] = {""};

    public SetItemViewChoose(Context context, String title){
        this(context, title, "");
    }

    public SetItemViewChoose(Context context, final String title, String value) {
        super(context, title, value);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseRfidOutTime(title, chooseItems);
            }
        });
    }

    public int getChooseId() {
        return this.chooseId;
    }

    public SetItemViewChoose setChooseItems(String[] chooseItems) {
        this.chooseItems = chooseItems;
        return this;
    }

    /**
     * 一个仿IOS的选择下弹框
     * @param title
     * @param items
     */
    public void chooseRfidOutTime(String title, final String ...items){
        new ActionSheetDialog(getContext())
                .builder()
                .setTitle(title)
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .setCallBack(new ActionSheetDialog.DialogCallBack() {
                    @Override
                    public void OnDismiss(Object obj) {
                        int index = (int)obj;

                        String res = chooseItems[index - 1];
                        saveValue(res);
                        setHint(res);
                        if(clickListener != null){
                            clickListener.onClickSetItem(instance);
                        }

                    }
                })
                .addSheetItem(ActionSheetDialog.SheetItemColor.Blue, items)
                .show();
    }

}
