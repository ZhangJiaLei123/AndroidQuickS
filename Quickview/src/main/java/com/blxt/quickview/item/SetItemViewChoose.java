/*     */ package com.blxt.quickview.item;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.util.AttributeSet;
/*     */ import android.view.LayoutInflater;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import android.widget.ImageView;
/*     */ import com.blxt.quickview.R;
/*     */ import com.blxt.quickview.dialog.ActionSheetDialog;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SetItemViewChoose
/*     */   extends BaseSetItemView
/*     */   implements View.OnClickListener
/*     */ {
/*     */   private ImageView iv_right;
/*  21 */   private int selectId = 0;
/*  22 */   private String[] selectItems = new String[] { "" };
/*     */ 
/*     */   
/*  25 */   public SetItemViewChoose(Context context) { super(context); }
/*     */ 
/*     */   
/*     */   public SetItemViewChoose(Context context, AttributeSet attrs) {
/*  29 */     super(context, attrs);
/*  30 */     LayoutInflater.from(getContext()).inflate(R.layout.__item_set__simple, (ViewGroup)this);
/*  31 */     init();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void init() {
/*  39 */     if (this.instance != null) {
/*     */       return;
/*     */     }
/*  42 */     this.instance = this;
/*     */     
/*  44 */     this.iv_right = (ImageView)findViewById(R.id._item_iright);
/*     */     
/*  46 */     loadStyle();
/*  47 */     addListener();
/*     */     
/*  49 */     initSubView();
/*     */ 
/*     */     
/*  52 */     intiValue(getHintText());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addListener() {
/*  59 */     this.iv_right.setOnClickListener(this);
/*  60 */     this.tv_hint.setOnClickListener(this);
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
/*     */   public void onClick(View v) {
/*  72 */     if (this.clickListener != null && isEnabled()) {
/*  73 */       chooseRfidOutTime(getTitle(), this.selectItems);
/*  74 */       this.clickListener.onClickSetItem((View)this.instance);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*  79 */   public int getSelectId() { return this.selectId; }
/*     */ 
/*     */ 
/*     */   
/*  83 */   public String getSelectItem() { return this.selectItems[this.selectId]; }
/*     */ 
/*     */   
/*     */   public SetItemViewChoose setSelectItems(String[] selectItems) {
/*  87 */     this.selectItems = selectItems;
/*  88 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public void chooseRfidOutTime(String title, String... items) { (new ActionSheetDialog(getContext()))
/*  98 */       .builder()
/*  99 */       .setTitle(title)
/* 100 */       .setCancelable(true)
/* 101 */       .setCanceledOnTouchOutside(true)
/* 102 */       .setCallBack(new ActionSheetDialog.DialogCallBack()
/*     */         {
/*     */           public void OnDismiss(Object obj) {
/* 105 */             SetItemViewChoose.this.selectId = ((Integer)obj).intValue();
/* 106 */             SetItemViewChoose.this.selectId = SetItemViewChoose.this.selectId - 1;
/*     */ 
/*     */ 
/*     */             
/* 110 */             if (SetItemViewChoose.this.clickListener != null) {
/* 111 */               SetItemViewChoose.this.clickListener.onClickSetItem((View)SetItemViewChoose.this.instance);
/*     */ 
/*     */             
/*     */             }
/*     */           }
/* 116 */         }).addSheetItem(ActionSheetDialog.SheetItemColor.Blue, items)
/* 117 */       .show(); }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\item\SetItemViewChoose.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */