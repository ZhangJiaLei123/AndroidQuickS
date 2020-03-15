/*     */ package com.blxt.quickview.editext;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import android.content.Context;
/*     */ import android.text.Editable;
/*     */ import android.text.TextUtils;
/*     */ import android.text.method.HideReturnsTransformationMethod;
/*     */ import android.text.method.PasswordTransformationMethod;
/*     */ import android.text.method.TransformationMethod;
/*     */ import android.util.AttributeSet;
/*     */ import android.view.LayoutInflater;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import android.widget.EditText;
/*     */ import android.widget.ImageView;
/*     */ import android.widget.LinearLayout;
/*     */ import com.blxt.quickview.AttributeHelper;
/*     */ import com.blxt.quickview.R;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InputEditItem
/*     */   extends LinearLayout
/*     */   implements View.OnClickListener
/*     */ {
/*     */   public static final int ED_MODEL_TEXT = 0;
/*     */   public static final int ED_MODEL_PWD = 1;
/*  29 */   private View view = null;
/*     */   
/*     */   private ImageView ivTitle;
/*     */   
/*     */   private EditText editText;
/*     */   private ImageView ivBtn;
/*     */   AttributeHelper attributeHelper;
/*  36 */   int btnModel = 0;
/*     */   
/*  38 */   int imageResTitle = 0;
/*  39 */   int imageResA = R.mipmap.close;
/*  40 */   int imageResB = 0;
/*     */   
/*  42 */   View.OnClickListener onClickListener = null;
/*  43 */   TextChangeWatcher textChangeWatcher = null;
/*     */   
/*     */   public InputEditItem(Context context) {
/*  46 */     super(context);
/*  47 */     initUI();
/*     */   }
/*     */   
/*     */   public InputEditItem(Context context, AttributeSet attrs) {
/*  51 */     super(context, attrs);
/*  52 */     initUI();
/*  53 */     initAttrs(context, attrs);
/*     */   }
/*     */   
/*     */   public InputEditItem(Context context, AttributeSet attrs, int defStyleAttr) {
/*  57 */     super(context, attrs, defStyleAttr);
/*  58 */     initUI();
/*  59 */     initAttrs(context, attrs);
/*     */   }
/*     */   
/*     */   @SuppressLint({"NewApi"})
/*     */   public InputEditItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
/*  64 */     super(context, attrs, defStyleAttr, defStyleRes);
/*  65 */     initUI();
/*  66 */     initAttrs(context, attrs);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initUI() {
/*  71 */     if (this.view != null) {
/*     */       return;
/*     */     }
/*     */     
/*  75 */     this.view = LayoutInflater.from(getContext()).inflate(R.layout.___input_edititem, (ViewGroup)this);
/*  76 */     this.ivTitle = (ImageView)this.view.findViewById(R.id.__iv_log);
/*  77 */     this.editText = (EditText)this.view.findViewById(R.id.__et_input);
/*  78 */     this.ivBtn = (ImageView)this.view.findViewById(R.id.__iv_btn);
/*  79 */     this.editText.clearFocus();
/*     */ 
/*     */     
/*  82 */     this.ivBtn.setOnClickListener(this);
/*  83 */     this.editText.addTextChangedListener(new TextChangeWatcher()
/*     */         {
/*     */           public void afterTextChanged(Editable editable) {
/*  86 */             super.afterTextChanged(editable);
/*  87 */             InputEditItem.this.isShowIvBtn();
/*     */           }
/*     */ 
/*     */           
/*     */           public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
/*  92 */             if (InputEditItem.this.textChangeWatcher != null) {
/*  93 */               InputEditItem.this.textChangeWatcher.onTextChanged(charSequence, start, before, count);
/*     */             }
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initAttrs(Context context, AttributeSet attrs) {
/* 107 */     if (this.attributeHelper != null) {
/*     */       return;
/*     */     }
/* 110 */     this.attributeHelper = new AttributeHelper(context, attrs);
/*     */ 
/*     */ 
/*     */     
/* 114 */     int defaultTitleColor = context.getResources().getColor(R.color.item_group_title);
/*     */ 
/*     */     
/* 117 */     String content = this.attributeHelper.getString("text", "");
/* 118 */     float contentSize = this.attributeHelper.getInt("textSize", 13);
/*     */     
/* 120 */     String hintContent = this.attributeHelper.getString("hint", "");
/*     */     
/* 122 */     int contentColor = this.attributeHelper.getColor("textColor", defaultTitleColor);
/* 123 */     this.editText.setText(content);
/* 124 */     this.editText.setHint(hintContent);
/* 125 */     this.editText.setTextSize(contentSize);
/* 126 */     this.editText.setTextColor(contentColor);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getText() {
/* 132 */     if (this.editText.getText() == null) {
/* 133 */       return "";
/*     */     }
/* 135 */     return this.editText.getText().toString();
/*     */   }
/*     */ 
/*     */   
/* 139 */   public void setText(int str) { this.editText.setText(str); }
/*     */ 
/*     */ 
/*     */   
/* 143 */   public void setText(String str) { this.editText.setText(str); }
/*     */ 
/*     */ 
/*     */   
/* 147 */   public void setHint(int str) { this.editText.setHint(str); }
/*     */ 
/*     */ 
/*     */   
/* 151 */   public void setHint(String str) { this.editText.setHint(str); }
/*     */ 
/*     */   
/*     */   public void setEnable(boolean b) {
/* 155 */     this.editText.setEnabled(b);
/* 156 */     this.ivBtn.setVisibility(b ? 0 : 8);
/* 157 */     if (b) {
/* 158 */       isShowIvBtn();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setImageResTitle(int imageResTitle) {
/* 163 */     this.imageResTitle = imageResTitle;
/* 164 */     this.ivTitle.setBackgroundResource(imageResTitle);
/*     */   }
/*     */   
/*     */   public void setImageResA(int imageResA) {
/* 168 */     this.imageResA = imageResA;
/* 169 */     this.ivBtn.setBackgroundResource(imageResA);
/*     */   }
/*     */ 
/*     */   
/* 173 */   public void setImageResB(int imageResB) { this.imageResB = imageResB; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 178 */   public EditText getEditText() { return this.editText; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 183 */   public void setOnClickListener(View.OnClickListener onClickListener) { this.onClickListener = onClickListener; }
/*     */ 
/*     */ 
/*     */   
/* 187 */   public void setTextChangeWatcher(TextChangeWatcher textChangeWatcher) { this.textChangeWatcher = textChangeWatcher; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBtnModel(int btnModel) {
/* 196 */     this.btnModel = btnModel;
/* 197 */     if (btnModel == 0) {
/* 198 */       if (this.imageResA != 0) {
/* 199 */         this.ivBtn.setBackgroundResource(this.imageResA);
/*     */       }
/* 201 */       isShowIvBtn();
/*     */       return;
/*     */     } 
/* 204 */     if (btnModel > 0 && btnModel % 2 == 1) {
/* 205 */       if (this.imageResB != 0) {
/* 206 */         this.ivBtn.setBackgroundResource(this.imageResB);
/*     */       }
/* 208 */       this.editText.setTransformationMethod((TransformationMethod)PasswordTransformationMethod.getInstance());
/*     */     } else {
/* 210 */       if (this.imageResA != 0) {
/* 211 */         this.ivBtn.setBackgroundResource(this.imageResA);
/*     */       }
/* 213 */       this.editText.setTransformationMethod((TransformationMethod)HideReturnsTransformationMethod.getInstance());
/*     */     } 
/* 215 */     isShowIvBtn();
/* 216 */     this.btnModel++;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onClick(View v) {
/* 222 */     int id = v.getId();
/* 223 */     if (id == R.id.__iv_btn) {
/* 224 */       if (this.btnModel == 0) {
/* 225 */         this.editText.setText("");
/* 226 */         this.ivBtn.setVisibility(8);
/*     */       } else {
/* 228 */         setBtnModel(this.btnModel);
/*     */       } 
/*     */     }
/*     */     
/* 232 */     if (this.onClickListener != null) {
/* 233 */       this.onClickListener.onClick((View)this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isShowIvBtn() {
/* 244 */     String content = this.editText.getText().toString().trim();
/* 245 */     if (!TextUtils.isEmpty(content)) {
/*     */       
/* 247 */       this.ivBtn.setVisibility(0);
/* 248 */       return true;
/*     */     } 
/* 250 */     this.ivBtn.setVisibility(8);
/* 251 */     return false;
/*     */   }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\editext\InputEditItem.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */