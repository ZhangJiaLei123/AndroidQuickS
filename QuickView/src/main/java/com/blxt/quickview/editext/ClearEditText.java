/*    */ package com.blxt.quickview.editext;
/*    */ 
/*    */ import android.annotation.SuppressLint;
/*    */ import android.content.Context;
/*    */ import android.graphics.drawable.Drawable;
/*    */ import android.text.Editable;
/*    */ import android.text.TextUtils;
/*    */ import android.text.TextWatcher;
/*    */ import android.util.AttributeSet;
/*    */ import android.view.MotionEvent;
/*    */ import androidx.appcompat.widget.AppCompatEditText;
/*    */ import com.blxt.quickview.R;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClearEditText
/*    */   extends AppCompatEditText
/*    */   implements TextWatcher
/*    */ {
/*    */   private Drawable mClearDrawable;
/*    */   
/* 28 */   public ClearEditText(Context context) { this(context, null); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   public ClearEditText(Context context, AttributeSet attrs) { this(context, attrs, 16842862); }
/*    */ 
/*    */   
/*    */   public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
/* 37 */     super(context, attrs, defStyleAttr);
/* 38 */     init();
/*    */   }
/*    */ 
/*    */   
/*    */   @SuppressLint({"NewApi"})
/*    */   private void init() {
/* 44 */     this.mClearDrawable = getCompoundDrawables()[2];
/* 45 */     if (this.mClearDrawable == null) {
/* 46 */       this.mClearDrawable = getResources().getDrawable(R.mipmap.close);
/*    */     }
/*    */     
/* 49 */     this.mClearDrawable.setBounds(0, 0, this.mClearDrawable.getIntrinsicWidth(), this.mClearDrawable.getIntrinsicHeight());
/* 50 */     setBackground(getResources().getDrawable(R.mipmap.sousuok));
/*    */     
/* 52 */     addTextChangedListener(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onTextChanged(CharSequence s, int start, int before, int count) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void afterTextChanged(Editable s) {
/* 68 */     String text = getText().toString().trim();
/* 69 */     if (TextUtils.isEmpty(text)) {
/*    */       
/* 71 */       setCompoundDrawables(null, null, null, null);
/*    */     } else {
/* 73 */       setCompoundDrawables(null, null, this.mClearDrawable, null);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onTouchEvent(MotionEvent event) {
/* 84 */     if (event.getAction() == 1 && 
/* 85 */       getCompoundDrawables()[2] != null) {
/*    */       
/* 87 */       boolean touchable = (event.getX() > (getWidth() - getTotalPaddingRight()) && event.getX() < (getWidth() - getPaddingRight()));
/* 88 */       if (touchable) {
/* 89 */         setText("");
/*    */       }
/*    */     } 
/*    */     
/* 93 */     return super.onTouchEvent(event);
/*    */   }
/*    */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\editext\ClearEditText.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */