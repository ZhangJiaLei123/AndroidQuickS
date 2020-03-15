/*     */ package com.blxt.quickview.inputeditext;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import android.content.Context;
/*     */ import android.text.Editable;
/*     */ import android.text.InputFilter;
/*     */ import android.text.TextWatcher;
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
/*     */ public class PhoneInputEditText
/*     */   extends LinearLayout
/*     */ {
/*     */   private ImageView IvLog;
/*     */   private EditText editText;
/*  27 */   View view = null;
/*     */   
/*     */   public PhoneInputEditText(Context context) {
/*  30 */     super(context);
/*  31 */     initUI();
/*     */   }
/*     */   
/*     */   public PhoneInputEditText(Context context, AttributeSet attrs) {
/*  35 */     super(context, attrs);
/*  36 */     initUI();
/*     */   }
/*     */   
/*     */   public PhoneInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
/*  40 */     super(context, attrs, defStyleAttr);
/*  41 */     initUI();
/*     */   }
/*     */   
/*     */   @SuppressLint({"NewApi"})
/*     */   public PhoneInputEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
/*  46 */     super(context, attrs, defStyleAttr, defStyleRes);
/*  47 */     initUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void initUI() {
/*  52 */     if (this.view == null) {
/*  53 */       this.view = LayoutInflater.from(getContext()).inflate(R.layout.___input_user_name, (ViewGroup)this);
/*  54 */       this.IvLog = (ImageView)this.view.findViewById(R.id.__iv_log);
/*  55 */       this.IvLog.setBackgroundResource(R.drawable.__ic_phone);
/*  56 */       this.editText = (EditText)this.view.findViewById(R.id.__et_input);
/*  57 */       this.editText.clearFocus();
/*  58 */       setHint("请输入电话号码");
/*  59 */       this.editText.setFilters(new InputFilter[] { (InputFilter)new InputFilter.LengthFilter(13) });
/*  60 */       this.editText.setInputType(3);
/*     */       
/*  62 */       this.editText.addTextChangedListener(new TextWatcher()
/*     */           {
/*     */             public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
/*     */ 
/*     */             
/*     */             public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
/*  68 */               if (charSequence == null || charSequence.length() == 0)
/*     */                 return; 
/*  70 */               StringBuilder stringBuilder = new StringBuilder();
/*  71 */               for (int i = 0; i < charSequence.length(); i++) {
/*  72 */                 if (i == 3 || i == 8 || charSequence.charAt(i) != ' ') {
/*     */ 
/*     */                   
/*  75 */                   stringBuilder.append(charSequence.charAt(i));
/*  76 */                   if ((stringBuilder.length() == 4 || stringBuilder.length() == 9) && stringBuilder
/*  77 */                     .charAt(stringBuilder.length() - 1) != ' ') {
/*  78 */                     stringBuilder.insert(stringBuilder.length() - 1, ' ');
/*     */                   }
/*     */                 } 
/*     */               } 
/*  82 */               if (!stringBuilder.toString().equals(charSequence.toString())) {
/*  83 */                 int index = start + 1;
/*  84 */                 if (stringBuilder.charAt(start) == ' ') {
/*  85 */                   if (before == 0) {
/*  86 */                     index++;
/*     */                   } else {
/*  88 */                     index--;
/*     */                   }
/*     */                 
/*  91 */                 } else if (before == 1) {
/*  92 */                   index--;
/*     */                 } 
/*     */                 
/*  95 */                 PhoneInputEditText.this.editText.setText(stringBuilder.toString());
/*  96 */                 PhoneInputEditText.this.editText.setSelection(index);
/*     */               } 
/*     */             }
/*     */ 
/*     */             
/*     */             public void afterTextChanged(Editable s) {}
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getText() {
/* 108 */     if (this.editText.getText() == null) {
/* 109 */       return "";
/*     */     }
/* 111 */     return this.editText.getText().toString().replace(" ", "");
/*     */   }
/*     */ 
/*     */   
/* 115 */   public void setText(int str) { this.editText.setText(str); }
/*     */ 
/*     */ 
/*     */   
/* 119 */   public void setText(String str) { this.editText.setText(str); }
/*     */ 
/*     */ 
/*     */   
/* 123 */   public void setHint(String str) { this.editText.setHint(str); }
/*     */ 
/*     */   
/* 126 */   public void setHint(int str) { this.editText.setHint(str); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 131 */   public void setEnable(boolean b) { this.editText.setEnabled(b); }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\inputeditext\PhoneInputEditText.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */