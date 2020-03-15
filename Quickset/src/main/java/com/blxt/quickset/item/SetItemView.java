 package com.blxt.quickset.item;

 import android.content.Context;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.ImageView;
 import android.widget.TextView;
 import com.blxt.quickset.R;







 public class SetItemView
   extends BaseSetItemView
 {
   private ImageView iv_right;

   public SetItemView(Context context, String title) {
     super(context);

     this.instance = this;
     LayoutInflater.from(getContext()).inflate(R.layout._item_set__simple, (ViewGroup)this);

     this.iv_bottom = (ImageView)findViewById(R.id._item_bottom);
     this.iv_logo = (ImageView)findViewById(R.id._item_title_ic);
     this.tv_title = (TextView)findViewById(R.id._item_title);
     this.tv_hint = (TextView)findViewById(R.id._item_tip);
     this.iv_right = (ImageView)findViewById(R.id._item_iright);

     setTitle(title);
     initview();

     setOnClickListener(new View.OnClickListener()
         {
           public void onClick(View view) {
             if (SetItemView.this.clickListener != null) {
               SetItemView.this.clickListener.onClickSetItem((View)SetItemView.this.instance);
             }
           }
         });
   }




   public void setEnabled(boolean isbootom) { setEnabled(isbootom); }
 }
