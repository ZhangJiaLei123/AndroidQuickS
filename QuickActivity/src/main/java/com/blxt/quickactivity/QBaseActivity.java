/*     */ package com.blxt.quickactivity;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.os.Bundle;
/*     */ import android.os.Handler;
/*     */ import android.os.Message;
/*     */ import android.view.WindowManager;
/*     */ import androidx.appcompat.app.AppCompatActivity;
/*     */ import com.blxt.quicklog.QLog;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class QBaseActivity
/*     */   extends AppCompatActivity
/*     */ {
/*  23 */   protected Handler handler = new Handler()
/*     */     {
/*     */       public void handleMessage(Message msg) {
/*  26 */         QBaseActivity.this.doMessage(msg);
/*  27 */         super.handleMessage(msg);
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*     */   protected static QAppManager appManager;
/*     */   protected static QAppConfig appConfig;
/*     */   
/*  35 */   protected QBaseActivity() { appManager = QAppManager.getAppManager(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onCreate(Bundle savedInstanceState) {
/*  41 */     super.onCreate(savedInstanceState);
/*  42 */     appManager.addActivity((Activity)this);
/*  43 */     appConfig = QAppConfig.getInstance((Context)this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onDestroy() {
/*  48 */     super.onDestroy();
/*  49 */     appManager.finishActivity((Activity)this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doMessage(Message message) {}
/*     */ 
/*     */ 
/*     */   
/*  58 */   protected Context getContent() { return (Context)this; }
/*     */ 
/*     */ 
/*     */   
/*  62 */   protected QBaseActivity getActivity() { return this; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void showActionBar() {
/*  69 */     if (getSupportActionBar() != null) {
/*  70 */       getSupportActionBar().show();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void hideActionBar() {
/*  79 */     if (getSupportActionBar() != null) {
/*  80 */       getSupportActionBar().hide();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void hideBottonSystem() {
/*  88 */     WindowManager.LayoutParams params = getWindow().getAttributes();
/*  89 */     params.systemUiVisibility = 2050;
/*  90 */     getWindow().setAttributes(params);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void startActivity(Class<?> cls) {
/*  98 */     Intent intent = new Intent((Context)this, cls);
/*  99 */     startActivity(intent);
/*     */     
/* 101 */     appManager.finishActivity((Activity)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   protected void exit() { appManager.AppExit((Context)this); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   protected void restartApp(int timeMillis) { appManager.restartApp((Activity)this, timeMillis); }
/*     */ 
/*     */ 
/*     */   
/* 121 */   protected static synchronized void Log(Object... obj) { QLog.i(obj); }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickactivity-1.1.2.aar!\classes.jar!\com\blxt\quickactivity\QBaseActivity.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */