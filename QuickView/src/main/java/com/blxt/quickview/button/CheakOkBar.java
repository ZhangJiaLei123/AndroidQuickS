/*     */ package com.blxt.quickview.button;
/*     */ 
/*     */ import android.animation.Animator;
/*     */ import android.animation.AnimatorListenerAdapter;
/*     */ import android.animation.TimeInterpolator;
/*     */ import android.animation.ValueAnimator;
/*     */ import android.annotation.SuppressLint;
/*     */ import android.content.Context;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.LinearGradient;
/*     */ import android.graphics.Paint;
/*     */ import android.graphics.Path;
/*     */ import android.graphics.PathMeasure;
/*     */ import android.graphics.Shader;
/*     */ import android.util.AttributeSet;
/*     */ import android.util.Log;
/*     */ import android.view.View;
/*     */ import android.view.animation.AccelerateDecelerateInterpolator;
/*     */ import androidx.annotation.ColorInt;
/*     */ import androidx.annotation.Nullable;
/*     */ import com.blxt.quickview.AttributeHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CheakOkBar
/*     */   extends View
/*     */ {
/*  31 */   private static final String TAG = CheakOkBar.class.getSimpleName();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Paint mPaint;
/*     */ 
/*     */ 
/*     */   
/*     */   private PathMeasure mPathMeasure;
/*     */ 
/*     */ 
/*     */   
/*     */   private Path mCirclePath;
/*     */ 
/*     */ 
/*     */   
/*     */   private Path mDstPath;
/*     */ 
/*     */ 
/*     */   
/*     */   private float mPathLength;
/*     */ 
/*     */ 
/*     */   
/*     */   private float mAnimatorValue;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean mIsHasCircle = false;
/*     */ 
/*     */ 
/*     */   
/*     */   private int mRealSize;
/*     */ 
/*     */ 
/*     */   
/*  68 */   private int mStartColor = -65536;
/*  69 */   private int mEndColor = -256;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   private float mStrokeWidth = 8.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ValueAnimator mCircleValueAnimator;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ValueAnimator mRightMarkValueAnimator;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   private int DEFAULT_SIZE = 40;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int ANIMATOR_TIME = 1000;
/*     */ 
/*     */ 
/*     */   
/*  97 */   public CheakOkBar(Context context) { this(context, null); }
/*     */ 
/*     */   
/*     */   public CheakOkBar(Context context, @Nullable AttributeSet attrs) {
/* 101 */     super(context, attrs);
/*     */     
/* 103 */     initPaint();
/*     */ 
/*     */     
/* 106 */     initCircleAnimator();
/* 107 */     AttributeHelper attributeHelper = new AttributeHelper(getContext(), attrs);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 117 */     post(new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 121 */             CheakOkBar.this.initCirclePath();
/*     */ 
/*     */ 
/*     */             
/* 125 */             CheakOkBar.this.initShader();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SuppressLint({"NewApi"})
/* 134 */   public CheakOkBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) { super(context, attrs, defStyleAttr, defStyleRes); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startAnimator() {
/* 142 */     this.mPaint.setStrokeWidth(this.mStrokeWidth);
/* 143 */     this.mPaint.setColor(this.mStartColor);
/* 144 */     this.mCircleValueAnimator.start();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColor(@ColorInt int startColor, @ColorInt int endColor) {
/* 151 */     this.mStartColor = startColor;
/* 152 */     this.mEndColor = endColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 159 */   public void setStrokeWidth(float strokeWidth) { this.mStrokeWidth = strokeWidth; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
/* 170 */     super.onMeasure(widthMeasureSpec, heightMeasureSpec);
/* 171 */     int wSpecMode = View.MeasureSpec.getMode(widthMeasureSpec);
/* 172 */     int wSpecSize = View.MeasureSpec.getSize(widthMeasureSpec);
/* 173 */     int hSpecMode = View.MeasureSpec.getMode(heightMeasureSpec);
/* 174 */     int hSpecSize = View.MeasureSpec.getSize(heightMeasureSpec);
/*     */ 
/*     */     
/* 177 */     int realSize = Math.min(wSpecSize, hSpecSize);
/*     */ 
/*     */     
/* 180 */     boolean isAnyOneAtMost = (wSpecMode == Integer.MIN_VALUE || hSpecMode == Integer.MIN_VALUE);
/*     */ 
/*     */     
/* 183 */     if (!isAnyOneAtMost) {
/*     */       
/* 185 */       realSize = Math.max(realSize, this.DEFAULT_SIZE);
/* 186 */       setMeasuredDimension(realSize, realSize);
/*     */     } else {
/* 188 */       setMeasuredDimension(this.DEFAULT_SIZE, this.DEFAULT_SIZE);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onDraw(Canvas canvas) {
/* 199 */     super.onDraw(canvas);
/* 200 */     if (this.mDstPath == null) {
/*     */       return;
/*     */     }
/*     */     
/* 204 */     if (this.mIsHasCircle) {
/* 205 */       canvas.drawPath(this.mCirclePath, this.mPaint);
/*     */     }
/*     */ 
/*     */     
/* 209 */     this.mDstPath.reset();
/*     */ 
/*     */     
/* 212 */     this.mDstPath.lineTo(0.0F, 0.0F);
/*     */ 
/*     */     
/* 215 */     float stop = this.mPathLength * this.mAnimatorValue;
/* 216 */     this.mPathMeasure.getSegment(0.0F, stop, this.mDstPath, true);
/*     */     
/* 218 */     canvas.drawPath(this.mDstPath, this.mPaint);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onDetachedFromWindow() {
/* 226 */     super.onDetachedFromWindow();
/*     */ 
/*     */     
/* 229 */     boolean isCircleNeedCancel = (this.mCircleValueAnimator != null && this.mCircleValueAnimator.isRunning());
/*     */     
/* 231 */     if (isCircleNeedCancel) {
/* 232 */       log("圆形动画取消");
/* 233 */       this.mCircleValueAnimator.cancel();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 238 */     boolean isRightMarkNeedCancel = (this.mRightMarkValueAnimator != null && this.mRightMarkValueAnimator.isRunning());
/* 239 */     if (isRightMarkNeedCancel) {
/* 240 */       log("对号动画取消");
/* 241 */       this.mRightMarkValueAnimator.cancel();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initShader() {
/* 251 */     LinearGradient shader = new LinearGradient(0.0F, 0.0F, this.mRealSize, this.mRealSize, this.mStartColor, this.mEndColor, Shader.TileMode.REPEAT);
/*     */     
/* 253 */     this.mPaint.setShader((Shader)shader);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initPaint() {
/* 260 */     this.mPaint = new Paint(1);
/* 261 */     this.mPaint.setStyle(Paint.Style.STROKE);
/* 262 */     this.mPaint.setStrokeJoin(Paint.Join.ROUND);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initCirclePath() {
/* 270 */     this.mRealSize = getWidth();
/*     */ 
/*     */     
/* 273 */     this.mCirclePath = new Path();
/* 274 */     float x = (this.mRealSize / 2);
/* 275 */     float y = (this.mRealSize / 2);
/* 276 */     float radius = x / 3.0F * 2.0F;
/* 277 */     this.mCirclePath.addCircle(x, y, radius, Path.Direction.CW);
/*     */ 
/*     */     
/* 280 */     this.mPathMeasure = new PathMeasure();
/* 281 */     this.mPathMeasure.setPath(this.mCirclePath, false);
/*     */ 
/*     */     
/* 284 */     this.mPathLength = this.mPathMeasure.getLength();
/*     */ 
/*     */     
/* 287 */     this.mDstPath = new Path();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initCircleAnimator() {
/* 295 */     this.mCircleValueAnimator = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F });
/*     */ 
/*     */     
/* 298 */     this.mCircleValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
/*     */         {
/*     */           public void onAnimationUpdate(ValueAnimator animation) {
/* 301 */             CheakOkBar.this.mAnimatorValue = ((Float)animation.getAnimatedValue()).floatValue();
/* 302 */             CheakOkBar.this.invalidate();
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 307 */     this.mCircleValueAnimator.setDuration(1000L);
/*     */ 
/*     */     
/* 310 */     this.mCircleValueAnimator.setInterpolator((TimeInterpolator)new AccelerateDecelerateInterpolator());
/*     */ 
/*     */     
/* 313 */     this.mCircleValueAnimator.addListener((Animator.AnimatorListener)new AnimatorListenerAdapter()
/*     */         {
/*     */           public void onAnimationEnd(Animator animation) {
/* 316 */             super.onAnimationEnd(animation);
/* 317 */             CheakOkBar.this.mIsHasCircle = true;
/* 318 */             CheakOkBar.this.initMarkAnimator();
/* 319 */             CheakOkBar.this.initRightMarkPath();
/* 320 */             CheakOkBar.this.mRightMarkValueAnimator.start();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initMarkAnimator() {
/* 329 */     this.mRightMarkValueAnimator = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F });
/*     */     
/* 331 */     this.mRightMarkValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
/*     */         {
/*     */           public void onAnimationUpdate(ValueAnimator animation) {
/* 334 */             CheakOkBar.this.mAnimatorValue = ((Float)animation.getAnimatedValue()).floatValue();
/* 335 */             CheakOkBar.this.invalidate();
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 340 */     this.mRightMarkValueAnimator.setDuration(1000L);
/*     */ 
/*     */     
/* 343 */     this.mRightMarkValueAnimator.setInterpolator((TimeInterpolator)new AccelerateDecelerateInterpolator());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initRightMarkPath() {
/* 351 */     Path path = new Path();
/*     */     
/* 353 */     float startX = (float)(0.3D * this.mRealSize);
/* 354 */     float startY = (float)(0.5D * this.mRealSize);
/* 355 */     path.moveTo(startX, startY);
/*     */ 
/*     */     
/* 358 */     float cornerX = (float)(0.43D * this.mRealSize);
/* 359 */     float cornerY = (float)(0.66D * this.mRealSize);
/* 360 */     path.lineTo(cornerX, cornerY);
/*     */ 
/*     */     
/* 363 */     float endX = (float)(0.75D * this.mRealSize);
/* 364 */     float endY = (float)(0.4D * this.mRealSize);
/* 365 */     path.lineTo(endX, endY);
/*     */ 
/*     */     
/* 368 */     this.mPathMeasure.setPath(path, false);
/*     */ 
/*     */     
/* 371 */     this.mPathLength = this.mPathMeasure.getLength();
/*     */   }
/*     */ 
/*     */   
/* 375 */   private void log(String s) { Log.e(TAG, " ---> { " + s + " }"); }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\button\CheakOkBar.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */