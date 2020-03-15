package com.blxt.quickactivity;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Set;






public class QAppConfig
{
  static QAppConfig instance;
  
  public static synchronized QAppConfig getInstance(Context context) {
    if (instance == null) {
      instance = new QAppConfig(context);
    }
    return instance;
  }
  
  protected static SharedPreferences sharedPreferences = null;
  
  private QAppConfig(Context context) {
    sharedPreferences = context.getSharedPreferences(context
        .getPackageName() + "_QAppShareConfig", 0);
  }

  
  public synchronized Boolean getBoolean(String key, boolean value) { return Boolean.valueOf(sharedPreferences.getBoolean(key, value)); }


  
  public synchronized int getInt(String key, int value) { return sharedPreferences.getInt(key, value); }


  
  public synchronized long getLong(String key, long value) { return sharedPreferences.getLong(key, value); }


  
  public synchronized float getFloat(String key, float value) { return sharedPreferences.getFloat(key, value); }


  
  public synchronized String getString(String key, String value) { return sharedPreferences.getString(key, value); }


  
  public synchronized Set<String> getStringSet(String key, Set<String> value) { return sharedPreferences.getStringSet(key, value); }




  
  public synchronized void putBoolean(String key, boolean value) { sharedPreferences.edit().putBoolean(key, value).apply(); }


  
  public synchronized void putString(String key, String value) { sharedPreferences.edit().putString(key, value).apply(); }


  
  public synchronized void putInt(String key, int value) { sharedPreferences.edit().putInt(key, value).apply(); }


  
  public synchronized void putFloat(String key, float value) { sharedPreferences.edit().putFloat(key, value).apply(); }


  
  public synchronized void putFloat(String key, Set<String> value) { sharedPreferences.edit().putStringSet(key, value).apply(); }


  
  public synchronized void putLong(String key, long value) { sharedPreferences.edit().putLong(key, value).apply(); }
}
 