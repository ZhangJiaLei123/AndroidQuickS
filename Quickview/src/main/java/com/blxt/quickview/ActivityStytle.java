 package com.blxt.quickview;

 import android.app.ActionBar;
 import android.app.Activity;
 import android.os.Build;
 import android.view.View;

















 public class ActivityStytle
 {
   public static void hintTitleBar(Activity activity) { activity.requestWindowFeature(1); }








   public static void setNavigationBar(Activity activity, int visible) {
     View decorView = activity.getWindow().getDecorView();

     if (8 == visible) {
       int option = 2;
       decorView.setSystemUiVisibility(option);
     }
   }






   public static void statusBarHide(Activity activity) {
     if (Build.VERSION.SDK_INT >= 21) {
       View decorView = activity.getWindow().getDecorView();
       int option = 1024;
       decorView.setSystemUiVisibility(option);
       activity.getWindow().setStatusBarColor(0);
       ActionBar actionBar = activity.getActionBar();
       if (actionBar != null) {
         actionBar.hide();
       }

       return;
     }

     if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21)
       activity.getWindow().addFlags(67108864);
   }
 }
