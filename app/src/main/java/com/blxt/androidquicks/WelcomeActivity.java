package com.blxt.androidquicks;

import android.content.Intent;
import android.os.Bundle;
import com.blxt.quickwelcome.BaseWelcomeActivity;
import com.blxt.quickwelcome.WellComeViewModel;

public class WelcomeActivity extends BaseWelcomeActivity implements WellComeViewModel.WellComeListener {

    /**
     * 随机的欢迎页图片
     */
    private final int[] Imgs={
            com.blxt.quickwelcome.R.drawable.wellcome1, com.blxt.quickwelcome.R.drawable.wellcome};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //获取ViewModel
        WellComeViewModel wellComeViewModel = getWellComeViewModel();

        // 首次运行判断
        if (isFirstRun()){
            // 开始加载
            wellComeViewModel.init();
        }
        else{
            finish();
            return;
        }

        // 设置logo
        wellComeViewModel.setWellcomLogo(R.drawable.quicklogo);
        // 设置标题
        wellComeViewModel.setWellcomTitle(R.string.app_name);
        // 设置Tips
        wellComeViewModel.setWellcomButtomTips("AndroidQuickS Demo");
        // 设置图片资源
        wellComeViewModel.setImagesWellcom(Imgs);

    }

    /**
     * 动画结束
     */
    @Override
    public void onAnimFinish() {
        super.onAnimFinish();
    }

    /**
     * 计时结束
     */
    @Override
    public void onTimeFinish() {
        super.onTimeFinish();

    }

    /**
     * 结束欢迎页
     */
    @Override
    public void finish() {
        super.finish();
        // 启动MainActivity
        startActivity(new Intent(this, MainActivity.class));
    }
}
