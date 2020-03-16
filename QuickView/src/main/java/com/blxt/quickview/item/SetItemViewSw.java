/*    */ package com.blxt.quickview.item;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.util.AttributeSet;
/*    */ import android.view.LayoutInflater;
/*    */ import android.view.View;
/*    */ import android.view.ViewGroup;
/*    */ import android.widget.CompoundButton;
/*    */ import android.widget.Switch;
/*    */ import com.blxt.quickview.R;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SetItemViewSw
/*    */   extends BaseSetItemView
/*    */   implements CompoundButton.OnCheckedChangeListener
/*    */ {
/* 19 */   final String ORIENTATION_VERTICAL = "vertical";
/* 20 */   final String ORIENTATION_HORIZONTAL = "horizontal";
/*    */   
/*    */   private Switch aSwitch;
/* 23 */   String orientation = "vertical";
/*    */ 
/*    */   
/* 26 */   public SetItemViewSw(Context context) { super(context); }
/*    */ 
/*    */ 
/*    */   
/*    */   public SetItemViewSw(Context context, AttributeSet attrs) {
/* 31 */     super(context, attrs);
/*    */     
/* 33 */     this.orientation = this.attributeHelper.getString("orientation", this.orientation);
/* 34 */     if (this.orientation.equals("vertical")) {
/* 35 */       LayoutInflater.from(getContext()).inflate(R.layout.__item_set__sw_v, (ViewGroup)this);
/*    */     }
/* 37 */     else if (this.orientation.equals("horizontal")) {
/* 38 */       LayoutInflater.from(getContext()).inflate(R.layout.__item_set__sw, (ViewGroup)this);
/*    */     } 
/*    */     
/* 41 */     init();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void init() {
/* 49 */     if (this.instance != null) {
/*    */       return;
/*    */     }
/* 52 */     this.instance = this;
/*    */     
/* 54 */     this.aSwitch = (Switch)findViewById(R.id._item_sw);
/*    */     
/* 56 */     boolean fal = this.attributeHelper.getBoolean("checked", false);
/* 57 */     this.aSwitch.setChecked(fal);
/*    */     
/* 59 */     loadStyle();
/* 60 */     addListener();
/*    */     
/* 62 */     initSubView();
/*    */ 
/*    */     
/* 65 */     intiValue(getHintText());
/*    */   }
/*    */ 
/*    */   
/* 69 */   private void addListener() { this.aSwitch.setOnCheckedChangeListener(this); }
/*    */ 
/*    */ 
/*    */   
/* 73 */   public void setTextOff(String textOff) { this.aSwitch.setTextOff(textOff); }
/*    */ 
/*    */ 
/*    */   
/* 77 */   public void setTextOn(String textOn) { this.aSwitch.setTextOn(textOn); }
/*    */ 
/*    */ 
/*    */   
/* 81 */   public void setEnabled(boolean isbootom) { this.aSwitch.setEnabled(isbootom); }
/*    */ 
/*    */ 
/*    */   
/* 85 */   public void setChecked(boolean checked) { this.aSwitch.setChecked(checked); }
/*    */ 
/*    */ 
/*    */   
/* 89 */   public boolean isChecked() { return this.aSwitch.isChecked(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
/* 95 */     if (this.clickListener != null)
/* 96 */       this.clickListener.onClickSetItem((View)this.instance); 
/*    */   }
/*    */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\item\SetItemViewSw.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */