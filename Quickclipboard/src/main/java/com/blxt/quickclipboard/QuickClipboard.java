package com.blxt.quickclipboard;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import java.util.List;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * 剪辑板工具,单例模式
 */
public class QuickClipboard {

    static QuickClipboard instance = null;
    Context context;
    ClipboardManager clipboardManager;
    ClipboardCallBack clipboardCallBack;

    public static QuickClipboard getInstance(Context context,
                                             ClipboardCallBack clipboardCallBack){
        if (instance == null){
            instance = new QuickClipboard(context);
        }
        return instance;
    }

    private QuickClipboard(Context context){
        this.context = context;
        clipboardManager = null;

        clipboardManager = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);


        clipboardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                if(clipboardCallBack != null)
                    clipboardCallBack.onClipboard(getDatas());
            }
        });
    }

    /**
     * 获取剪辑板
     * @return
     */
    public ClipboardManager getClipboard(){

        return clipboardManager;
    }


    /**
     * 填充文本到剪辑板
     * @param content
     * @return
     */
    public  boolean setClipboardContent(String content){
        if (clipboardManager == null){
            return false;
        }
        clipboardManager.setText(content);

        return true;
    }


    /**
     * 获取剪辑板内容
     */
    public String[] getDatas(){

        if (clipboardManager == null){
            return null;
        }

        ClipData data = clipboardManager.getPrimaryClip();

        String items[] = new String[data.getItemCount()];
        int iCount = 0;
        for (int i = 0; i < data.getItemCount(); i++){
            ClipData.Item item = data.getItemAt(i);
            if(item == null){
                continue;
            }
            if(item.getText() == null || item.getText().toString() == null){
                continue;
            }
            String _tmp = item.getText().toString();
            if(_tmp.isEmpty()){
                continue;
            }
            items[iCount] = _tmp;
            iCount++;
        }

        return items;
    }

    public interface ClipboardCallBack{
        void onClipboard(String datas[]);
    }

}
