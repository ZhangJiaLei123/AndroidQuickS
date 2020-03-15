/*    */ package com.blxt.quickview.dialog;
/*    */ 
/*    */ import android.app.Activity;
/*    */ import android.content.Context;
/*    */ import android.os.Bundle;
/*    */ import android.widget.SeekBar;
/*    */ import android.widget.Toast;
/*    */ import com.blxt.quicktools.system.Brightness;
/*    */ import com.blxt.quickview.R;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BrightnessDialog
/*    */   extends BaseSeekbarDialog
/*    */   implements SeekBar.OnSeekBarChangeListener
/*    */ {
/*    */   Activity activity;
/*    */   
/*    */   public BrightnessDialog(Activity activity) {
/* 20 */     super((Context)activity, R.style.BrightnessDialog);
/* 21 */     this.activity = activity;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void onCreate(Bundle savedInstanceState) {
/* 27 */     super.onCreate(savedInstanceState);
/*    */ 
/*    */     
/* 30 */     initUI();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void initUI() {
/* 35 */     int max = 255;
/*    */     
/* 37 */     this.seekBar.setMax(max);
/* 38 */     this.seekBar.setProgress(Brightness.getBrightness((Context)getActivity()));
/* 39 */     this.seekBar.setOnSeekBarChangeListener(this);
/*    */     
/* 41 */     this.title.setText("亮度调节");
/*    */   }
/*    */ 
/*    */   
/* 45 */   public void setValue(int value) { this.seekBar.setProgress(value); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
/* 51 */     boolean fal = Brightness.checkPermiss(getActivity());
/* 52 */     if (fal) {
/* 53 */       Brightness.setBrightness(getContext(), i);
/* 54 */       if (this.callBack != null) {
/* 55 */         this.callBack.onProgressChanged(seekBar, Brightness.getBrightness(getContext()));
/*    */       }
/*    */     } else {
/*    */       
/* 59 */       Toast.makeText(getContext(), "没有权限", 1);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onStartTrackingTouch(SeekBar seekBar) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void onStopTrackingTouch(SeekBar seekBar) {}
/*    */ 
/*    */ 
/*    */   
/* 74 */   public Activity getActivity() { return this.activity; }
/*    */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\dialog\BrightnessDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */