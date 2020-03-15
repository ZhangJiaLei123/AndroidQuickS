package com.blxt.quickview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

import com.blxt.quickview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义弹出菜单视图类 - 仿IOS的选择弹窗
 *
 eg:
         new ActionSheetDialog(context)
         .builder()
         .setTitle("自动息屏延时")
         .setCancelable(true)
         .setCanceledOnTouchOutside(true)
         .setCallBack(new DialogCallBack() {
        @Override
        public void OnDismiss(Object obj) {
        int index = (int)obj;
        switch (index){
        case 1: itSetDisplay.setHint("关闭");break;
        case 2: itSetDisplay.setHint("1分钟");break;
        case 3: itSetDisplay.setHint("3分钟");break;
        case 4: itSetDisplay.setHint("10分钟");break;
        }
        LOG.i("关闭" + index);
        }
        })
         .addSheetItem("关闭", ActionSheetDialog.SheetItemColor.Blue)
         .addSheetItem("1分钟", ActionSheetDialog.SheetItemColor.Blue)
         .addSheetItem("3分钟", ActionSheetDialog.SheetItemColor.Blue)
         .addSheetItem("10分钟", ActionSheetDialog.SheetItemColor.Blue)
         .show();
 */
public class ActionSheetDialog {
    private Context context;
    private Dialog dialog;
    private TextView txt_title;
    private TextView txt_cancel;
    private LinearLayout lLayout_content;
    private ScrollView sLayout_content;
    private boolean showTitle = false;
    private List<SheetItem> sheetItemList;
    private Display display;
    DialogCallBack callBack;

    public ActionSheetDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public ActionSheetDialog builder() {
        View view = LayoutInflater.from(context).inflate(
                R.layout.__action_sheet_, null);

        view.setMinimumWidth(display.getWidth());

        sLayout_content = (ScrollView) view.findViewById(R.id.sLayout_content);
        lLayout_content = (LinearLayout) view
                .findViewById(R.id.lLayout_content);
        txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_cancel = (TextView) view.findViewById(R.id.txt_cancel);
        txt_cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog = new Dialog(context, R.style.DialogTheme);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);

        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);// 弹窗位置
        window.setWindowAnimations(R.style.dialog_animation); // 添加动画

        // 消除状态栏处理
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;
            view.setSystemUiVisibility(uiOptions);
        }

        return this;
    }

    public ActionSheetDialog setTitle(String title) {
        showTitle = true;
        txt_title.setVisibility(View.VISIBLE);
        txt_title.setText(title);
        return this;
    }

    public ActionSheetDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public ActionSheetDialog setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }


    public ActionSheetDialog addSheetItem(SheetItemColor color, String ...strItem) {
        if (sheetItemList == null) {
            sheetItemList = new ArrayList<SheetItem>();
        }
        for(String s : strItem){
            sheetItemList.add(new SheetItem(s, color));
        }
        return this;
    }

    private void setSheetItems() {
        if (sheetItemList == null || sheetItemList.size() <= 0) {
            return;
        }

        int size = sheetItemList.size();

        if (size >= 7) {
            LayoutParams params = (LayoutParams) sLayout_content
                    .getLayoutParams();
            params.height = display.getHeight() / 2;
            sLayout_content.setLayoutParams(params);
        }

        for (int i = 1; i <= size; i++) {
            final int index = i;
            SheetItem sheetItem = sheetItemList.get(i - 1);
            String strItem = sheetItem.name;
            SheetItemColor color = sheetItem.color;

            TextView textView = new TextView(context);
            textView.setText(strItem);
            textView.setTextSize(18);
            textView.setGravity(Gravity.CENTER);

            if (size == 1) {
                if (showTitle) {
                    textView.setBackgroundResource(R.drawable.__selector_actionsheet_bottom);
                } else {
                    textView.setBackgroundResource(R.drawable.__selector_actionsheet_single);
                }
            } else {
                if (showTitle) {
                    if (i >= 1 && i < size) {
                        textView.setBackgroundResource(R.drawable.__selector_actionsheet_middle);
                    } else {
                        textView.setBackgroundResource(R.drawable.__selector_actionsheet_bottom);
                    }
                } else {
                    if (i == 1) {
                        textView.setBackgroundResource(R.drawable.__selector_actionsheet_top);
                    } else if (i < size) {
                        textView.setBackgroundResource(R.drawable.__selector_actionsheet_middle);
                    } else {
                        textView.setBackgroundResource(R.drawable.__selector_actionsheet_bottom);
                    }
                }
            }

            if (color == null) {
                textView.setTextColor(Color.parseColor(SheetItemColor.Blue
                        .getName()));
            } else {
                textView.setTextColor(Color.parseColor(color.getName()));
            }

            float scale = context.getResources().getDisplayMetrics().density;
            int height = (int) (45 * scale + 0.5f);
            textView.setLayoutParams(new LayoutParams(
                    LayoutParams.MATCH_PARENT, height));

            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss(index);
                }
            });

            lLayout_content.addView(textView);
        }
    }

    public void show() {
        setSheetItems();

        // 消除状态栏处理
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        dialog.show();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
    }

    public void dismiss(int index) {
        if(callBack != null){
            callBack.OnDismiss(index);
        }
        dialog.dismiss();
    }

    public ActionSheetDialog setCallBack(DialogCallBack callBack){
        this.callBack = callBack;
        return this;
    }


    public class SheetItem {
        String name;
        SheetItemColor color;

        public SheetItem(String name, SheetItemColor color) {
            this.name = name;
            this.color = color;
        }
    }

    public enum SheetItemColor {
        Blue("#037BFF"), Red("#FD4A2E");

        private String name;

        private SheetItemColor(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * 对话框关闭接口
     */
    public interface DialogCallBack {
        void OnDismiss(Object obj);
    }

}
