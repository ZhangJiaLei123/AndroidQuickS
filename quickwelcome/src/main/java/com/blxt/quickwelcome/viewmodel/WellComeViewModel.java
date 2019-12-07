package com.blxt.quickwelcome.viewmodel;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.SystemClock;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModel;

import com.blxt.quickview.progressbar.RoundProgressBar;
import com.blxt.quickwelcome.BaseWelcomeActivity;
import com.blxt.quickwelcome.R;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * 欢迎页视图Model
 * 注意 : logo图片的属性是wrap_content
 */
public class WellComeViewModel extends ViewModel implements RoundProgressBar.ProgressChangeListener {

    View view;

    private ImageView mIVEntry;
    private RoundProgressBar spikBar;
    /**
     * 预留的自定义视图Frame
     */
    private FrameLayout wellcomFramelayout;
    private TextView WellcomTitle;
    private ImageView WellcomLogo;
    private TextView WellcomButtomTips;

    /**
     * 随机的欢迎页图片
     */
    private static int[] imagesWellcom ={};


    AnimatorSet set;
    private static final int ANIM_TIME = 2000;
    private static final float SCALE_END = 1.15F;

    /**
     * WellCome页面状态监听
     */
    WellComeListener listener;

    public WellComeViewModel(View view){
        this.view = view;
        findViewById();
    }

    public void init(){
        initView();
    }

    /**
     * 初始化Id
     */
    private void findViewById(){

        mIVEntry = view.findViewById(R.id.iv_entry);
        spikBar = view.findViewById(R.id.spikBar);
        spikBar.setEnabled(false);
        spikBar.setProgressChangeListener(this);
        spikBar.setCountDownTimeMillis(BaseWelcomeActivity.spikTimeMillis);
        wellcomFramelayout = view.findViewById(R.id._wellcom_framelayout);
        WellcomTitle = view.findViewById(R.id._wellcom_title);
        WellcomLogo = view.findViewById(R.id._wellcom_logo);
        WellcomButtomTips = view.findViewById(R.id._wellcom_buttom_tips);

    }

    /**
     * 初始化视图
     */
    private void initView(){

        Random random = new Random(SystemClock.elapsedRealtime());//SystemClock.elapsedRealtime() 从开机到现在的毫秒数（手机睡眠(sleep)的时间也包括在内）
        mIVEntry.setImageResource(imagesWellcom[random.nextInt(imagesWellcom.length)]);

        Observable.timer(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>()
                {

                    @Override
                    public void call(Long aLong)
                    {
                        // 1s后,才允许点击
                        if(BaseWelcomeActivity.isSpikWithoutTime){
                            spikBar.setEnabled(true);
                        }
                        else{
                            spikBar.setEnabled(false);
                        }
                        // 开始动画
                        startAnim();
                    }
                });
    }

    /**
     * 动画
     */
    private void startAnim() {

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mIVEntry, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mIVEntry, "scaleY", 1f, SCALE_END);

        set = new AnimatorSet();
        set.setDuration(ANIM_TIME).play(animatorX).with(animatorY);
        set.start();

        set.addListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                // 动画结束
                if(listener != null){
                    listener.onAnimFinish();
                    //Log.i("WellComeViewModel","动画结束");
                }
            }
        });
    }


    /**
     * 结束,释放动画资源
     */
    public void finish() {
        if(set != null )
        {
            set.cancel();
            set = null;
        }
        if (spikBar != null){
            spikBar.pause();
            spikBar.destroy();
            spikBar = null;
        }

    }


    /**
     * 设置启动图片资源ID
     * @param imagesWellcom
     */
    public void setImagesWellcom(int[] imagesWellcom){
        WellComeViewModel.imagesWellcom = imagesWellcom;
    }


    /**
     * 设置允许点跳过按钮
     * @param b
     */
    public void setEnabledSpik(boolean b){
        spikBar.setEnabled(b);
    }

    /**
     * 设置监听回调
     * @param wellComeListener
     */
    public void setProgressChangeListener(WellComeListener wellComeListener) {
        listener = wellComeListener;
    }

    /**
     * 倒计时结束
     */
    @Override
    public void onFinish() {
        listener.onTimeFinish();
    }

    /**
     * 设置标题
     * @param resId
     */
    public void setWellcomTitle(int resId){
        getWellcomTitle().setText(resId);
    }

    public void setWellcomTitle(String resId){
        getWellcomTitle().setText(resId);
    }

    /**
     * 设置底部Tips
     * @param resId
     */
    public void setWellcomButtomTips(int resId){
        getWellcomButtomTips().setText(resId);
    }
    public void setWellcomButtomTips(String resId){
        getWellcomButtomTips().setText(resId);
    }

    /**
     * 设置底部图标
     * @param resID
     */
    public void setWellcomLogo(int resID){
        getWellcomLogo().setBackgroundResource(resID);
    }

    /**
     * 获取预留的自定义FrameLayout
     * @return
     */
    public FrameLayout getFrameLayout(){
        return wellcomFramelayout;
    }




    @Override
    public void onProgressChanged(int progress) {

    }

    public TextView getWellcomTitle() {
        return WellcomTitle;
    }

    public void setWellcomTitle(TextView wellcomTitle) {
        WellcomTitle = wellcomTitle;
    }

    public ImageView getWellcomLogo() {
        return WellcomLogo;
    }

    public void setWellcomLogo(ImageView wellcomLogo) {
        WellcomLogo = wellcomLogo;
    }

    public TextView getWellcomButtomTips() {
        return WellcomButtomTips;
    }

    public void setWellcomButtomTips(TextView wellcomButtomTips) {
        WellcomButtomTips = wellcomButtomTips;
    }

    /**
     * 页面状态回调
     */
    public interface WellComeListener {
        /**
         * 动画结束
         */
        void onAnimFinish();

        /**
         * 倒计时结束
         */
        void onTimeFinish();
    }
}
