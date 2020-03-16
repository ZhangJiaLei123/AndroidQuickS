/*    */ package com.blxt.quickview.spinner;
/*    */ 
/*    */ import android.annotation.SuppressLint;
/*    */ import android.content.Context;
/*    */ import android.content.res.Resources;
/*    */ import android.text.TextUtils;
/*    */ import android.util.AttributeSet;
/*    */ import android.widget.Spinner;
/*    */ import android.widget.SpinnerAdapter;
/*    */ import androidx.annotation.RequiresApi;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SuppressLint({"ViewConstructor", "AppCompatCustomView"})
/*    */ public class MSpinner
/*    */   extends Spinner
/*    */ {
/* 19 */   public MSpinner(Context context) { super(context); }
/*    */ 
/*    */ 
/*    */   
/* 23 */   public MSpinner(Context context, int mode) { super(context, mode); }
/*    */ 
/*    */ 
/*    */   
/* 27 */   public MSpinner(Context context, AttributeSet attrs) { super(context, attrs); }
/*    */ 
/*    */ 
/*    */   
/* 31 */   public MSpinner(Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public MSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode) { super(context, attrs, defStyleAttr, mode); }
/*    */ 
/*    */ 
/*    */   
/*    */   @RequiresApi(api = 21)
/* 40 */   public MSpinner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, int mode) { super(context, attrs, defStyleAttr, defStyleRes, mode); }
/*    */ 
/*    */ 
/*    */   
/*    */   @RequiresApi(api = 23)
/* 45 */   public MSpinner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, int mode, Resources.Theme popupTheme) { super(context, attrs, defStyleAttr, defStyleRes, mode, popupTheme); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setDefaultValue(String value) {
/* 53 */     SpinnerAdapter apsAdapter = getAdapter();
/* 54 */     int size = apsAdapter.getCount();
/* 55 */     for (int i = 0; i < size; i++) {
/* 56 */       if (TextUtils.equals(value, apsAdapter.getItem(i).toString())) {
/* 57 */         setSelection(i, true);
/*    */         break;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\spinner\MSpinner.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */