package com.blxt.quickclipboard;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


/**
 * 剪辑板监听服务
 */
public class BaseClipboardServer extends Service {
   // final String TAG = "BaseClipboardServer";

    public QuickClipboard quickClipboard;

    public QuickClipboard.ClipboardCallBack clipboardCallBack;


    @Override
    public void onCreate() {
        super.onCreate();
        quickClipboard = QuickClipboard.getInstance(this, clipboardCallBack);

    }


    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return START_REDELIVER_INTENT;
    }


    /**
     * 剪辑板内容获取
     * @param datas
     */
    public void OnClipboard(String datas[]){

    }
}
