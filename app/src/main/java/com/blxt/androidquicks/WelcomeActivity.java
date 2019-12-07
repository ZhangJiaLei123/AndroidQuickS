package com.blxt.androidquicks;

import android.content.Intent;
import android.os.Bundle;
import com.blxt.quickwelcome.BaseWelcomeActivity;
import com.blxt.quickwelcome.viewmodel.WellComeViewModel;

import static com.blxt.androidquicks.GuideActivity.isGuideRuned;

public class WelcomeActivity extends BaseWelcomeActivity implements WellComeViewModel.WellComeListener {

    /**
     * 随机的欢迎页图片
     */
    private final int[] Imgs={
            R.drawable.wellcome1, R.drawable.wellcome};

    /**
     * 是否进行首次运行检查
     */
    boolean isCheakFirstRun = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 如果引导页运行过了,就不要显示欢迎页了
        if(!isGuideRuned){
            //获取ViewModel
            WellComeViewModel wellComeViewModel = getWellComeViewModel();

            // 首次运行判断
            if (!isCheakFirstRun || isFirstRun()){
                // 设置logo
                wellComeViewModel.setWellcomLogo(R.drawable.quicklogo);
                // 设置标题
                wellComeViewModel.setWellcomTitle(R.string.app_name);
                // 设置Tips
                wellComeViewModel.setWellcomButtomTips("AndroidQuickS Demo");

                // 设置图片资源
                wellComeViewModel.setImagesWellcom(Imgs);
                // 开始加载
                wellComeViewModel.init();
                return;
            }
        }

        finish();

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
