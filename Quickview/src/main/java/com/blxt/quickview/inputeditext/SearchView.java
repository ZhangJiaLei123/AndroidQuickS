package com.blxt.quickview.inputeditext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blxt.quickview.R;

/**
 * 用户名输入EditText
 */
public class SearchView extends RelativeLayout implements View.OnClickListener {

    private SearchView searchView;
    private View view = null;
    private EditText edtSearch;
    private ImageView ivSearch;
    OnSearchClick click = null;

    public SearchView(Context context) {
        super(context);
        initUI();
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI();
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI();
    }

    @SuppressLint("NewApi")
    public SearchView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initUI();
    }


    public void initUI(){
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.__searchview,this);
            searchView = this;
            edtSearch = findViewById(R.id.edt_search);
            ivSearch = findViewById(R.id.iv_search);

            ivSearch.setOnClickListener(this);
            edtSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
            edtSearch.setInputType(EditorInfo.TYPE_CLASS_TEXT);

            edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId==EditorInfo.IME_ACTION_SEND ||(event!=null&&event.getKeyCode()== KeyEvent.KEYCODE_ENTER))
                    {
                        // 回车键
                        if(click != null){
                            click.onSearchClick(searchView);
                        }
                        return true;
                    }
                    return false;
                }
            });

        }
      //  EtInput.clearFocus();
    }



    public String getText(){
        if(edtSearch.getText() == null){
            return "";
        }
        return edtSearch.getText().toString();
    }

    public void setText(int str){
        edtSearch.setText(str);
    }

    public void setText(String str){
        edtSearch.setText(str);
    }

    public void setHint(int str){
        edtSearch.setHint(str);
    }
    public void setHint(String str){
        edtSearch.setHint(str);
    }

    @Override
    public void onClick(View view) {
        if(click != null){
            click.onSearchClick(this);
        }
    }


    public void setOnSearchClick(OnSearchClick click) {
        this.click = click;
    }

    public interface OnSearchClick{
        void onSearchClick(SearchView searchView);
    }


}
