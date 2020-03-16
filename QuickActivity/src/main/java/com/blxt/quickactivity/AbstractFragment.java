package com.blxt.quickactivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;


public abstract class AbstractFragment
  extends Fragment
{
  public static final int LIVE_FRAGMENT_ON_MSGID = 99;
  public static final int LIVE_ON_CREATED = 0;
  public static final int LIVE_ON_START = 1;
  public static final int LIVE_ON_RESUME = 2;
  public static final int LIVE_ON_DESTROY = 3;
  public static final int LIVE_ON_UPDATA = 4;
  protected View fragmentView;
  protected int tag = 0;

  
  public Handler fragmentCallbck;

  
  @SuppressLint({"HandlerLeak"})
  public Handler fHandler = new Handler()
    {
      public void handleMessage(Message msg) {
        AbstractFragment.this.doMessage(msg);
        super.handleMessage(msg);
      }
    };




  
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { return super.onCreateView(inflater, container, savedInstanceState); }


  
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    sendEmptyMessage(0);
  }


  
  public void onStart() {
    super.onStart();
    sendEmptyMessage(1);
  }


  
  public void onResume() {
    super.onResume();
    sendEmptyMessage(2);
  }


  
  public void onDestroy() {
    super.onDestroy();
    sendEmptyMessage(3);
  }


  
  public void onRelease() {}


  
  public abstract boolean doMessage(Message paramMessage);

  
  public AbstractFragment setCallbckHandler(Handler fragmentCallbck) {
    this.fragmentCallbck = fragmentCallbck;
    return this;
  }






  
  protected void sendMessage(Message message) {
    if (this.fragmentCallbck != null) {
      this.fragmentCallbck.sendMessage(message);
    }
  }

  
  protected Message sendEmptyMessage(int messgId) {
    Message message = null;
    message = new Message();
    message.what = 99;
    message.arg1 = messgId;
    message.arg2 = this.tag;
    
    sendMessage(message);
    return message;
  }
}
 