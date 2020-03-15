/*     */ package com.blxt.quickview;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.util.AttributeSet;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
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
/*     */ public class AttributeHelper
/*     */ {
/*     */   public static final String ANDROID_NAMESPACE = "http://schemas.android.com/apk/res/android";
/*     */   private Context mContext;
/*     */   private AttributeSet mAttrs;
/*     */   
/*     */   public AttributeHelper(Context context, AttributeSet attrs) {
/*  24 */     this.mContext = context;
/*  25 */     this.mAttrs = attrs;
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
/*  36 */   public boolean hasAttr(String attribute) { return (getValue(attribute) != null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getValue(String attribute) {
/*  46 */     if (this.mAttrs == null) {
/*  47 */       return null;
/*     */     }
/*     */     
/*  50 */     String string = this.mAttrs.getAttributeValue("http://schemas.android.com/apk/res/android", attribute);
/*     */     
/*  52 */     return string;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInt(String attribute, int def) {
/*  62 */     if (this.mAttrs == null) {
/*  63 */       return def;
/*     */     }
/*     */     
/*  66 */     String string = this.mAttrs.getAttributeValue("http://schemas.android.com/apk/res/android", attribute);
/*     */     
/*  68 */     if (string == null) {
/*  69 */       return def;
/*     */     }
/*     */     
/*  72 */     String regEx = "[^0-9]";
/*  73 */     Pattern p = Pattern.compile(regEx);
/*  74 */     Matcher m = p.matcher(string);
/*  75 */     string = m.replaceAll("").trim();
/*     */     
/*     */     try {
/*  78 */       return Integer.parseInt(string);
/*  79 */     } catch (Exception exception) {
/*     */ 
/*     */ 
/*     */       
/*  83 */       return def;
/*     */     } 
/*     */   }
/*     */   public int getColor(String attribute, int def) {
/*  87 */     String string = this.mAttrs.getAttributeValue("http://schemas.android.com/apk/res/android", attribute);
/*     */     
/*  89 */     if (string == null || string.isEmpty()) {
/*  90 */       return def;
/*     */     }
/*     */     
/*  93 */     int color = 0;
/*  94 */     if (string.startsWith("@")) {
/*  95 */       color = Integer.parseInt(string.substring(1, string.length()));
/*  96 */       return getColor(color);
/*     */     } 
/*  98 */     if (string.startsWith("#")) {
/*  99 */       int backRes = Integer.parseInt(string.substring(3, string.length()), 16);
/* 100 */       return backRes | 0xFF000000;
/*     */     } 
/*     */     
/* 103 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getResId(String attribute, int def) {
/* 111 */     String string = getValue(attribute);
/*     */ 
/*     */     
/* 114 */     if (string == null) {
/* 115 */       return def;
/*     */     }
/*     */     
/* 118 */     if (!string.startsWith("@")) {
/* 119 */       return def;
/*     */     }
/* 121 */     int index = string.indexOf("/");
/* 122 */     if (index < 0) {
/* 123 */       string = string.substring(1);
/*     */       try {
/* 125 */         return Integer.parseInt(string);
/* 126 */       } catch (Exception e) {
/* 127 */         return def;
/*     */       } 
/*     */     } 
/*     */     
/* 131 */     String name = string.substring(index + 1);
/* 132 */     String type = string.substring(1, index);
/*     */     
/* 134 */     if (type.equals("mipmap")) {
/* 135 */       return CPResourceUtil.getMipmapId(this.mContext, name);
/*     */     }
/* 137 */     if (type.equals("drawable")) {
/* 138 */       return CPResourceUtil.getDrawableId(this.mContext, name);
/*     */     }
/* 140 */     if (type.equals("layout")) {
/* 141 */       return CPResourceUtil.getLayoutId(this.mContext, name);
/*     */     }
/* 143 */     if (type.equals("string")) {
/* 144 */       return CPResourceUtil.getStringId(this.mContext, name);
/*     */     }
/* 146 */     if (type.equals("style")) {
/* 147 */       return CPResourceUtil.getStyleId(this.mContext, name);
/*     */     }
/* 149 */     if (type.equals("id")) {
/* 150 */       return CPResourceUtil.getId(this.mContext, name);
/*     */     }
/* 152 */     if (type.equals("color")) {
/* 153 */       return CPResourceUtil.getColorId(this.mContext, name);
/*     */     }
/* 155 */     if (type.equals("array")) {
/* 156 */       return CPResourceUtil.getArrayId(this.mContext, name);
/*     */     }
/* 158 */     return def;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString(String attribute, String def) {
/* 168 */     String string = getValue(attribute);
/*     */     
/* 170 */     if (string == null) {
/* 171 */       return def;
/*     */     }
/*     */     
/* 174 */     if (string.startsWith("@")) {
/* 175 */       int resid = getResId(attribute, 0);
/* 176 */       if (resid == 0) {
/* 177 */         return def;
/*     */       }
/*     */       
/* 180 */       return this.mContext.getResources().getString(resid);
/*     */     } 
/*     */     
/* 183 */     return string;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getTextArray(String attribute) {
/* 193 */     String string = getValue(attribute);
/*     */     
/* 195 */     if (string != null && string.startsWith("@")) {
/* 196 */       return this.mContext.getResources().getStringArray(
/* 197 */           Integer.parseInt(string.substring(1)));
/*     */     }
/* 199 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getBoolean(String attribute, boolean def) {
/* 209 */     String string = getValue(attribute);
/*     */     
/* 211 */     if (string == null) {
/* 212 */       return def;
/*     */     }
/*     */     
/* 215 */     if (string != null && string.startsWith("@")) {
/* 216 */       return this.mContext.getResources().getBoolean(
/* 217 */           Integer.parseInt(string.substring(1)));
/*     */     }
/* 219 */     return Boolean.parseBoolean(string);
/*     */   }
/*     */ 
/*     */   
/* 223 */   public int getColor(int id) { return this.mContext.getResources().getColor(id); }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\AttributeHelper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */