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
/*    */ public class UserInputEditText
/*    */   extends LinearLayout
/*    */ {
/*    */   private ImageView IvLog;
/*    */   private EditText editText;
/* 23 */   View view = null;
/*    */   
/*    */   public UserInputEditText(Context context) {
/* 26 */     super(context);
/* 27 */     initUI();
/*    */   }
/*    */   
/*    */   public UserInputEditText(Context context, AttributeSet attrs) {
/* 31 */     super(context, attrs);
/* 32 */     initUI();
/*    */   }
/*    */   
/*    */   public UserInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
/* 36 */     super(context, attrs, defStyleAttr);
/* 37 */     initUI();
/*    */   }
/*    */   
/*    */   @SuppressLint({"NewApi"})
/*    */   public UserInputEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
/* 42 */     super(context, attrs, defStyleAttr, defStyleRes);
/* 43 */     initUI();
/*    */   }
/*    */ 
/*    */   
/*    */   public void initUI() {
/* 48 */     if (this.view == null) {
/* 49 */       this.view = LayoutInflater.from(getContext()).inflate(R.layout.___input_user_name, (ViewGroup)this);
/* 50 */       this.IvLog = (ImageView)this.view.findViewById(R.id.__iv_log);
/* 51 */       this.IvLog.setBackgroundResource(R.drawable.__ic_input_user);
/* 52 */       this.editText = (EditText)this.view.findViewById(R.id.__et_input);
/* 53 */       this.editText.clearFocus();
/* 54 */       setHint("请输入昵称");
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String getText() {
/* 60 */     if (this.editText.getText() == null) {
/* 61 */       return "";
/*    */     }
/* 63 */     return this.editText.getText().toString();
/*    */   }
/*    */ 
/*    */   
/* 67 */   public void setText(int str) { this.editText.setText(str); }
/*    */ 
/*    */ 
/*    */   
/* 71 */   public void setText(String str) { this.editText.setText(str); }
/*    */ 
/*    */ 
/*    */   
/* 75 */   public void setEnable(boolean b) { this.editText.setEnabled(b); }
/*    */ 
/*    */ 
/*    */   
/* 79 */   public void setHint(String str) { this.editText.setHint(str); }
/*    */ 
/*    */   
/* 82 */   public void setHint(int str) { this.editText.setHint(str); }
/*    */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\inputeditext\UserInputEditText.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */