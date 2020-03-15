package com.blxt.quickactivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import java.util.Stack;
 

public class QAppManager
{
  private static Stack<Activity> m_stack_activity;
  private static QAppManager instance;
  
  public static QAppManager getAppManager() {
    if (instance == null) {
      instance = new QAppManager();
    }
    return instance;
  }

  
  private QAppManager() {
    if (m_stack_activity == null) {
      m_stack_activity = new Stack<>();
    }
  }




  
  public int getSize() {
    if (m_stack_activity == null) {
      return 0;
    }
    return m_stack_activity.size();
  }



  
  public void addActivity(Activity activity) {
    if (m_stack_activity == null) {
      m_stack_activity = new Stack<>();
    }
    m_stack_activity.add(activity);
  }




  
  public Activity currentActivity() {
    Activity activity = m_stack_activity.lastElement();
    return activity;
  }



  
  public void finishActivity() {
    Activity activity = m_stack_activity.lastElement();
    finishActivity(activity);
  }



  
  public void finishActivity(Activity activity) {
    if (activity != null) {
      m_stack_activity.remove(activity);
      activity.finish();
      activity = null;
    } 
  }



  
  public void finishActivity(Class<?> cls) {
    for (Activity activity : m_stack_activity) {
      if (activity.getClass().equals(cls)) {
        finishActivity(activity);
      }
    } 
  }



  
  public void finishAllActivity() {
    for (int i = 0, size = m_stack_activity.size(); i < size; i++) {
      if (null != m_stack_activity.get(i)) {
        ((Activity)m_stack_activity.get(i)).finish();
      }
    } 
    m_stack_activity.clear();
  }



  
  public void AppExit(Context context) {
    try {
      finishAllActivity();
      
      ActivityManager activityMgr = (ActivityManager)context.getSystemService("activity");
      activityMgr.restartPackage(context.getPackageName());
      System.exit(0);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }

 
  public void restartApp(Activity activity, int timeMillis) {
    Intent intent = activity.getPackageManager().getLaunchIntentForPackage(activity
        .getPackageName());
    PendingIntent restartIntent = PendingIntent.getActivity(activity.getApplicationContext(), 0, intent, 1073741824);
    
    AlarmManager mgr = (AlarmManager)activity.getSystemService("alarm");
    mgr.set(1, System.currentTimeMillis() + timeMillis, restartIntent);
    
    ActivityManager am = (ActivityManager)activity.getSystemService("activity");
    am.restartPackage(activity.getPackageName());
    
    Process.killProcess(Process.myPid());
    
    AppExit((Context)activity);
  }
}
 