package com.blxt.quickwelcome;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

/**
 * 欢迎页
 */
public class BaseWelcomeActivity extends Activity implements WellComeViewModel.WellComeListener {


    protected static SharedPreferences sharedPreferences = null;

    SharedPreferences getSP(Context context){
        if(sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences(context.getPackageName() + "_preferences", MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    /**
     * 首次运行标记     */
    boolean isFristRun = false;

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

        View view = getWindow().getDecorView();
        wellComeViewModel = new WellComeViewModel(view);
        wellComeViewModel.setProgressChangeListener(this);

        getSP(view.getContext());

        // 判断首次运行
        String strTmp = sharedPreferences.getString("首次运行", "true");

        if(strTmp.equals("true") || strTmp.equals("TRUE")){
            isFristRun = true;
        }
        else{
            isFristRun = false;
        }
        // 标记首次运行
        sharedPreferences.edit().putString("首次运行", "false").commit();
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
        wellComeViewModel = null;
        super.finish();
    }

    public boolean isFirstRun(){
        return isFristRun;
    }

    public WellComeViewModel getWellComeViewModel(){
        return wellComeViewModel;
    }
}
