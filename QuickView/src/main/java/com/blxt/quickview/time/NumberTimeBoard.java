/*     */ package com.blxt.quickview.time;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import android.content.Context;
/*     */ import android.util.AttributeSet;
/*     */ import android.view.LayoutInflater;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import android.widget.LinearLayout;
/*     */ import android.widget.TextView;
/*     */ import androidx.annotation.Nullable;
/*     */ import com.blxt.quickview.R;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NumberTimeBoard
/*     */   extends LinearLayout
/*     */ {
/*  26 */   View view = null;
/*  27 */   Timer timer = null;
/*  28 */   TimerTask timerTask = null;
/*     */   
/*  30 */   NumberTimeBoardViewHolder viewHolder = null;
/*     */   
/*     */   public NumberTimeBoard(Context context) {
/*  33 */     super(context);
/*  34 */     initUI();
/*     */   }
/*     */   
/*     */   public NumberTimeBoard(Context context, @Nullable AttributeSet attrs) {
/*  38 */     super(context, attrs);
/*  39 */     initUI();
/*     */   }
/*     */   
/*     */   public NumberTimeBoard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
/*  43 */     super(context, attrs, defStyleAttr);
/*  44 */     initUI();
/*     */   }
/*     */   
/*     */   @SuppressLint({"NewApi"})
/*     */   public NumberTimeBoard(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
/*  49 */     super(context, attrs, defStyleAttr, defStyleRes);
/*  50 */     initUI();
/*     */   }
/*     */   
/*     */   private void initUI() {
/*  54 */     if (this.view != null) {
/*     */       return;
/*     */     }
/*     */     
/*  58 */     this.view = LayoutInflater.from(getContext()).inflate(R.layout.__item_number_time, (ViewGroup)this);
/*  59 */     this.viewHolder = new NumberTimeBoardViewHolder(this.view);
/*     */     
/*  61 */     if (this.timer == null) {
/*  62 */       this.timer = new Timer();
/*     */     }
/*  64 */     if (this.timerTask == null) {
/*  65 */       this.timerTask = new TimerTask()
/*     */         {
/*     */           public void run()
/*     */           {
/*  69 */             NumberTimeBoard.this.view.post(new Runnable()
/*     */                 {
/*     */                   public void run() {
/*  72 */                     NumberTimeBoard.this.viewHolder.upData(new Date());
/*     */                   }
/*     */                 });
/*     */           }
/*     */         };
/*     */       
/*  78 */       this.timer.schedule(this.timerTask, 0L, 1000L);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onDetachedFromWindow() {
/*  87 */     super.onDetachedFromWindow();
/*  88 */     if (this.timer != null) {
/*  89 */       this.timer.cancel();
/*     */     }
/*  91 */     if (this.timerTask != null) {
/*  92 */       this.timerTask.cancel();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  98 */   public NumberTimeBoardViewHolder getViewHolder() { return this.viewHolder; }
/*     */ 
/*     */   
/*     */   public class NumberTimeBoardViewHolder
/*     */   {
/*     */     protected View view;
/*     */     
/*     */     private TextView tvTime;
/*     */     private TextView tvDate;
/* 107 */     private String timeFormat = "HH:mm";
/* 108 */     private String DateFormat = "yyyy MM-dd E";
/*     */     
/*     */     public NumberTimeBoardViewHolder(View view) {
/* 111 */       this.view = view;
/* 112 */       this.tvTime = findViewById(R.id.__tv_time);
/* 113 */       this.tvDate = findViewById(R.id.__tv_date);
/*     */     }
/*     */     
/*     */     public boolean upData(Object o) {
/* 117 */       Date currentTime = (Date)o;
/* 118 */       this.tvTime.setText(getTimeStr(currentTime, this.timeFormat));
/* 119 */       this.tvDate.setText(getTimeStr(currentTime, this.DateFormat));
/* 120 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 125 */     public final <T extends View> T findViewById(int id) { return (T)this.view.findViewById(id); }
/*     */ 
/*     */ 
/*     */     
/* 129 */     public TextView getTvTime() { return this.tvTime; }
/*     */ 
/*     */ 
/*     */     
/* 133 */     public void setTvTime(TextView tvTime) { this.tvTime = tvTime; }
/*     */ 
/*     */ 
/*     */     
/* 137 */     public TextView getTvDate() { return this.tvDate; }
/*     */ 
/*     */ 
/*     */     
/* 141 */     public void setTvDate(TextView tvDate) { this.tvDate = tvDate; }
/*     */ 
/*     */ 
/*     */     
/* 145 */     public String getTimeFormat() { return this.timeFormat; }
/*     */ 
/*     */ 
/*     */     
/* 149 */     public void setTimeFormat(String timeFormat) { this.timeFormat = timeFormat; }
/*     */ 
/*     */ 
/*     */     
/* 153 */     public String getDateFormat() { return this.DateFormat; }
/*     */ 
/*     */ 
/*     */     
/* 157 */     public void setDateFormat(String dateFormat) { this.DateFormat = dateFormat; }
/*     */ 
/*     */     
/*     */     public String getTimeStr(Date date, String format) {
/* 161 */       SimpleDateFormat formatter = new SimpleDateFormat(format);
/* 162 */       String dateString = formatter.format(date);
/* 163 */       return dateString;
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\time\NumberTimeBoard.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */