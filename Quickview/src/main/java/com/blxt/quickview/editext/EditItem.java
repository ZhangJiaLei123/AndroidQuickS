/*     */ package com.blxt.quickview.editext;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.res.TypedArray;
/*     */ import android.text.Editable;
/*     */ import android.text.TextUtils;
/*     */ import android.util.AttributeSet;
/*     */ import android.util.TypedValue;
/*     */ import android.view.LayoutInflater;
/*     */ import android.view.View;
/*     */ import android.widget.EditText;
/*     */ import android.widget.FrameLayout;
/*     */ import android.widget.ImageView;
/*     */ import android.widget.LinearLayout;
/*     */ import android.widget.TextView;
/*     */ import androidx.annotation.AttrRes;
/*     */ import androidx.annotation.NonNull;
/*     */ import androidx.annotation.Nullable;
/*     */ import com.blxt.quickview.AttributeHelper;
/*     */ import com.blxt.quickview.R;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EditItem
/*     */   extends FrameLayout
/*     */   implements View.OnClickListener
/*     */ {
/*     */   private LinearLayout itemGroupLayout;
/*     */   private TextView titleTv;
/*     */   private EditText contentEdt;
/*     */   private ImageView clearIv;
/*     */   private ImageView jtRightIv;
/*     */   private ItemOnClickListener itemOnClickListener;
/*  40 */   View view = null;
/*     */   EditItem in;
/*     */   AttributeHelper attributeHelper;
/*  43 */   View.OnClickListener onClickListener = null;
/*  44 */   View.OnLongClickListener onLongClickListener = null;
/*     */   
/*     */   public EditItem(@NonNull Context context) {
/*  47 */     super(context);
/*  48 */     initView(context);
/*     */   }
/*     */   
/*     */   public EditItem(@NonNull Context context, @Nullable AttributeSet attrs) {
/*  52 */     super(context, attrs);
/*  53 */     initView(context);
/*  54 */     initAttrs(context, attrs);
/*     */   }
/*     */   
/*     */   public EditItem(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
/*  58 */     super(context, attrs, defStyleAttr);
/*  59 */     initView(context);
/*  60 */     initAttrs(context, attrs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void initView(Context context) {
/*  66 */     if (this.view != null) {
/*     */       return;
/*     */     }
/*     */     
/*  70 */     this.in = this;
/*  71 */     this.view = LayoutInflater.from(context).inflate(R.layout.item_group_layout, null);
/*  72 */     this.itemGroupLayout = (LinearLayout)this.view.findViewById(R.id.item_group_layout);
/*  73 */     this.titleTv = (TextView)this.view.findViewById(R.id.title_tv);
/*  74 */     this.contentEdt = (EditText)this.view.findViewById(R.id.content_edt);
/*  75 */     this.clearIv = (ImageView)this.view.findViewById(R.id.clear_iv);
/*  76 */     this.jtRightIv = (ImageView)this.view.findViewById(R.id.jt_right_iv);
/*  77 */     addView(this.view);
/*     */     
/*  79 */     this.itemGroupLayout.setOnClickListener(this);
/*  80 */     this.clearIv.setOnClickListener(this);
/*  81 */     this.contentEdt.addTextChangedListener(new TextChangeWatcher()
/*     */         {
/*     */           public void afterTextChanged(Editable editable) {
/*  84 */             super.afterTextChanged(editable);
/*     */             
/*  86 */             String content = EditItem.this.contentEdt.getText().toString().trim();
/*  87 */             if (!TextUtils.isEmpty(content)) {
/*     */               
/*  89 */               EditItem.this.clearIv.setVisibility(0);
/*     */             } else {
/*  91 */               EditItem.this.clearIv.setVisibility(8);
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initAttrs(Context context, AttributeSet attrs) {
/* 105 */     if (this.attributeHelper != null) {
/*     */       return;
/*     */     }
/* 108 */     this.attributeHelper = new AttributeHelper(context, attrs);
/*     */ 
/*     */ 
/*     */     
/* 112 */     int defaultTitleColor = context.getResources().getColor(R.color.item_group_title);
/*     */     
/* 114 */     float defaultEdtSize = TypedValue.applyDimension(2, 13.0F, context.getResources().getDisplayMetrics());
/*     */     
/* 116 */     int defaultEdtColor = context.getResources().getColor(R.color.item_group_edt);
/*     */     
/* 118 */     int defaultHintColor = context.getResources().getColor(R.color.item_group_edt);
/*     */ 
/*     */     
/* 121 */     TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditItem);
/* 122 */     String title = typedArray.getString(R.styleable.EditItem_title);
/* 123 */     float paddingLeft = typedArray.getDimension(R.styleable.EditItem_paddingLeft, 15.0F);
/* 124 */     float paddingRight = typedArray.getDimension(R.styleable.EditItem_paddingRight, 15.0F);
/* 125 */     float paddingTop = typedArray.getDimension(R.styleable.EditItem_paddingTop, 5.0F);
/* 126 */     float paddingBottom = typedArray.getDimension(R.styleable.EditItem_paddingTop, 5.0F);
/*     */ 
/*     */     
/* 129 */     String content = this.attributeHelper.getString("text", "");
/* 130 */     float contentSize = this.attributeHelper.getInt("textSize", (int)defaultEdtSize);
/* 131 */     int contentColor = this.attributeHelper.getColor("textColor", defaultTitleColor);
/*     */ 
/*     */     
/* 134 */     float titleSize = typedArray.getDimension(R.styleable.EditItem_titleSize, contentSize);
/* 135 */     int titleColor = typedArray.getColor(R.styleable.EditItem_titleColor, defaultTitleColor);
/*     */ 
/*     */     
/* 138 */     String hintContent = this.attributeHelper.getString("hint", "");
/* 139 */     int hintColor = typedArray.getColor(R.styleable.EditItem_hintColor, defaultHintColor);
/*     */     
/* 141 */     boolean isEditable = this.attributeHelper.getBoolean("enabled", true);
/*     */     
/* 143 */     boolean showJtIcon = typedArray.getBoolean(R.styleable.EditItem_jt_visible, true);
/* 144 */     typedArray.recycle();
/*     */ 
/*     */ 
/*     */     
/* 148 */     this.itemGroupLayout.setPadding((int)paddingLeft, (int)paddingTop, (int)paddingRight, (int)paddingBottom);
/* 149 */     this.titleTv.setText(title);
/* 150 */     this.titleTv.setTextColor(titleColor);
/*     */     
/* 152 */     this.contentEdt.setText(content);
/* 153 */     if (contentSize != 0.0F) {
/* 154 */       this.contentEdt.setTextSize(contentSize);
/* 155 */       this.titleTv.setTextSize(titleSize);
/*     */     } 
/* 157 */     if (contentColor != 0) {
/* 158 */       this.contentEdt.setTextColor(contentColor);
/*     */     }
/*     */     
/* 161 */     this.contentEdt.setHint(hintContent);
/* 162 */     this.contentEdt.setHintTextColor(hintColor);
/* 163 */     this.contentEdt.setFocusableInTouchMode(isEditable);
/* 164 */     this.contentEdt.setLongClickable(false);
/* 165 */     this.jtRightIv.setVisibility(showJtIcon ? 0 : 8);
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
/*     */ 
/*     */ 
/*     */   
/* 179 */   public void setItemOnClickListener(ItemOnClickListener itemOnClickListener) { this.itemOnClickListener = itemOnClickListener; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 184 */   public void setTitle(String title) { this.titleTv.setText(title); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 189 */   public String getText() { return this.contentEdt.getText().toString().trim(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 194 */   public void setText(String text) { this.contentEdt.setText(text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 202 */     this.contentEdt.setEnabled(enabled);
/* 203 */     this.clearIv.setVisibility(enabled ? 0 : 8);
/* 204 */     this.jtRightIv.setVisibility(enabled ? 8 : 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTextSize(int textSize) {
/* 212 */     this.contentEdt.setTextSize(textSize);
/* 213 */     this.titleTv.setTextSize(textSize);
/*     */   }
/*     */ 
/*     */   
/* 217 */   public TextView getTitleTv() { return this.titleTv; }
/*     */ 
/*     */ 
/*     */   
/* 221 */   public EditText getContentEdt() { return this.contentEdt; }
/*     */ 
/*     */ 
/*     */   
/* 225 */   public ImageView getClearIv() { return this.clearIv; }
/*     */ 
/*     */ 
/*     */   
/* 229 */   public ImageView getJtRightIv() { return this.jtRightIv; }
/*     */ 
/*     */   
/*     */   public void setOnClickListener(View.OnClickListener l) {
/* 233 */     this.onClickListener = l;
/* 234 */     this.jtRightIv.setOnClickListener(new View.OnClickListener()
/*     */         {
/*     */           public void onClick(View v) {
/* 237 */             EditItem.this.onClickListener.onClick((View)EditItem.this.in);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOnLongClickListener(View.OnLongClickListener l) {
/* 246 */     this.onLongClickListener = l;
/* 247 */     this.contentEdt.setLongClickable(true);
/* 248 */     this.jtRightIv.setOnLongClickListener(new View.OnLongClickListener()
/*     */         {
/*     */           public boolean onLongClick(View v) {
/* 251 */             return EditItem.this.onLongClickListener.onLongClick((View)EditItem.this.in);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void onClick(View view) {
/* 258 */     int id = view.getId();
/* 259 */     if (id == R.id.item_group_layout) {
/* 260 */       if (this.itemOnClickListener != null) {
/* 261 */         this.itemOnClickListener.onItemClick((View)this);
/*     */       }
/* 263 */     } else if (id == R.id.clear_iv) {
/* 264 */       this.contentEdt.setText("");
/* 265 */       this.clearIv.setVisibility(8);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static interface ItemOnClickListener {
/*     */     void onItemClick(View param1View);
/*     */   }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\editext\EditItem.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */