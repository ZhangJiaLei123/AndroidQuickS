/*     */ package com.blxt.quickview.progressbar;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import android.content.Context;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Paint;
/*     */ import android.util.AttributeSet;
/*     */ import android.view.View;
/*     */ import androidx.annotation.Nullable;
/*     */ import com.blxt.quickview.AttributeHelper;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProgressLine
/*     */   extends View
/*     */ {
/*  21 */   AttributeHelper attributeHelper = null;
/*  22 */   private Paint mPaint = null;
/*  23 */   private int iWidth = 0;
/*  24 */   private int iHeight = 0;
/*     */   
/*  26 */   List<ProgressItem> progressItems = null;
/*     */ 
/*     */   
/*  29 */   public ProgressLine(Context context) { super(context); }
/*     */ 
/*     */   
/*     */   public ProgressLine(Context context, @Nullable AttributeSet attrs) {
/*  33 */     super(context, attrs);
/*  34 */     init(attrs);
/*     */   }
/*     */   
/*     */   public ProgressLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
/*  38 */     super(context, attrs, defStyleAttr);
/*  39 */     init(attrs);
/*     */   }
/*     */   
/*     */   @SuppressLint({"NewApi"})
/*     */   public ProgressLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
/*  44 */     super(context, attrs, defStyleAttr, defStyleRes);
/*  45 */     init(attrs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void init(AttributeSet attrs) {
/*  53 */     if (this.attributeHelper != null) {
/*     */       return;
/*     */     }
/*  56 */     this.attributeHelper = new AttributeHelper(getContext(), attrs);
/*  57 */     this.mPaint = new Paint();
/*  58 */     this.mPaint.setColor(this.attributeHelper.getColor("background", -6710887));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   protected void onFinishInflate() { super.onFinishInflate(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onDraw(Canvas canvas) {
/*  73 */     super.onDraw(canvas);
/*  74 */     this.iWidth = getWidth();
/*  75 */     this.iHeight = getHeight();
/*     */     
/*  77 */     initProgressItems();
/*  78 */     onDrawProgressItems(canvas);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initProgressItems() {
/*  85 */     if (this.progressItems != null) {
/*     */       return;
/*     */     }
/*  88 */     this.progressItems = new ArrayList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void onDrawProgressItems(Canvas canvas) {
/*  96 */     Paint mPaint = new Paint();
/*     */     
/*  98 */     for (ProgressItem progressItem : this.progressItems) {
/*  99 */       int w = (int)(this.iWidth * progressItem.progress);
/* 100 */       mPaint.setColor(progressItem.color);
/* 101 */       canvas.drawLine(0.0F, 0.0F, w, this.iHeight, mPaint);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addProgressItem(String tag, float progress, int color) {
/* 112 */     initProgressItems();
/*     */     
/* 114 */     this.progressItems.add(new ProgressItem(tag, progress, color));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   class ProgressItem
/*     */   {
/* 121 */     String tag = "";
/* 122 */     float progress = 0.0F;
/* 123 */     int color = 0;
/*     */     
/*     */     public ProgressItem(String tag, float progress, int color) {
/* 126 */       this.tag = tag;
/* 127 */       this.progress = progress;
/* 128 */       this.color = color;
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\progressbar\ProgressLine.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */