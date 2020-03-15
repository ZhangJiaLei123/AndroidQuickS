/*     */ package com.blxt.quickview.progressbar;
/*     */ 
/*     */ import android.animation.Animator;
/*     */ import android.animation.TimeInterpolator;
/*     */ import android.animation.ValueAnimator;
/*     */ import android.content.Context;
/*     */ import android.content.res.TypedArray;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Color;
/*     */ import android.graphics.Paint;
/*     */ import android.graphics.Rect;
/*     */ import android.graphics.RectF;
/*     */ import android.os.Build;
/*     */ import android.text.TextUtils;
/*     */ import android.util.AttributeSet;
/*     */ import android.util.DisplayMetrics;
/*     */ import android.util.TypedValue;
/*     */ import android.view.View;
/*     */ import android.view.animation.LinearInterpolator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RoundProgressBar
/*     */   extends View
/*     */ {
/*     */   private Paint arcPaint;
/*     */   private RectF arcRect;
/*     */   private Paint textPaint;
/*     */   private int strokeWidth;
/*     */   private int strokeColor;
/*     */   private int progress;
/*     */   private int countDownTimeMillis;
/*     */   private Paint centerBgPaint;
/*     */   private int centerBackground;
/*     */   private String centerText;
/*  74 */   private String emptyText = "100%";
/*     */ 
/*     */ 
/*     */   
/*     */   private int centerTextColor;
/*     */ 
/*     */ 
/*     */   
/*     */   private float centerTextSize;
/*     */ 
/*     */ 
/*     */   
/*     */   private Rect textBounds;
/*     */ 
/*     */ 
/*     */   
/*     */   private int startAngle;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isAutoStart;
/*     */ 
/*     */ 
/*     */   
/*     */   private ProgressChangeListener mProgressChangeListener;
/*     */ 
/*     */   
/*     */   private Direction mDirection;
/*     */ 
/*     */   
/*     */   private int directionIndex;
/*     */ 
/*     */   
/* 107 */   private final Direction[] mDirections = new Direction[] { Direction.FORWARD, Direction.REVERSE };
/*     */   private ValueAnimator animator;
/*     */   private boolean shouldDrawOutsideWrapper;
/*     */   private int outsideWrapperColor;
/*     */   private int defaultSpace;
/*     */   private boolean isSupportEts;
/*     */   private long currentTime;
/*     */   
/*     */   public enum Direction {
/* 116 */     FORWARD(0),
/*     */ 
/*     */ 
/*     */     
/* 120 */     REVERSE(1);
/*     */     final int nativeInt;
/*     */     
/* 123 */     Direction(int ni) { this.nativeInt = ni; }
/*     */   }
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
/*     */   
/* 153 */   public RoundProgressBar(Context context) { this(context, null); }
/*     */ 
/*     */ 
/*     */   
/* 157 */   public RoundProgressBar(Context context, AttributeSet attrs) { this(context, attrs, 0); }
/*     */ 
/*     */   
/*     */   public RoundProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
/* 161 */     super(context, attrs, defStyleAttr);
/*     */     
/* 163 */     TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar);
/* 164 */     this.strokeWidth = a.getDimensionPixelSize(R.styleable.RoundProgressBar_rpb_sweepStrokeWidth, (int)dp2px(2.0F));
/* 165 */     this.strokeColor = a.getColor(R.styleable.RoundProgressBar_rpb_sweepStrokeColor, -16777216);
/* 166 */     this.startAngle = a.getInteger(R.styleable.RoundProgressBar_rpb_sweepStartAngle, -90);
/* 167 */     this.centerText = a.getString(R.styleable.RoundProgressBar_rpb_centerText);
/* 168 */     this.centerTextSize = a.getDimension(R.styleable.RoundProgressBar_rpb_centerTextSize, sp2px(12.0F));
/* 169 */     this.centerTextColor = a.getColor(R.styleable.RoundProgressBar_rpb_centerTextColor, -1);
/* 170 */     this.centerBackground = a.getColor(R.styleable.RoundProgressBar_rpb_centerBackgroundColor, Color.parseColor("#808080"));
/* 171 */     this.countDownTimeMillis = a.getInteger(R.styleable.RoundProgressBar_rpb_countDownTimeInMillis, 3000);
/* 172 */     this.directionIndex = a.getInt(R.styleable.RoundProgressBar_rpb_progressDirection, 0);
/* 173 */     this.isAutoStart = a.getBoolean(R.styleable.RoundProgressBar_rpb_autoStart, true);
/* 174 */     this.shouldDrawOutsideWrapper = a.getBoolean(R.styleable.RoundProgressBar_rpb_drawOutsideWrapper, false);
/* 175 */     this.outsideWrapperColor = a.getColor(R.styleable.RoundProgressBar_rpb_outsideWrapperColor, Color.parseColor("#E8E8E8"));
/* 176 */     this.isSupportEts = a.getBoolean(R.styleable.RoundProgressBar_rpb_supportEndToStart, false);
/* 177 */     a.recycle();
/* 178 */     this.defaultSpace = this.strokeWidth * 2;
/* 179 */     this.arcPaint = new Paint(5);
/* 180 */     this.arcPaint.setStyle(Paint.Style.STROKE);
/*     */     
/* 182 */     this.textPaint = new Paint(1);
/* 183 */     this.textPaint.setTextSize(this.centerTextSize);
/* 184 */     this.textPaint.setTextAlign(Paint.Align.CENTER);
/*     */     
/* 186 */     this.centerBgPaint = new Paint(5);
/* 187 */     this.centerBgPaint.setStyle(Paint.Style.FILL);
/*     */     
/* 189 */     this.arcRect = new RectF();
/* 190 */     this.textBounds = new Rect();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
/* 197 */     int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
/* 198 */     int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
/* 199 */     int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
/* 200 */     int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
/* 201 */     int minWidth = getMinWidth(widthMode, widthSize);
/* 202 */     int minHeight = getMinHeight(heightMode, heightSize);
/* 203 */     if (minWidth != minHeight) {
/* 204 */       int suggestedSize = Math.max(minWidth, minHeight);
/* 205 */       minWidth = suggestedSize;
/* 206 */       minHeight = suggestedSize;
/*     */     } 
/* 208 */     setMeasuredDimension(minWidth, minHeight);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onSizeChanged(int w, int h, int oldw, int oldh) {
/* 213 */     super.onSizeChanged(w, h, oldw, oldh);
/* 214 */     this.arcRect.left = (this.defaultSpace >> 1);
/* 215 */     this.arcRect.top = (this.defaultSpace >> 1);
/* 216 */     this.arcRect.right = (w - (this.defaultSpace >> 1));
/* 217 */     this.arcRect.bottom = (h - (this.defaultSpace >> 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getMinWidth(int mode, int measuredSize) {
/* 228 */     int suggestSize = 0;
/* 229 */     switch (mode) {
/*     */       case 0:
/*     */       case -2147483648:
/* 232 */         if (TextUtils.isEmpty(this.centerText)) {
/* 233 */           this.textPaint.getTextBounds(this.emptyText, 0, this.emptyText.length(), this.textBounds);
/*     */         } else {
/* 235 */           this.textPaint.getTextBounds(this.centerText, 0, this.centerText.length(), this.textBounds);
/*     */         } 
/* 237 */         suggestSize = getPaddingLeft() + getPaddingRight() + this.textBounds.width() + this.defaultSpace;
/*     */         break;
/*     */       case 1073741824:
/* 240 */         suggestSize = measuredSize;
/*     */         break;
/*     */     } 
/*     */     
/* 244 */     return suggestSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getMinHeight(int mode, int measuredSize) {
/* 255 */     int suggestSize = 0;
/* 256 */     switch (mode) {
/*     */       case 0:
/*     */       case -2147483648:
/* 259 */         if (TextUtils.isEmpty(this.centerText)) {
/* 260 */           this.textPaint.getTextBounds(this.emptyText, 0, this.emptyText.length(), this.textBounds);
/*     */         } else {
/* 262 */           this.textPaint.getTextBounds(this.centerText, 0, this.centerText.length(), this.textBounds);
/*     */         } 
/* 264 */         suggestSize = getPaddingTop() + getPaddingBottom() + this.textBounds.height() + this.defaultSpace;
/*     */         break;
/*     */       case 1073741824:
/* 267 */         suggestSize = measuredSize;
/*     */         break;
/*     */     } 
/*     */     
/* 271 */     return suggestSize;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onDraw(Canvas canvas) {
/* 277 */     drawCenterBackground(canvas);
/* 278 */     if (this.shouldDrawOutsideWrapper) {
/* 279 */       drawOutsideWrapper(canvas);
/*     */     }
/* 281 */     drawArc(canvas);
/* 282 */     drawCenterText(canvas);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawCenterBackground(Canvas canvas) {
/* 291 */     this.centerBgPaint.setColor(this.centerBackground);
/* 292 */     canvas.drawCircle(this.arcRect.centerX(), this.arcRect.centerY(), (this.arcRect.width() - (this.defaultSpace >> 2)) / 2.0F, this.centerBgPaint);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawOutsideWrapper(Canvas canvas) {
/* 301 */     this.arcPaint.setColor(this.outsideWrapperColor);
/* 302 */     canvas.drawArc(this.arcRect, 0.0F, 360.0F, false, this.arcPaint);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawArc(Canvas canvas) {
/* 312 */     this.arcPaint.setStrokeWidth(this.strokeWidth);
/* 313 */     this.arcPaint.setColor(this.strokeColor);
/* 314 */     canvas.drawArc(this.arcRect, this.startAngle, this.isSupportEts ? (this.progress - 360) : this.progress, false, this.arcPaint);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawCenterText(Canvas canvas) {
/* 323 */     this.textPaint.setColor(this.centerTextColor);
/* 324 */     if (TextUtils.isEmpty(this.centerText)) {
/* 325 */       canvas.drawText(Math.abs((int)(this.progress / 3.6D)) + "%", this.arcRect.centerX(), this.arcRect.centerY() - (this.textPaint.descent() + this.textPaint.ascent()) / 2.0F, this.textPaint);
/*     */     } else {
/* 327 */       canvas.drawText(this.centerText, this.arcRect.centerX(), this.arcRect.centerY() - (this.textPaint.descent() + this.textPaint.ascent()) / 2.0F, this.textPaint);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 335 */   public void start() { initAnimator(this.countDownTimeMillis, this.mDirection); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stop() {
/* 342 */     if (this.animator != null) {
/* 343 */       this.animator.cancel();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pause() {
/* 351 */     if (this.animator != null) {
/* 352 */       if (Build.VERSION.SDK_INT >= 19) {
/* 353 */         this.animator.pause();
/*     */       } else {
/* 355 */         this.currentTime = this.animator.getCurrentPlayTime();
/* 356 */         this.animator.cancel();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resume() {
/* 365 */     if (this.animator != null) {
/* 366 */       if (Build.VERSION.SDK_INT >= 19) {
/* 367 */         this.animator.resume();
/*     */       } else {
/* 369 */         this.animator.setCurrentPlayTime(this.currentTime);
/* 370 */         this.animator.start();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void destroy() {
/* 376 */     this.animator.removeAllUpdateListeners();
/* 377 */     this.animator.removeAllListeners();
/* 378 */     this.mProgressChangeListener = null;
/* 379 */     stop();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onFinishInflate() {
/* 384 */     super.onFinishInflate();
/* 385 */     setDirection(this.mDirections[this.directionIndex]);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onDetachedFromWindow() {
/* 390 */     super.onDetachedFromWindow();
/* 391 */     stop();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initAnimator(int duration, Direction direction) {
/* 401 */     if (this.animator != null && this.animator.isRunning()) {
/* 402 */       this.animator.cancel();
/*     */     }
/* 404 */     int start = 0;
/* 405 */     int end = 360;
/* 406 */     if (direction == Direction.REVERSE) {
/* 407 */       start = 360;
/* 408 */       end = 0;
/*     */     } 
/* 410 */     this.animator = ValueAnimator.ofInt(new int[] { start, end }).setDuration(duration);
/* 411 */     this.animator.setRepeatCount(0);
/* 412 */     this.animator.setInterpolator((TimeInterpolator)new LinearInterpolator());
/* 413 */     this.animator.start();
/* 414 */     this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
/*     */         {
/*     */           public void onAnimationUpdate(ValueAnimator animation) {
/* 417 */             RoundProgressBar.this.progress = ((Integer)animation.getAnimatedValue()).intValue();
/* 418 */             if (RoundProgressBar.this.mProgressChangeListener != null) {
/* 419 */               RoundProgressBar.this.mProgressChangeListener.onProgressChanged((int)(RoundProgressBar.this.progress / 3.6D));
/*     */             }
/* 421 */             RoundProgressBar.this.invalidate();
/*     */           }
/*     */         });
/* 424 */     this.animator.addListener(new Animator.AnimatorListener()
/*     */         {
/*     */           public void onAnimationStart(Animator animation) {}
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void onAnimationEnd(Animator animation) {
/* 432 */             if (RoundProgressBar.this.mProgressChangeListener != null) {
/* 433 */               RoundProgressBar.this.mProgressChangeListener.onFinish();
/*     */             }
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void onAnimationCancel(Animator animation) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void onAnimationRepeat(Animator animation) {}
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStrokeWidth(int strokeWidth) {
/* 457 */     if (strokeWidth > 0) {
/* 458 */       this.strokeWidth = strokeWidth;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 468 */   public void setStrokeColor(int strokeColor) { this.strokeColor = strokeColor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 477 */   public void setCountDownTimeMillis(int countDownTimeMillis) { this.countDownTimeMillis = countDownTimeMillis; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 486 */   public void setCenterBackground(int centerBackground) { this.centerBackground = centerBackground; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 496 */   public void setCenterText(String centerText) { this.centerText = centerText; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 505 */   public void setCenterTextColor(int centerTextColor) { this.centerTextColor = centerTextColor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 514 */   public void setCenterTextSize(float centerTextSize) { this.centerTextSize = centerTextSize; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 523 */   public void setStartAngle(int startAngle) { this.startAngle = startAngle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 532 */   public void setAutoStart(boolean isAutoStart) { this.isAutoStart = isAutoStart; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 541 */   public void setProgressChangeListener(ProgressChangeListener progressChangeListener) { this.mProgressChangeListener = progressChangeListener; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDirection(Direction direction) {
/* 550 */     if (direction == null) {
/* 551 */       throw new RuntimeException("Direction is null");
/*     */     }
/* 553 */     this.mDirection = direction;
/* 554 */     switch (direction) {
/*     */       
/*     */       default:
/* 557 */         this.progress = 0;
/*     */         break;
/*     */       case REVERSE:
/* 560 */         this.progress = 360;
/*     */         break;
/*     */     } 
/* 563 */     if (this.isAutoStart) {
/* 564 */       start();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setProgress(int progress) {
/* 574 */     if (progress > 360) {
/* 575 */       progress = 360;
/* 576 */     } else if (progress < 0) {
/* 577 */       progress = 0;
/*     */     } 
/* 579 */     this.progress = progress;
/* 580 */     invalidate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setProgressPercent(int progressPercent) {
/* 589 */     if (progressPercent > 100) {
/* 590 */       progressPercent = 100;
/* 591 */     } else if (progressPercent < 0) {
/* 592 */       progressPercent = 0;
/*     */     } 
/* 594 */     this.progress = (int)(progressPercent * 3.6D);
/* 595 */     invalidate();
/*     */   }
/*     */ 
/*     */   
/* 599 */   public void setShouldDrawOutsideWrapper(boolean shouldDrawOutsideWrapper) { this.shouldDrawOutsideWrapper = shouldDrawOutsideWrapper; }
/*     */ 
/*     */ 
/*     */   
/* 603 */   public void setOutsideWrapperColor(int outsideWrapperColor) { this.outsideWrapperColor = outsideWrapperColor; }
/*     */ 
/*     */ 
/*     */   
/* 607 */   public boolean isSupportEts() { return this.isSupportEts; }
/*     */ 
/*     */ 
/*     */   
/* 611 */   public void setSupportEts(boolean supportEts) { this.isSupportEts = supportEts; }
/*     */ 
/*     */ 
/*     */   
/* 615 */   private float sp2px(float inParam) { return TypedValue.applyDimension(2, inParam, getContext().getResources().getDisplayMetrics()); }
/*     */ 
/*     */   
/*     */   private float dp2px(float dp) {
/* 619 */     DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
/* 620 */     return (dp < 0.0F) ? dp : Math.round(dp * displayMetrics.density);
/*     */   }
/*     */   
/*     */   public static interface ProgressChangeListener {
/*     */     void onFinish();
/*     */     
/*     */     void onProgressChanged(int param1Int);
/*     */   }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\progressbar\RoundProgressBar.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */