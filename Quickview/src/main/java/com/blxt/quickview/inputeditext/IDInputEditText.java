/*     */ package com.blxt.quickview.inputeditext;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import android.content.Context;
/*     */ import android.text.method.DigitsKeyListener;
/*     */ import android.text.method.KeyListener;
/*     */ import android.util.AttributeSet;
/*     */ import android.view.LayoutInflater;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import android.widget.EditText;
/*     */ import android.widget.ImageView;
/*     */ import android.widget.LinearLayout;
/*     */ import com.blxt.quickview.R;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IDInputEditText
/*     */   extends LinearLayout
/*     */ {
/*     */   private ImageView IvLog;
/*     */   private EditText editText;
/*  29 */   View view = null;
/*     */   String regular;
/*     */   
/*  32 */   public IDInputEditText(Context context) { super(context);
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
/*  52 */     this.regular = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890"; initUI(); } public IDInputEditText(Context context, AttributeSet attrs) { super(context, attrs); this.regular = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890"; initUI(); } public IDInputEditText(Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); this.regular = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890"; initUI(); } @SuppressLint({"NewApi"}) public IDInputEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) { super(context, attrs, defStyleAttr, defStyleRes); this.regular = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
/*     */     initUI(); } public void initUI() {
/*  54 */     if (this.view == null) {
/*  55 */       this.view = LayoutInflater.from(getContext()).inflate(R.layout.___input_user_name, (ViewGroup)this);
/*  56 */       this.IvLog = (ImageView)this.view.findViewById(R.id.__iv_log);
/*  57 */       this.IvLog.setBackgroundResource(R.drawable.__id_input);
/*  58 */       this.editText = (EditText)this.view.findViewById(R.id.__et_input);
/*  59 */       this.editText.clearFocus();
/*  60 */       this.editText.setInputType(96);
/*     */       
/*  62 */       this.editText.setKeyListener((KeyListener)new DigitsKeyListener()
/*     */           {
/*     */             public int getInputType() {
/*  65 */               return 96;
/*     */             }
/*     */ 
/*     */             
/*     */             protected char[] getAcceptedChars() {
/*  70 */               char[] ac = IDInputEditText.this.regular.toCharArray();
/*  71 */               return ac;
/*     */             }
/*     */           });
/*     */ 
/*     */ 
/*     */       
/*  77 */       setHint("请输入用户名");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getText() {
/*  83 */     if (this.editText.getText() == null) {
/*  84 */       return "";
/*     */     }
/*  86 */     return this.editText.getText().toString();
/*     */   }
/*     */ 
/*     */   
/*  90 */   public void setText(int str) { this.editText.setText(str); }
/*     */ 
/*     */ 
/*     */   
/*  94 */   public void setText(String str) { this.editText.setText(str); }
/*     */ 
/*     */ 
/*     */   
/*  98 */   public void setHint(String str) { this.editText.setHint(str); }
/*     */ 
/*     */   
/* 101 */   public void setHint(int str) { this.editText.setHint(str); }
/*     */ 
/*     */ 
/*     */   
/* 105 */   public void setEnable(boolean b) { this.editText.setEnabled(b); }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\inputeditext\IDInputEditText.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */