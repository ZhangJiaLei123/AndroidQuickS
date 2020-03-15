/*    */ package com.blxt.quickview.viewpager;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.util.AttributeSet;
/*    */ import android.view.MotionEvent;
/*    */ import androidx.annotation.NonNull;
/*    */ import androidx.annotation.Nullable;
/*    */ import androidx.viewpager.widget.ViewPager;
/*    */ 
/*    */ 
/*    */ public class MViewPager
/*    */   extends ViewPager
/*    */ {
/*    */   private boolean isScroll = true;
/*    */   
/* 16 */   public MViewPager(@NonNull Context context) { super(context); }
/*    */ 
/*    */ 
/*    */   
/* 20 */   public MViewPager(@NonNull Context context, @Nullable AttributeSet attrs) { super(context, attrs); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onTouchEvent(MotionEvent arg0) {
/* 26 */     if (this.isScroll) {
/* 27 */       return false;
/*    */     }
/* 29 */     return super.onTouchEvent(arg0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onInterceptTouchEvent(MotionEvent arg0) {
/* 35 */     if (this.isScroll) {
/* 36 */       return false;
/*    */     }
/* 38 */     return super.onInterceptTouchEvent(arg0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 43 */   public void setScroll(boolean isScroll) { this.isScroll = isScroll; }
/*    */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\viewpager\MViewPager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */