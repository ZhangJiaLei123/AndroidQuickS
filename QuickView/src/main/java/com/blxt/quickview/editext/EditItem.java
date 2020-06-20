package com.blxt.quickview.editext;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blxt.quickview.AttributeHelper;
import com.blxt.quickview.R;


public class EditItem
        extends FrameLayout
        implements View.OnClickListener {
    private LinearLayout itemGroupLayout;
    private TextView titleTv;
    private EditText contentEdt;
    private ImageView clearIv;
    private ImageView jtRightIv;
    private ItemOnClickListener itemOnClickListener;
    View view = null;
    EditItem in;
    AttributeHelper attributeHelper;
    View.OnClickListener onClickListener = null;
    View.OnLongClickListener onLongClickListener = null;

    public EditItem(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public EditItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initAttrs(context, attrs);
    }

    public EditItem(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initAttrs(context, attrs);
    }


    private void initView(Context context) {
        if (this.view != null) {
            return;
        }

        this.in = this;
        this.view = LayoutInflater.from(context).inflate(R.layout.item_group_layout, null);
        this.itemGroupLayout = this.view.findViewById(R.id.item_group_layout);
        this.titleTv = this.view.findViewById(R.id.title_tv);
        this.contentEdt = this.view.findViewById(R.id.content_edt);
        this.clearIv = this.view.findViewById(R.id.clear_iv);
        this.jtRightIv = this.view.findViewById(R.id.jt_right_iv);
        addView(this.view);

        this.itemGroupLayout.setOnClickListener(this);
        this.clearIv.setOnClickListener(this);
        this.contentEdt.addTextChangedListener(new TextChangeWatcher() {
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);

                String content = EditItem.this.contentEdt.getText().toString().trim();
                if (!TextUtils.isEmpty(content)) {

                    EditItem.this.clearIv.setVisibility(0);
                } else {
                    EditItem.this.clearIv.setVisibility(8);
                }
            }
        });
    }


    private void initAttrs(Context context, AttributeSet attrs) {
        if (this.attributeHelper != null) {
            return;
        }
        this.attributeHelper = new AttributeHelper(context, attrs);


        int defaultTitleColor = context.getResources().getColor(R.color.item_group_title);

        float defaultEdtSize = TypedValue.applyDimension(2, 13.0F, context.getResources().getDisplayMetrics());

        int defaultEdtColor = context.getResources().getColor(R.color.item_group_edt);

        int defaultHintColor = context.getResources().getColor(R.color.item_group_edt);


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditItem);
        String title = typedArray.getString(R.styleable.EditItem_title);
        float paddingLeft = typedArray.getDimension(R.styleable.EditItem_paddingLeft, 15.0F);
        float paddingRight = typedArray.getDimension(R.styleable.EditItem_paddingRight, 15.0F);
        float paddingTop = typedArray.getDimension(R.styleable.EditItem_paddingTop, 5.0F);
        float paddingBottom = typedArray.getDimension(R.styleable.EditItem_paddingTop, 5.0F);


        String content = this.attributeHelper.getString("text", "");
        float contentSize = this.attributeHelper.getInt("textSize", (int) defaultEdtSize);
        int contentColor = this.attributeHelper.getColor("textColor", defaultTitleColor);


        float titleSize = typedArray.getDimension(R.styleable.EditItem_titleSize, contentSize);
        int titleColor = typedArray.getColor(R.styleable.EditItem_titleColor, defaultTitleColor);


        String hintContent = this.attributeHelper.getString("hint", "");
        int hintColor = typedArray.getColor(R.styleable.EditItem_hintColor, defaultHintColor);

        boolean isEditable = this.attributeHelper.getBoolean("enabled", true);

        boolean showJtIcon = typedArray.getBoolean(R.styleable.EditItem_jt_visible, true);
        typedArray.recycle();


        this.itemGroupLayout.setPadding((int) paddingLeft, (int) paddingTop, (int) paddingRight, (int) paddingBottom);
        this.titleTv.setText(title);
        this.titleTv.setTextColor(titleColor);

        this.contentEdt.setText(content);
        if (contentSize != 0.0F) {
            this.contentEdt.setTextSize(contentSize);
            this.titleTv.setTextSize(titleSize);
        }
        if (contentColor != 0) {
            this.contentEdt.setTextColor(contentColor);
        }

        this.contentEdt.setHint(hintContent);
        this.contentEdt.setHintTextColor(hintColor);
        this.contentEdt.setFocusableInTouchMode(isEditable);
        this.contentEdt.setLongClickable(false);
        this.jtRightIv.setVisibility(showJtIcon ? 0 : 8);
    }


    public void setItemOnClickListener(ItemOnClickListener itemOnClickListener) {
        this.itemOnClickListener = itemOnClickListener;
    }


    public void setTitle(String title) {
        this.titleTv.setText(title);
    }


    public String getText() {
        return this.contentEdt.getText().toString().trim();
    }


    public void setText(String text) {
        this.contentEdt.setText(text);
    }


    public void setEnabled(boolean enabled) {
        this.contentEdt.setEnabled(enabled);
        this.clearIv.setVisibility(enabled ? 0 : 8);
        this.jtRightIv.setVisibility(enabled ? 8 : 0);
    }


    public void setTextSize(int textSize) {
        this.contentEdt.setTextSize(textSize);
        this.titleTv.setTextSize(textSize);
    }


    public TextView getTitleTv() {
        return this.titleTv;
    }


    public EditText getContentEdt() {
        return this.contentEdt;
    }


    public ImageView getClearIv() {
        return this.clearIv;
    }


    public ImageView getJtRightIv() {
        return this.jtRightIv;
    }


    public void setOnClickListener(View.OnClickListener l) {
        this.onClickListener = l;
        this.jtRightIv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditItem.this.onClickListener.onClick((View) EditItem.this.in);
            }
        });
    }


    public void setOnLongClickListener(View.OnLongClickListener l) {
        this.onLongClickListener = l;
        this.contentEdt.setLongClickable(true);
        this.jtRightIv.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                return EditItem.this.onLongClickListener.onLongClick((View) EditItem.this.in);
            }
        });
    }


    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.item_group_layout) {
            if (this.itemOnClickListener != null) {
                this.itemOnClickListener.onItemClick((View) this);
            }
        } else if (id == R.id.clear_iv) {
            this.contentEdt.setText("");
            this.clearIv.setVisibility(8);
        }
    }

    public static interface ItemOnClickListener {
        void onItemClick(View param1View);
    }
}

