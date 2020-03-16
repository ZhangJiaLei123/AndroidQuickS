/*    */ package com.blxt.quickview.dialog;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.os.Bundle;
/*    */ import android.widget.SeekBar;
/*    */ import com.blxt.quicktools.system.VoiceTools;
/*    */ import com.blxt.quickview.R;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VoiceDialog
/*    */   extends BaseSeekbarDialog
/*    */   implements SeekBar.OnSeekBarChangeListener
/*    */ {
/* 18 */   public VoiceDialog(Context context) { super(context, R.style.DialogTheme); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void onCreate(Bundle savedInstanceState) {
/* 24 */     super.onCreate(savedInstanceState);
/*    */     
/* 26 */     initUI();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void initUI() {
/* 31 */     this.seekBar.setMax(VoiceTools.getMax(getContext()));
/* 32 */     this.seekBar.setProgress(VoiceTools.getVolume(getContext()));
/* 33 */     this.seekBar.setOnSeekBarChangeListener(this);
/* 34 */     this.title.setText("声音调节");
/*    */   }
/*    */ 
/*    */   
/*    */   public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
/* 39 */     VoiceTools.setVolume(getContext(), i);
/* 40 */     if (this.callBack != null)
/* 41 */       this.callBack.onProgressChanged(seekBar, VoiceTools.getVolume(getContext())); 
/*    */   }
/*    */   
/*    */   public void onStartTrackingTouch(SeekBar seekBar) {}
/*    */   
/*    */   public void onStopTrackingTouch(SeekBar seekBar) {}
/*    */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\dialog\VoiceDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */