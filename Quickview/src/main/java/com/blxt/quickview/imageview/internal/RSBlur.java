/*    */ package com.blxt.quickview.imageview.internal;
/*    */ 
/*    */ import android.annotation.TargetApi;
/*    */ import android.content.Context;
/*    */ import android.graphics.Bitmap;
/*    */ import android.renderscript.Allocation;
/*    */ import android.renderscript.Element;
/*    */ import android.renderscript.RSRuntimeException;
/*    */ import android.renderscript.RenderScript;
/*    */ import android.renderscript.ScriptIntrinsicBlur;
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
/*    */ public class RSBlur
/*    */ {
/*    */   @TargetApi(17)
/*    */   public static Bitmap blur(Context context, Bitmap bitmap, int radius) throws RSRuntimeException {
/* 32 */     RenderScript rs = null;
/* 33 */     Allocation input = null;
/* 34 */     Allocation output = null;
/* 35 */     ScriptIntrinsicBlur blur = null;
/*    */     try {
/* 37 */       rs = RenderScript.create(context);
/* 38 */       rs.setMessageHandler(new RenderScript.RSMessageHandler());
/* 39 */       input = Allocation.createFromBitmap(rs, bitmap, Allocation.MipmapControl.MIPMAP_NONE, 1);
/*    */       
/* 41 */       output = Allocation.createTyped(rs, input.getType());
/* 42 */       blur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
/*    */       
/* 44 */       blur.setInput(input);
/* 45 */       blur.setRadius(radius);
/* 46 */       blur.forEach(output);
/* 47 */       output.copyTo(bitmap);
/*    */     } finally {
/* 49 */       if (rs != null) {
/* 50 */         rs.destroy();
/*    */       }
/* 52 */       if (input != null) {
/* 53 */         input.destroy();
/*    */       }
/* 55 */       if (output != null) {
/* 56 */         output.destroy();
/*    */       }
/* 58 */       if (blur != null) {
/* 59 */         blur.destroy();
/*    */       }
/*    */     } 
/*    */     
/* 63 */     return bitmap;
/*    */   }
/*    */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\imageview\internal\RSBlur.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */