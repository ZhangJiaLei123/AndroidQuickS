package com.blxt.quickwelcome;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blxt.quickwelcome.bgabanner.BGABanner;
import com.blxt.quickwelcome.bgabanner.BGALocalImageSize;


/**
 * 快速搭建引导页
 */
public class BaseGuideActivity extends Activity {
    private static final String TAG = BaseGuideActivity.class.getSimpleName();

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

    private BGABanner mBackgroundBanner;
    private BGABanner mForegroundBanner;
    private TextView tvGuideSkip;
    private Button btnGuideEnter;

    GuideListener listener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_guide);
        View view = getWindow().getDecorView();

        initView();
        setListener();
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

    private void initView() {
        mBackgroundBanner = findViewById(R.id.banner_guide_background);
        mForegroundBanner = findViewById(R.id.banner_guide_foreground);
        tvGuideSkip = findViewById(R.id.tv_guide_skip);
        btnGuideEnter = findViewById(R.id.btn_guide_enter);
    }

    private void setListener() {
        /**
         * 设置进入按钮和跳过按钮控件资源 id 及其点击事件
         * 如果进入按钮和跳过按钮有一个不存在的话就传 0
         * 在 BGABanner 里已经帮开发者处理了防止重复点击事件
         * 在 BGABanner 里已经帮开发者处理了「跳过按钮」和「进入按钮」的显示与隐藏
         */
        mForegroundBanner.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                if(listener != null) {
                    listener.onFinish();
                }
            }
        });
    }

    /**
     * 设置数据源
     * @param backgroundImages  背景图片
     * @param foregroundImages  上景图片
     */
    protected void processLogic(int[] backgroundImages, int[] foregroundImages) {
        // Bitmap 的宽高在 maxWidth maxHeight 和 minWidth minHeight 之间
        BGALocalImageSize localImageSize = new BGALocalImageSize(720, 1280, 320, 640);
        // 设置数据源

        mBackgroundBanner.setData(localImageSize, ImageView.ScaleType.CENTER_CROP, backgroundImages);

        mForegroundBanner.setData(localImageSize, ImageView.ScaleType.CENTER_CROP, foregroundImages);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 如果开发者的引导页主题是透明的，需要在界面可见时给背景 Banner 设置一个白色背景，避免滑动过程中两个 Banner 都设置透明度后能看到 Launcher
        mBackgroundBanner.setBackgroundResource(android.R.color.white);
    }


    /**
     * 添加引导页回调
     * @param listener
     */
    public void setGuideListener(GuideListener listener){
        this.listener = listener;
    }

    public TextView getGuideSkip() {
        return tvGuideSkip;
    }

    public void setGuideSkip(TextView tvGuideSkip) {
        this.tvGuideSkip = tvGuideSkip;
    }

    public Button getBtnGuideEnter() {
        return btnGuideEnter;
    }

    public void setBtnGuideEnter(Button btnGuideEnter) {
        this.btnGuideEnter = btnGuideEnter;
    }

    public boolean isFirstRun(){
        return isFristRun;
    }

    /**
     * 页面状态回调
     */
    public interface GuideListener {

        /**
         * 倒计时结束
         */
        void onFinish();
    }
}