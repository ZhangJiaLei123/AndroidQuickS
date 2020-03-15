 package com.blxt.quickview.imageview;

 import android.annotation.SuppressLint;
 import android.content.Context;
 import android.content.res.TypedArray;
 import android.graphics.Bitmap;
 import android.graphics.BitmapShader;
 import android.graphics.Canvas;
 import android.graphics.Color;
 import android.graphics.Matrix;
 import android.graphics.Paint;
 import android.graphics.Path;
 import android.graphics.RectF;
 import android.graphics.Shader;
 import android.graphics.drawable.BitmapDrawable;
 import android.graphics.drawable.ColorDrawable;
 import android.graphics.drawable.Drawable;
 import android.os.Build;
 import android.os.Parcel;
 import android.os.Parcelable;
 import android.util.AttributeSet;
 import android.view.View;
 import android.widget.ImageView;
 import androidx.annotation.ColorInt;
 import androidx.annotation.Nullable;
 import androidx.annotation.RequiresApi;
 import com.blxt.quickview.R;
 import com.blxt.quickview.imageview.internal.FastBlur;
 import com.blxt.quickview.imageview.internal.RSBlur;

 @SuppressLint({"AppCompatCustomView"})
 public class RounImageView
   extends ImageView
 {
   private static final String TAG = "BaseImageView";
   private float cornerRadius;
   private float borderWidth;
   private int borderColor;
   private int defaultColor;
   private boolean isCircle;
   private boolean hasBorder;
   private boolean isBlur;
   private boolean isOval;
   private float blurRadius;
   private int width;
   private int height;
   private Paint mPaintDrawable;
   private Paint mPaintBorder;
   private BitmapShader mBitmapShader;
   private Matrix mMatrix;
   private Path path = null;


   public enum CornerType
   {
     ALL,
     TOP_LEFT, TOP_RIGHT, BOTTOM_RIGHT, BOTTOM_LEFT,
     TOP_LEFT_TOP_RIGHT, TOP_LEFT_BOTTOM_RIGHT, TOP_LEFT_BOTTOM_LEFT,
     TOP_RIGHT_BOTTOM_RIGHT, TOP_RIGHT_BOTTOM_LEFT, BOTTOM_RIGHT_BOTTOM_LEFT,
     TOP_LEFT_TOP_RIGHT_BOTTOM_RIGHT, TOP_LEFT_TOP_RIGHT_BOTTOM_LEFT, TOP_LEFT_BOTTOM_RIGHT_BOTTOM_LEFT, TOP_RIGHT_BOTTOM_RIGHT_BOTTOM_LEFT;
   }

   private CornerType cornerType = null;

   private OtherType otherType;

   public enum OtherType
   {
     STAR, BEAR, HEXAGON;
   }



   public RounImageView(Context context) {
     super(context);
     init();
   }


   public RounImageView(Context context, @Nullable AttributeSet attrs) { this(context, attrs, 0); }


   public RounImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
     super(context, attrs, defStyleAttr);
     obtainStyledAttrs(context, attrs, defStyleAttr);
     init();
   }

   @RequiresApi(api = 21)
   public RounImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
     super(context, attrs, defStyleAttr, defStyleRes);
     obtainStyledAttrs(context, attrs, defStyleAttr);
     init();
   }

   private void obtainStyledAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
     TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RounImageView, defStyleAttr, 0);
     try {
       int ct = a.getInteger(R.styleable.RounImageView_baselib_corner_type, 0);
       this.cornerType = getCornerType(ct);

       int ot = a.getInteger(R.styleable.RounImageView_baselib_other_type, 0);
       this.otherType = getOtherType(ot);

       this.cornerRadius = a.getDimension(R.styleable.RounImageView_baselib_corner_radius, 0.0F);
       this.blurRadius = a.getFloat(R.styleable.RounImageView_baselib_blur_radius, 0.0F);

       this.borderWidth = a.getDimension(R.styleable.RounImageView_baselib_border_width, 0.0F);
       this.borderColor = a.getColor(R.styleable.RounImageView_baselib_border_color, 0);
       this.defaultColor = a.getColor(R.styleable.RounImageView_baselib_default_color, 0);

       this.isCircle = a.getBoolean(R.styleable.RounImageView_baselib_is_circle, false);
       this.hasBorder = a.getBoolean(R.styleable.RounImageView_baselib_has_border, false);
       this.isBlur = a.getBoolean(R.styleable.RounImageView_baselib_is_blur, false);
       this.isOval = a.getBoolean(R.styleable.RounImageView_baselib_is_oval, false);
     } finally {
       a.recycle();
     }
   }

   private void init() {
     if (this.path != null) {
       return;
     }
     this.path = new Path();

     this.mMatrix = new Matrix();
     this.mPaintDrawable = new Paint();
     this.mPaintDrawable.setAntiAlias(true);
     this.mPaintDrawable.setStyle(Paint.Style.FILL);
     this.mPaintDrawable.setStrokeJoin(Paint.Join.ROUND);
     this.mPaintDrawable.setStrokeCap(Paint.Cap.BUTT);
     this.mPaintDrawable.setDither(true);
     this.mPaintDrawable.setShader(null);

     this.mPaintBorder = new Paint(this.mPaintDrawable);
     this.mPaintBorder.setStyle(Paint.Style.STROKE);
     this.mPaintBorder.setStrokeWidth(this.borderWidth);
     this.mPaintBorder.setColor(this.borderColor);
     this.mPaintBorder.setStrokeJoin(Paint.Join.MITER);
   }




   protected void onSizeChanged(int w, int h, int oldw, int oldh) {
     super.onSizeChanged(w, h, oldw, oldh);
     this.width = w;
     this.height = h;
   }


   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
     super.onMeasure(widthMeasureSpec, heightMeasureSpec);



     if (this.isCircle) {
       int result = Math.min(getMeasuredHeight(), getMeasuredWidth());
       setMeasuredDimension(result, result);
     }
   }



   protected void onDraw(Canvas canvas) {
     drawBitmap(canvas);
     if (this.hasBorder) {
       drawBorder(canvas);
     }
   }

   private void drawBitmap(Canvas canvas) {
     Drawable drawable = getDrawable();
     Bitmap bitmap = getBitmap(drawable);

     setUpShader(bitmap);
     if (this.isCircle) {
       float r = this.hasBorder ? (this.width / 2.0F - this.borderWidth) : (this.width / 2.0F);
       canvas.drawCircle(this.width / 2.0F, this.height / 2.0F, r, this.mPaintDrawable);
     } else if (this.cornerType != null) {
       RectF rf;

       float cr = this.cornerRadius - this.borderWidth;
       cr = (cr > 0.0F) ? cr : 0.0F;
       switch (this.cornerType) {
         case ALL:
           if (this.hasBorder) {

             rf = new RectF(this.borderWidth, this.borderWidth, (float)Math.ceil((this.width - this.borderWidth)), (float)Math.ceil((this.height - this.borderWidth)));

             canvas.drawRoundRect(rf, cr, cr, this.mPaintDrawable); break;
           }
           rf = new RectF(0.0F, 0.0F, this.width, this.height);

           canvas.drawRoundRect(rf, this.cornerRadius, this.cornerRadius, this.mPaintDrawable);
           break;


         case TOP_LEFT:
           if (this.hasBorder) {

             canvas.drawArc(new RectF(this.borderWidth, this.borderWidth, this.cornerRadius * 2.0F - this.borderWidth, cr * 2.0F + this.borderWidth), 180.0F, 90.0F, true, this.mPaintDrawable);
             canvas.drawRect(new RectF(this.borderWidth, this.cornerRadius, this.cornerRadius, this.height - this.borderWidth), this.mPaintDrawable);
             canvas.drawRect(new RectF(this.cornerRadius, this.borderWidth, this.width - this.borderWidth, this.height - this.borderWidth), this.mPaintDrawable);
             break;
           }
           canvas.drawRoundRect(new RectF(0.0F, 0.0F, this.cornerRadius * 2.0F, this.cornerRadius * 2.0F), this.cornerRadius, this.cornerRadius, this.mPaintDrawable);
           canvas.drawRect(new RectF(0.0F, this.cornerRadius, this.cornerRadius, this.height), this.mPaintDrawable);
           canvas.drawRect(new RectF(this.cornerRadius, 0.0F, this.width, this.height), this.mPaintDrawable);
           break;
       }

     } else if (this.isOval) {
       RectF rf = this.hasBorder ? new RectF(this.borderWidth, this.borderWidth, (float)Math.ceil((this.width - this.borderWidth)), (float)Math.ceil((this.height - this.borderWidth))) : new RectF(0.0F, 0.0F, this.width, this.height);
       canvas.drawOval(rf, this.mPaintDrawable);
     } else if (this.otherType != null) {
       switch (this.otherType) {

         case HEXAGON:
           if (this.hasBorder) {
             break;
           }
           this.path.reset();
           this.path.moveTo(this.width * 0.25F, 0.0F);
           this.path.lineTo(this.width * 0.75F, 0.0F);
           this.path.lineTo(this.width, this.height * 0.5F);
           this.path.lineTo(this.width * 0.75F, this.height);
           this.path.lineTo(this.width * 0.25F, this.height);
           this.path.lineTo(0.0F, this.height * 0.5F);
           this.path.close();
           canvas.drawPath(this.path, this.mPaintDrawable);
           break;
       }


     } else {
       RectF rf = this.hasBorder ? new RectF(this.borderWidth, this.borderWidth, (float)Math.ceil((this.width - this.borderWidth)), (float)Math.ceil((this.height - this.borderWidth))) : new RectF(0.0F, 0.0F, this.width, this.height);
       canvas.drawRect(rf, this.mPaintDrawable);
     }
   }

   private void drawBorder(Canvas canvas) {
     if (this.isCircle) {

       canvas.drawCircle(this.width / 2.0F, this.height / 2.0F, (this.width - this.borderWidth) / 2.0F, this.mPaintBorder);
     } else if (this.cornerType != null) {
       RectF rf;
       float cr = this.cornerRadius - this.borderWidth / 2.0F;
       cr = (cr > 0.0F) ? cr : 0.0F;
       switch (this.cornerType) {

         case ALL:
           rf = new RectF(this.borderWidth / 2.0F, this.borderWidth / 2.0F, this.width - this.borderWidth / 2.0F, this.height - this.borderWidth / 2.0F);
           canvas.drawRoundRect(rf, cr, cr, this.mPaintBorder);
           break;

         case TOP_LEFT:
           canvas.drawArc(new RectF(this.borderWidth / 2.0F, this.borderWidth / 2.0F, this.cornerRadius * 2.0F - this.borderWidth / 2.0F, this.cornerRadius * 2.0F - this.borderWidth / 2.0F), 180.0F, 90.0F, false, this.mPaintBorder);
           canvas.drawLine(this.cornerRadius, this.borderWidth / 2.0F, this.width, this.borderWidth / 2.0F, this.mPaintBorder);
           canvas.drawLine(this.width - this.borderWidth / 2.0F, this.borderWidth, this.width - this.borderWidth / 2.0F, this.height, this.mPaintBorder);
           canvas.drawLine(this.width - this.borderWidth, this.height - this.borderWidth / 2.0F, 0.0F, this.height - this.borderWidth / 2.0F, this.mPaintBorder);
           canvas.drawLine(this.borderWidth / 2.0F, this.height - this.borderWidth, this.borderWidth / 2.0F, this.cornerRadius, this.mPaintBorder);
           break;
       }

     } else if (this.isOval) {
       RectF rf = new RectF(this.borderWidth / 2.0F, this.borderWidth / 2.0F, this.width - this.borderWidth / 2.0F, this.height - this.borderWidth / 2.0F);
       canvas.drawOval(rf, this.mPaintBorder);
     } else if (this.otherType != null) {
       switch (this.otherType) {

       }






     } else {
       RectF rf = new RectF(this.borderWidth / 2.0F, this.borderWidth / 2.0F, this.width - this.borderWidth / 2.0F, this.height - this.borderWidth / 2.0F);
       canvas.drawRect(rf, this.mPaintBorder);
     }
   }







   private void setUpShader(Bitmap bitmap) {
     this.mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

     int dWidth = bitmap.getWidth();
     int dHeight = bitmap.getHeight();

     int vWidth = this.width;
     int vHeight = this.height;
     if (this.hasBorder) {
       vWidth = (int)(vWidth - 2.0F * this.borderWidth);
       vHeight = (int)(vHeight - 2.0F * this.borderWidth);
     }
     if (dWidth != vWidth || dHeight != vHeight) {


       float scale = 1.0F;
       float dx = 0.0F, dy = 0.0F;

       if (dWidth * vHeight > vWidth * dHeight) {
         scale = vHeight / dHeight;
         dx = (vWidth - dWidth * scale) * 0.5F;
       } else {
         scale = vWidth / dWidth;
         dy = (vHeight - dHeight * scale) * 0.5F;
       }

       this.mMatrix.setScale(scale, scale);
       if (this.hasBorder) {
         dx += this.borderWidth;
         dy += this.borderWidth;
       }


       this.mMatrix.postTranslate(dx, dy);

       this.mBitmapShader.setLocalMatrix(this.mMatrix);
     }

     this.mPaintDrawable.setShader((Shader)this.mBitmapShader);
   }












   private Bitmap getBitmap(Drawable drawable) {
     Bitmap bitmap = null;
     if (drawable instanceof BitmapDrawable) {
       bitmap = ((BitmapDrawable)drawable).getBitmap();
     } else if (drawable instanceof ColorDrawable) {
       bitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888);
       Canvas c = new Canvas(bitmap);
       int color = ((ColorDrawable)drawable).getColor();
       c.drawARGB(Color.alpha(color), Color.red(color), Color.green(color), Color.blue(color));
     } else {
       bitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888);
       Canvas c = new Canvas(bitmap);
       c.drawARGB(Color.alpha(this.defaultColor), Color.red(this.defaultColor), Color.green(this.defaultColor), Color.blue(this.defaultColor));
     }
     if (this.isBlur)
     {
       if (Build.VERSION.SDK_INT >= 17) {
         try {
           bitmap = RSBlur.blur(getContext(), bitmap, (int)this.blurRadius);
         } catch (Exception e) {
           bitmap = FastBlur.blur(bitmap, (int)this.blurRadius, true);
         }
       } else {
         bitmap = FastBlur.blur(bitmap, (int)this.blurRadius, true);
       }
     }
     return bitmap;
   }


   @Nullable
   protected Parcelable onSaveInstanceState() {
     Parcelable superState = super.onSaveInstanceState();
     SavedState savedState = new SavedState(superState);
     savedState.cornerRadius = this.cornerRadius;
     savedState.isCircle = this.isCircle;
     savedState.cornerType = this.cornerType;
     savedState.otherType = this.otherType;
     savedState.blurRadius = this.blurRadius;
     savedState.borderColor = this.borderColor;
     savedState.defaultColor = this.defaultColor;
     savedState.borderWidth = this.borderWidth;
     savedState.hasBorder = this.hasBorder;
     savedState.isBlur = this.isBlur;
     savedState.isOval = this.isOval;
     return (Parcelable)savedState;
   }


   protected void onRestoreInstanceState(Parcelable state) {
     if (state == null || !(state instanceof SavedState)) {
       super.onRestoreInstanceState(state);

       return;
     }
     SavedState savedState = (SavedState)state;

     super.onRestoreInstanceState((Build.VERSION.SDK_INT >= 23) ? (Parcelable)savedState : savedState.getSuperState());

     setHasBorder(savedState.hasBorder);
     setCircle(savedState.isCircle);
     setBlur(savedState.isBlur);
     setOval(savedState.isOval);

     setCornerRadius(savedState.cornerRadius);
     setBorderWidth(savedState.borderWidth);
     setBlurRadius(savedState.blurRadius);

     setBorderColor(savedState.borderColor);
     setDefaultColor(savedState.defaultColor);

     setOtherType(savedState.otherType);
     setCornerType(savedState.cornerType);
     reDraw();
   }

   static class SavedState
     extends View.BaseSavedState {
     int borderColor;
     int defaultColor;
     RounImageView.OtherType otherType;
     RounImageView.CornerType cornerType;
     boolean isCircle;

     public SavedState(Parcelable superState) { super(superState); }
     boolean hasBorder; boolean isBlur; boolean isOval; float cornerRadius; float borderWidth; float blurRadius;

     private SavedState(Parcel in) {
       super(in);
       this.borderColor = in.readInt();
       this.defaultColor = in.readInt();
       this.otherType = (RounImageView.OtherType)in.readSerializable();
       this.cornerType = (RounImageView.CornerType)in.readSerializable();
       this.isCircle = (in.readByte() != 0);
       this.hasBorder = (in.readByte() != 0);
       this.isBlur = (in.readByte() != 0);
       this.isOval = (in.readByte() != 0);
       this.cornerRadius = in.readFloat();
       this.borderWidth = in.readFloat();
       this.blurRadius = in.readFloat();
     }


     public void writeToParcel(Parcel dest, int flags) {
       super.writeToParcel(dest, flags);
       dest.writeInt(this.borderColor);
       dest.writeInt(this.defaultColor);
       dest.writeSerializable(this.otherType);
       dest.writeSerializable(this.cornerType);
       dest.writeByte((byte)((this.isCircle == true) ? 1 : 0));
       dest.writeByte((byte)((this.hasBorder == true) ? 1 : 0));
       dest.writeByte((byte)((this.isBlur == true) ? 1 : 0));
       dest.writeByte((byte)((this.isOval == true) ? 1 : 0));
       dest.writeFloat(this.cornerRadius);
       dest.writeFloat(this.borderWidth);
       dest.writeFloat(this.blurRadius);
     }

     public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>()
       {
         public RounImageView.SavedState createFromParcel(Parcel in) {
           return new RounImageView.SavedState(in);
         }



         public RounImageView.SavedState[] newArray(int size) { return new RounImageView.SavedState[size]; }
       };
   }


   private OtherType getOtherType(int ot) {
     switch (ot) {
       case 1:
         return OtherType.STAR;
       case 2:
         return OtherType.BEAR;
       case 3:
         return OtherType.HEXAGON;
     }
     return null;
   }

   private CornerType getCornerType(int ct) {
     switch (ct) {
       case 1234:
         return CornerType.ALL;
       case 1:
         return CornerType.TOP_LEFT;
       case 2:
         return CornerType.TOP_RIGHT;
       case 3:
         return CornerType.BOTTOM_RIGHT;
       case 4:
         return CornerType.BOTTOM_LEFT;
       case 12:
         return CornerType.TOP_LEFT_TOP_RIGHT;
       case 13:
         return CornerType.TOP_LEFT_BOTTOM_RIGHT;
       case 14:
         return CornerType.TOP_LEFT_BOTTOM_LEFT;
       case 23:
         return CornerType.TOP_RIGHT_BOTTOM_RIGHT;
       case 24:
         return CornerType.TOP_RIGHT_BOTTOM_LEFT;
       case 34:
         return CornerType.BOTTOM_RIGHT_BOTTOM_LEFT;
       case 123:
         return CornerType.TOP_LEFT_TOP_RIGHT_BOTTOM_RIGHT;
       case 124:
         return CornerType.TOP_LEFT_TOP_RIGHT_BOTTOM_LEFT;
       case 134:
         return CornerType.TOP_LEFT_BOTTOM_RIGHT_BOTTOM_LEFT;
       case 234:
         return CornerType.TOP_RIGHT_BOTTOM_RIGHT_BOTTOM_LEFT;
     }
     return null;
   }




   public void reDraw() {
     init();
     postInvalidate();
   }


   public RounImageView setCornerRadius(float cornerRadius) {
     this.cornerRadius = cornerRadius;
     return this;
   }

   public RounImageView setBorderWidth(float borderWidth) {
     this.borderWidth = borderWidth;
     return this;
   }

   public RounImageView setBorderColor(@ColorInt int borderColor) {
     this.borderColor = borderColor;
     return this;
   }

   public RounImageView setDefaultColor(@ColorInt int defaultColor) {
     this.defaultColor = defaultColor;
     return this;
   }

   public RounImageView setCircle(boolean circle) {
     this.isCircle = circle;
     return this;
   }

   public RounImageView setHasBorder(boolean hasBorder) {
     this.hasBorder = hasBorder;
     return this;
   }

   public RounImageView setBlur(boolean blur) {
     this.isBlur = blur;
     return this;
   }

   public RounImageView setBlurRadius(float blurRadius) {
     this.blurRadius = blurRadius;
     return this;
   }

   public RounImageView setCornerType(CornerType cornerType) {
     this.cornerType = cornerType;
     return this;
   }

   public RounImageView setOval(boolean oval) {
     this.isOval = oval;
     return this;
   }

   public RounImageView setOtherType(OtherType otherType) {
     this.otherType = otherType;
     return this;
   }


   public OtherType getOtherType() { return this.otherType; }



   public boolean isOval() { return this.isOval; }



   public CornerType getCornerType() { return this.cornerType; }



   public float getCornerRadius() { return this.cornerRadius; }



   public float getBorderWidth() { return this.borderWidth; }



   public int getBorderColor() { return this.borderColor; }



   public int getDefaultColor() { return this.defaultColor; }



   public boolean isCircle() { return this.isCircle; }



   public boolean isHasBorder() { return this.hasBorder; }



   public boolean isBlur() { return this.isBlur; }



   public float getBlurRadius() { return this.blurRadius; }
 }
