/*     */ package com.blxt.quickview.textview;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import android.content.Context;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Paint;
/*     */ import android.text.TextPaint;
/*     */ import android.text.TextUtils;
/*     */ import android.util.AttributeSet;
/*     */ import android.view.ViewGroup;
/*     */ import android.view.ViewTreeObserver;
/*     */ import android.widget.TextView;
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
/*     */ @SuppressLint({"AppCompatCustomView"})
/*     */ public class AlignTextView
/*     */   extends TextView
/*     */ {
/*     */   private float textSize;
/*     */   private float textLineHeight;
/*     */   private int top;
/*     */   private int y;
/*     */   private int lines;
/*     */   private int bottom;
/*     */   private int right;
/*     */   private int left;
/*     */   private int lineDrawWords;
/*     */   private char[] textCharArray;
/*     */   private float singleWordWidth;
/*     */   private float lineSpacingExtra;
/*     */   private boolean isFirst = true;
/*     */   int maxLine;
/*     */   
/*     */   public AlignTextView(Context context, AttributeSet attrs, int defStyle) {
/*  44 */     super(context, attrs, defStyle);
/*  45 */     getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener()
/*     */         {
/*     */           public boolean onPreDraw() {
/*  48 */             AlignTextView.this.initTextInfo();
/*  49 */             return true;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*  55 */   public AlignTextView(Context context, AttributeSet attrs) { this(context, attrs, 0); }
/*     */ 
/*     */ 
/*     */   
/*  59 */   public AlignTextView(Context context) { this(context, null, 0); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initTextInfo() {
/*  64 */     this.textSize = getTextSize();
/*     */     
/*  66 */     this.textLineHeight = getLineHeight();
/*  67 */     this.left = 0;
/*  68 */     this.right = getRight();
/*  69 */     this.y = getTop();
/*     */     
/*  71 */     int drawTotalWidth = this.right - this.left;
/*  72 */     String text = getText().toString();
/*  73 */     if (!TextUtils.isEmpty(text) && this.isFirst) {
/*  74 */       this.textCharArray = text.toCharArray();
/*  75 */       TextPaint mTextPaint = new TextPaint(1);
/*  76 */       mTextPaint.density = (getResources().getDisplayMetrics()).density;
/*  77 */       mTextPaint.setTextSize(this.textSize);
/*     */       
/*  79 */       this.singleWordWidth = mTextPaint.measureText("一") + this.lineSpacingExtra;
/*     */       
/*  81 */       this.lineDrawWords = (int)(drawTotalWidth / this.singleWordWidth);
/*  82 */       int length = this.textCharArray.length;
/*  83 */       this.lines = length / this.lineDrawWords;
/*  84 */       if (length % this.lineDrawWords > 0) {
/*  85 */         this.lines++;
/*     */       }
/*     */       
/*  88 */       ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams)getLayoutParams();
/*  89 */       int totalHeight = (int)(this.lines * this.textLineHeight + this.textLineHeight * 2.0F + getPaddingBottom() + getPaddingTop() + layoutParams.bottomMargin + layoutParams.topMargin);
/*  90 */       setHeight(totalHeight);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onDraw(Canvas canvas) {
/*  96 */     this.bottom = getBottom();
/*  97 */     int drawTotalLine = this.lines;
/*     */     
/*  99 */     if (this.maxLine != 0 && drawTotalLine > this.maxLine) {
/* 100 */       drawTotalLine = this.maxLine;
/*     */     }
/*     */     
/* 103 */     for (int i = 0; i < drawTotalLine; i++) {
/*     */       try {
/* 105 */         int length = this.textCharArray.length;
/* 106 */         int mLeft = this.left;
/*     */         
/* 108 */         int startIndex = i * 1 * this.lineDrawWords;
/*     */         
/* 110 */         int endTextIndex = startIndex + this.lineDrawWords;
/* 111 */         if (endTextIndex > length) {
/* 112 */           endTextIndex = length;
/* 113 */           this.y = (int)(this.y + this.textLineHeight);
/*     */         } else {
/* 115 */           this.y = (int)(this.y + this.textLineHeight);
/*     */         } 
/* 117 */         for (; startIndex < endTextIndex; startIndex++) {
/* 118 */           char c = this.textCharArray[startIndex];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 124 */           canvas.drawText(String.valueOf(c), mLeft, this.y, (Paint)getPaint());
/* 125 */           mLeft = (int)(mLeft + this.singleWordWidth);
/*     */         } 
/* 127 */       } catch (Exception e) {
/* 128 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 136 */   public void setMaxLines(int max) { this.maxLine = max; }
/*     */ 
/*     */ 
/*     */   
/* 140 */   public void setLineSpacingExtra(int lineSpacingExtra) { this.lineSpacingExtra = lineSpacingExtra; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean containChinese(String string) {
/* 148 */     boolean flag = false;
/* 149 */     for (int i = 0; i < string.length(); i++) {
/* 150 */       char c = string.charAt(i);
/* 151 */       if (c >= '一' && c <= '龥') {
/* 152 */         flag = true;
/*     */       }
/*     */     } 
/* 155 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String ToDBC(String input) {
/* 161 */     char[] c = input.toCharArray();
/* 162 */     for (int i = 0; i < c.length; i++) {
/* 163 */       if (c[i] == ' ') {
/* 164 */         c[i] = '　';
/* 165 */       } else if (c[i] < '') {
/* 166 */         c[i] = (char)(c[i] + 65248);
/*     */       } 
/*     */     } 
/* 169 */     return new String(c);
/*     */   }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\textview\AlignTextView.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */