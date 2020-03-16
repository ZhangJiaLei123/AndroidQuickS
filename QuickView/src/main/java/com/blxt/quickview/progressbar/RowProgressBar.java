/*     */ package com.blxt.quickview.progressbar;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.res.TypedArray;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Paint;
/*     */ import android.graphics.RectF;
/*     */ import android.text.TextUtils;
/*     */ import android.util.AttributeSet;
/*     */ import android.view.View;
/*     */ import com.blxt.quickview.R;
/*     */ import java.text.DecimalFormat;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RowProgressBar
/*     */   extends View
/*     */ {
/*  43 */   private float mMax = 100.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   private float mProgress = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   private final int DEFAULT_FINISHED_COLOR = 15948121;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   private final int DEFAULT_UNFINISHED_COLOR = 10384757;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int mReachedBarColor;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int mUnreachedBarColor;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float mBarHeight;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int mTextColor;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   private String mSuffix = "%";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   private String mPrefix = "";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   private RectF mUnreachedRectF = new RectF(0.0F, 0.0F, 0.0F, 0.0F);
/*     */ 
/*     */ 
/*     */   
/*  97 */   private RectF mReachedRectF = new RectF(0.0F, 0.0F, 0.0F, 0.0F);
/*     */ 
/*     */ 
/*     */   
/*     */   private Paint mPaint;
/*     */ 
/*     */   
/*     */   private boolean mTextVisibility;
/*     */ 
/*     */ 
/*     */   
/* 108 */   public RowProgressBar(Context context) { this(context, null); }
/*     */ 
/*     */ 
/*     */   
/* 112 */   public RowProgressBar(Context context, AttributeSet attrs) { this(context, attrs, 0); }
/*     */ 
/*     */   
/*     */   public RowProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
/* 116 */     super(context, attrs, defStyleAttr);
/* 117 */     initAttrs(context, attrs);
/* 118 */     initPainters();
/*     */   }
/*     */   
/*     */   private void initAttrs(Context context, AttributeSet attrs) {
/* 122 */     TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RowProgressBar);
/* 123 */     this.mMax = typedArray.getInteger(R.styleable.RowProgressBar_updataAPPMax, (int)this.mMax);
/* 124 */     this.mProgress = typedArray.getInteger(R.styleable.RowProgressBar_updataAPPProgress, (int)this.mProgress);
/* 125 */     this.mReachedBarColor = typedArray.getColor(R.styleable.RowProgressBar_rowProgressBarColor, 15948121);
/* 126 */     this.mUnreachedBarColor = typedArray.getColor(R.styleable.RowProgressBar_rowUnProgressBarColor, 10384757);
/* 127 */     this.mTextColor = typedArray.getColor(R.styleable.RowProgressBar_updataAPPTextColor, 10384757);
/* 128 */     this.mSuffix = TextUtils.isEmpty(typedArray.getString(R.styleable.RowProgressBar_updataAPPSuffix)) ? this.mSuffix : typedArray.getString(R.styleable.RowProgressBar_updataAPPSuffix);
/* 129 */     this.mPrefix = TextUtils.isEmpty(typedArray.getString(R.styleable.RowProgressBar_updataAPPPrefix)) ? this.mPrefix : typedArray.getString(R.styleable.RowProgressBar_updataAPPPrefix);
/* 130 */     this.mBarHeight = typedArray.getDimension(R.styleable.RowProgressBar_updataAPPBarHeight, dp2px(2.0F));
/* 131 */     this.mTextVisibility = typedArray.getBoolean(R.styleable.RowProgressBar_updataAPPTextVisibility, true);
/* 132 */     typedArray.recycle();
/*     */   }
/*     */   
/*     */   private void initPainters() {
/* 136 */     this.mPaint = new Paint(1);
/* 137 */     this.mPaint.setAntiAlias(true);
/* 138 */     this.mPaint.setStrokeCap(Paint.Cap.ROUND);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onDraw(Canvas canvas) {
/* 143 */     super.onDraw(canvas);
/* 144 */     calculateDrawRectFWithoutProgressText();
/* 145 */     this.mPaint.setColor(this.mUnreachedBarColor);
/* 146 */     canvas.drawRoundRect(this.mUnreachedRectF, this.mBarHeight / 2.0F, this.mBarHeight / 2.0F, this.mPaint);
/*     */     
/* 148 */     this.mPaint.setColor(this.mReachedBarColor);
/* 149 */     canvas.drawRoundRect(this.mReachedRectF, this.mBarHeight / 2.0F, this.mBarHeight / 2.0F, this.mPaint);
/*     */ 
/*     */     
/* 152 */     this.mPaint.setColor(this.mTextColor);
/* 153 */     this.mPaint.setTextSize(this.mBarHeight * 0.6F);
/* 154 */     String mCurrentDrawText = (new DecimalFormat("#")).format((getProgress() * 100.0F / getMax()));
/* 155 */     mCurrentDrawText = this.mPrefix + mCurrentDrawText + this.mSuffix;
/* 156 */     float mDrawTextWidth = this.mPaint.measureText(mCurrentDrawText);
/* 157 */     if (this.mTextVisibility && getProgress() > 0.0F && this.mReachedRectF.right > mDrawTextWidth) {
/* 158 */       canvas.drawText(mCurrentDrawText, this.mReachedRectF.right - mDrawTextWidth - this.mBarHeight * 0.4F, (int)(getHeight() / 2.0F - (this.mPaint.descent() + this.mPaint.ascent()) / 2.0F), this.mPaint);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void calculateDrawRectFWithoutProgressText() {
/* 165 */     this.mReachedRectF.left = getPaddingLeft();
/* 166 */     this.mReachedRectF.top = getHeight() / 2.0F - this.mBarHeight / 2.0F;
/* 167 */     this.mReachedRectF.right = (getWidth() - getPaddingLeft() - getPaddingRight()) / getMax() * 1.0F * getProgress() + getPaddingLeft();
/* 168 */     this.mReachedRectF.bottom = getHeight() / 2.0F + this.mBarHeight / 2.0F;
/*     */     
/* 170 */     this.mUnreachedRectF.left = getPaddingLeft();
/* 171 */     this.mUnreachedRectF.top = getHeight() / 2.0F + -this.mBarHeight / 2.0F;
/* 172 */     this.mUnreachedRectF.right = (getWidth() - getPaddingRight());
/* 173 */     this.mUnreachedRectF.bottom = getHeight() / 2.0F + this.mBarHeight / 2.0F;
/*     */   }
/*     */ 
/*     */   
/* 177 */   public float getMax() { return this.mMax; }
/*     */ 
/*     */ 
/*     */   
/* 181 */   public float getProgress() { return this.mProgress; }
/*     */ 
/*     */   
/*     */   public void setMax(int max) {
/* 185 */     this.mMax = max;
/* 186 */     invalidate();
/*     */   }
/*     */   
/*     */   public void setProgress(float progress) {
/* 190 */     this.mProgress = checkProgress(progress);
/* 191 */     invalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   private float checkProgress(float progress) {
/* 196 */     if (progress < 0.0F) return 0.0F; 
/* 197 */     return (progress > this.mMax) ? this.mMax : progress;
/*     */   }
/*     */   
/*     */   private int dp2px(float dpValue) {
/* 201 */     float scale = (getContext().getResources().getDisplayMetrics()).density;
/* 202 */     return (int)(dpValue * scale + 0.5F);
/*     */   }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\progressbar\RowProgressBar.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */