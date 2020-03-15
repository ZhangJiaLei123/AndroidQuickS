/*    */ package com.blxt.quickview;
/*    */ 
/*    */ import android.content.Context;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CPResourceUtil
/*    */ {
/*    */   public static int getLayoutId(Context paramContext, String paramString) {
/* 11 */     return paramContext.getResources().getIdentifier(paramString, "layout", paramContext
/* 12 */         .getPackageName());
/*    */   }
/*    */   
/*    */   public static int getStringId(Context paramContext, String paramString) {
/* 16 */     return paramContext.getResources().getIdentifier(paramString, "string", paramContext
/* 17 */         .getPackageName());
/*    */   }
/*    */   
/*    */   public static int getDrawableId(Context paramContext, String paramString) {
/* 21 */     return paramContext.getResources().getIdentifier(paramString, "drawable", paramContext
/* 22 */         .getPackageName());
/*    */   }
/*    */   
/*    */   public static int getMipmapId(Context paramContext, String paramString) {
/* 26 */     return paramContext.getResources().getIdentifier(paramString, "mipmap", paramContext
/* 27 */         .getPackageName());
/*    */   }
/*    */   
/*    */   public static int getStyleId(Context paramContext, String paramString) {
/* 31 */     return paramContext.getResources().getIdentifier(paramString, "style", paramContext
/* 32 */         .getPackageName());
/*    */   }
/*    */ 
/*    */   
/* 36 */   public static int getId(Context paramContext, String paramString) { return paramContext.getResources().getIdentifier(paramString, "id", paramContext.getPackageName()); }
/*    */ 
/*    */   
/*    */   public static int getColorId(Context paramContext, String paramString) {
/* 40 */     return paramContext.getResources().getIdentifier(paramString, "color", paramContext
/* 41 */         .getPackageName());
/*    */   }
/*    */   public static int getArrayId(Context paramContext, String paramString) {
/* 44 */     return paramContext.getResources().getIdentifier(paramString, "array", paramContext
/* 45 */         .getPackageName());
/*    */   }
/*    */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\CPResourceUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */