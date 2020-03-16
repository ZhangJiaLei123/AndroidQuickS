/*    */ package com.blxt.quickview.item;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.view.LayoutInflater;
/*    */ import android.view.ViewGroup;
/*    */ import android.widget.LinearLayout;
/*    */ import android.widget.TextView;
/*    */ import com.blxt.quickview.R;
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
/*    */ public class SetItemViewSeparator
/*    */   extends LinearLayout
/*    */ {
/*    */   private TextView ItemTitle;
/*    */   
/*    */   public SetItemViewSeparator(Context context, String title, int colorResId) {
/* 27 */     super(context);
/* 28 */     LayoutInflater.from(getContext()).inflate(R.layout.__item_set_separator, (ViewGroup)this);
/*    */     
/* 30 */     this.ItemTitle = (TextView)findViewById(R.id._item_title);
/* 31 */     if (title != null) {
/* 32 */       this.ItemTitle.setText(title);
/*    */     } else {
/*    */       
/* 35 */       this.ItemTitle.setVisibility(8);
/* 36 */       removeAllViews();
/*    */       
/* 38 */       LinearLayout.LayoutParams layout_923 = new LinearLayout.LayoutParams(-1, 1);
/* 39 */       layout_923.leftMargin = 50;
/* 40 */       layout_923.rightMargin = 50;
/*    */       
/* 42 */       setLayoutParams((ViewGroup.LayoutParams)layout_923);
/*    */     } 
/*    */     
/* 45 */     if (colorResId != 0) {
/* 46 */       setBackgroundResource(colorResId);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/* 51 */   public SetItemViewSeparator(Context context, String title) { this(context, title, 0); }
/*    */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\item\SetItemViewSeparator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */