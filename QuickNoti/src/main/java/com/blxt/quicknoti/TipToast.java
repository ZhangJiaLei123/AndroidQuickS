package com.blxt.quicknoti;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.blxt.quicknoti.unit.Tools;

public class TipToast {
    Context context;
    public static final int GRAVITY_TOP = 1;
    public static final int GRAVITY_BOTTOM = 2;
    public static final int GRAVITY_CENTER = 3;
    public static final int TIME_SHORT = 0;
    public static final int TIME_LONG = 1;

    @SuppressLint({"HandlerLeak"})
    static public Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            // super.handleMessage(msg);
        }
    };

    public static TipToast newInstance(Context context, Handler callBack) {
        return new TipToast(context, callBack);
    }

    private TipToast(Context context, Handler handler) {
        this.context = context;
        if (TipToast.handler == null) {
            TipToast.handler = handler;
        }

    }

    public TipToast(Context context) {
        this.context = context;
    }

    public TipToast setHandel(Handler mHandler) {
        if (TipToast.handler == null) {
            TipToast.handler = mHandler;
        }
        return this;
    }

    public void d(Object tag, Object... args) {
        if (Tools.isDebug(context)) {
            showToast(2, tag, args);
        }
    }

    public void i(Object tag, Object... args) {
        showToast(2, tag, args);
    }

    public void e(Object tag, Object... args) {
        showToast(2, tag, args);
    }

    public void showToast(Object tag, Object... args) {
        showToast(2, tag, args);
    }


    public void showToast(final Handler handler, final Object tag, final Object... args) {
      handler.post(new Runnable() {
        public void run() {
          TipToast.this.showToast(2, tag, args);
        }
      });
    }

    public void showToast(int state, Object tag, Object... args) {
        showToast(state, state, tag, args);
    }


    public void showToast(final int resourceId, final int state, final Object tag, final Object... args) {
        if (this.handler == null) {
            m_showToast(resourceId, state, tag, args);
        } else {
          handler.post(new Runnable() {
            public void run() {
              TipToast.this.showToast(resourceId, state, tag, args);
            }
          });
        }
    }


    private void m_showToast(int resourceId, int state, Object tag, Object... args) {
        String info = "";
        for (int i = 0; i < args.length - 1; i++) {
            info = info + args[i] + ",";
        }
        info = info + args[args.length - 1];

        Toast mToast = null;

        if (resourceId != 0) {
            LayoutInflater inflater = LayoutInflater.from(this.context);
            View toastView = inflater.inflate(resourceId, null);
            toastView.setBackgroundColor(0);
            mToast = new Toast(this.context);
            mToast.setView(toastView);
        } else {

            mToast = Toast.makeText(this.context, info, TIME_SHORT);
        }


        if (GRAVITY_TOP == 1) {

            mToast.setGravity(112, 0, 100);
        } else if (GRAVITY_BOTTOM == 2) {

            mToast.setGravity(80, 0, 100);
        } else if (GRAVITY_CENTER == 3) {

            mToast.setGravity(81, 0, 100);
        }

        mToast.show();
    }

}
