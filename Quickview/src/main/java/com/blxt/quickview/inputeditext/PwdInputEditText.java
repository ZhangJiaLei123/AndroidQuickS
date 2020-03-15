/*     */ package com.blxt.quickview.inputeditext;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import android.content.Context;
/*     */ import android.text.method.DigitsKeyListener;
/*     */ import android.text.method.HideReturnsTransformationMethod;
/*     */ import android.text.method.KeyListener;
/*     */ import android.text.method.PasswordTransformationMethod;
/*     */ import android.text.method.TransformationMethod;
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
/*     */ public class PwdInputEditText
/*     */   extends LinearLayout
/*     */ {
/*  23 */   private View view = null;
/*     */   private ImageView IvLog;
/*     */   private EditText editText;
/*     */   private ImageView IvEye;
/*     */   boolean isEyes = false;
/*     */   String regular;
/*     */   
/*  30 */   public PwdInputEditText(Context context) { super(context);
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
/*  51 */     this.regular = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890.-_+@#$%,[]`~·!"; initUI(); } public PwdInputEditText(Context context, AttributeSet attrs) { super(context, attrs); this.regular = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890.-_+@#$%,[]`~·!"; initUI(); } public PwdInputEditText(Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); this.regular = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890.-_+@#$%,[]`~·!"; initUI(); } @SuppressLint({"NewApi"}) public PwdInputEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) { super(context, attrs, defStyleAttr, defStyleRes); this.regular = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890.-_+@#$%,[]`~·!";
/*     */     initUI(); }
/*     */    public void initUI() {
/*  54 */     if (this.view == null) {
/*  55 */       this.view = LayoutInflater.from(getContext()).inflate(R.layout.___input_user_pwd, (ViewGroup)this);
/*  56 */       this.IvLog = (ImageView)this.view.findViewById(R.id.__iv_log);
/*  57 */       this.editText = (EditText)this.view.findViewById(R.id.__et_input);
/*  58 */       this.IvEye = (ImageView)this.view.findViewById(R.id._iv_eye);
/*  59 */       this.editText.clearFocus();
/*  60 */       this.editText.setKeyListener((KeyListener)new DigitsKeyListener()
/*     */           {
/*     */             public int getInputType() {
/*  63 */               return 128;
/*     */             }
/*     */             
/*     */             protected char[] getAcceptedChars() {
/*  67 */               char[] ac = PwdInputEditText.this.regular.toCharArray();
/*  68 */               return ac;
/*     */             }
/*     */           });
/*     */ 
/*     */       
/*  73 */       this.isEyes = false;
/*  74 */       setPwdModel(Boolean.valueOf(this.isEyes));
/*  75 */       this.IvEye.setOnClickListener(new View.OnClickListener()
/*     */           {
/*     */             public void onClick(View view) {
/*  78 */               PwdInputEditText.this.isEyes = !PwdInputEditText.this.isEyes;
/*  79 */               PwdInputEditText.this.setPwdModel(Boolean.valueOf(PwdInputEditText.this.isEyes));
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setPwdModel(Boolean fal) {
/*  87 */     if (fal.booleanValue()) {
/*  88 */       this.IvEye.setBackgroundResource(R.drawable.__eye_on);
/*  89 */       this.editText.setTransformationMethod((TransformationMethod)HideReturnsTransformationMethod.getInstance());
/*     */     } else {
/*     */       
/*  92 */       this.IvEye.setBackgroundResource(R.drawable.__eye_off);
/*  93 */       this.editText.setTransformationMethod((TransformationMethod)PasswordTransformationMethod.getInstance());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getText() {
/*  99 */     if (this.editText.getText() == null) {
/* 100 */       return "";
/*     */     }
/* 102 */     return this.editText.getText().toString();
/*     */   }
/*     */ 
/*     */   
/* 106 */   public void setText(int str) { this.editText.setText(str); }
/*     */ 
/*     */ 
/*     */   
/* 110 */   public void setText(String str) { this.editText.setText(str); }
/*     */ 
/*     */ 
/*     */   
/* 114 */   public void setHint(int str) { this.editText.setHint(str); }
/*     */ 
/*     */   
/* 117 */   public void setHint(String str) { this.editText.setHint(str); }
/*     */ 
/*     */ 
/*     */   
/* 121 */   public void setEnable(boolean b) { this.editText.setEnabled(b); }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\inputeditext\PwdInputEditText.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */