/*     */ package com.blxt.quickview.menu;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.util.AttributeSet;
/*     */ import android.view.View;
/*     */ import android.widget.LinearLayout;
/*     */ import com.blxt.quickview.button.TextBotton;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MenuBar
/*     */   extends LinearLayout
/*     */   implements View.OnClickListener
/*     */ {
/*  19 */   private List<TextBotton> menus = null;
/*  20 */   Context context = null;
/*  21 */   MenuBarChooseListener chooseListener = null;
/*  22 */   private int selectId = 0;
/*     */ 
/*     */   
/*  25 */   public MenuBar(Context context) { super(context); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  30 */   public MenuBar(Context context, AttributeSet attrs) { this(context, attrs, 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   public MenuBar(Context context, AttributeSet attrs, int defStyle) { super(context, attrs, defStyle); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onFinishInflate() {
/*  46 */     super.onFinishInflate();
/*  47 */     initSubView();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initSubView() {
/*  54 */     if (this.menus != null) {
/*  55 */       this.menus.clear();
/*     */     }
/*  57 */     for (int i = 0; i < getChildCount(); i++) {
/*  58 */       View view = getChildAt(i);
/*  59 */       if (view instanceof TextBotton) {
/*  60 */         addMenuBtn((TextBotton)view);
/*     */       }
/*     */     } 
/*  63 */     if (getChildCount() > 0) {
/*  64 */       chooseMenu(getChildAt(0));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void addView(View child) {
/*  70 */     super.addView(child);
/*  71 */     if (child instanceof TextBotton) {
/*  72 */       addMenuBtn((TextBotton)child);
/*     */     } else {
/*     */       
/*  75 */       child.setOnClickListener(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addMenuBtn(TextBotton view) {
/*  84 */     if (this.menus == null || view == null) {
/*  85 */       this.menus = new ArrayList<>();
/*     */     }
/*  87 */     this.menus.add(view);
/*  88 */     view.setOnClickListener(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TextBotton getMenuButton(int index) {
/*  95 */     if (this.menus == null || index >= this.menus.size()) {
/*  96 */       return null;
/*     */     }
/*  98 */     return this.menus.get(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTextSize(int textSize) {
/* 103 */     for (TextBotton botton : this.menus) {
/* 104 */       botton.setTextSize(textSize);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHintImageOn(int resId) {
/* 112 */     for (TextBotton botton : this.menus) {
/* 113 */       botton.setHintImageOn(resId);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHintImageOff(int resId) {
/* 120 */     for (TextBotton botton : this.menus) {
/* 121 */       botton.setHintImageOff(resId);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTextColorOn(int textColorOn) {
/* 130 */     for (TextBotton botton : this.menus) {
/* 131 */       botton.setHintImageOff(textColorOn);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTextColorOff(int textColorOff) {
/* 140 */     for (TextBotton botton : this.menus) {
/* 141 */       botton.setHintImageOff(textColorOff);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocationDef(int locationDef) {
/* 150 */     for (TextBotton botton : this.menus) {
/* 151 */       botton.setHintImageOff(locationDef);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void chooseMenu(View view) {
/* 161 */     if (view == null) {
/*     */       return;
/*     */     }
/*     */     
/* 165 */     boolean fal = true;
/* 166 */     if (this.chooseListener != null) {
/* 167 */       fal = this.chooseListener.OnMenuBarChooseListener(view);
/*     */     }
/*     */     
/* 170 */     if (view instanceof TextBotton) {
/* 171 */       this.selectId = 0;
/* 172 */       if (fal) {
/* 173 */         for (int i = 0; i < this.menus.size(); i++) {
/* 174 */           ((TextBotton)this.menus.get(i)).setCheck(false);
/* 175 */           if (view.getId() == ((TextBotton)this.menus.get(i)).getId()) {
/* 176 */             this.selectId = i;
/*     */           }
/*     */         } 
/* 179 */         ((TextBotton)view).setCheck(true);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 187 */   public void onClick(View v) { chooseMenu(v); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 195 */   public int getSelectId() { return this.selectId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 203 */   public void setOnMenuBarChooseListener(MenuBarChooseListener chooseListener) { this.chooseListener = chooseListener; }
/*     */   
/*     */   public static interface MenuBarChooseListener {
/*     */     boolean OnMenuBarChooseListener(View param1View);
/*     */   }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\menu\MenuBar.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */