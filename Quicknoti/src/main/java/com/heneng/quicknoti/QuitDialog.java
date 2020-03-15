/*     */ package com.heneng.quicknoti;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.app.ProgressDialog;
/*     */ import android.content.Context;
/*     */ import android.content.DialogInterface;
/*     */ import android.content.Intent;
/*     */ import android.net.Uri;
/*     */ import android.os.Build;
/*     */ import android.view.View;
/*     */ import androidx.appcompat.app.AlertDialog;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class QuitDialog
/*     */ {
/*  55 */   public static ProgressDialog showProgressDialog(Activity context, String msg) { return showProgressDialog(context, null, msg); }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ProgressDialog showProgressDialog(Activity context, String title, String msg) {
/*  60 */     ProgressDialog mDialog = new ProgressDialog((Context)context);
/*  61 */     if (title != null) {
/*  62 */       mDialog.setTitle(title);
/*     */     }
/*  64 */     mDialog.setMessage(msg);
/*  65 */     mDialog.setCanceledOnTouchOutside(false);
/*  66 */     mDialog.show();
/*  67 */     return mDialog;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   public static AlertDialog showDialog(Activity context, String title, String msg) { return showDialog(context, -1, title, msg, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   public static AlertDialog showDialog(Activity context, String title, String msg, DialogInterface.OnClickListener onClickListener) { return showDialog(context, -1, title, msg, onClickListener); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   public static AlertDialog showDialog(Activity context, String title, String msg, String okText, String cancelText, DialogInterface.OnClickListener onClickListener) { return showDialog(context, title, msg, okText, cancelText, null, onClickListener); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public static AlertDialog showDialog(Activity context, String title, String msg, String okText, String cancelText, String neutral, DialogInterface.OnClickListener onClickListener) { return showDialog((Context)context, -1, null, title, msg, okText, cancelText, neutral, onClickListener); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   public static AlertDialog showDialog(Activity context, int iconResID, String title, String msg, DialogInterface.OnClickListener onClickListener) { return showDialog((Context)context, iconResID, null, title, msg, "确认", "取消", null, onClickListener); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   public static AlertDialog showDialog(Context context, View view, String title, DialogInterface.OnClickListener onClickListener) { return showDialog(context, view, title, "确定", "取消", onClickListener); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 119 */   public static AlertDialog showDialog(Context context, View view, String title, String okText, String cancelText, DialogInterface.OnClickListener onClickListener) { return showDialog(context, -1, view, title, null, okText, cancelText, null, onClickListener); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 128 */   public static AlertDialog showDialog(Context context, int icoResId, View view, String title, String okText, String cancelText, DialogInterface.OnClickListener onClickListener) { return showDialog(context, icoResId, view, title, null, okText, cancelText, null, onClickListener); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   public static AlertDialog showDialog(Context context, View view, String title, String okText, String cancelText, String neutral, DialogInterface.OnClickListener onClickListener) { return showDialog(context, -1, view, title, null, okText, cancelText, neutral, onClickListener); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 146 */   public static AlertDialog showDialogWithIco(Context context, int icoResId, View view, String title, String okText, String cancelText, String neutral, DialogInterface.OnClickListener onClickListener) { return showDialog(context, icoResId, view, title, null, okText, cancelText, neutral, onClickListener); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 174 */   public static AlertDialog showDialog(Context context, int iconResID, View view, String title, String msg, String okText, String cancelText, String neutral) { return showDialog(context, iconResID, view, title, msg, okText, cancelText, neutral, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AlertDialog showDialog(Context context, int iconResID, View view, String title, String msg, String okText, String cancelText, String neutral, DialogInterface.OnClickListener onClickListener) {
/* 182 */     AlertDialog.Builder builder = new AlertDialog.Builder(context);
/*     */     
/* 184 */     if (title != null) {
/* 185 */       builder.setTitle(title);
/*     */     }
/* 187 */     if (iconResID != -1) {
/* 188 */       builder.setIcon(iconResID);
/*     */     }
/* 190 */     if (view != null)
/*     */     {
/* 192 */       builder.setView(view);
/*     */     }
/* 194 */     if (msg != null)
/*     */     {
/* 196 */       builder.setMessage(msg);
/*     */     }
/*     */     
/* 199 */     if (okText != null)
/*     */     {
/* 201 */       builder.setPositiveButton(okText, onClickListener);
/*     */     }
/*     */     
/* 204 */     if (cancelText != null)
/*     */     {
/* 206 */       builder.setNegativeButton(cancelText, onClickListener);
/*     */     }
/*     */     
/* 209 */     if (neutral != null)
/*     */     {
/* 211 */       builder.setNeutralButton(neutral, onClickListener);
/*     */     }
/*     */     
/* 214 */     builder.setCancelable(false);
/*     */     
/* 216 */     AlertDialog dialog = builder.create();
/* 217 */     dialog.setCanceledOnTouchOutside(false);
/* 218 */     dialog.show();
/* 219 */     return dialog;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void startSetting(final Activity activity, String content) {
/* 315 */     AlertDialog alertDialog = (new AlertDialog.Builder((Context)activity)).setTitle("提示").setMessage(content).setPositiveButton("打开app应用程序信息界面", new DialogInterface.OnClickListener() { public void onClick(DialogInterface dialog, int which) { QuitDialog.startSetting((Context)activity); } }).create();
/* 316 */     alertDialog.show();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void startSetting(Context context) {
/* 323 */     Intent intent = new Intent();
/* 324 */     intent.addFlags(268435456);
/* 325 */     if (Build.VERSION.SDK_INT >= 9) {
/* 326 */       intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
/* 327 */       intent.setData(Uri.fromParts("package", context.getPackageName(), null));
/* 328 */     } else if (Build.VERSION.SDK_INT <= 8) {
/* 329 */       intent.setAction("android.intent.action.VIEW");
/* 330 */       intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
/* 331 */       intent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
/*     */     } 
/* 333 */     context.startActivity(intent);
/*     */   }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quicknoti-1.0.2.aar!\classes.jar!\com\heneng\quicknoti\QuitDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */