/*     */ package com.blxt.quickview.window;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.content.Context;
/*     */ import android.graphics.drawable.ColorDrawable;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import android.view.LayoutInflater;
/*     */ import android.view.View;
/*     */ import android.view.WindowManager;
/*     */ import android.widget.PopupWindow;
/*     */ import androidx.annotation.FloatRange;
/*     */ import androidx.annotation.LayoutRes;
/*     */ import androidx.annotation.StyleRes;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommonPopWindow
/*     */   extends CommonPopWindowBase
/*     */   implements PopupWindow.OnDismissListener
/*     */ {
/*     */   protected Context mContext;
/*  23 */   protected int mLayoutResId = 0;
/*     */   
/*     */   protected int mWidth;
/*     */   
/*     */   protected int mHeight;
/*     */   
/*     */   protected int mAnimationStyle;
/*     */   protected Drawable mDrawable;
/*  31 */   protected float mDarkAlpha = 1.0F; protected boolean mTouchable = true;
/*     */   protected boolean mFocusable = true;
/*     */   protected boolean mOutsideTouchable = true;
/*     */   protected boolean mBackgroundDarkEnable = false;
/*     */   protected CommonPopWindowBase.ViewClickListener mListener;
/*     */   
/*  37 */   public CommonPopWindow(Context context) { this.mContext = context; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CommonPopWindow loadLayout(@LayoutRes int layoutResId) {
/*  48 */     this.mLayoutResId = layoutResId;
/*     */     
/*  50 */     if (this.mLayoutResId != 0) {
/*  51 */       mContentView = LayoutInflater.from(this.mContext).inflate(this.mLayoutResId, null);
/*     */     }
/*     */     
/*  54 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CommonPopWindow build() {
/*  65 */     if (this.mWidth != 0 && this.mHeight != 0) {
/*  66 */       mPopupWindow = new PopupWindow(mContentView, this.mWidth, this.mHeight);
/*     */     } else {
/*  68 */       mPopupWindow = new PopupWindow(mContentView, -2, -2);
/*     */     } 
/*     */ 
/*     */     
/*  72 */     mPopupWindow.setTouchable(this.mTouchable);
/*  73 */     mPopupWindow.setFocusable(this.mFocusable);
/*  74 */     mPopupWindow.setOutsideTouchable(this.mOutsideTouchable);
/*     */     
/*  76 */     if (this.mDrawable != null) {
/*  77 */       mPopupWindow.setBackgroundDrawable(this.mDrawable);
/*     */     } else {
/*  79 */       mPopupWindow.setBackgroundDrawable((Drawable)new ColorDrawable());
/*     */     } 
/*  81 */     if (this.mAnimationStyle != -1) {
/*  82 */       mPopupWindow.setAnimationStyle(this.mAnimationStyle);
/*     */     }
/*  84 */     if (this.mWidth == 0 || this.mHeight == 0) {
/*  85 */       measureWidthAndHeight(mContentView);
/*     */       
/*  87 */       this.mWidth = mPopupWindow.getContentView().getMeasuredWidth();
/*  88 */       this.mHeight = mPopupWindow.getContentView().getMeasuredHeight();
/*     */     } 
/*     */     
/*  91 */     Activity activity = (Activity)this.mContext;
/*  92 */     if (activity != null && this.mBackgroundDarkEnable) {
/*  93 */       float alpha = (this.mDarkAlpha >= 0.0F || this.mDarkAlpha <= 1.0F) ? this.mDarkAlpha : 0.7F;
/*  94 */       mWindow = activity.getWindow();
/*  95 */       WindowManager.LayoutParams params = mWindow.getAttributes();
/*  96 */       params.alpha = alpha;
/*  97 */       mWindow.setAttributes(params);
/*     */     } 
/*     */     
/* 100 */     mPopupWindow.setOnDismissListener(this);
/*     */     
/* 102 */     mPopupWindow.update();
/*     */     
/* 104 */     if (this.mListener != null && this.mLayoutResId != 0) {
/* 105 */       this.mListener.getChildView(mPopupWindow, mContentView, this.mLayoutResId);
/*     */     }
/*     */     
/* 108 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public void onDismiss() { dismiss(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private CommonPopWindow measureWidthAndHeight(View mContentView) {
/* 125 */     int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(1073741823, -2147483648);
/* 126 */     int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(1073741823, -2147483648);
/* 127 */     mContentView.measure(widthMeasureSpec, heightMeasureSpec);
/* 128 */     return this;
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
/*     */   public CommonPopWindow setSize(int width, int height) {
/* 140 */     this.mWidth = width;
/* 141 */     this.mHeight = height;
/* 142 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CommonPopWindow setBackgroundDrawable(Drawable drawable) {
/* 152 */     this.mDrawable = drawable;
/* 153 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CommonPopWindow setBackgroundDarkEnable(boolean darkEnable) {
/* 163 */     this.mBackgroundDarkEnable = darkEnable;
/* 164 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CommonPopWindow setBackgroundAlpha(@FloatRange(from = 0.0D, to = 1.0D) float dackAlpha) {
/* 174 */     this.mDarkAlpha = dackAlpha;
/* 175 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CommonPopWindow setOutsideTouchable(boolean touchable) {
/* 185 */     this.mOutsideTouchable = touchable;
/* 186 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CommonPopWindow setTouchable(boolean touchable) {
/* 196 */     this.mTouchable = touchable;
/* 197 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CommonPopWindow setAnimationStyle(@StyleRes int animationStyle) {
/* 207 */     this.mAnimationStyle = animationStyle;
/* 208 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CommonPopWindow setFocusable(boolean focusable) {
/* 218 */     this.mFocusable = focusable;
/* 219 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CommonPopWindow setViewOnClickListener(CommonPopWindowBase.ViewClickListener listener) {
/* 229 */     this.mListener = listener;
/* 230 */     return this;
/*     */   }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\window\CommonPopWindow.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */