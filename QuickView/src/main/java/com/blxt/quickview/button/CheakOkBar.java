package com.blxt.quickview.button;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import com.blxt.quickview.AttributeHelper;


public class CheakOkBar
        extends View {
    private static final String TAG = CheakOkBar.class.getSimpleName();


    private Paint mPaint;


    private PathMeasure mPathMeasure;


    private Path mCirclePath;


    private Path mDstPath;


    private float mPathLength;


    private float mAnimatorValue;


    private boolean mIsHasCircle = false;


    private int mRealSize;


    private int mStartColor = -65536;
    private int mEndColor = -256;


    private float mStrokeWidth = 8.0F;


    private ValueAnimator mCircleValueAnimator;


    private ValueAnimator mRightMarkValueAnimator;


    private int DEFAULT_SIZE = 40;


    public static final int ANIMATOR_TIME = 1000;


    public CheakOkBar(Context context) {
        this(context, null);
    }


    public CheakOkBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initPaint();


        initCircleAnimator();
        AttributeHelper attributeHelper = new AttributeHelper(getContext(), attrs);


        post(new Runnable() {
            public void run() {
                CheakOkBar.this.initCirclePath();


                CheakOkBar.this.initShader();
            }
        });
    }


    @SuppressLint({"NewApi"})
    public CheakOkBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public void startAnimator() {
        this.mPaint.setStrokeWidth(this.mStrokeWidth);
        this.mPaint.setColor(this.mStartColor);
        this.mCircleValueAnimator.start();
    }


    public void setColor(@ColorInt int startColor, @ColorInt int endColor) {
        this.mStartColor = startColor;
        this.mEndColor = endColor;
    }


    public void setStrokeWidth(float strokeWidth) {
        this.mStrokeWidth = strokeWidth;
    }


    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wSpecMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int wSpecSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int hSpecMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int hSpecSize = View.MeasureSpec.getSize(heightMeasureSpec);


        int realSize = Math.min(wSpecSize, hSpecSize);


        boolean isAnyOneAtMost = (wSpecMode == Integer.MIN_VALUE || hSpecMode == Integer.MIN_VALUE);


        if (!isAnyOneAtMost) {

            realSize = Math.max(realSize, this.DEFAULT_SIZE);
            setMeasuredDimension(realSize, realSize);
        } else {
            setMeasuredDimension(this.DEFAULT_SIZE, this.DEFAULT_SIZE);
        }
    }


    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mDstPath == null) {
            return;
        }

        if (this.mIsHasCircle) {
            canvas.drawPath(this.mCirclePath, this.mPaint);
        }


        this.mDstPath.reset();


        this.mDstPath.lineTo(0.0F, 0.0F);


        float stop = this.mPathLength * this.mAnimatorValue;
        this.mPathMeasure.getSegment(0.0F, stop, this.mDstPath, true);

        canvas.drawPath(this.mDstPath, this.mPaint);
    }


    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();


        boolean isCircleNeedCancel = (this.mCircleValueAnimator != null && this.mCircleValueAnimator.isRunning());

        if (isCircleNeedCancel) {
            log("圆形动画取消");
            this.mCircleValueAnimator.cancel();
        }


        boolean isRightMarkNeedCancel = (this.mRightMarkValueAnimator != null && this.mRightMarkValueAnimator.isRunning());
        if (isRightMarkNeedCancel) {
            log("对号动画取消");
            this.mRightMarkValueAnimator.cancel();
        }
    }


    private void initShader() {
        LinearGradient shader = new LinearGradient(0.0F, 0.0F, this.mRealSize, this.mRealSize, this.mStartColor, this.mEndColor, Shader.TileMode.REPEAT);

        this.mPaint.setShader((Shader) shader);
    }


    private void initPaint() {
        this.mPaint = new Paint(1);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeJoin(Paint.Join.ROUND);
    }


    private void initCirclePath() {
        this.mRealSize = getWidth();


        this.mCirclePath = new Path();
        float x = (this.mRealSize / 2);
        float y = (this.mRealSize / 2);
        float radius = x / 3.0F * 2.0F;
        this.mCirclePath.addCircle(x, y, radius, Path.Direction.CW);


        this.mPathMeasure = new PathMeasure();
        this.mPathMeasure.setPath(this.mCirclePath, false);


        this.mPathLength = this.mPathMeasure.getLength();


        this.mDstPath = new Path();
    }


    private void initCircleAnimator() {
        this.mCircleValueAnimator = ValueAnimator.ofFloat(new float[]{0.0F, 1.0F});


        this.mCircleValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                CheakOkBar.this.mAnimatorValue = ((Float) animation.getAnimatedValue()).floatValue();
                CheakOkBar.this.invalidate();
            }
        });


        this.mCircleValueAnimator.setDuration(1000L);


        this.mCircleValueAnimator.setInterpolator((TimeInterpolator) new AccelerateDecelerateInterpolator());


        this.mCircleValueAnimator.addListener((Animator.AnimatorListener) new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                CheakOkBar.this.mIsHasCircle = true;
                CheakOkBar.this.initMarkAnimator();
                CheakOkBar.this.initRightMarkPath();
                CheakOkBar.this.mRightMarkValueAnimator.start();
            }
        });
    }


    private void initMarkAnimator() {
        this.mRightMarkValueAnimator = ValueAnimator.ofFloat(new float[]{0.0F, 1.0F});

        this.mRightMarkValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                CheakOkBar.this.mAnimatorValue = ((Float) animation.getAnimatedValue()).floatValue();
                CheakOkBar.this.invalidate();
            }
        });


        this.mRightMarkValueAnimator.setDuration(1000L);


        this.mRightMarkValueAnimator.setInterpolator((TimeInterpolator) new AccelerateDecelerateInterpolator());
    }


    private void initRightMarkPath() {
        Path path = new Path();

        float startX = (float) (0.3D * this.mRealSize);
        float startY = (float) (0.5D * this.mRealSize);
        path.moveTo(startX, startY);


        float cornerX = (float) (0.43D * this.mRealSize);
        float cornerY = (float) (0.66D * this.mRealSize);
        path.lineTo(cornerX, cornerY);


        float endX = (float) (0.75D * this.mRealSize);
        float endY = (float) (0.4D * this.mRealSize);
        path.lineTo(endX, endY);


        this.mPathMeasure.setPath(path, false);


        this.mPathLength = this.mPathMeasure.getLength();
    }


    private void log(String s) {
        Log.e(TAG, " ---> { " + s + " }");
    }
}

