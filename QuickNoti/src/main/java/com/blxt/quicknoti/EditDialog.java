package com.blxt.quicknoti;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditDialog
        extends Dialog {
    private Button yes;
    private Button no;
    private TextView titleTv;
    private EditText et_input;
    private String titleStr;
    private String hintStr;
    private String messageStr = null;


    private String yesStr;


    private String noStr;

    private onNoOnclickListener noOnclickListener;

    private onYesOnclickListener yesOnclickListener;


    public void setNoOnclickListener(String str, onNoOnclickListener onNoOnclickListener) {
        if (str != null) {
            this.noStr = str;
        }
        this.noOnclickListener = onNoOnclickListener;
    }


    public void setYesOnclickListener(String str, onYesOnclickListener onYesOnclickListener) {
        if (str != null) {
            this.yesStr = str;
        }
        this.yesOnclickListener = onYesOnclickListener;
    }


    public EditDialog(Context context) {
        super(context, R.style.dialog_edit);
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_edit);


        initView();

        initData();

        initEvent();
    }


    private void initEvent() {
        this.yes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (EditDialog.this.yesOnclickListener != null) {
                    EditDialog.this.yesOnclickListener.onYesClick(EditDialog.this.et_input.getText().toString());
                }
            }
        });

        this.no.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (EditDialog.this.noOnclickListener != null) {
                    EditDialog.this.noOnclickListener.onNoClick();
                }
            }
        });
    }


    private void initData() {
        if (this.titleStr != null) {
            this.titleTv.setText(this.titleStr);
        }
        if (this.messageStr != null) {
            this.et_input.setHint(this.hintStr);
        }

        if (this.yesStr != null) {
            this.yes.setText(this.yesStr);
        }
        this.et_input.setHint(this.hintStr);
    }


    private void initView() {
        this.yes = (Button) findViewById(R.id.yes);
        this.no = (Button) findViewById(R.id.no);
        this.titleTv = (TextView) findViewById(R.id.title);
        this.et_input = (EditText) findViewById(R.id.et_input);
    }


    public void setTitle(String title) {
        this.titleStr = title;
    }


    public void setHint(String hint) {
        this.hintStr = hint;
        if (this.et_input != null) {
            this.et_input.setHint(hint);
        }
    }


    public void setInputType(int type) {
        this.et_input.setInputType(type);
    }


    public void setMessage(String message) {
        this.messageStr = message;
    }


    public void show() {
        super.show();

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = -1;
        layoutParams.height = -1;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layoutParams);
    }

    public static interface onYesOnclickListener {
        void onYesClick(String param1String);
    }

    public static interface onNoOnclickListener {
        void onNoClick();
    }
}
