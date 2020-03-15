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
/*     */ public class ListItemUser
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
/*     */   public ListItemUser(Context context) {
/*  35 */     super(context);
/*  36 */     initUI();
/*     */   }
/*     */   
/*     */   public ListItemUser(Context context, AttributeSet attrs) {
/*  40 */     super(context, attrs);
/*  41 */     initUI();
/*     */   }
/*     */   
/*     */   public ListItemUser(Context context, AttributeSet attrs, int defStyleAttr) {
/*  45 */     super(context, attrs, defStyleAttr);
/*  46 */     initUI();
/*     */   }
/*     */   
/*     */   @SuppressLint({"NewApi"})
/*     */   public ListItemUser(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
/*  73 */     this.btnOk.setVisibility(8);
/*  74 */     this.btnCancel.setVisibility(8);
/*  75 */     this.tvUserTip.setVisibility(8);
/*     */   }
/*     */   
/*     */   public ListItemUser setIndex(int index) {
/*  79 */     this.tvNumber.setText("" + index);
/*  80 */     this.index = index;
/*  81 */     if (index > 1000) {
/*  82 */       this.tvNumber.setTextSize(24.0F);
/*     */     }
/*     */     
/*  85 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ListItemUser setName(String name) {
/*  90 */     this.tvUsername.setText(name);
/*  91 */     return this;
/*     */   }
/*     */   
/*     */   public ListItemUser setUserId(String userID) {
/*  95 */     this.tvUserId.setText(userID);
/*  96 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onClick(View view) {
/* 103 */     int id = view.getId();
/*     */     
/* 105 */     if (id == R.id.btn_ok) {
/* 106 */       this.btnId = 1;
/* 107 */     } else if (id == R.id.btn_cancel) {
/* 108 */       this.btnId = 2;
/*     */     } 
/*     */     
/* 111 */     if (this.callback != null) {
/* 112 */       this.callback.OnClick(this);
/*     */     }
/*     */     
/* 115 */     this.btnId = -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public ListItemUser setCallback(ItemUserCallback callback) {
/* 120 */     this.callback = callback;
/* 121 */     return this;
/*     */   }
/*     */   
/*     */   public static interface ItemUserCallback {
/*     */     void OnClick(ListItemUser param1ListItemUser);
/*     */   }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\item\ListItemUser.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */