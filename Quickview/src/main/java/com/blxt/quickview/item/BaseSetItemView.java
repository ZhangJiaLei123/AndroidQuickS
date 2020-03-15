/*     */ package com.blxt.quickview.item;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.SharedPreferences;
/*     */ import android.graphics.Typeface;
/*     */ import android.util.AttributeSet;
/*     */ import android.view.View;
/*     */ import android.widget.ImageView;
/*     */ import android.widget.RelativeLayout;
/*     */ import android.widget.TextView;
/*     */ import com.blxt.quickview.AttributeHelper;
/*     */ import com.blxt.quickview.R;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BaseSetItemView
/*     */   extends RelativeLayout
/*     */ {
/*  23 */   protected static SharedPreferences sharedPreferences = null;
/*     */   
/*     */   SharedPreferences getSP(Context context) {
/*  26 */     if (sharedPreferences == null) {
/*  27 */       sharedPreferences = context.getSharedPreferences(context.getPackageName() + "_preferences", 0);
/*     */     }
/*  29 */     return sharedPreferences;
/*     */   }
/*     */   
/*  32 */   AttributeHelper attributeHelper = null;
/*     */   
/*     */   protected BaseSetItemView instance;
/*     */   
/*     */   protected ImageView iv_logo;
/*     */   
/*     */   protected TextView tv_title;
/*     */   
/*     */   protected TextView tv_hint;
/*     */   protected View iv_bottom;
/*  42 */   protected List<View> subView = new ArrayList<>();
/*     */   
/*  44 */   private CharSequence tipText = null;
/*  45 */   private String spKey = null;
/*     */   OnClickListenerCallBack clickListener;
/*  47 */   public BaseSetItemView(Context context) { super(context);
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
/* 305 */     this.clickListener = null; } public BaseSetItemView(Context context, AttributeSet attrs) { super(context, attrs); this.clickListener = null;
/*     */     initBase(context, attrs); }
/*     */ 
/*     */   
/*     */   private void initBase(Context context, AttributeSet attrs) {
/*     */     sharedPreferences = getSP(context);
/*     */     this.attributeHelper = new AttributeHelper(getContext(), attrs);
/*     */   }
/*     */   
/*     */   protected void loadStyle() {
/*     */     this.iv_bottom = findViewById(R.id._item_bottom);
/*     */     this.iv_logo = (ImageView)findViewById(R.id._item_title_ic);
/*     */     this.tv_title = (TextView)findViewById(R.id._item_title);
/*     */     this.tv_hint = (TextView)findViewById(R.id._item_tip);
/*     */     String textStyle = this.attributeHelper.getString("textStyle", "null");
/*     */     if (textStyle.equals("bold")) {
/*     */       this.tv_title.setTypeface(Typeface.DEFAULT, 1);
/*     */     } else {
/*     */       this.tv_title.setTypeface(Typeface.DEFAULT, 0);
/*     */     } 
/*     */     String titleStr = this.attributeHelper.getString("text", "");
/*     */     this.tv_title.setText(titleStr);
/*     */     String hintStr = this.attributeHelper.getString("hint", "");
/*     */     this.tv_hint.setText(hintStr);
/*     */     int titleColor = this.attributeHelper.getColor("textColor", -16449536);
/*     */     this.tv_title.setTextColor(titleColor);
/*     */     int hintColor = this.attributeHelper.getColor("textColorHint", -7698810);
/*     */     this.tv_hint.setTextColor(hintColor);
/*     */     int titleSize = this.attributeHelper.getInt("textSize", 16);
/*     */     this.tv_title.setTextSize(titleSize);
/*     */     int hintSize = this.attributeHelper.getInt("minHeight", titleSize);
/*     */     this.tv_hint.setTextSize(hintSize);
/*     */     int titleIcResId = this.attributeHelper.getResId("src", 0);
/*     */     if (titleIcResId != 0)
/*     */       this.iv_logo.setImageResource(titleIcResId); 
/*     */   }
/*     */   
/*     */   protected void initSubView() {
/*     */     int visibility = getVisibility();
/*     */     if (this.subView != null)
/*     */       setSubViewVisibility(visibility); 
/*     */   }
/*     */   
/*     */   protected void intiValue(String value) {
/*     */     String tmp = getValue();
/*     */     if (tmp == null || tmp.length() == 0) {
/*     */       this.tv_hint.setText(value);
/*     */     } else {
/*     */       this.tv_hint.setText(tmp);
/*     */     } 
/*     */   }
/*     */   
/*     */   public BaseSetItemView setTitle(String title) {
/*     */     this.tv_title.setText(title);
/*     */     return this;
/*     */   }
/*     */   
/*     */   public String getTitle() { return this.tv_title.getText().toString(); }
/*     */   
/*     */   public BaseSetItemView setTitleImage(int res) {
/*     */     this.iv_logo.setBackgroundResource(res);
/*     */     return this;
/*     */   }
/*     */   
/*     */   public BaseSetItemView setHint(String tipText) {
/*     */     this.tipText = tipText;
/*     */     this.tv_hint.setText(tipText);
/*     */     return this;
/*     */   }
/*     */   
/*     */   public BaseSetItemView setHint(int tipText) {
/*     */     this.tv_hint.setText(tipText);
/*     */     this.tipText = this.tv_hint.getText();
/*     */     return this;
/*     */   }
/*     */   
/*     */   public BaseSetItemView setHint(CharSequence tipText, String vueal) {
/*     */     this.tipText = tipText;
/*     */     this.tv_hint.setText(tipText);
/*     */     return this;
/*     */   }
/*     */   
/*     */   public String getHintText() { return this.tv_hint.getText().toString(); }
/*     */   
/*     */   public BaseSetItemView setSubView(View view) {
/*     */     this.subView.add(view);
/*     */     return this;
/*     */   }
/*     */   
/*     */   public TextView getTv_title() { return this.tv_title; }
/*     */   
/*     */   public TextView getTv_hint() { return this.tv_hint; }
/*     */   
/*     */   public void setOnClickListener(OnClickListenerCallBack clickListener) { this.clickListener = clickListener; }
/*     */   
/*     */   public View getView() { return (View)this; }
/*     */   
/*     */   public void setVisibility(int visibility) {
/*     */     super.setVisibility(visibility);
/*     */     if (this.subView != null)
/*     */       setSubViewVisibility(visibility); 
/*     */   }
/*     */   
/*     */   protected void setSubViewVisibility(int visibility) {
/*     */     for (int i = 0; i < this.subView.size(); i++)
/*     */       ((View)this.subView.get(i)).setVisibility(visibility); 
/*     */   }
/*     */   
/*     */   public SharedPreferences getSharedPreferences() { return sharedPreferences; }
/*     */   
/*     */   public boolean saveValue(String value) {
/*     */     if (sharedPreferences != null) {
/*     */       String key = getKey();
/*     */       if (key == null || key.length() <= 0)
/*     */         return false; 
/*     */       sharedPreferences.edit().putString(getKey(), value).commit();
/*     */       return true;
/*     */     } 
/*     */     return false;
/*     */   }
/*     */   
/*     */   public String getValue() { return sharedPreferences.getString(getKey(), ""); }
/*     */   
/*     */   public BaseSetItemView setSpKey(String key) {
/*     */     this.spKey = key;
/*     */     return this;
/*     */   }
/*     */   
/*     */   public String getKey() {
/*     */     String key = null;
/*     */     if (this.spKey != null) {
/*     */       key = this.spKey;
/*     */     } else if (this.tv_title.getText() != null) {
/*     */       key = this.tv_title.getText().toString();
/*     */     } 
/*     */     if ((key == null || key.trim().length() <= 0) && getTag() != null)
/*     */       key = getTag().toString(); 
/*     */     if (key == null || key.trim().length() <= 0)
/*     */       key = getId() + ""; 
/*     */     return key;
/*     */   }
/*     */   
/*     */   public void setEnabled(boolean fal) { super.setEnabled(fal); }
/*     */   
/*     */   public static interface OnClickListenerCallBack {
/*     */     boolean onClickSetItem(View param1View);
/*     */   }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\item\BaseSetItemView.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */