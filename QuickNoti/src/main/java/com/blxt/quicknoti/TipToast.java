package com.blxt.quicknoti;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class TipToast
{
  Context context;
  Handler handler;
  public static final int GRAVITY_TOP = 1;
  public static final int GRAVITY_BOTTOM = 2;
  public static final int GRAVITY_CENTER = 3;
  public static final int TIME_SHORT = 0;
  public static final int TIME_LONG = 1;

  public static TipToast newInstance(Context context, Handler callBack) { return new TipToast(context, callBack); }

  private TipToast(Context context, Handler handler) {
    this.context = context;
    this.handler = handler;
  }

  public TipToast(Context context) {
    this.context = context;
    this.handler = null;
  }


  public TipToast setHandel(Handler mHandler) {
    this.handler = mHandler;
    return this;
  }


  public void showToast(Object tag, Object... args) { showToast(2, tag, args); }



  public void showToast(final Handler handler, final Object tag,final Object... args) { (new Thread(new Runnable()
        {
          public void run()
          {
            handler.post(new Runnable()
                {
                  public void run() {
                    TipToast.this.showToast(2, tag, args);
                  }
                });
          }
        })).start(); }


  public void showToast(int state, Object tag, Object... args) { showToast(0, state, tag, args); }


  public void showToast(final int resourceId, final int state, final Object tag,final Object... args) {
    if (this.handler == null) {
      m_showToast(resourceId, state, tag, args);
    }
    else {

      (new Thread(new Runnable()
          {
            public void run()
            {
              TipToast.this.handler.post(new Runnable()
                  {
                    public void run() {
                      TipToast.this.m_showToast(resourceId, state, tag, args);
                    }
                  });
            }
          })).start();
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


    if (state == 1) {

      mToast.setGravity(112, 0, 100);
    }
    else if (state == 2) {

      mToast.setGravity(80, 0, 100);
    }
    else if (state == 3) {

      mToast.setGravity(81, 0, 100);
    }

    mToast.show();
  }
}
