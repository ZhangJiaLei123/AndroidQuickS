/*     */ package com.blxt.quickview.imageview.internal;
/*     */ 
/*     */ import android.graphics.Bitmap;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FastBlur
/*     */ {
/*     */   public static Bitmap blur(Bitmap sentBitmap, int radius, boolean canReuseInBitmap) {
/*     */     Bitmap bitmap;
/*  54 */     if (canReuseInBitmap) {
/*  55 */       bitmap = sentBitmap;
/*     */     } else {
/*  57 */       bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
/*     */     } 
/*     */     
/*  60 */     if (radius < 1) {
/*  61 */       return null;
/*     */     }
/*     */     
/*  64 */     int w = bitmap.getWidth();
/*  65 */     int h = bitmap.getHeight();
/*     */     
/*  67 */     int[] pix = new int[w * h];
/*  68 */     bitmap.getPixels(pix, 0, w, 0, 0, w, h);
/*     */     
/*  70 */     int wm = w - 1;
/*  71 */     int hm = h - 1;
/*  72 */     int wh = w * h;
/*  73 */     int div = radius + radius + 1;
/*     */     
/*  75 */     int[] r = new int[wh];
/*  76 */     int[] g = new int[wh];
/*  77 */     int[] b = new int[wh];
/*     */     
/*  79 */     int[] vmin = new int[Math.max(w, h)];
/*     */     
/*  81 */     int divsum = div + 1 >> 1;
/*  82 */     divsum *= divsum;
/*  83 */     int[] dv = new int[256 * divsum]; int i;
/*  84 */     for (i = 0; i < 256 * divsum; i++) {
/*  85 */       dv[i] = i / divsum;
/*     */     }
/*     */     
/*  88 */     int yi = 0, yw = yi;
/*     */     
/*  90 */     int[][] stack = new int[div][3];
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  95 */     int r1 = radius + 1;
/*     */     
/*     */     int y;
/*     */     
/*  99 */     for (y = 0; y < h; y++) {
/* 100 */       int bsum = 0, gsum = bsum, rsum = gsum, boutsum = rsum, goutsum = boutsum, routsum = goutsum, binsum = routsum, ginsum = binsum, rinsum = ginsum;
/* 101 */       for (i = -radius; i <= radius; i++) {
/* 102 */         int p = pix[yi + Math.min(wm, Math.max(i, 0))];
/* 103 */         int[] sir = stack[i + radius];
/* 104 */         sir[0] = (p & 0xFF0000) >> 16;
/* 105 */         sir[1] = (p & 0xFF00) >> 8;
/* 106 */         sir[2] = p & 0xFF;
/* 107 */         int rbs = r1 - Math.abs(i);
/* 108 */         rsum += sir[0] * rbs;
/* 109 */         gsum += sir[1] * rbs;
/* 110 */         bsum += sir[2] * rbs;
/* 111 */         if (i > 0) {
/* 112 */           rinsum += sir[0];
/* 113 */           ginsum += sir[1];
/* 114 */           binsum += sir[2];
/*     */         } else {
/* 116 */           routsum += sir[0];
/* 117 */           goutsum += sir[1];
/* 118 */           boutsum += sir[2];
/*     */         } 
/*     */       } 
/* 121 */       int stackpointer = radius;
/*     */       
/* 123 */       for (int x = 0; x < w; x++) {
/*     */         
/* 125 */         r[yi] = dv[rsum];
/* 126 */         g[yi] = dv[gsum];
/* 127 */         b[yi] = dv[bsum];
/*     */         
/* 129 */         rsum -= routsum;
/* 130 */         gsum -= goutsum;
/* 131 */         bsum -= boutsum;
/*     */         
/* 133 */         int stackstart = stackpointer - radius + div;
/* 134 */         int[] sir = stack[stackstart % div];
/*     */         
/* 136 */         routsum -= sir[0];
/* 137 */         goutsum -= sir[1];
/* 138 */         boutsum -= sir[2];
/*     */         
/* 140 */         if (y == 0) {
/* 141 */           vmin[x] = Math.min(x + radius + 1, wm);
/*     */         }
/* 143 */         int p = pix[yw + vmin[x]];
/*     */         
/* 145 */         sir[0] = (p & 0xFF0000) >> 16;
/* 146 */         sir[1] = (p & 0xFF00) >> 8;
/* 147 */         sir[2] = p & 0xFF;
/*     */         
/* 149 */         rinsum += sir[0];
/* 150 */         ginsum += sir[1];
/* 151 */         binsum += sir[2];
/*     */         
/* 153 */         rsum += rinsum;
/* 154 */         gsum += ginsum;
/* 155 */         bsum += binsum;
/*     */         
/* 157 */         stackpointer = (stackpointer + 1) % div;
/* 158 */         sir = stack[stackpointer % div];
/*     */         
/* 160 */         routsum += sir[0];
/* 161 */         goutsum += sir[1];
/* 162 */         boutsum += sir[2];
/*     */         
/* 164 */         rinsum -= sir[0];
/* 165 */         ginsum -= sir[1];
/* 166 */         binsum -= sir[2];
/*     */         
/* 168 */         yi++;
/*     */       } 
/* 170 */       yw += w;
/*     */     } 
/* 172 */     for (int x = 0; x < w; x++) {
/* 173 */       int bsum = 0, gsum = bsum, rsum = gsum, boutsum = rsum, goutsum = boutsum, routsum = goutsum, binsum = routsum, ginsum = binsum, rinsum = ginsum;
/* 174 */       int yp = -radius * w;
/* 175 */       for (i = -radius; i <= radius; i++) {
/* 176 */         yi = Math.max(0, yp) + x;
/*     */         
/* 178 */         int[] sir = stack[i + radius];
/*     */         
/* 180 */         sir[0] = r[yi];
/* 181 */         sir[1] = g[yi];
/* 182 */         sir[2] = b[yi];
/*     */         
/* 184 */         int rbs = r1 - Math.abs(i);
/*     */         
/* 186 */         rsum += r[yi] * rbs;
/* 187 */         gsum += g[yi] * rbs;
/* 188 */         bsum += b[yi] * rbs;
/*     */         
/* 190 */         if (i > 0) {
/* 191 */           rinsum += sir[0];
/* 192 */           ginsum += sir[1];
/* 193 */           binsum += sir[2];
/*     */         } else {
/* 195 */           routsum += sir[0];
/* 196 */           goutsum += sir[1];
/* 197 */           boutsum += sir[2];
/*     */         } 
/*     */         
/* 200 */         if (i < hm) {
/* 201 */           yp += w;
/*     */         }
/*     */       } 
/* 204 */       yi = x;
/* 205 */       int stackpointer = radius;
/* 206 */       for (y = 0; y < h; y++) {
/*     */         
/* 208 */         pix[yi] = 0xFF000000 & pix[yi] | dv[rsum] << 16 | dv[gsum] << 8 | dv[bsum];
/*     */         
/* 210 */         rsum -= routsum;
/* 211 */         gsum -= goutsum;
/* 212 */         bsum -= boutsum;
/*     */         
/* 214 */         int stackstart = stackpointer - radius + div;
/* 215 */         int[] sir = stack[stackstart % div];
/*     */         
/* 217 */         routsum -= sir[0];
/* 218 */         goutsum -= sir[1];
/* 219 */         boutsum -= sir[2];
/*     */         
/* 221 */         if (x == 0) {
/* 222 */           vmin[y] = Math.min(y + r1, hm) * w;
/*     */         }
/* 224 */         int p = x + vmin[y];
/*     */         
/* 226 */         sir[0] = r[p];
/* 227 */         sir[1] = g[p];
/* 228 */         sir[2] = b[p];
/*     */         
/* 230 */         rinsum += sir[0];
/* 231 */         ginsum += sir[1];
/* 232 */         binsum += sir[2];
/*     */         
/* 234 */         rsum += rinsum;
/* 235 */         gsum += ginsum;
/* 236 */         bsum += binsum;
/*     */         
/* 238 */         stackpointer = (stackpointer + 1) % div;
/* 239 */         sir = stack[stackpointer];
/*     */         
/* 241 */         routsum += sir[0];
/* 242 */         goutsum += sir[1];
/* 243 */         boutsum += sir[2];
/*     */         
/* 245 */         rinsum -= sir[0];
/* 246 */         ginsum -= sir[1];
/* 247 */         binsum -= sir[2];
/*     */         
/* 249 */         yi += w;
/*     */       } 
/*     */     } 
/*     */     
/* 253 */     bitmap.setPixels(pix, 0, w, 0, 0, w, h);
/*     */     
/* 255 */     return bitmap;
/*     */   }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\imageview\internal\FastBlur.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */