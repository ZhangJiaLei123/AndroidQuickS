package com.blxt.quickview.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blxt.quickview.R;

public class ListItemUserWithBtn extends RelativeLayout implements View.OnClickListener {
    // 确定键
    public static final int BTN_OK = 1;
    // 取消键
    public static final int BTN_CANCEL = 2;

    ItemUserCallback callback;

    View view = null;
    int index = 0;
    // 按键响应
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

    @SuppressLint("NewApi")
    public ListItemUserWithBtn(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initUI();
    }


    private void initUI(){
        if(view != null){
            return ;
        }

        view = LayoutInflater.from(getContext()).inflate(R.layout.__item_list_user,this);

        tvNumber = view.findViewById(R.id.tv_number);
        tvUsername = view.findViewById(R.id.tv_username);
        tvUserTip = view.findViewById(R.id.tv_user_tip);
        tvUserId = view.findViewById(R.id.tv_user_id);
        btnOk = view.findViewById(R.id.btn_ok);
        btnCancel = view.findViewById(R.id.btn_cancel);

        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        btnOk.setVisibility(VISIBLE);
        btnCancel.setVisibility(VISIBLE);
        tvUserTip.setVisibility(VISIBLE);

    }

    public ListItemUserWithBtn setIndex(int index){
        tvNumber.setText("" + index);
        this.index = index;
        if(index > 1000){
            tvNumber.setTextSize(24);
        }

        return this;
    }


    public ListItemUserWithBtn setUserName(String name){
        tvUsername.setText(name);
        return this;
    }

    public ListItemUserWithBtn setUserId(String userID){
        tvUserId.setText(userID);
        return this;
    }

    public ListItemUserWithBtn setTips(String tips){
        tvUserTip.setText(tips);
        return this;
    }

    public ListItemUserWithBtn setTipsBackgroundResource(int resId){
        tvUserTip.setBackgroundResource(resId);
        return this;
    }

    public ListItemUserWithBtn setTipsColor(int resId){
        tvUserTip.setTextColor(resId);
        return this;
    }

    public String getUserName() {
        if(tvUsername.getText() == null){
            return "";
        }
        return tvUsername.getText().toString();
    }

    public String getIndex() {
        if(tvNumber.getText() == null){
            return "";
        }
        return tvNumber.getText().toString();
    }

    public String getUserId() {
        if(tvUserId.getText() == null){
            return "";
        }
        return tvUserId.getText().toString();
    }

    public Button getBtnOk() {
        return btnOk;
    }

    public Button getBtnCancel() {
        return btnCancel;
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (id == R.id.btn_ok) {
            btnId = BTN_OK;
        } else if (id == R.id.btn_cancel) {
            btnId = BTN_CANCEL;
        }

        if(this.callback != null){
            callback.OnClick(this);
        }

        btnId = -1;
    }

    public int getBtnId(){
        return btnId;
    }

    public ListItemUserWithBtn setCallback(ItemUserCallback callback) {
        this.callback = callback;
        return this;
    }

    public interface ItemUserCallback{
        void OnClick(ListItemUserWithBtn userItem);
    }
}
