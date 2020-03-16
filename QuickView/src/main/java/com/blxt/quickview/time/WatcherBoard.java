/*     */ package com.blxt.quickview.time;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.res.TypedArray;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Color;
/*     */ import android.graphics.Paint;
/*     */ import android.graphics.Rect;
/*     */ import android.graphics.RectF;
/*     */ import android.util.AttributeSet;
/*     */ import android.view.View;
/*     */ import com.blxt.quickview.R;
/*     */ import java.util.Calendar;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WatcherBoard
/*     */   extends View
/*     */ {
/*  23 */   private float SizeMix = 1.0F;
/*     */   
/*  25 */   private float lineWidth = 10.0F * this.SizeMix;
/*  26 */   private float lineWidthHour = 15.0F * this.SizeMix;
/*     */   private float mRadius;
/*  28 */   private float mPadding = 5.0F * this.SizeMix;
/*  29 */   private float mTextSize = 16.0F * this.SizeMix;
/*  30 */   private float mHourPointWidth = 2.0F * this.SizeMix;
/*  31 */   private float mMinutePointWidth = 2.0F * this.SizeMix;
/*  32 */   private float mSecondPointWidth = 1.0F * this.SizeMix;
/*  33 */   private int mPointRadius = (int)(10.0F * this.SizeMix);
/*  34 */   private float mPointEndLength = 1.0F * this.SizeMix;
/*     */   
/*     */   private int mColorLong;
/*     */   
/*     */   private int mColorShort;
/*     */   
/*     */   private int mHourPointColor;
/*     */   private int mMinutePointColor;
/*     */   private int mSecondPointColor;
/*     */   private Paint mPaint;
/*     */   
/*  45 */   public WatcherBoard(Context context) { this(context, null); }
/*     */ 
/*     */   
/*     */   public WatcherBoard(Context context, AttributeSet attrs) {
/*  49 */     super(context, attrs);
/*  50 */     Utils.init(context);
/*  51 */     obtainStyledAttrs(attrs);
/*  52 */     init();
/*     */   }
/*     */   
/*     */   private void obtainStyledAttrs(AttributeSet attrs) {
/*  56 */     TypedArray array = null;
/*     */     try {
/*  58 */       array = getContext().obtainStyledAttributes(attrs, R.styleable.WatchBoard);
/*  59 */       this.mPadding = array.getDimension(R.styleable.WatchBoard_wb_padding, DpToPx((int)this.mPadding));
/*  60 */       this.mTextSize = array.getDimension(R.styleable.WatchBoard_wb_text_size, SpToPx((int)this.mTextSize));
/*  61 */       this.mHourPointWidth = array.getDimension(R.styleable.WatchBoard_wb_hour_pointer_width, DpToPx((int)this.mHourPointWidth));
/*  62 */       this.mMinutePointWidth = array.getDimension(R.styleable.WatchBoard_wb_minute_pointer_width, DpToPx((int)this.mMinutePointWidth));
/*  63 */       this.mSecondPointWidth = array.getDimension(R.styleable.WatchBoard_wb_second_pointer_width, DpToPx((int)this.mSecondPointWidth));
/*  64 */       this.mPointRadius = (int)array.getDimension(R.styleable.WatchBoard_wb_pointer_corner_radius, DpToPx(this.mPointRadius));
/*  65 */       this.mPointEndLength = array.getDimension(R.styleable.WatchBoard_wb_pointer_end_length, DpToPx((int)this.mPointEndLength));
/*     */       
/*  67 */       this.mColorLong = array.getColor(R.styleable.WatchBoard_wb_scale_long_color, Color.argb(225, 0, 0, 0));
/*  68 */       this.mColorShort = array.getColor(R.styleable.WatchBoard_wb_scale_short_color, Color.argb(125, 0, 0, 0));
/*  69 */       this.mHourPointColor = array.getColor(R.styleable.WatchBoard_wb_hour_pointer_color, -16777216);
/*  70 */       this.mMinutePointColor = array.getColor(R.styleable.WatchBoard_wb_minute_pointer_color, -16777216);
/*  71 */       this.mSecondPointColor = array.getColor(R.styleable.WatchBoard_wb_second_pointer_color, -65536);
/*  72 */     } catch (Exception e) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  82 */       this.mColorLong = Color.argb(225, 0, 0, 0);
/*  83 */       this.mColorShort = Color.argb(125, 0, 0, 0);
/*  84 */       this.mMinutePointColor = -16777216;
/*  85 */       this.mSecondPointColor = -65536;
/*     */     } finally {
/*  87 */       if (array != null) {
/*  88 */         array.recycle();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   private float DpToPx(int value) { return SizeUtils.dp2px(value); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   private float SpToPx(int value) { return SizeUtils.sp2px(value); }
/*     */ 
/*     */ 
/*     */   
/*     */   private void init() {
/* 106 */     this.mPaint = new Paint();
/* 107 */     this.mPaint.setAntiAlias(true);
/* 108 */     this.mPaint.setDither(true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
/* 113 */     super.onMeasure(widthMeasureSpec, heightMeasureSpec);
/* 114 */     int width = 1000;
/*     */     
/* 116 */     int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
/* 117 */     int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
/* 118 */     int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
/* 119 */     int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
/*     */ 
/*     */     
/* 122 */     if (widthMode == Integer.MIN_VALUE || widthMode == 0 || heightMeasureSpec == Integer.MIN_VALUE || heightMeasureSpec == 0) {
/*     */       
/*     */       try {
/* 125 */         throw new NoDetermineSizeException("宽度高度至少有一个确定的值,不能同时为wrap_content");
/* 126 */       } catch (NoDetermineSizeException e) {
/* 127 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/* 130 */       if (widthMode == 1073741824) {
/* 131 */         width = Math.min(widthSize, width);
/*     */       }
/* 133 */       if (heightMode == 1073741824) {
/* 134 */         width = Math.min(heightSize, width);
/*     */       }
/*     */     } 
/*     */     
/* 138 */     setMeasuredDimension(width, width);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onSizeChanged(int w, int h, int oldw, int oldh) {
/* 144 */     super.onSizeChanged(w, h, oldw, oldh);
/* 145 */     this.mRadius = ((Math.min(w, h) - getPaddingLeft() - getPaddingRight()) / 2);
/* 146 */     this.mPointEndLength = this.mRadius / 6.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void paintCircle(Canvas canvas) {
/* 151 */     this.mPaint.setColor(-1);
/* 152 */     this.mPaint.setStyle(Paint.Style.FILL);
/* 153 */     canvas.drawCircle(0.0F, 0.0F, this.mRadius, this.mPaint);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onDraw(Canvas canvas) {
/* 158 */     canvas.save();
/* 159 */     canvas.translate((getWidth() / 2), (getHeight() / 2));
/*     */     
/* 161 */     paintCircle(canvas);
/*     */     
/* 163 */     paintScale(canvas);
/*     */     
/* 165 */     paintPointer(canvas);
/* 166 */     canvas.restore();
/*     */     
/* 168 */     postInvalidateDelayed(1000L);
/*     */   }
/*     */   
/*     */   private void paintPointer(Canvas canvas) {
/* 172 */     Calendar calendar = Calendar.getInstance();
/* 173 */     int hour = calendar.get(11);
/* 174 */     int minute = calendar.get(12);
/* 175 */     int second = calendar.get(13);
/* 176 */     int angleHour = hour % 12 * 360 / 12;
/* 177 */     int angleMinute = minute * 360 / 60;
/* 178 */     int angleSecond = second * 360 / 60;
/*     */     
/* 180 */     canvas.save();
/* 181 */     canvas.rotate(angleHour);
/* 182 */     RectF rectFHour = new RectF(-this.mHourPointWidth / 2.0F, -this.mRadius * 3.0F / 5.0F, this.mHourPointWidth / 2.0F, this.mPointEndLength);
/* 183 */     this.mPaint.setColor(this.mHourPointColor);
/* 184 */     this.mPaint.setStyle(Paint.Style.STROKE);
/* 185 */     this.mPaint.setStrokeWidth(this.mHourPointWidth);
/* 186 */     canvas.drawRoundRect(rectFHour, this.mPointRadius, this.mPointRadius, this.mPaint);
/* 187 */     canvas.restore();
/*     */     
/* 189 */     canvas.save();
/* 190 */     canvas.rotate(angleMinute);
/* 191 */     RectF rectFMinute = new RectF(-this.mMinutePointWidth / 2.0F, -this.mRadius * 3.5F / 5.0F, this.mMinutePointWidth / 2.0F, this.mPointEndLength);
/* 192 */     this.mPaint.setColor(this.mMinutePointColor);
/* 193 */     this.mPaint.setStrokeWidth(this.mMinutePointWidth);
/* 194 */     canvas.drawRoundRect(rectFMinute, this.mPointRadius, this.mPointRadius, this.mPaint);
/* 195 */     canvas.restore();
/*     */     
/* 197 */     canvas.save();
/* 198 */     canvas.rotate(angleSecond);
/* 199 */     RectF rectFSecond = new RectF(-this.mSecondPointWidth / 2.0F, -this.mRadius + 15.0F, this.mSecondPointWidth / 2.0F, this.mPointEndLength);
/* 200 */     this.mPaint.setColor(this.mSecondPointColor);
/* 201 */     this.mPaint.setStrokeWidth(this.mSecondPointWidth);
/* 202 */     canvas.drawRoundRect(rectFSecond, this.mPointRadius, this.mPointRadius, this.mPaint);
/* 203 */     canvas.restore();
/*     */     
/* 205 */     this.mPaint.setStyle(Paint.Style.FILL);
/* 206 */     this.mPaint.setColor(this.mSecondPointColor);
/* 207 */     canvas.drawCircle(0.0F, 0.0F, this.mSecondPointWidth * 4.0F, this.mPaint);
/*     */   }
/*     */ 
/*     */   
/*     */   private void paintScale(Canvas canvas) {
/* 212 */     this.mPaint.setStrokeWidth(SizeUtils.dp2px(1.0F));
/* 213 */     float line = 0.0F;
/* 214 */     for (int i = 0; i < 60; i++) {
/* 215 */       if (i % 5 == 0) {
/* 216 */         this.mPaint.setStrokeWidth(SizeUtils.dp2px(1.5F));
/* 217 */         this.mPaint.setColor(this.mColorLong);
/* 218 */         line = this.lineWidthHour;
/*     */         
/* 220 */         this.mPaint.setTextSize(this.mTextSize);
/* 221 */         String text = ((i / 5 == 0) ? 12 : (i / 5)) + "";
/* 222 */         Rect textBound = new Rect();
/* 223 */         this.mPaint.getTextBounds(text, 0, text.length(), textBound);
/* 224 */         this.mPaint.setColor(-16777216);
/* 225 */         canvas.save();
/* 226 */         canvas.translate(0.0F, -this.mRadius + DpToPx(5) + line + this.mPadding + ((textBound.bottom - textBound.top) / 2));
/* 227 */         this.mPaint.setStyle(Paint.Style.FILL);
/* 228 */         canvas.rotate((-6 * i));
/* 229 */         canvas.drawText(text, (-(textBound.right + textBound.left) / 2), (-(textBound.bottom + textBound.top) / 2), this.mPaint);
/* 230 */         canvas.restore();
/*     */       } else {
/* 232 */         line = this.lineWidth;
/* 233 */         this.mPaint.setColor(this.mColorShort);
/* 234 */         this.mPaint.setStrokeWidth(SizeUtils.dp2px(1.0F));
/*     */       } 
/* 236 */       canvas.drawLine(0.0F, -this.mRadius + this.mPadding, 0.0F, -this.mRadius + this.mPadding + line, this.mPaint);
/* 237 */       canvas.rotate(6.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 243 */   public float getmRadius() { return this.mRadius; }
/*     */ 
/*     */ 
/*     */   
/* 247 */   public void setmRadius(float mRadius) { this.mRadius = mRadius; }
/*     */ 
/*     */ 
/*     */   
/* 251 */   public float getmPadding() { return this.mPadding; }
/*     */ 
/*     */ 
/*     */   
/* 255 */   public void setmPadding(float mPadding) { this.mPadding = mPadding; }
/*     */ 
/*     */ 
/*     */   
/* 259 */   public float getmTextSize() { return this.mTextSize; }
/*     */ 
/*     */ 
/*     */   
/* 263 */   public void setmTextSize(float mTextSize) { this.mTextSize = mTextSize; }
/*     */ 
/*     */ 
/*     */   
/* 267 */   public float getmHourPointWidth() { return this.mHourPointWidth; }
/*     */ 
/*     */ 
/*     */   
/* 271 */   public void setmHourPointWidth(float mHourPointWidth) { this.mHourPointWidth = mHourPointWidth; }
/*     */ 
/*     */ 
/*     */   
/* 275 */   public float getmMinutePointWidth() { return this.mMinutePointWidth; }
/*     */ 
/*     */ 
/*     */   
/* 279 */   public void setmMinutePointWidth(float mMinutePointWidth) { this.mMinutePointWidth = mMinutePointWidth; }
/*     */ 
/*     */ 
/*     */   
/* 283 */   public float getmSecondPointWidth() { return this.mSecondPointWidth; }
/*     */ 
/*     */ 
/*     */   
/* 287 */   public void setmSecondPointWidth(float mSecondPointWidth) { this.mSecondPointWidth = mSecondPointWidth; }
/*     */ 
/*     */ 
/*     */   
/* 291 */   public int getmPointRadius() { return this.mPointRadius; }
/*     */ 
/*     */ 
/*     */   
/* 295 */   public void setmPointRadius(int mPointRadius) { this.mPointRadius = mPointRadius; }
/*     */ 
/*     */ 
/*     */   
/* 299 */   public float getmPointEndLength() { return this.mPointEndLength; }
/*     */ 
/*     */ 
/*     */   
/* 303 */   public void setmPointEndLength(float mPointEndLength) { this.mPointEndLength = mPointEndLength; }
/*     */ 
/*     */ 
/*     */   
/* 307 */   public int getmColorLong() { return this.mColorLong; }
/*     */ 
/*     */ 
/*     */   
/* 311 */   public void setmColorLong(int mColorLong) { this.mColorLong = mColorLong; }
/*     */ 
/*     */ 
/*     */   
/* 315 */   public int getmColorShort() { return this.mColorShort; }
/*     */ 
/*     */ 
/*     */   
/* 319 */   public void setmColorShort(int mColorShort) { this.mColorShort = mColorShort; }
/*     */ 
/*     */ 
/*     */   
/* 323 */   public int getmHourPointColor() { return this.mHourPointColor; }
/*     */ 
/*     */ 
/*     */   
/* 327 */   public void setmHourPointColor(int mHourPointColor) { this.mHourPointColor = mHourPointColor; }
/*     */ 
/*     */ 
/*     */   
/* 331 */   public int getmMinutePointColor() { return this.mMinutePointColor; }
/*     */ 
/*     */ 
/*     */   
/* 335 */   public void setmMinutePointColor(int mMinutePointColor) { this.mMinutePointColor = mMinutePointColor; }
/*     */ 
/*     */ 
/*     */   
/* 339 */   public int getmSecondPointColor() { return this.mSecondPointColor; }
/*     */ 
/*     */ 
/*     */   
/* 343 */   public void setmSecondPointColor(int mSecondPointColor) { this.mSecondPointColor = mSecondPointColor; }
/*     */ 
/*     */ 
/*     */   
/* 347 */   public float getLineWidth() { return this.lineWidth; }
/*     */ 
/*     */ 
/*     */   
/* 351 */   public void setLineWidth(float lineWidth) { this.lineWidth = lineWidth; }
/*     */ 
/*     */ 
/*     */   
/* 355 */   public float getLineWidthHour() { return this.lineWidthHour; }
/*     */ 
/*     */ 
/*     */   
/* 359 */   public void setLineWidthHour(float lineWidthHour) { this.lineWidthHour = lineWidthHour; }
/*     */   
/*     */   class NoDetermineSizeException
/*     */     extends Exception
/*     */   {
/* 364 */     NoDetermineSizeException(String message) { super(message); }
/*     */   }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\time\WatcherBoard.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */