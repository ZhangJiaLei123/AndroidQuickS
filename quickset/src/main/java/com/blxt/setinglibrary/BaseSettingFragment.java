package com.blxt.setinglibrary;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.blxt.quickset.R;
import com.blxt.setinglibrary.item.BaseSetItemView;
import com.blxt.setinglibrary.viewmodel.SettingViewModel;


/**
 * 设置页基类 Fragment
 */
@SuppressLint("ValidFragment")
public class BaseSettingFragment extends Fragment {

    protected SettingViewModel mViewModel;
    protected View rootView;
    protected Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.setting_fragment, container, false);
        context = rootView.getContext();
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new SettingViewModel();
        mViewModel.setRootView(rootView);
    }

    /**
     * 设置监听回调,需要在addItem()前使用
     * @param clickListener
     */
    public void setClickListener(BaseSetItemView.OnClickListenerCallBack clickListener){
        mViewModel.setClickListener(clickListener);
    }

    /**
     * 获取ViewModel
     * @return
     */
    public SettingViewModel getSettingViewModel(){
        return mViewModel;
    }

    /**
     * 添加配置项视图
     * @param view
     */
    public void addItem(View view){
        mViewModel.addItem(view);
    }

    public final Context getContext() {
        return context;
    }
}
