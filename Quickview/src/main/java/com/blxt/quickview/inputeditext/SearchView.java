/*     */ package com.blxt.quickview.inputeditext;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import android.content.Context;
/*     */ import android.util.AttributeSet;
/*     */ import android.view.KeyEvent;
/*     */ import android.view.LayoutInflater;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import android.widget.EditText;
/*     */ import android.widget.ImageView;
/*     */ import android.widget.RelativeLayout;
/*     */ import android.widget.TextView;
/*     */ import com.blxt.quickview.R;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SearchView
/*     */   extends RelativeLayout
/*     */   implements View.OnClickListener
/*     */ {
/*     */   private SearchView searchView;
/*  26 */   private View view = null;
/*     */   private EditText edtSearch;
/*     */   private ImageView ivSearch;
/*  29 */   OnSearchClick click = null;
/*     */   
/*     */   public SearchView(Context context) {
/*  32 */     super(context);
/*  33 */     initUI();
/*     */   }
/*     */   
/*     */   public SearchView(Context context, AttributeSet attrs) {
/*  37 */     super(context, attrs);
/*  38 */     initUI();
/*     */   }
/*     */   
/*     */   public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
/*  42 */     super(context, attrs, defStyleAttr);
/*  43 */     initUI();
/*     */   }
/*     */   
/*     */   @SuppressLint({"NewApi"})
/*     */   public SearchView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
/*  48 */     super(context, attrs, defStyleAttr, defStyleRes);
/*  49 */     initUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void initUI() {
/*  54 */     if (this.view == null) {
/*  55 */       this.view = LayoutInflater.from(getContext()).inflate(R.layout.__searchview, (ViewGroup)this);
/*  56 */       this.searchView = this;
/*  57 */       this.edtSearch = (EditText)findViewById(R.id.edt_search);
/*  58 */       this.ivSearch = (ImageView)findViewById(R.id.iv_search);
/*     */       
/*  60 */       this.ivSearch.setOnClickListener(this);
/*  61 */       this.edtSearch.setImeOptions(3);
/*  62 */       this.edtSearch.setInputType(1);
/*     */       
/*  64 */       this.edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
/*     */             public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
/*  66 */               if (actionId == 4 || (event != null && event.getKeyCode() == 66)) {
/*     */ 
/*     */                 
/*  69 */                 if (SearchView.this.click != null) {
/*  70 */                   SearchView.this.click.onSearchClick(SearchView.this.searchView);
/*     */                 }
/*  72 */                 return true;
/*     */               } 
/*  74 */               return false;
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */   
/*     */   public static interface OnSearchClick {
/*     */     void onSearchClick(SearchView param1SearchView);
/*     */   }
/*     */   
/*     */   public String getText() {
/*  85 */     if (this.edtSearch.getText() == null) {
/*  86 */       return "";
/*     */     }
/*  88 */     return this.edtSearch.getText().toString();
/*     */   }
/*     */ 
/*     */   
/*  92 */   public void setText(int str) { this.edtSearch.setText(str); }
/*     */ 
/*     */ 
/*     */   
/*  96 */   public void setText(String str) { this.edtSearch.setText(str); }
/*     */ 
/*     */ 
/*     */   
/* 100 */   public void setHint(int str) { this.edtSearch.setHint(str); }
/*     */ 
/*     */   
/* 103 */   public void setHint(String str) { this.edtSearch.setHint(str); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onClick(View view) {
/* 108 */     if (this.click != null) {
/* 109 */       this.click.onSearchClick(this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 115 */   public void setOnSearchClick(OnSearchClick click) { this.click = click; }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\inputeditext\SearchView.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */