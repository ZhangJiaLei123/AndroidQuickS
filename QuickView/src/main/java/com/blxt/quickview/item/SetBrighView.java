/*    */ package com.blxt.quickview.item;
/*    */ 
/*    */ import android.app.Activity;
/*    */ import android.content.Context;
/*    */ import android.view.LayoutInflater;
/*    */ import android.view.View;
/*    */ import android.view.ViewGroup;
/*    */ import android.widget.ImageView;
/*    */ import android.widget.SeekBar;
/*    */ import android.widget.TextView;
/*    */ import com.blxt.quicktools.system.Brightness;
/*    */ import com.blxt.quickview.R;
/*    */ import com.blxt.quickview.dialog.BaseSeekbarDialog;
/*    */ import com.blxt.quickview.dialog.BrightnessDialog;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SetBrighView
/*    */   extends BaseSetItemView
/*    */ {
/*    */   private ImageView iv_right;
/*    */   Activity activity;
/*    */   
/*    */   public SetBrighView(Activity activity, String title) {
/* 27 */     super((Context)activity);
/* 28 */     this.activity = activity;
/*    */     
/* 30 */     this.instance = this;
/* 31 */     LayoutInflater.from(getContext()).inflate(R.layout.__item_set__simple, (ViewGroup)this);
/*    */     
/* 33 */     this.iv_bottom = findViewById(R.id._item_bottom);
/* 34 */     this.iv_logo = (ImageView)findViewById(R.id._item_title_ic);
/* 35 */     this.tv_title = (TextView)findViewById(R.id._item_title);
/* 36 */     this.tv_hint = (TextView)findViewById(R.id._item_tip);
/* 37 */     this.iv_right = (ImageView)findViewById(R.id._item_iright);
/*    */     
/* 39 */     setTitle(title);
/* 40 */     initSubView();
/*    */     
/* 42 */     this.tv_hint.setText("" + Brightness.getBrightness(getContext()));
/*    */     
/* 44 */     setOnClickListener(new View.OnClickListener()
/*    */         {
/*    */           public void onClick(View view) {
/* 47 */             boolean fal = true;
/* 48 */             if (SetBrighView.this.clickListener != null) {
/* 49 */               fal = SetBrighView.this.clickListener.onClickSetItem((View)SetBrighView.this.instance);
/*    */             }
/* 51 */             if (fal) {
/* 52 */               BrightnessDialog brightnessDialog = new BrightnessDialog(SetBrighView.this.getActivity());
/* 53 */               brightnessDialog.setBackCancelable(new BaseSeekbarDialog.SeekBarCallBack()
/*    */                   {
/*    */                     public void onProgressChanged(SeekBar seekBar, int i)
/*    */                     {
/* 57 */                       SetBrighView.this.tv_hint.setText("" + i);
/* 58 */                       SetBrighView.this.saveValue("" + i);
/*    */                     }
/*    */                   });
/* 61 */               brightnessDialog.show();
/*    */             } 
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 70 */   public void setEnabled(boolean isbootom) { setEnabled(isbootom); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 75 */   public Activity getActivity() { return this.activity; }
/*    */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\item\SetBrighView.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */