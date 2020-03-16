/*     */ package com.blxt.quickview.select;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Paint;
/*     */ import android.os.Handler;
/*     */ import android.os.Message;
/*     */ import android.util.AttributeSet;
/*     */ import android.view.MotionEvent;
/*     */ import android.view.View;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
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
/*     */ public class PickerScrollView
/*     */   extends View
/*     */ {
/*     */   public static final String TAG = "PickerScrollView:";
/*     */   public static final float MARGIN_ALPHA = 2.8F;
/*     */   public static final float SPEED = 2.0F;
/*     */   private List<PickerDataBean> mDataList;
/*     */   private int mCurrentSelected;
/*     */   private Paint mPaint;
/*  39 */   private float mMaxTextSize = 20.0F;
/*  40 */   private float mMinTextSize = 10.0F;
/*     */   
/*  42 */   private float mMaxTextAlpha = 255.0F;
/*  43 */   private float mMinTextAlpha = 120.0F;
/*     */   
/*  45 */   private int mColorText = 3355443;
/*     */ 
/*     */   
/*     */   private int mViewHeight;
/*     */   
/*     */   private int mViewWidth;
/*     */   
/*     */   private float mLastDownY;
/*     */   
/*  54 */   private float mMoveLen = 0.0F;
/*     */   private boolean isInit = false;
/*     */   private onSelectListener mSelectListener;
/*     */   private Timer timer;
/*     */   private MyTimerTask mTask;
/*     */   
/*  60 */   Handler updateHandler = new Handler()
/*     */     {
/*     */       public void handleMessage(Message msg)
/*     */       {
/*  64 */         if (Math.abs(PickerScrollView.this.mMoveLen) < 2.0F) {
/*  65 */           PickerScrollView.this.mMoveLen = 0.0F;
/*  66 */           if (PickerScrollView.this.mTask != null) {
/*  67 */             PickerScrollView.this.mTask.cancel();
/*  68 */             PickerScrollView.this.mTask = null;
/*  69 */             PickerScrollView.this.performSelect();
/*     */           } 
/*     */         } else {
/*     */           
/*  73 */           PickerScrollView.this.mMoveLen = PickerScrollView.this.mMoveLen - PickerScrollView.this.mMoveLen / Math.abs(PickerScrollView.this.mMoveLen) * 2.0F;
/*  74 */         }  PickerScrollView.this.invalidate();
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*     */   public PickerScrollView(Context context) {
/*  80 */     super(context);
/*  81 */     init();
/*     */   }
/*     */   
/*     */   public PickerScrollView(Context context, AttributeSet attrs) {
/*  85 */     super(context, attrs);
/*  86 */     init();
/*     */   }
/*     */ 
/*     */   
/*  90 */   public void setOnSelectListener(onSelectListener listener) { this.mSelectListener = listener; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void performSelect() {
/*  97 */     if (this.mSelectListener != null)
/*  98 */       this.mSelectListener.onSelect(1, this.mDataList.get(this.mCurrentSelected)); 
/*     */   }
/*     */   
/*     */   public void setData(List<PickerDataBean> datas) {
/* 102 */     this.mDataList = datas;
/* 103 */     this.mCurrentSelected = datas.size() / 2;
/* 104 */     invalidate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelected(int selected) {
/* 113 */     this.mCurrentSelected = selected;
/* 114 */     int distance = this.mDataList.size() / 2 - this.mCurrentSelected;
/* 115 */     if (distance < 0) {
/* 116 */       for (int i = 0; i < -distance; i++) {
/* 117 */         moveHeadToTail();
/* 118 */         this.mCurrentSelected--;
/*     */       } 
/* 120 */     } else if (distance > 0) {
/* 121 */       for (int i = 0; i < distance; i++) {
/* 122 */         moveTailToHead();
/* 123 */         this.mCurrentSelected++;
/*     */       } 
/* 125 */     }  invalidate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelected(String mSelectItem) {
/* 134 */     for (int i = 0; i < this.mDataList.size(); i++) {
/* 135 */       if (((PickerDataBean)this.mDataList.get(i)).equals(mSelectItem)) {
/* 136 */         setSelected(i);
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private void moveHeadToTail() {
/* 142 */     PickerDataBean pickerDataBean = this.mDataList.get(0);
/* 143 */     this.mDataList.remove(0);
/* 144 */     this.mDataList.add(pickerDataBean);
/*     */   }
/*     */   
/*     */   private void moveTailToHead() {
/* 148 */     PickerDataBean pickerDataBean = this.mDataList.get(this.mDataList.size() - 1);
/* 149 */     this.mDataList.remove(this.mDataList.size() - 1);
/* 150 */     this.mDataList.add(0, pickerDataBean);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
/* 155 */     super.onMeasure(widthMeasureSpec, heightMeasureSpec);
/* 156 */     this.mViewHeight = getMeasuredHeight();
/* 157 */     this.mViewWidth = getMeasuredWidth();
/*     */     
/* 159 */     this.mMaxTextSize = this.mViewHeight / 8.0F;
/* 160 */     this.mMinTextSize = this.mMaxTextSize / 2.0F;
/* 161 */     this.isInit = true;
/* 162 */     invalidate();
/*     */   }
/*     */   
/*     */   private void init() {
/* 166 */     this.timer = new Timer();
/* 167 */     this.mDataList = new ArrayList<>();
/* 168 */     this.mPaint = new Paint(1);
/* 169 */     this.mPaint.setStyle(Paint.Style.FILL);
/* 170 */     this.mPaint.setTextAlign(Paint.Align.CENTER);
/* 171 */     this.mPaint.setColor(this.mColorText);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onDraw(Canvas canvas) {
/* 176 */     super.onDraw(canvas);
/*     */     
/* 178 */     if (this.isInit) {
/* 179 */       drawData(canvas);
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawData(Canvas canvas) {
/* 184 */     float scale = parabola(this.mViewHeight / 4.0F, this.mMoveLen);
/* 185 */     float size = (this.mMaxTextSize - this.mMinTextSize) * scale + this.mMinTextSize;
/* 186 */     this.mPaint.setTextSize(size);
/* 187 */     this.mPaint.setAlpha((int)((this.mMaxTextAlpha - this.mMinTextAlpha) * scale + this.mMinTextAlpha));
/*     */     
/* 189 */     float x = (float)(this.mViewWidth / 2.0D);
/* 190 */     float y = (float)(this.mViewHeight / 2.0D + this.mMoveLen);
/* 191 */     Paint.FontMetricsInt fmi = this.mPaint.getFontMetricsInt();
/* 192 */     float baseline = (float)(y - fmi.bottom / 2.0D + fmi.top / 2.0D);
/*     */     
/* 194 */     int indexs = this.mCurrentSelected;
/* 195 */     String textData = ((PickerDataBean)this.mDataList.get(indexs)).getName();
/* 196 */     canvas.drawText(textData, x, baseline, this.mPaint);
/*     */ 
/*     */     
/* 199 */     for (int i = 1; this.mCurrentSelected - i >= 0; i++) {
/* 200 */       drawOtherText(canvas, i, -1);
/*     */     }
/*     */     
/* 203 */     for (int i = 1; this.mCurrentSelected + i < this.mDataList.size(); i++) {
/* 204 */       drawOtherText(canvas, i, 1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawOtherText(Canvas canvas, int position, int type) {
/* 214 */     float d = 2.8F * this.mMinTextSize * position + type * this.mMoveLen;
/*     */     
/* 216 */     float scale = parabola(this.mViewHeight / 4.0F, d);
/* 217 */     float size = (this.mMaxTextSize - this.mMinTextSize) * scale + this.mMinTextSize;
/* 218 */     this.mPaint.setTextSize(size);
/* 219 */     this.mPaint.setAlpha((int)((this.mMaxTextAlpha - this.mMinTextAlpha) * scale + this.mMinTextAlpha));
/* 220 */     float y = (float)(this.mViewHeight / 2.0D + (type * d));
/* 221 */     Paint.FontMetricsInt fmi = this.mPaint.getFontMetricsInt();
/* 222 */     float baseline = (float)(y - fmi.bottom / 2.0D + fmi.top / 2.0D);
/*     */     
/* 224 */     int indexs = this.mCurrentSelected + type * position;
/* 225 */     String textData = ((PickerDataBean)this.mDataList.get(indexs)).getName();
/* 226 */     canvas.drawText(textData, (float)(this.mViewWidth / 2.0D), baseline, this.mPaint);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float parabola(float zero, float x) {
/* 237 */     float f = (float)(1.0D - Math.pow((x / zero), 2.0D));
/* 238 */     return (f < 0.0F) ? 0.0F : f;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onTouchEvent(MotionEvent event) {
/* 243 */     switch (event.getActionMasked()) {
/*     */       case 0:
/* 245 */         doDown(event);
/*     */         break;
/*     */       case 2:
/* 248 */         doMove(event);
/*     */         break;
/*     */       case 1:
/* 251 */         doUp(event);
/*     */         break;
/*     */     } 
/* 254 */     return true;
/*     */   }
/*     */   
/*     */   private void doDown(MotionEvent event) {
/* 258 */     if (this.mTask != null) {
/* 259 */       this.mTask.cancel();
/* 260 */       this.mTask = null;
/*     */     } 
/* 262 */     this.mLastDownY = event.getY();
/*     */   }
/*     */ 
/*     */   
/*     */   private void doMove(MotionEvent event) {
/* 267 */     this.mMoveLen += event.getY() - this.mLastDownY;
/*     */     
/* 269 */     if (this.mMoveLen > 2.8F * this.mMinTextSize / 2.0F) {
/*     */       
/* 271 */       moveTailToHead();
/* 272 */       this.mMoveLen -= 2.8F * this.mMinTextSize;
/* 273 */     } else if (this.mMoveLen < -2.8F * this.mMinTextSize / 2.0F) {
/*     */       
/* 275 */       moveHeadToTail();
/* 276 */       this.mMoveLen += 2.8F * this.mMinTextSize;
/*     */     } 
/*     */     
/* 279 */     this.mLastDownY = event.getY();
/* 280 */     invalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   private void doUp(MotionEvent event) {
/* 285 */     if (Math.abs(this.mMoveLen) < 1.0E-4D) {
/* 286 */       this.mMoveLen = 0.0F;
/*     */       return;
/*     */     } 
/* 289 */     if (this.mTask != null) {
/* 290 */       this.mTask.cancel();
/* 291 */       this.mTask = null;
/*     */     } 
/* 293 */     this.mTask = new MyTimerTask(this.updateHandler);
/* 294 */     this.timer.schedule(this.mTask, 0L, 10L);
/*     */   }
/*     */   
/*     */   class MyTimerTask
/*     */     extends TimerTask {
/*     */     Handler handler;
/*     */     
/* 301 */     public MyTimerTask(Handler handler) { this.handler = handler; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 306 */     public void run() { this.handler.sendMessage(this.handler.obtainMessage()); }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface onSelectListener
/*     */   {
/*     */     void onSelect(int param1Int, PickerScrollView.PickerDataBean param1PickerDataBean);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class PickerDataBean
/*     */   {
/*     */     private int id;
/*     */     
/*     */     private String name;
/*     */     
/*     */     private Object data;
/*     */ 
/*     */     
/*     */     public PickerDataBean(int ID, String name, Object data) {
/* 328 */       this.id = ID;
/* 329 */       this.name = name;
/* 330 */       this.data = data;
/*     */     }
/*     */ 
/*     */     
/* 334 */     public int getID() { return this.id; }
/*     */ 
/*     */ 
/*     */     
/* 338 */     public void setID(int ID) { this.id = ID; }
/*     */ 
/*     */ 
/*     */     
/* 342 */     public String getName() { return this.name; }
/*     */ 
/*     */ 
/*     */     
/* 346 */     public void setName(String name) { this.name = name; }
/*     */ 
/*     */ 
/*     */     
/* 350 */     public Object getData() { return this.data; }
/*     */ 
/*     */ 
/*     */     
/* 354 */     public void setData(Object data) { this.data = data; }
/*     */   }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\select\PickerScrollView.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */