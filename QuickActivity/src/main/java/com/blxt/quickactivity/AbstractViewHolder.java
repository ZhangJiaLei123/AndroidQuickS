package com.blxt.quickactivity;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.blxt.quicklog.QLog;


public abstract class AbstractViewHolder
        extends ViewModel {
    protected View view;
    protected int tag = 0;

    public static final int LIVE_VIEWMODEL_ON_MSGID = 100;


    public Handler callbck;

    public AbstractViewHolder(@NonNull View view) {
        this.view = view;
    }

    public final <T extends View> T findViewById(int id) {
        return (T) this.view.findViewById(id);
    }

    public abstract void onResume();

    public boolean onDestroy() {
        sendEmptyMessage(3);
        return false;
    }

    public void onRelease() {
    }

    public void setView(View view) {
        this.view = view;
    }

    protected void sendMessage(Message message) {
        if (this.callbck != null) {
            this.callbck.sendMessage(message);
        }
    }


    protected Message sendEmptyMessage(int messgId) {
        Message message = new Message();
        message.what = 100;
        message.arg1 = messgId;
        message.arg2 = this.tag;

        sendMessage(message);
        return message;
    }


    protected static synchronized void Log(Object... obj) {
        QLog.i(obj);
    }


    public Context getContext() {
        return this.view.getContext();
    }


    public void setCallbckHandler(Handler callbck) {
        this.callbck = callbck;
    }

    public boolean doMessage(Message message) {
        return false;
    }
}

