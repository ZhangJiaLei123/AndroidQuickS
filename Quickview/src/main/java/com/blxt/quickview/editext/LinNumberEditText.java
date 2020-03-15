/*     */ package com.blxt.quickview.editext;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import android.content.Context;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Paint;
/*     */ import android.util.AttributeSet;
/*     */ import android.widget.EditText;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LinNumberEditText
/*     */   extends EditText
/*     */ {
/*     */   private boolean isDrawLine = true;
/*     */   private Paint line;
/*     */   private Paint line_row;
/*  22 */   private int textlines = 2;
/*     */   
/*     */   @SuppressLint({"ResourceAsColor"})
/*     */   public LinNumberEditText(Context context, AttributeSet As) {
/*  26 */     super(context, As);
/*  27 */     if (this.isDrawLine) {
/*  28 */       setFocusable(true);
/*  29 */       this.line = new Paint();
/*  30 */       this.line.setColor(-7829368);
/*  31 */       this.line.setStrokeWidth(2.0F);
/*     */       
/*  33 */       this.line_row = new Paint();
/*  34 */       this.line_row.setColor(-7829368);
/*  35 */       this.line_row.setStrokeWidth(4.0F);
/*  36 */       int w = (int)getTextSize() * this.textlines;
/*  37 */       setPadding(w + 10, 0, 0, 10);
/*  38 */       setGravity(48);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SuppressLint({"ResourceAsColor"})
/*     */   protected void onDraw(Canvas canvas) {
/*  46 */     if (this.isDrawLine) {
/*  47 */       if (getLineCount() >= 1000) {
/*  48 */         this.textlines = 3;
/*     */       }
/*     */ 
/*     */       
/*  52 */       if (getText().toString().length() != 0) {
/*  53 */         float y = 0.0F;
/*  54 */         Paint p = new Paint();
/*     */         
/*  56 */         p.setTextSize(getTextSize());
/*  57 */         for (int l = 0; l < getLineCount(); l++) {
/*  58 */           y = ((l + 1) * getLineHeight() - getLineHeight() / 4);
/*  59 */           canvas.drawText(String.valueOf(l + 1), 0.0F, y, p);
/*  60 */           canvas.save();
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/*  65 */       int k = getLineHeight();
/*  66 */       int i = getLineCount();
/*  67 */       int w = (int)getTextSize() * this.textlines;
/*     */ 
/*     */       
/*  70 */       canvas.drawLine(w, 0.0F, w, (getHeight() + i * k), this.line);
/*  71 */       int y = (getLayout().getLineForOffset(getSelectionStart()) + 1) * k;
/*     */       
/*  73 */       canvas.drawLine(0.0F, (y - 1), w, (y - 1), this.line_row);
/*  74 */       canvas.save();
/*  75 */       canvas.restore();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  80 */     super.onDraw(canvas);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LinNumberEditText setLineColor(int color) {
/*  90 */     this.line.setColor(color);
/*  91 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LinNumberEditText setRowColor(int color) {
/* 100 */     this.line_row.setColor(color);
/* 101 */     return this;
/*     */   }
/*     */ 
/*     */   
/* 105 */   public boolean isDrawLine() { return this.isDrawLine; }
/*     */ 
/*     */ 
/*     */   
/* 109 */   public void setDrawLine(boolean drawLine) { this.isDrawLine = drawLine; }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\editext\LinNumberEditText.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */