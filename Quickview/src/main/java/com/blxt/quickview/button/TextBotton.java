/*     */ package com.blxt.quickview.button;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import android.content.Context;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import android.util.AttributeSet;
/*     */ import android.widget.Button;
/*     */ import com.blxt.quickview.AttributeHelper;
/*     */ import com.blxt.quickview.R;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SuppressLint({"AppCompatCustomView"})
/*     */ public class TextBotton
/*     */   extends Button
/*     */ {
/*  20 */   AttributeHelper attributeHelper = null;
/*     */   
/*  22 */   int textColorOn = getResources().getColor(R.color.btn_text_on);
/*  23 */   int textColorOff = getResources().getColor(R.color.btn_text_off);
/*     */   
/*  25 */   int resIdOn = R.drawable.__roll;
/*  26 */   int resIdOff = R.drawable.__roll_off;
/*     */   
/*  28 */   int locationDef = 2;
/*     */   
/*     */   private boolean isCheck = false;
/*     */ 
/*     */   
/*  33 */   public TextBotton(Context context) { super(context); }
/*     */ 
/*     */ 
/*     */   
/*  37 */   public TextBotton(Context context, AttributeSet attrs) { this(context, attrs, 0); }
/*     */ 
/*     */ 
/*     */   
/*     */   public TextBotton(Context context, AttributeSet attrs, int defStyle) {
/*  42 */     super(context, attrs, defStyle);
/*  43 */     init(attrs);
/*  44 */     setCheck(this.isCheck);
/*     */   }
/*     */   
/*     */   @SuppressLint({"ResourceType"})
/*     */   private void init(AttributeSet attrs) {
/*  49 */     if (this.attributeHelper != null) {
/*     */       return;
/*     */     }
/*  52 */     this.attributeHelper = new AttributeHelper(getContext(), attrs);
/*     */     
/*  54 */     this.textColorOn = getTextColors().getDefaultColor();
/*  55 */     this.textColorOff = getHintTextColors().getDefaultColor();
/*  56 */     setCheck(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCheck(boolean isCheck) {
/*  63 */     this.isCheck = isCheck;
/*     */     
/*  65 */     int color = this.textColorOn;
/*  66 */     int image = this.resIdOn;
/*  67 */     if (!isCheck) {
/*  68 */       color = this.textColorOff;
/*  69 */       image = this.resIdOff;
/*     */     } 
/*     */     
/*  72 */     setTextColor(color);
/*  73 */     setBtnBack(image);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   public void setHintImageOn(int resId) { this.resIdOn = resId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public void setHintImageOff(int resId) { this.resIdOff = resId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   public void setTextColorOn(int textColorOn) { this.textColorOn = textColorOn; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   public void setTextColorOff(int textColorOff) { this.textColorOff = textColorOff; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   public void setLocationDef(int locationDef) { this.locationDef = locationDef; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setBtnBack(int resId) {
/* 119 */     Drawable drawable = getResources().getDrawable(resId);
/*     */     
/* 121 */     drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
/* 122 */     setCompoundDrawables(null, null, null, drawable);
/*     */   }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\button\TextBotton.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */