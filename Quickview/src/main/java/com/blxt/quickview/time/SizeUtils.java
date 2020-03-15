/*     */ package com.blxt.quickview.time;
/*     */ 
/*     */ import android.util.DisplayMetrics;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
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
/*     */ public final class SizeUtils
/*     */ {
/*  29 */   private SizeUtils() { throw new UnsupportedOperationException("u can't instantiate me..."); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int dp2px(float dpValue) {
/*  39 */     float scale = (Utils.getContext().getResources().getDisplayMetrics()).density;
/*  40 */     return (int)(dpValue * scale + 0.5F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int px2dp(float pxValue) {
/*  50 */     float scale = (Utils.getContext().getResources().getDisplayMetrics()).density;
/*  51 */     return (int)(pxValue / scale + 0.5F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int sp2px(float spValue) {
/*  61 */     float fontScale = (Utils.getContext().getResources().getDisplayMetrics()).scaledDensity;
/*  62 */     return (int)(spValue * fontScale + 0.5F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int px2sp(float pxValue) {
/*  72 */     float fontScale = (Utils.getContext().getResources().getDisplayMetrics()).scaledDensity;
/*  73 */     return (int)(pxValue / fontScale + 0.5F);
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
/*     */   public static float applyDimension(int unit, float value, DisplayMetrics metrics) {
/*  86 */     switch (unit) {
/*     */       case 0:
/*  88 */         return value;
/*     */       case 1:
/*  90 */         return value * metrics.density;
/*     */       case 2:
/*  92 */         return value * metrics.scaledDensity;
/*     */       case 3:
/*  94 */         return value * metrics.xdpi * 0.013888889F;
/*     */       case 4:
/*  96 */         return value * metrics.xdpi;
/*     */       case 5:
/*  98 */         return value * metrics.xdpi * 0.03937008F;
/*     */     } 
/* 100 */     return 0.0F;
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
/*     */   public static void forceGetViewSize(final View view, final onGetSizeListener listener) {
/* 120 */     view.post(new Runnable()
/*     */         {
/*     */           public void run() {
/* 123 */             if (listener != null) {
/* 124 */               listener.onGetSize(view);
/*     */             }
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface onGetSizeListener
/*     */   {
/*     */     void onGetSize(View param1View);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int[] measureView(View view) {
/*     */     int heightSpec;
/* 144 */     ViewGroup.LayoutParams lp = view.getLayoutParams();
/* 145 */     if (lp == null) {
/* 146 */       lp = new ViewGroup.LayoutParams(-1, -2);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 151 */     int widthSpec = ViewGroup.getChildMeasureSpec(0, 0, lp.width);
/* 152 */     int lpHeight = lp.height;
/*     */     
/* 154 */     if (lpHeight > 0) {
/* 155 */       heightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight, 1073741824);
/*     */     } else {
/* 157 */       heightSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
/*     */     } 
/* 159 */     view.measure(widthSpec, heightSpec);
/* 160 */     return new int[] { view.getMeasuredWidth(), view.getMeasuredHeight() };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 170 */   public static int getMeasuredWidth(View view) { return measureView(view)[0]; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 180 */   public static int getMeasuredHeight(View view) { return measureView(view)[1]; }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\time\SizeUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */