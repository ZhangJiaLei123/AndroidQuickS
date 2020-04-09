package com.blxt.quickview.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blxt.quickview.R;


public class ListItemUserWithBtn
        extends RelativeLayout
        implements View.OnClickListener {
    public static final int BTN_OK = 1;
    public static final int BTN_CANCEL = 2;
    ItemUserCallback callback;
    View view = null;
    int index = 0;

    int btnId = -1;

    private TextView tvNumber;
    private TextView tvUsername;
    private TextView tvUserTip;
    private TextView tvUserId;
    private Button btnOk;
    private Button btnCancel;

    public ListItemUserWithBtn(Context context) {
        super(context);
        initUI();
    }

    public ListItemUserWithBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI();
    }

    public ListItemUserWithBtn(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI();
    }

    @SuppressLint({"NewApi"})
    public ListItemUserWithBtn(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initUI();
    }


    private void initUI() {
        if (this.view != null) {
            return;
        }

        this.view = LayoutInflater.from(getContext()).inflate(R.layout.__item_list_user, (ViewGroup) this);

        this.tvNumber = (TextView) this.view.findViewById(R.id.tv_number);
        this.tvUsername = (TextView) this.view.findViewById(R.id.tv_username);
        this.tvUserTip = (TextView) this.view.findViewById(R.id.tv_user_tip);
        this.tvUserId = (TextView) this.view.findViewById(R.id.tv_user_id);
        this.btnOk = (Button) this.view.findViewById(R.id.btn_ok);
        this.btnCancel = (Button) this.view.findViewById(R.id.btn_cancel);

        this.btnOk.setOnClickListener(this);
        this.btnCancel.setOnClickListener(this);

        this.btnOk.setVisibility(0);
        this.btnCancel.setVisibility(0);
        this.tvUserTip.setVisibility(0);
    }


    public ListItemUserWithBtn setIndex(int index) {
        this.tvNumber.setText("" + index);
        this.index = index;
        if (index > 1000) {
            this.tvNumber.setTextSize(24.0F);
        }

        return this;
    }


    public ListItemUserWithBtn setUserName(String name) {
        this.tvUsername.setText(name);
        return this;
    }

    public ListItemUserWithBtn setUserId(String userID) {
        this.tvUserId.setText(userID);
        return this;
    }

    public ListItemUserWithBtn setTips(String tips) {
        this.tvUserTip.setText(tips);
        return this;
    }

    public ListItemUserWithBtn setTipsBackgroundResource(int resId) {
        this.tvUserTip.setBackgroundResource(resId);
        return this;
    }

    public ListItemUserWithBtn setTipsColor(int resId) {
        this.tvUserTip.setTextColor(resId);
        return this;
    }

    public String getUserName() {
        if (this.tvUsername.getText() == null) {
            return "";
        }
        return this.tvUsername.getText().toString();
    }

    public String getIndex() {
        if (this.tvNumber.getText() == null) {
            return "";
        }
        return this.tvNumber.getText().toString();
    }

    public String getUserId() {
        if (this.tvUserId.getText() == null) {
            return "";
        }
        return this.tvUserId.getText().toString();
    }


    public Button getBtnOk() {
        return this.btnOk;
    }


    public Button getBtnCancel() {
        return this.btnCancel;
    }


    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btn_ok) {
            this.btnId = 1;
        } else if (id == R.id.btn_cancel) {
            this.btnId = 2;
        }

        if (this.callback != null) {
            this.callback.OnClick(this);
        }

        this.btnId = -1;
    }


    public int getBtnId() {
        return this.btnId;
    }


    public ListItemUserWithBtn setCallback(ItemUserCallback callback) {
        this.callback = callback;
        return this;
    }

    public static interface ItemUserCallback {
        void OnClick(ListItemUserWithBtn param1ListItemUserWithBtn);
    }
}
