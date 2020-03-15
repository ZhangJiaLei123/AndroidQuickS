 package com.blxt.quickview.button;

 import android.animation.Animator;
 import android.animation.AnimatorSet;
 import android.animation.ObjectAnimator;
 import android.animation.TimeInterpolator;
 import android.animation.ValueAnimator;
 import android.content.Context;
 import android.graphics.Canvas;
 import android.graphics.DashPathEffect;
 import android.graphics.Paint;
 import android.graphics.Path;
 import android.graphics.PathEffect;
 import android.graphics.PathMeasure;
 import android.graphics.Rect;
 import android.graphics.RectF;
 import android.util.AttributeSet;
 import android.view.View;
 import android.view.animation.AccelerateDecelerateInterpolator;
 import com.blxt.quickview.AttributeHelper;


 public class AnimationButton
   extends View
 {
   private int width;
   private int height;
   private int circleAngle;
   private int default_two_circle_distance;
   private int two_circle_distance;
   private int bg_color = -12213259;



   private String buttonString = "确认完成";



   private int duration = 1000;



   private int move_distance = 300;




   private int ok_distance = 3;




   private Paint paint;



   private Paint textPaint;



   private Paint okPaint;



   private Rect textRect = new Rect();




   private AnimatorSet animatorSet = new AnimatorSet();




   private ValueAnimator animator_rect_to_angle;




   private ValueAnimator animator_rect_to_square;




   private ObjectAnimator animator_move_to_up;



   private ValueAnimator animator_draw_ok;



   private boolean startDrawOk = false;



   private RectF rectf = new RectF();




   private Path path = new Path();



   private PathMeasure pathMeasure;



   private PathEffect effect;


   private AnimationButtonListener animationButtonListener;



   public void setAnimationButtonListener(AnimationButtonListener listener) { this.animationButtonListener = listener; }



   public AnimationButton(Context context) { this(context, null); }



   public AnimationButton(Context context, AttributeSet attrs) { this(context, attrs, 0); }


   public AnimationButton(Context context, AttributeSet attrs, int defStyleAttr) {
     super(context, attrs, defStyleAttr);

     initPaint();

     initView(attrs);

     setOnClickListener(new View.OnClickListener()
         {
           public void onClick(View v) {
             if (AnimationButton.this.animationButtonListener != null) {
               AnimationButton.this.animationButtonListener.onClickListener();
             }
           }
         });

     this.animatorSet.addListener(new Animator.AnimatorListener()
         {
           public void onAnimationStart(Animator animation) {}




           public void onAnimationEnd(Animator animation) {
             if (AnimationButton.this.animationButtonListener != null) {
               AnimationButton.this.animationButtonListener.animationFinish();
             }
           }





           public void onAnimationCancel(Animator animation) {}




           public void onAnimationRepeat(Animator animation) {}
         });
   }




   public void initView(AttributeSet attrs) {
     AttributeHelper attributeHelper = new AttributeHelper(getContext(), attrs);
     this.buttonString = attributeHelper.getString("text", "");

     String _tmp = "";

     this.bg_color = attributeHelper.getColor("background", this.bg_color);
   }






   private void initPaint() {
     this.paint = new Paint();
     this.paint.setStrokeWidth(4.0F);
     this.paint.setStyle(Paint.Style.FILL);
     this.paint.setAntiAlias(true);
     this.paint.setColor(this.bg_color);

     this.textPaint = new Paint(1);
     this.textPaint.setTextSize(40.0F);
     this.textPaint.setColor(-1);
     this.textPaint.setTextAlign(Paint.Align.CENTER);
     this.textPaint.setAntiAlias(true);

     this.okPaint = new Paint();
     this.okPaint.setStrokeWidth(this.ok_distance);
     this.okPaint.setStyle(Paint.Style.STROKE);
     this.okPaint.setAntiAlias(true);
     this.okPaint.setColor(-1);
   }




   private void initAnimation() {
     set_rect_to_angle_animation();
     set_rect_to_circle_animation();
     set_move_to_up_animation();
     set_draw_ok_animation();

     this.animatorSet
       .play((Animator)this.animator_move_to_up)
       .before((Animator)this.animator_draw_ok)
       .after((Animator)this.animator_rect_to_square)
       .after((Animator)this.animator_rect_to_angle);
   }






   private void set_rect_to_angle_animation() {
     this.animator_rect_to_angle = ValueAnimator.ofInt(new int[] { 0, this.height / 2 });
     this.animator_rect_to_angle.setDuration(this.duration);
     this.animator_rect_to_angle.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
         {
           public void onAnimationUpdate(ValueAnimator animation) {
             AnimationButton.this.circleAngle = ((Integer)animation.getAnimatedValue()).intValue();
             AnimationButton.this.invalidate();
           }
         });
   }





   private void set_rect_to_circle_animation() {
     this.animator_rect_to_square = ValueAnimator.ofInt(new int[] { 0, this.default_two_circle_distance });
     this.animator_rect_to_square.setDuration(this.duration);
     this.animator_rect_to_square.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
         {
           public void onAnimationUpdate(ValueAnimator animation) {
             AnimationButton.this.two_circle_distance = ((Integer)animation.getAnimatedValue()).intValue();

             int alpha = 255 - AnimationButton.this.two_circle_distance * 255 / AnimationButton.this.default_two_circle_distance;

             AnimationButton.this.textPaint.setAlpha(alpha);

             AnimationButton.this.invalidate();
           }
         });
   }





   private void set_move_to_up_animation() {
     float curTranslationY = getTranslationY();
     this.animator_move_to_up = ObjectAnimator.ofFloat(this, "translationY", new float[] { curTranslationY, curTranslationY - this.move_distance });
     this.animator_move_to_up.setDuration(this.duration);
     this.animator_move_to_up.setInterpolator((TimeInterpolator)new AccelerateDecelerateInterpolator());
   }




   private void set_draw_ok_animation() {
     this.animator_draw_ok = ValueAnimator.ofFloat(new float[]{1.0F, 0.0F});
     this.animator_draw_ok.setDuration((long)this.duration);
     this.animator_draw_ok.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
       public void onAnimationUpdate(ValueAnimator animation) {
         AnimationButton.this.startDrawOk = true;
         float value = (Float)animation.getAnimatedValue();
         AnimationButton.this.effect = new DashPathEffect(new float[]{AnimationButton.this.pathMeasure.getLength(), AnimationButton.this.pathMeasure.getLength()}, value * AnimationButton.this.pathMeasure.getLength());
         AnimationButton.this.okPaint.setPathEffect(AnimationButton.this.effect);
         AnimationButton.this.invalidate();
       }
     });
   }



   protected void onSizeChanged(int w, int h, int oldw, int oldh) {
     super.onSizeChanged(w, h, oldw, oldh);

     this.width = w;
     this.height = h;

     this.default_two_circle_distance = (w - h) / 2;

     initOk();
     initAnimation();
   }



   protected void onDraw(Canvas canvas) {
     super.onDraw(canvas);

     draw_oval_to_circle(canvas);
     drawText(canvas);

     if (this.startDrawOk) {
       canvas.drawPath(this.path, this.okPaint);
     }
   }









   private void draw_oval_to_circle(Canvas canvas) {
     this.rectf.left = this.two_circle_distance;
     this.rectf.top = 0.0F;
     this.rectf.right = (this.width - this.two_circle_distance);
     this.rectf.bottom = this.height;


     canvas.drawRoundRect(this.rectf, this.circleAngle, this.circleAngle, this.paint);
   }








   private void drawText(Canvas canvas) {
     this.textRect.left = 0;
     this.textRect.top = 0;
     this.textRect.right = this.width;
     this.textRect.bottom = this.height;
     Paint.FontMetricsInt fontMetrics = this.textPaint.getFontMetricsInt();
     int baseline = (this.textRect.bottom + this.textRect.top - fontMetrics.bottom - fontMetrics.top) / 2;

     canvas.drawText(this.buttonString, this.textRect.centerX(), baseline, this.textPaint);
   }





   private void initOk() {
     this.path.moveTo((this.default_two_circle_distance + this.height / 8 * 3), (this.height / 2));
     this.path.lineTo((this.default_two_circle_distance + this.height / 2), (this.height / 5 * 3));
     this.path.lineTo((this.default_two_circle_distance + this.height / 3 * 2), (this.height / 5 * 2));

     this.pathMeasure = new PathMeasure(this.path, true);
   }






   public void start() { this.animatorSet.start(); }





   public void reset() {
     this.startDrawOk = false;
     this.circleAngle = 0;
     this.two_circle_distance = 0;
     this.default_two_circle_distance = (this.width - this.height) / 2;
     this.textPaint.setAlpha(255);
     setTranslationY(getTranslationY() + this.move_distance);
     invalidate();
   }





   public AnimationButton setOk_distance(int ok_distance) {
     this.ok_distance = ok_distance;
     return this;
   }

   public static interface AnimationButtonListener {
     void onClickListener();

     void animationFinish();
   }
 }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\button\AnimationButton.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */