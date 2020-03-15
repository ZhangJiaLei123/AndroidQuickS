/*     */ package com.blxt.quickview.textview;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Paint;
/*     */ import android.util.AttributeSet;
/*     */ import android.view.View;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AutoTextView
/*     */   extends View
/*     */ {
/*     */   private Context context;
/*     */   private int canvasWidth;
/*     */   private int canvasHeight;
/*     */   private Paint paint;
/*  21 */   private int maxTextSize = 50;
/*  22 */   private String text = "";
/*     */   public AutoTextView(Context context) {
/*  24 */     super(context);
/*  25 */     this.context = context;
/*  26 */     initPaint();
/*     */   }
/*     */   
/*     */   public AutoTextView(Context context, AttributeSet attrs) {
/*  30 */     super(context, attrs);
/*  31 */     this.context = context;
/*  32 */     initPaint();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void initPaint() {
/*  38 */     this.paint = new Paint(1);
/*     */     
/*  40 */     this.paint.setStrokeWidth(1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AutoTextView setText(String text) {
/*  48 */     this.text = text;
/*  49 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AutoTextView setMaxTextSize(int size) {
/*  57 */     this.maxTextSize = size;
/*  58 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onSizeChanged(int w, int h, int oldw, int oldh) {
/*  63 */     super.onSizeChanged(w, h, oldw, oldh);
/*     */     
/*  65 */     this.canvasWidth = getWidth();
/*  66 */     this.canvasHeight = getHeight();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   protected void onDraw(Canvas canvas) { drawText(canvas); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawText(Canvas canvas) {
/*  78 */     float textSize = getRightSize();
/*     */     
/*  80 */     this.paint.setTextSize(sp2px(textSize));
/*     */ 
/*     */     
/*  83 */     int x = (int)((this.canvasWidth / 2) - this.paint.measureText(this.text) / 2.0F);
/*     */ 
/*     */ 
/*     */     
/*  87 */     int y = (int)((this.canvasHeight / 2) - (this.paint.descent() + this.paint.ascent()) / 2.0F);
/*     */ 
/*     */     
/*  90 */     canvas.drawText(this.text, x, y, this.paint);
/*     */   }
/*     */ 
/*     */   
/*     */   private float getRightSize() {
/*  95 */     this.paint.setTextSize(sp2px(this.maxTextSize));
/*  96 */     this.paint.setTextAlign(Paint.Align.LEFT);
/*     */     
/*  98 */     float preWidth = this.paint.measureText(this.text);
/*     */     
/* 100 */     if (preWidth < this.canvasWidth) {
/* 101 */       return this.maxTextSize;
/*     */     }
/*     */     
/* 104 */     return (this.maxTextSize * this.canvasWidth) / preWidth;
/*     */   }
/*     */ 
/*     */   
/*     */   private int sp2px(float spValue) {
/* 109 */     float scale = (this.context.getResources().getDisplayMetrics()).density;
/* 110 */     return (int)(spValue * scale + 0.5F);
/*     */   }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\textview\AutoTextView.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */