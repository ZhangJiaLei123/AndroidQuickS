/*     */ package com.blxt.quickview.item;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.util.AttributeSet;
/*     */ import android.view.LayoutInflater;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import android.widget.ImageView;
/*     */ import com.blxt.quickview.R;
/*     */ import java.lang.annotation.Retention;
/*     */ import java.lang.annotation.RetentionPolicy;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SetItemView
/*     */   extends BaseSetItemView
/*     */   implements View.OnClickListener
/*     */ {
/*     */   private ImageView iv_right;
/*     */   
/*  27 */   public SetItemView(Context context) { super(context); }
/*     */ 
/*     */   
/*     */   public SetItemView(Context context, AttributeSet attrs) {
/*  31 */     super(context, attrs);
/*  32 */     LayoutInflater.from(getContext()).inflate(R.layout.__item_set__simple, (ViewGroup)this);
/*     */     
/*  34 */     init();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onFinishInflate() {
/*  44 */     super.onFinishInflate();
/*  45 */     initSubView();
/*  46 */     setVisibility(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void init() {
/*  53 */     if (this.instance != null) {
/*     */       return;
/*     */     }
/*  56 */     this.instance = this;
/*     */     
/*  58 */     this.iv_right = (ImageView)findViewById(R.id._item_iright);
/*     */     
/*  60 */     loadStyle();
/*  61 */     addListener();
/*  62 */     initSubView();
/*     */     
/*  64 */     intiValue(getHintText());
/*     */ 
/*     */     
/*  67 */     String rightVis = this.attributeHelper.getString("visibility", "null");
/*  68 */     if (rightVis.equals("2")) {
/*  69 */       setRightBtnVisibility(4);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addListener() {
/*  80 */     this.iv_right.setOnClickListener(this);
/*  81 */     this.tv_hint.setOnClickListener(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRightBtnVisibility(int visibility) {
/*  92 */     if (visibility == 8) {
/*  93 */       visibility = 4;
/*     */     }
/*  95 */     this.iv_right.setVisibility(visibility);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onClick(View v) {
/* 104 */     if (this.clickListener != null)
/* 105 */       this.clickListener.onClickSetItem((View)this.instance); 
/*     */   }
/*     */   
/*     */   @Retention(RetentionPolicy.SOURCE)
/*     */   public static @interface Visibility {}
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\item\SetItemView.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */