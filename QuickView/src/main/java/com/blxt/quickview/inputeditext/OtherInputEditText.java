/*    */ package com.blxt.quickview.inputeditext;
/*    */ 
/*    */ import android.annotation.SuppressLint;
/*    */ import android.content.Context;
/*    */ import android.util.AttributeSet;
/*    */ import android.view.LayoutInflater;
/*    */ import android.view.View;
/*    */ import android.view.ViewGroup;
/*    */ import android.widget.EditText;
/*    */ import android.widget.ImageView;
/*    */ import android.widget.LinearLayout;
/*    */ import com.blxt.quickview.R;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OtherInputEditText
/*    */   extends LinearLayout
/*    */ {
/*    */   private ImageView IvLog;
/*    */   private EditText editText;
/* 23 */   View view = null;
/*    */   
/*    */   public OtherInputEditText(Context context) {
/* 26 */     super(context);
/* 27 */     initUI();
/*    */   }
/*    */   
/*    */   public OtherInputEditText(Context context, AttributeSet attrs) {
/* 31 */     super(context, attrs);
/* 32 */     initUI();
/*    */   }
/*    */   
/*    */   public OtherInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
/* 36 */     super(context, attrs, defStyleAttr);
/* 37 */     initUI();
/*    */   }
/*    */   
/*    */   @SuppressLint({"NewApi"})
/*    */   public OtherInputEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
/* 42 */     super(context, attrs, defStyleAttr, defStyleRes);
/* 43 */     initUI();
/*    */   }
/*    */ 
/*    */   
/*    */   public void initUI() {
/* 48 */     if (this.view == null) {
/* 49 */       this.view = LayoutInflater.from(getContext()).inflate(R.layout.___input_user_name, (ViewGroup)this);
/* 50 */       this.IvLog = (ImageView)this.view.findViewById(R.id.__iv_log);
/* 51 */       this.IvLog.setBackgroundResource(R.mipmap.__ic_other);
/* 52 */       this.editText = (EditText)this.view.findViewById(R.id.__et_input);
/* 53 */       this.editText.clearFocus();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String getText() {
/* 59 */     if (this.editText.getText() == null) {
/* 60 */       return "";
/*    */     }
/* 62 */     return this.editText.getText().toString();
/*    */   }
/*    */ 
/*    */   
/* 66 */   public void setText(int str) { this.editText.setText(str); }
/*    */ 
/*    */ 
/*    */   
/* 70 */   public void setText(String str) { this.editText.setText(str); }
/*    */ 
/*    */ 
/*    */   
/* 74 */   public void setEnable(boolean b) { this.editText.setEnabled(b); }
/*    */ 
/*    */ 
/*    */   
/* 78 */   public void setHint(String str) { this.editText.setHint(str); }
/*    */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\inputeditext\OtherInputEditText.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */