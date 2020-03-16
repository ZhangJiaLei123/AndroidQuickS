
package com.blxt.quicknoti;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.View;

import androidx.appcompat.app.AlertDialog;


public class QDialog {
    public static ProgressDialog showProgressDialog(Activity context, String msg) {
        return showProgressDialog(context, null, msg);
    }


    public static ProgressDialog showProgressDialog(Activity context, String title, String msg) {
        ProgressDialog mDialog = new ProgressDialog((Context) context);
        if (title != null) {
            mDialog.setTitle(title);
        }
        mDialog.setMessage(msg);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
        return mDialog;
    }


    public static AlertDialog showDialog(Activity context, String title, String msg) {
        return showDialog(context, -1, title, msg, null);
    }


    public static AlertDialog showDialog(Activity context, String title, String msg, DialogInterface.OnClickListener onClickListener) {
        return showDialog(context, -1, title, msg, onClickListener);
    }


    public static AlertDialog showDialog(Activity context, String title, String msg, String okText, String cancelText, DialogInterface.OnClickListener onClickListener) {
        return showDialog(context, title, msg, okText, cancelText, null, onClickListener);
    }


    public static AlertDialog showDialog(Activity context, String title, String msg, String okText, String cancelText, String neutral, DialogInterface.OnClickListener onClickListener) {
        return showDialog((Context) context, -1, null, title, msg, okText, cancelText, neutral, onClickListener);
    }


    public static AlertDialog showDialog(Activity context, int iconResID, String title, String msg, DialogInterface.OnClickListener onClickListener) {
        return showDialog((Context) context, iconResID, null, title, msg, "确认", "取消", null, onClickListener);
    }


    public static AlertDialog showDialog(Context context, View view, String title, DialogInterface.OnClickListener onClickListener) {
        return showDialog(context, view, title, "确定", "取消", onClickListener);
    }


    public static AlertDialog showDialog(Context context, View view, String title, String okText, String cancelText, DialogInterface.OnClickListener onClickListener) {
        return showDialog(context, -1, view, title, null, okText, cancelText, null, onClickListener);
    }


    public static AlertDialog showDialog(Context context, int icoResId, View view, String title, String okText, String cancelText, DialogInterface.OnClickListener onClickListener) {
        return showDialog(context, icoResId, view, title, null, okText, cancelText, null, onClickListener);
    }


    public static AlertDialog showDialog(Context context, View view, String title, String okText, String cancelText, String neutral, DialogInterface.OnClickListener onClickListener) {
        return showDialog(context, -1, view, title, null, okText, cancelText, neutral, onClickListener);
    }


    public static AlertDialog showDialogWithIco(Context context, int icoResId, View view, String title, String okText, String cancelText, String neutral, DialogInterface.OnClickListener onClickListener) {
        return showDialog(context, icoResId, view, title, null, okText, cancelText, neutral, onClickListener);
    }


    public static AlertDialog showDialog(Context context, int iconResID, View view, String title, String msg, String okText, String cancelText, String neutral) {
        return showDialog(context, iconResID, view, title, msg, okText, cancelText, neutral, null);
    }


    public static AlertDialog showDialog(Context context, int iconResID, View view, String title, String msg, String okText, String cancelText, String neutral, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (title != null) {
            builder.setTitle(title);
        }
        if (iconResID != -1) {
            builder.setIcon(iconResID);
        }
        if (view != null) {
            builder.setView(view);
        }
        if (msg != null) {
            builder.setMessage(msg);
        }

        if (okText != null) {
            builder.setPositiveButton(okText, onClickListener);
        }

        if (cancelText != null) {
            builder.setNegativeButton(cancelText, onClickListener);
        }

        if (neutral != null) {
            builder.setNeutralButton(neutral, onClickListener);
        }

        builder.setCancelable(false);

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        return dialog;
    }


    public static void startSetting(final Activity activity, String content) {
        AlertDialog alertDialog = (new AlertDialog.Builder((Context) activity)).setTitle("提示").setMessage(content).setPositiveButton("打开app应用程序信息界面", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                QDialog.startSetting((Context) activity);
            }
        }).create();
        alertDialog.show();
    }


    public static void startSetting(Context context) {
        Intent intent = new Intent();
        intent.addFlags(268435456);
        if (Build.VERSION.SDK_INT >= 9) {
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            intent.setAction("android.intent.action.VIEW");
            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            intent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        context.startActivity(intent);
    }
}
