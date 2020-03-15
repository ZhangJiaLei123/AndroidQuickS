/*     */ package com.blxt.quickview.item;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import android.content.Context;
/*     */ import android.util.AttributeSet;
/*     */ import android.view.LayoutInflater;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import android.widget.Button;
/*     */ import android.widget.RelativeLayout;
/*     */ import android.widget.TextView;
/*     */ import com.blxt.quickview.R;
/*     */ 
/*     */ 
/*     */ public class ListItemUserWithBtn
/*     */   extends RelativeLayout
/*     */   implements View.OnClickListener
/*     */ {
/*     */   public static final int BTN_OK = 1;
/*     */   public static final int BTN_CANCEL = 2;
/*     */   ItemUserCallback callback;
/*  22 */   View view = null;
/*  23 */   int index = 0;
/*     */   
/*  25 */   int btnId = -1;
/*     */   
/*     */   private TextView tvNumber;
/*     */   private TextView tvUsername;
/*     */   private TextView tvUserTip;
/*     */   private TextView tvUserId;
/*     */   private Button btnOk;
/*     */   private Button btnCancel;
/*     */   
/*     */   public ListItemUserWithBtn(Context context) {
/*  35 */     super(context);
/*  36 */     initUI();
/*     */   }
/*     */   
/*     */   public ListItemUserWithBtn(Context context, AttributeSet attrs) {
/*  40 */     super(context, attrs);
/*  41 */     initUI();
/*     */   }
/*     */   
/*     */   public ListItemUserWithBtn(Context context, AttributeSet attrs, int defStyleAttr) {
/*  45 */     super(context, attrs, defStyleAttr);
/*  46 */     initUI();
/*     */   }
/*     */   
/*     */   @SuppressLint({"NewApi"})
/*     */   public ListItemUserWithBtn(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
/*  51 */     super(context, attrs, defStyleAttr, defStyleRes);
/*  52 */     initUI();
/*     */   }
/*     */ 
/*     */   
/*     */   private void initUI() {
/*  57 */     if (this.view != null) {
/*     */       return;
/*     */     }
/*     */     
/*  61 */     this.view = LayoutInflater.from(getContext()).inflate(R.layout.__item_list_user, (ViewGroup)this);
/*     */     
/*  63 */     this.tvNumber = (TextView)this.view.findViewById(R.id.tv_number);
/*  64 */     this.tvUsername = (TextView)this.view.findViewById(R.id.tv_username);
/*  65 */     this.tvUserTip = (TextView)this.view.findViewById(R.id.tv_user_tip);
/*  66 */     this.tvUserId = (TextView)this.view.findViewById(R.id.tv_user_id);
/*  67 */     this.btnOk = (Button)this.view.findViewById(R.id.btn_ok);
/*  68 */     this.btnCancel = (Button)this.view.findViewById(R.id.btn_cancel);
/*     */     
/*  70 */     this.btnOk.setOnClickListener(this);
/*  71 */     this.btnCancel.setOnClickListener(this);
/*     */     
/*  73 */     this.btnOk.setVisibility(0);
/*  74 */     this.btnCancel.setVisibility(0);
/*  75 */     this.tvUserTip.setVisibility(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public ListItemUserWithBtn setIndex(int index) {
/*  80 */     this.tvNumber.setText("" + index);
/*  81 */     this.index = index;
/*  82 */     if (index > 1000) {
/*  83 */       this.tvNumber.setTextSize(24.0F);
/*     */     }
/*     */     
/*  86 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ListItemUserWithBtn setUserName(String name) {
/*  91 */     this.tvUsername.setText(name);
/*  92 */     return this;
/*     */   }
/*     */   
/*     */   public ListItemUserWithBtn setUserId(String userID) {
/*  96 */     this.tvUserId.setText(userID);
/*  97 */     return this;
/*     */   }
/*     */   
/*     */   public ListItemUserWithBtn setTips(String tips) {
/* 101 */     this.tvUserTip.setText(tips);
/* 102 */     return this;
/*     */   }
/*     */   
/*     */   public ListItemUserWithBtn setTipsBackgroundResource(int resId) {
/* 106 */     this.tvUserTip.setBackgroundResource(resId);
/* 107 */     return this;
/*     */   }
/*     */   
/*     */   public ListItemUserWithBtn setTipsColor(int resId) {
/* 111 */     this.tvUserTip.setTextColor(resId);
/* 112 */     return this;
/*     */   }
/*     */   
/*     */   public String getUserName() {
/* 116 */     if (this.tvUsername.getText() == null) {
/* 117 */       return "";
/*     */     }
/* 119 */     return this.tvUsername.getText().toString();
/*     */   }
/*     */   
/*     */   public String getIndex() {
/* 123 */     if (this.tvNumber.getText() == null) {
/* 124 */       return "";
/*     */     }
/* 126 */     return this.tvNumber.getText().toString();
/*     */   }
/*     */   
/*     */   public String getUserId() {
/* 130 */     if (this.tvUserId.getText() == null) {
/* 131 */       return "";
/*     */     }
/* 133 */     return this.tvUserId.getText().toString();
/*     */   }
/*     */ 
/*     */   
/* 137 */   public Button getBtnOk() { return this.btnOk; }
/*     */ 
/*     */ 
/*     */   
/* 141 */   public Button getBtnCancel() { return this.btnCancel; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onClick(View view) {
/* 147 */     int id = view.getId();
/*     */     
/* 149 */     if (id == R.id.btn_ok) {
/* 150 */       this.btnId = 1;
/* 151 */     } else if (id == R.id.btn_cancel) {
/* 152 */       this.btnId = 2;
/*     */     } 
/*     */     
/* 155 */     if (this.callback != null) {
/* 156 */       this.callback.OnClick(this);
/*     */     }
/*     */     
/* 159 */     this.btnId = -1;
/*     */   }
/*     */ 
/*     */   
/* 163 */   public int getBtnId() { return this.btnId; }
/*     */ 
/*     */   
/*     */   public ListItemUserWithBtn setCallback(ItemUserCallback callback) {
/* 167 */     this.callback = callback;
/* 168 */     return this;
/*     */   }
/*     */   
/*     */   public static interface ItemUserCallback {
/*     */     void OnClick(ListItemUserWithBtn param1ListItemUserWithBtn);
/*     */   }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\item\ListItemUserWithBtn.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */