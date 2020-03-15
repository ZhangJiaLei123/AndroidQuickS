/*    */ package com.blxt.quickview.time;
/*    */ 
/*    */ import android.annotation.SuppressLint;
/*    */ import android.content.Context;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class Utils
/*    */ {
/*    */   @SuppressLint({"StaticFieldLeak"})
/*    */   private static Context context;
/*    */   
/* 32 */   private Utils() { throw new UnsupportedOperationException("u can't instantiate me..."); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   public static void init(Context context) { Utils.context = context.getApplicationContext(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Context getContext() {
/* 53 */     if (context != null) return context; 
/* 54 */     throw new NullPointerException("u should init first");
/*    */   }
/*    */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\time\Utils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */