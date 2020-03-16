package com.blxt.quickactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.blxt.quicklog.QLog;


public abstract class QBaseActivity
        extends AppCompatActivity {
    protected Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            QBaseActivity.this.doMessage(msg);
            super.handleMessage(msg);
        }
    };


    protected static QAppManager appManager;
    protected static QAppConfig appConfig;

    protected QBaseActivity() {
        appManager = QAppManager.getAppManager();
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appManager.addActivity((Activity) this);
        appConfig = QAppConfig.getInstance((Context) this);
    }


    protected void onDestroy() {
        super.onDestroy();
        appManager.finishActivity((Activity) this);
    }


    protected void doMessage(Message message) {
    }


    protected Context getContent() {
        return (Context) this;
    }


    protected QBaseActivity getActivity() {
        return this;
    }


    protected void showActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
        }
    }


    protected void hideActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }


    private void hideBottonSystem() {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.systemUiVisibility = 2050;
        getWindow().setAttributes(params);
    }


    protected void startActivity(Class<?> cls) {
        Intent intent = new Intent((Context) this, cls);
        startActivity(intent);

        appManager.finishActivity((Activity) this);
    }


    protected void exit() {
        appManager.AppExit((Context) this);
    }


    protected void restartApp(int timeMillis) {
        appManager.restartApp((Activity) this, timeMillis);
    }


    protected static synchronized void Log(Object... obj) {
        QLog.i(obj);
    }
}
