 package com.blxt.quicknoti;

 import android.app.Dialog;
 import android.content.Context;
 import android.view.LayoutInflater;
 import android.view.View;
 import androidx.annotation.NonNull;
 import androidx.annotation.Nullable;
 import com.wang.avi.AVLoadingIndicatorView;



 public class LoadingDialog
   extends Dialog
 {
   View view = null;

   private AVLoadingIndicatorView loading;


   public LoadingDialog(@NonNull Context context) {
     super(context, R.style.dialog_loading);
     inti();
   }

   public LoadingDialog(@NonNull Context context, int themeResId) {
     super(context, themeResId);
     inti();
   }

   protected LoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
     super(context, cancelable, cancelListener);
     inti();
   }






   private void inti() {
     if (this.view != null) {
       return;
     }
     this
       .view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_loading, null, false);

     setContentView(this.view);
     setCanceledOnTouchOutside(false);

     this.loading = (AVLoadingIndicatorView)this.view.findViewById(R.id.loading);
   }






   public void setIndicator(String indicatorName) { this.loading.setIndicator(indicatorName); }







   public void setIndicatorColor(int color) { this.loading.setIndicatorColor(color); }
 }
