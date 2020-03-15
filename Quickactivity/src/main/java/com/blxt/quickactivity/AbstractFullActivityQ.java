package com.blxt.quickactivity;

import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.app.AppCompatActivity;

public abstract class AbstractFullActivityQ
  extends QPermissionActivity
{
  protected static String TAG = "QPermissionActivity";

  
  public AppCompatActivity activity;

  
  protected void onCreate(Bundle savedInstanceState) {
    getWindow().setFlags(1024, 1024);
    super.onCreate(savedInstanceState);

    
    if (getSupportActionBar() != null) {
      getSupportActionBar().hide();
    }

    
    WindowManager.LayoutParams params = getWindow().getAttributes();
    params.systemUiVisibility = 2050;
    getWindow().setAttributes(params);
    
    this.activity = this;
  }

  
  protected void onDestroy() {
    super.onDestroy();
    this.activity = null;
  }








  
  public boolean dispatchTouchEvent(MotionEvent ev) {
    if (ev.getAction() == 0) {
      View v = getCurrentFocus();
      if (isShouldHideKeyboard(v, ev)) {
        boolean res = hideKeyboard(v.getWindowToken());
        if (res)
        {
          return true;
        }
      } 
    } 
    return super.dispatchTouchEvent(ev);
  }







  
  private boolean isShouldHideKeyboard(View v, MotionEvent event) {
    if (v != null && v instanceof android.widget.EditText) {
      int[] l = { 0, 0 };
      v.getLocationInWindow(l);
      int left = l[0];
      int top = l[1];
      int bottom = top + v.getHeight();
      int right = left + v.getWidth();
      if (event.getX() > left && event.getX() < right && event
        .getY() > top && event.getY() < bottom)
      {
        return false;
      }
      return true;
    } 

    
    return false;
  }


  private boolean hideKeyboard(IBinder token) {
    if (token != null) {
      InputMethodManager im = (InputMethodManager)getSystemService("input_method");
      return im.hideSoftInputFromWindow(token, 2);
    } 
    return false;
  }
}

