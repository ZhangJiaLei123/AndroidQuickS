package com.blxt.quickset.tools;

import android.content.Context;
import android.media.AudioManager;

/**
 * 声音调节工具
 */
public class VoiceTools {

    /**
     * 设置音量
     * @param context
     * @param value
     * @return
     */
    public static int setVolume(Context context, int value){
        int a = (int) Math.ceil((value)* getMax(context)*0.01);
        a = a<=0 ? 0 : a;
        a = a>=100 ? 100 : a;

        AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, value,0);
        mAudioManager.setStreamVolume(AudioManager.STREAM_ALARM, value,0);
        mAudioManager.setStreamVolume(AudioManager.STREAM_RING, value,0);
        return getVolume(context);
    }

    /**
     * 获取最大音量
     * @param context
     * @return
     */
    public static int getMax(Context context){
        //获取系统的Audio管理者
        AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        return mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    }



    /**
     * 获取 当前音量
     * @param context
     * @return
     */
    public static int getVolume(Context context){
        //获取系统的Audio管理者
        AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        //当前音量
        return mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

}
