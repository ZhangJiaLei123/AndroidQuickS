/*     */ package com.blxt.quickview.window;
/*     */ 
/*     */ import android.view.View;
/*     */ import android.view.Window;
/*     */ import android.view.WindowManager;
/*     */ import android.widget.PopupWindow;
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
/*     */ public class CommonPopWindowBase
/*     */ {
/*     */   protected static PopupWindow mPopupWindow;
/*     */   protected static View mContentView;
/*     */   protected static Window mWindow;
/*     */   
/*     */   public int getWidth() {
/*  33 */     if (mPopupWindow != null) {
/*  34 */       return mContentView.getMeasuredWidth();
/*     */     }
/*  36 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeight() {
/*  46 */     if (mPopupWindow != null) {
/*  47 */       return mContentView.getMeasuredHeight();
/*     */     }
/*  49 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CommonPopWindowBase showDownPop(View parent) {
/*  56 */     if (parent.getVisibility() == 8) {
/*  57 */       mPopupWindow.showAtLocation(parent, 0, 0, 0);
/*     */     } else {
/*  59 */       int[] location = new int[2];
/*  60 */       parent.getLocationOnScreen(location);
/*  61 */       if (mPopupWindow != null) {
/*  62 */         mPopupWindow.showAtLocation(parent, 0, location[0], location[1] + parent.getHeight());
/*     */       }
/*     */     } 
/*  65 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CommonPopWindowBase showAsUp(View view) {
/*  72 */     if (view.getVisibility() == 8) {
/*  73 */       mPopupWindow.showAtLocation(view, 0, 0, 0);
/*     */     } else {
/*  75 */       int[] location = new int[2];
/*  76 */       view.getLocationOnScreen(location);
/*  77 */       if (mPopupWindow != null) {
/*  78 */         mPopupWindow.showAtLocation(view, 0, location[0], location[1] - view.getHeight());
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  83 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CommonPopWindowBase showAsLeft(View view) {
/*  90 */     if (view.getVisibility() == 8) {
/*  91 */       mPopupWindow.showAtLocation(view, 0, 0, 0);
/*     */     } else {
/*  93 */       int[] location = new int[2];
/*  94 */       view.getLocationOnScreen(location);
/*  95 */       if (mPopupWindow != null) {
/*  96 */         mPopupWindow.showAtLocation(view, 0, location[0] - getWidth(), location[1]);
/*     */       }
/*     */     } 
/*  99 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CommonPopWindowBase showAsRight(View view) {
/* 106 */     if (view.getVisibility() == 8) {
/* 107 */       mPopupWindow.showAtLocation(view, 0, 0, 0);
/*     */     } else {
/* 109 */       int[] location = new int[2];
/* 110 */       view.getLocationOnScreen(location);
/* 111 */       if (mPopupWindow != null) {
/* 112 */         mPopupWindow.showAtLocation(view, 0, location[0] + view.getWidth(), location[1]);
/*     */       }
/*     */     } 
/* 115 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CommonPopWindowBase showAsDown(View view) {
/* 125 */     if (mPopupWindow != null) {
/* 126 */       mPopupWindow.showAsDropDown(view);
/*     */     }
/* 128 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CommonPopWindowBase showAsBottom(View view) {
/* 135 */     if (view.getVisibility() == 8) {
/* 136 */       mPopupWindow.showAtLocation(view, 0, 0, 0);
/*     */     } else {
/* 138 */       int[] location = new int[2];
/* 139 */       view.getLocationOnScreen(location);
/* 140 */       if (mPopupWindow != null) {
/* 141 */         mPopupWindow.showAtLocation(view, 80, 0, 0);
/*     */       }
/*     */     } 
/* 144 */     return this;
/*     */   }
/*     */   
/*     */   public CommonPopWindowBase showAtLocation(View anchor, int gravity, int x, int y) {
/* 148 */     if (mPopupWindow != null) {
/* 149 */       mPopupWindow.showAtLocation(anchor, gravity, x, y);
/*     */     }
/* 151 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void dismiss() {
/* 158 */     if (mWindow != null) {
/* 159 */       WindowManager.LayoutParams params = mWindow.getAttributes();
/* 160 */       params.alpha = 1.0F;
/* 161 */       mWindow.setAttributes(params);
/*     */     } 
/* 163 */     if (mPopupWindow != null && mPopupWindow.isShowing())
/* 164 */       mPopupWindow.dismiss(); 
/*     */   }
/*     */   
/*     */   public static interface ViewClickListener {
/*     */     void getChildView(PopupWindow param1PopupWindow, View param1View, int param1Int);
/*     */   }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\window\CommonPopWindowBase.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */