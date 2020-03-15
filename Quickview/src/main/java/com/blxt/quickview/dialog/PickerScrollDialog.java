/*    */ package com.blxt.quickview.dialog;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.view.View;
/*    */ import android.widget.PopupWindow;
/*    */ import android.widget.TextView;
/*    */ import com.blxt.quickview.R;
/*    */ import com.blxt.quickview.select.PickerScrollView;
/*    */ import com.blxt.quickview.window.CommonPopWindow;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PickerScrollDialog
/*    */   extends CommonPopWindow
/*    */ {
/*    */   TextView IdTitle;
/* 19 */   TextView imageBtn = null;
/* 20 */   PickerScrollView addressSelector = null;
/*    */   
/* 22 */   PickerScrollView.onSelectListener listener = null;
/*    */ 
/*    */   
/*    */   public PickerScrollDialog(Context context) {
/* 26 */     super(context);
/*    */     
/* 28 */     loadLayout(R.layout.dialog_picker_selector_bottom);
/*    */     
/* 30 */     this.IdTitle = (TextView)mContentView.findViewById(R.id.__id_title);
/* 31 */     this.imageBtn = (TextView)mContentView.findViewById(R.id.img_guanbi);
/* 32 */     this.addressSelector = (PickerScrollView)mContentView.findViewById(R.id.address);
/*    */ 
/*    */     
/* 35 */     this.addressSelector.setOnSelectListener(new PickerScrollView.onSelectListener()
/*    */         {
/*    */           public void onSelect(int state, PickerScrollView.PickerDataBean pickers) {
/* 38 */             if (PickerScrollDialog.this.listener != null) {
/* 39 */               PickerScrollDialog.this.listener.onSelect(state, pickers);
/*    */             }
/*    */           }
/*    */         });
/*    */ 
/*    */     
/* 45 */     this.imageBtn.setOnClickListener(new View.OnClickListener()
/*    */         {
/*    */           public void onClick(View v) {
/* 48 */             mPopupWindow.dismiss();
/* 49 */             if (PickerScrollDialog.this.listener != null) {
/* 50 */               PickerScrollDialog.this.listener.onSelect(-1, null);
/*    */             }
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PickerScrollDialog setTitle(String title) {
/* 62 */     this.IdTitle.setText(title);
/* 63 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PickerScrollDialog setPickerDatas(List<PickerScrollView.PickerDataBean> pickerDataBeanList) {
/* 72 */     this.addressSelector.setData(pickerDataBeanList);
/* 73 */     this.addressSelector.setSelected(0);
/* 74 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PickerScrollDialog setListener(PickerScrollView.onSelectListener listener) {
/* 82 */     this.listener = listener;
/* 83 */     return this;
/*    */   }
/*    */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\dialog\PickerScrollDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */