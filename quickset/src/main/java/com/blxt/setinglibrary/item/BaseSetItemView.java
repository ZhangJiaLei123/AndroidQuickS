package com.blxt.setinglibrary.item;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class BaseSetItemView extends LinearLayout {

    protected static SharedPreferences sharedPreferences = null;

    SharedPreferences getSP(Context context){
        if(sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences(context.getPackageName() + "_preferences", MODE_PRIVATE);
        }
        return sharedPreferences;
    }
    protected BaseSetItemView instance;
    protected ImageView iv_logo;//item的图标
    protected TextView tv_title; //item的title文字
    protected TextView tv_hint; //item的内容提示文字
    protected ImageView iv_bottom; // 底部分割线


    /** 关联子视图 */
    protected List<View> subView = new ArrayList<>();

    private CharSequence tipText = null;
    private String spKey = null;
    public BaseSetItemView(Context context) {
        super(context);

        sharedPreferences = getSP(context);

    }

    protected void initview() {

        int visibility = this.getVisibility();
        if(subView != null){
            setSubViewVisibility(visibility);
        }

    }

    /**
     *
     * 设置标题
     * @param title
     */
    public BaseSetItemView setTitle(String title){
        tv_title.setText(title);
        return this;
    }

    /**
     * 获取标题
     * @return
     */
    public String getTitle(){
        return tv_title.getText().toString();
    }

    /**
     * 设置标题
     * @param res
     */
    public BaseSetItemView setTitleImage(int res){
        iv_logo.setBackgroundResource(res);
        return this;
    }

    /**
     * 设置提示文本
     * @param tipText
     */
    public BaseSetItemView setHint(String tipText){
        this.tipText = tipText;
        tv_hint.setText(tipText);
        return this;
       // saveValue(tipText);
    }

    /**
     * 设置提示信息
     * @param tipText
     * @return
     */
    public BaseSetItemView setHint(int tipText){
        tv_hint.setText(tipText);
        this.tipText = tv_hint.getText();
        return this;
        // saveValue(tipText);
    }

    /**
     * 设置提示文本和值
     * @param tipText  显示的文本
     * @param vueal    实际保存的值
     */
    public BaseSetItemView setHint(CharSequence tipText, String vueal){
        this.tipText = tipText;
        tv_hint.setText(tipText);
        return this;
      //  saveValue(vueal);
    }

    /**
     * 获取提示文本信息
     * @return
     */
    public String getHintText(){
        return tv_hint.getText().toString();
    }


    public BaseSetItemView setSubView(View view){
        subView.add(view);
        return this;
    }


    /**
     * 添加监听
     * @param clickListener
     */
    public void setOnClickListener(OnClickListenerCallBack clickListener) {
        this.clickListener = clickListener;
    }

    /**
     * 获取当前视图
     * @return
     */
    public View getView(){
        return this;
    }


    /** 当前设置可见性，关联子视图 */
    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if(subView != null){
            setSubViewVisibility(visibility);
        }
    }

    /** 设置子视图的可见性 */
    protected void setSubViewVisibility(int visibility){
        for(int i = 0; i < subView.size(); i++) {
            subView.get(i).setVisibility(visibility);
        }
    }


    /**
     * 获取设置的SharedPreferences
     * @return
     */
    public SharedPreferences getSharedPreferences(){
        return sharedPreferences;
    }

    public boolean saveValue(String value){
        if(sharedPreferences != null) {
            String key = getKey();
            if(key == null || key.length() <= 0){
                return false;
            }
            sharedPreferences.edit().putString(getKey() , value).commit();
            return true;
        }
        return false;
    }

    public String getValue(){

        return sharedPreferences.getString(getKey(), "null");
    }

    public BaseSetItemView setSpKey(String key){
        spKey = key;
        return this;
    }

    public String getKey(){
        String key = null;

        if(spKey != null){
            key = spKey;
        }
        else if(tv_title.getText() != null ){
            key = tv_title.getText().toString();
        }
        if(key == null || key.trim().length() <= 0){
            if(getTag() != null){
                key = getTag().toString();
            }
        }
        if(key == null || key.trim().length() <= 0){
            key = getId() + "";
        }

        return key;
    }



    OnClickListenerCallBack clickListener = null;
    public interface OnClickListenerCallBack{
        boolean onClickSetItem(View view);
    }
}
