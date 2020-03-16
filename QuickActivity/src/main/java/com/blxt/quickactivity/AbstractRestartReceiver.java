package com.blxt.quickactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;



public abstract class AbstractRestartReceiver
  extends BroadcastReceiver
{
  private final String TAG = "AbstractRestartReceiver";


  
  public void onReceive(Context context, Intent intent) {
    if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
      
      Log.e("AbstractRestartReceiver", "onReceive - BOOT_COMPLETED");
      doStart();
      return;
    } 
    doOnReceive(context, intent);
  }


  public boolean doOnReceive(Context context, Intent intent) { return false; }
  
  protected abstract void doStart();
}

