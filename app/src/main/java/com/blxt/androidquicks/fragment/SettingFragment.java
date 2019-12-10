package com.blxt.androidquicks.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blxt.androidquicks.R;
import com.blxt.quickset.BaseSettingFragment;
import com.blxt.quickset.dialog.BrightnessDialog;
import com.blxt.quickset.dialog.VoiceDialog;
import com.blxt.quickset.item.BaseSetItemView;
import com.blxt.quickset.item.SetBrighView;
import com.blxt.quickset.item.SetItemView;
import com.blxt.quickset.item.SetItemViewSeparator;
import com.blxt.quickset.item.SetItemViewSw;
import com.blxt.quickset.item.SetVoiceView;

public class SettingFragment extends BaseSettingFragment implements BaseSetItemView.OnClickListenerCallBack {

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setClickListener(this);
        mViewModel = getSettingViewModel();
        initItems();
        mViewModel.setAppName(R.string.app_name);
        mViewModel.setAppVersion(R.string.app_version);
        mViewModel.setAppImage(R.mipmap.ic_launcher_round);
    }

    private void initItems(){

        addItem(new SetItemViewSw(getContext(), "开机自启")
                .setTitleImage(R.mipmap.ic_launcher_round)
                .setHint("需要权限"));
        addItem(new SetItemViewSw(getContext(), "固定通知")
                .setTitleImage(R.mipmap.ic_launcher_round));

        addItem(new SetBrighView( getActivity(),"亮度")
                .setTitleImage(R.mipmap.ic_launcher_round));

        addItem(new SetVoiceView(getContext(), "音量")
                .setTitleImage(R.mipmap.ic_launcher_round));
        // 分组
        addItem(new SetItemViewSeparator(getContext(), "分享"));

        addItem(new SetItemView(getContext(), "分享")
                .setTitleImage(R.mipmap.ic_launcher_round)
                .setHint("分享安装连接"));
        addItem(new SetItemView(getContext(), "支持")
                .setTitleImage(R.mipmap.ic_launcher_round)
                .setHint("去评分"));
        addItem(new SetItemView(getContext(), "GitHub开源")
                .setTitleImage(R.mipmap.ic_launcher_round)
                .setHint("欢迎Star"));
        addItem(new SetItemView(getContext(), "检查更新")
                .setTitleImage(R.mipmap.ic_launcher_round)
                .setHint(R.string.app_version));
        addItem(new SetItemViewSw(getContext(), "恢复出厂模式")
                .setTitleImage(R.mipmap.ic_launcher_round)
                .setHint("重新运行引导页")
                .setSpKey("首次运行"));
    }


    @Override
    public boolean onClickSetItem(View view) {
       // super
        if(view instanceof BaseSetItemView){
            BaseSetItemView baseSetItemView = (BaseSetItemView)view;
            String title = baseSetItemView.getTitle();
            Log.i("点击",title);
            if (title.equals("恢复出厂模式")){
                baseSetItemView.setHint("下次启动时,将重新运行引导页");
            }
          //  else if(title.equals("亮度")){
          //      BrightnessDialog brightnessDialog = new BrightnessDialog(getActivity());
          //      brightnessDialog.show();
          //  }
          //  else if(title.equals("音量")){
          //      VoiceDialog brightnessDialog = new VoiceDialog(getActivity());
          //      brightnessDialog.show();
          //  }
        }
        return true;
    }

}
