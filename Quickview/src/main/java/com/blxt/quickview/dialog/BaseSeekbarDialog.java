/*    */ package com.blxt.quickview.dialog;
/*    */ 
/*    */ import android.app.Dialog;
/*    */ import android.content.Context;
/*    */ import android.os.Bundle;
/*    */ import android.view.Window;
/*    */ import android.view.WindowManager;
/*    */ import android.widget.SeekBar;
/*    */ import android.widget.TextView;
/*    */ import com.blxt.quickview.R;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BaseSeekbarDialog
/*    */   extends Dialog
/*    */ {
/*    */   protected SeekBar seekBar;
/*    */   protected TextView title;
/*    */   protected boolean iscancelable = true;
/*    */   protected boolean isBackCancelable = true;
/* 26 */   SeekBarCallBack callBack = null;
/*    */ 
/*    */   
/* 29 */   public BaseSeekbarDialog(Context context, int styleId) { super(context, styleId); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void onCreate(Bundle savedInstanceState) {
/* 35 */     super.onCreate(savedInstanceState);
/* 36 */     setContentView(R.layout.__dialog_brightness);
/*    */ 
/*    */     
/* 39 */     this.title = (TextView)findViewById(R.id._dialog_title);
/* 40 */     this.seekBar = (SeekBar)findViewById(R.id._dialog_seekBar);
/*    */     
/* 42 */     setCancelable(this.iscancelable);
/* 43 */     setCanceledOnTouchOutside(this.isBackCancelable);
/* 44 */     Window window = getWindow();
/* 45 */     window.setGravity(80);
/* 46 */     WindowManager.LayoutParams params = window.getAttributes();
/* 47 */     params.width = -1;
/* 48 */     params.height = -2;
/* 49 */     window.setAttributes(params);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void initUI() {}
/*    */ 
/*    */   
/* 56 */   public void setBackCancelable(SeekBarCallBack callBack) { this.callBack = callBack; }
/*    */   
/*    */   public static interface SeekBarCallBack {
/*    */     void onProgressChanged(SeekBar param1SeekBar, int param1Int);
/*    */   }
/*    */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\dialog\BaseSeekbarDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */