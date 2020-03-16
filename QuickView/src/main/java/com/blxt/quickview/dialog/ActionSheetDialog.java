/*     */ package com.blxt.quickview.dialog;
/*     */ 
/*     */ import android.app.Dialog;
/*     */ import android.content.Context;
/*     */ import android.graphics.Color;
/*     */ import android.os.Build;
/*     */ import android.view.Display;
/*     */ import android.view.LayoutInflater;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import android.view.Window;
/*     */ import android.view.WindowManager;
/*     */ import android.widget.LinearLayout;
/*     */ import android.widget.ScrollView;
/*     */ import android.widget.TextView;
/*     */ import com.blxt.quickview.R;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ActionSheetDialog
/*     */ {
/*     */   private Context context;
/*     */   private Dialog dialog;
/*     */   private TextView txt_title;
/*     */   private TextView txt_cancel;
/*     */   private LinearLayout lLayout_content;
/*     */   private ScrollView sLayout_content;
/*     */   private boolean showTitle = false;
/*     */   private List<SheetItem> sheetItemList;
/*     */   private Display display;
/*     */   DialogCallBack callBack;
/*     */   
/*     */   public ActionSheetDialog(Context context) {
/*  65 */     this.context = context;
/*     */     
/*  67 */     WindowManager windowManager = (WindowManager)context.getSystemService("window");
/*  68 */     this.display = windowManager.getDefaultDisplay();
/*     */   }
/*     */   
/*     */   public ActionSheetDialog builder() {
/*  72 */     View view = LayoutInflater.from(this.context).inflate(R.layout.__action_sheet_, null);
/*     */ 
/*     */     
/*  75 */     view.setMinimumWidth(this.display.getWidth());
/*     */     
/*  77 */     this.sLayout_content = (ScrollView)view.findViewById(R.id.sLayout_content);
/*  78 */     this
/*  79 */       .lLayout_content = (LinearLayout)view.findViewById(R.id.lLayout_content);
/*  80 */     this.txt_title = (TextView)view.findViewById(R.id.txt_title);
/*  81 */     this.txt_cancel = (TextView)view.findViewById(R.id.txt_cancel);
/*  82 */     this.txt_cancel.setOnClickListener(new View.OnClickListener()
/*     */         {
/*     */           public void onClick(View v) {
/*  85 */             ActionSheetDialog.this.dialog.dismiss();
/*     */           }
/*     */         });
/*     */     
/*  89 */     this.dialog = new Dialog(this.context, R.style.DialogTheme);
/*  90 */     this.dialog.setContentView(view);
/*  91 */     Window dialogWindow = this.dialog.getWindow();
/*  92 */     dialogWindow.setGravity(83);
/*  93 */     WindowManager.LayoutParams lp = dialogWindow.getAttributes();
/*  94 */     lp.x = 0;
/*  95 */     lp.y = 0;
/*  96 */     dialogWindow.setAttributes(lp);
/*     */     
/*  98 */     Window window = this.dialog.getWindow();
/*  99 */     window.setGravity(80);
/* 100 */     window.setWindowAnimations(R.style.dialog_animation);
/*     */ 
/*     */     
/* 103 */     if (Build.VERSION.SDK_INT >= 19) {
/* 104 */       int uiOptions = 5894;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 110 */       view.setSystemUiVisibility(uiOptions);
/*     */     } 
/*     */     
/* 113 */     return this;
/*     */   }
/*     */   
/*     */   public ActionSheetDialog setTitle(String title) {
/* 117 */     this.showTitle = true;
/* 118 */     this.txt_title.setVisibility(0);
/* 119 */     this.txt_title.setText(title);
/* 120 */     return this;
/*     */   }
/*     */   
/*     */   public ActionSheetDialog setCancelable(boolean cancel) {
/* 124 */     this.dialog.setCancelable(cancel);
/* 125 */     return this;
/*     */   }
/*     */   
/*     */   public ActionSheetDialog setCanceledOnTouchOutside(boolean cancel) {
/* 129 */     this.dialog.setCanceledOnTouchOutside(cancel);
/* 130 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionSheetDialog addSheetItem(SheetItemColor color, String... strItem) {
/* 135 */     if (this.sheetItemList == null) {
/* 136 */       this.sheetItemList = new ArrayList<>();
/*     */     }
/* 138 */     for (String s : strItem) {
/* 139 */       this.sheetItemList.add(new SheetItem(s, color));
/*     */     }
/* 141 */     return this;
/*     */   }
/*     */   
/*     */   private void setSheetItems() {
/* 145 */     if (this.sheetItemList == null || this.sheetItemList.size() <= 0) {
/*     */       return;
/*     */     }
/*     */     
/* 149 */     int size = this.sheetItemList.size();
/*     */     
/* 151 */     if (size >= 7) {
/*     */       
/* 153 */       LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)this.sLayout_content.getLayoutParams();
/* 154 */       params.height = this.display.getHeight() / 2;
/* 155 */       this.sLayout_content.setLayoutParams((ViewGroup.LayoutParams)params);
/*     */     } 
/*     */     
/* 158 */     for (int i = 1; i <= size; i++) {
/* 159 */       final int index = i;
/* 160 */       SheetItem sheetItem = this.sheetItemList.get(i - 1);
/* 161 */       String strItem = sheetItem.name;
/* 162 */       SheetItemColor color = sheetItem.color;
/*     */       
/* 164 */       TextView textView = new TextView(this.context);
/* 165 */       textView.setText(strItem);
/* 166 */       textView.setTextSize(18.0F);
/* 167 */       textView.setGravity(17);
/*     */       
/* 169 */       if (size == 1) {
/* 170 */         if (this.showTitle) {
/* 171 */           textView.setBackgroundResource(R.drawable.__selector_actionsheet_bottom);
/*     */         } else {
/* 173 */           textView.setBackgroundResource(R.drawable.__selector_actionsheet_single);
/*     */         }
/*     */       
/* 176 */       } else if (this.showTitle) {
/* 177 */         if (i >= 1 && i < size) {
/* 178 */           textView.setBackgroundResource(R.drawable.__selector_actionsheet_middle);
/*     */         } else {
/* 180 */           textView.setBackgroundResource(R.drawable.__selector_actionsheet_bottom);
/*     */         }
/*     */       
/* 183 */       } else if (i == 1) {
/* 184 */         textView.setBackgroundResource(R.drawable.__selector_actionsheet_top);
/* 185 */       } else if (i < size) {
/* 186 */         textView.setBackgroundResource(R.drawable.__selector_actionsheet_middle);
/*     */       } else {
/* 188 */         textView.setBackgroundResource(R.drawable.__selector_actionsheet_bottom);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 193 */       if (color == null) {
/* 194 */         textView.setTextColor(Color.parseColor(SheetItemColor.Blue
/* 195 */               .getName()));
/*     */       } else {
/* 197 */         textView.setTextColor(Color.parseColor(color.getName()));
/*     */       } 
/*     */       
/* 200 */       float scale = (this.context.getResources().getDisplayMetrics()).density;
/* 201 */       int height = (int)(45.0F * scale + 0.5F);
/* 202 */       textView.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, height));
/*     */ 
/*     */       
/* 205 */       textView.setOnClickListener(new View.OnClickListener()
/*     */           {
/*     */             public void onClick(View v) {
/* 208 */               ActionSheetDialog.this.dismiss(index);
/*     */             }
/*     */           });
/*     */       
/* 212 */       this.lLayout_content.addView((View)textView);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void show() {
/* 217 */     setSheetItems();
/*     */ 
/*     */     
/* 220 */     this.dialog.getWindow().setFlags(8, 8);
/* 221 */     this.dialog.show();
/* 222 */     this.dialog.getWindow().clearFlags(8);
/*     */   }
/*     */   
/*     */   public void dismiss(int index) {
/* 226 */     if (this.callBack != null) {
/* 227 */       this.callBack.OnDismiss(Integer.valueOf(index));
/*     */     }
/* 229 */     this.dialog.dismiss();
/*     */   }
/*     */   
/*     */   public ActionSheetDialog setCallBack(DialogCallBack callBack) {
/* 233 */     this.callBack = callBack;
/* 234 */     return this;
/*     */   }
/*     */   
/*     */   public class SheetItem
/*     */   {
/*     */     String name;
/*     */     ActionSheetDialog.SheetItemColor color;
/*     */     
/*     */     public SheetItem(String name, ActionSheetDialog.SheetItemColor color) {
/* 243 */       this.name = name;
/* 244 */       this.color = color;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum SheetItemColor {
/* 249 */     Blue("#037BFF"), Red("#FD4A2E");
/*     */     
/*     */     private String name;
/*     */ 
/*     */     
/* 254 */     SheetItemColor(String name) { this.name = name; }
/*     */ 
/*     */ 
/*     */     
/* 258 */     public String getName() { return this.name; }
/*     */ 
/*     */ 
/*     */     
/* 262 */     public void setName(String name) { this.name = name; }
/*     */   }
/*     */   
/*     */   public static interface DialogCallBack {
/*     */     void OnDismiss(Object param1Object);
/*     */   }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\dialog\ActionSheetDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */