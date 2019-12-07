package com.blxt.quickwelcome;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * 欢迎页
 */
public class BaseWelcomeActivity extends Activity implements WellComeViewModel.WellComeListener {

    /**
     * 自动跳出
     */
    public static boolean isAutoSpik = true;
    /**
     * 未完成计时,是否允许点击跳过
     */
    public static boolean isSpikWithoutTime = true;
    /**
     * 倒计时时间
     */
    public static int spikTimeMillis = 3000;

    /**
     * 视图Model
     */
    private WellComeViewModel wellComeViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout._welcomelayout);

        wellComeViewModel = new WellComeViewModel(getWindow().getDecorView());
        wellComeViewModel.setProgressChangeListener(this);

    }


    /**
     * 跳过的按钮点击响应
     * @param view
     */
    public void onClickSpik(View view){
        BaseWelcomeActivity.this.finish();
    }

    /**
     * 动画结束
     */
    @Override
    public void onAnimFinish() {

    }

    /**
     * 计时结束
     */
    @Override
    public void onTimeFinish() {
        if(isAutoSpik){
            BaseWelcomeActivity.this.finish();
        }
        else{
            wellComeViewModel.setEnabledSpik(true);
        }
    }


    /**
     * 结束欢迎页
     */
    @Override
    public void finish() {
        wellComeViewModel.finish();
        super.finish();
    }

    public WellComeViewModel getWellComeViewModel(){
        return wellComeViewModel;
    }
}
