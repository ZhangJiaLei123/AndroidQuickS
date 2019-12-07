package com.blxt.androidquicks;

import android.content.Intent;
import android.os.Bundle;

import com.blxt.quickwelcome.BaseGuideActivity;

public class GuideActivity extends BaseGuideActivity implements BaseGuideActivity.GuideListener {


    // 背景图片
    private int[] backgroundImages = {R.drawable.wellcome, R.drawable.wellcome1};
    // 上景图片
    private int[] foregroundImages = {R.drawable.uoko_guide_foreground_1, R.drawable.uoko_guide_foreground_2};

    //
    public static boolean isGuideRuned = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 首次运行判断
        if (isFirstRun()){
            // 开始加载
            setGuideListener(this);
            getBtnGuideEnter().setText("开始体验");
            // 加载图片
            processLogic(backgroundImages, foregroundImages);
            isGuideRuned = true;
        }
        else{
            isGuideRuned = false;
            finish();
            return;
        }

    }


    public void finish() {
        super.finish();
        startActivity(new Intent(this, WelcomeActivity.class));
    }

    @Override
    public void onFinish() {
        finish();

    }
}
