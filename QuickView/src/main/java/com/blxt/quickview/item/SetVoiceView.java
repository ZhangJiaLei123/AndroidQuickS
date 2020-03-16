/*    */ package com.blxt.quickview.item;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.view.LayoutInflater;
/*    */ import android.view.View;
/*    */ import android.view.ViewGroup;
/*    */ import android.widget.ImageView;
/*    */ import android.widget.SeekBar;
/*    */ import android.widget.TextView;
/*    */ import com.blxt.quicktools.system.VoiceTools;
/*    */ import com.blxt.quickview.R;
/*    */ import com.blxt.quickview.dialog.BaseSeekbarDialog;
/*    */ import com.blxt.quickview.dialog.VoiceDialog;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SetVoiceView
/*    */   extends BaseSetItemView
/*    */ {
/*    */   private ImageView iv_right;
/*    */   
/*    */   public SetVoiceView(Context context, String title) {
/* 27 */     super(context);
/*    */     
/* 29 */     this.instance = this;
/* 30 */     LayoutInflater.from(getContext()).inflate(R.layout.__item_set__simple, (ViewGroup)this);
/*    */     
/* 32 */     this.iv_bottom = findViewById(R.id._item_bottom);
/* 33 */     this.iv_logo = (ImageView)findViewById(R.id._item_title_ic);
/* 34 */     this.tv_title = (TextView)findViewById(R.id._item_title);
/* 35 */     this.tv_hint = (TextView)findViewById(R.id._item_tip);
/* 36 */     this.iv_right = (ImageView)findViewById(R.id._item_iright);
/*    */     
/* 38 */     setTitle(title);
/* 39 */     initSubView();
/*    */     
/* 41 */     this.tv_hint.setText("" + VoiceTools.getVolume(getContext()));
/*    */     
/* 43 */     setOnClickListener(new View.OnClickListener()
/*    */         {
/*    */           public void onClick(View view) {
/* 46 */             boolean fal = true;
/* 47 */             if (SetVoiceView.this.clickListener != null) {
/* 48 */               fal = SetVoiceView.this.clickListener.onClickSetItem((View)SetVoiceView.this.instance);
/*    */             }
/* 50 */             if (fal) {
/* 51 */               VoiceDialog voiceDialog = new VoiceDialog(SetVoiceView.this.getContext());
/* 52 */               voiceDialog.setBackCancelable(new BaseSeekbarDialog.SeekBarCallBack()
/*    */                   {
/*    */                     public void onProgressChanged(SeekBar seekBar, int i)
/*    */                     {
/* 56 */                       SetVoiceView.this.tv_hint.setText("" + i);
/* 57 */                       SetVoiceView.this.saveValue("" + i);
/*    */                     }
/*    */                   });
/* 60 */               voiceDialog.show();
/*    */             } 
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 69 */   public void setEnabled(boolean isbootom) { setEnabled(isbootom); }
/*    */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\item\SetVoiceView.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */