package com.blxt.quicknoti;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import java.lang.reflect.Method;


public class QNotif {
    NotificationManager mNotificationManager;
    Context context;
    int notifId = 1;
    String CHANNEL_ID = "通知";
    String CHANNEL_NAME = "提示通知";

    RemoteViews view = null;
    PendingIntent pintent = null;

    String title;

    String msg;
    int imageResId = 0;


    boolean isKeep = false;


    public QNotif(Context context, int notifId) {
        this.context = context;
        this.notifId = notifId;
    }


    public void showNotification(String title, String msg) {
        showNotification(this.notifId, this.imageResId, title, msg, this.isKeep, this.view, this.pintent);
    }


    public void showNotification(int notifId, String title, String msg) {
        showNotification(notifId, this.imageResId, title, msg, this.isKeep, this.view, this.pintent);
    }


    public void showNotification(int notifId, String title, String msg, boolean isKeep) {
        showNotification(notifId, this.imageResId, title, msg, isKeep, this.view, this.pintent);
    }


    public void showNotification(int notifId, int imageResId, String title, String msg, boolean isKeep, RemoteViews view, PendingIntent pintent) {
        NotificationManager notificationManager = getNotificationManager(this.context);

        Notification notification = builder(imageResId, title, msg, isKeep, view, pintent);

        notificationManager.notify(notifId, notification);
    }


    public Notification builder() {
        return builder(this.imageResId, this.title, this.msg, this.isKeep, this.view, this.pintent);
    }


    public Notification builder(int imageResId, String title, String msg, boolean isKeep, RemoteViews view, PendingIntent pintent) {
        NotificationManager notificationManager = getNotificationManager(this.context);

        Notification notification = null;

        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel(this.CHANNEL_ID, this.CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH));
        }

        Object builder = makeBuilder(this.context, imageResId, title, msg, view, pintent);

        if (builder == null) {
            return null;
        }

        if (builder instanceof Notification.Builder) {
            if (Build.VERSION.SDK_INT > 15) {
                notification = ((Notification.Builder) builder).build();
            } else {
                return null;
            }
        } else if (builder instanceof NotificationCompat.Builder) {
            notification = ((NotificationCompat.Builder) builder).build();
        }

        if (isKeep) {
            notification.flags |= NotificationCompat.FLAG_ONGOING_EVENT
                    | NotificationCompat.FLAG_FOREGROUND_SERVICE;
        }

        return notification;
    }


    public void close() {
        if (this.mNotificationManager != null) {
            this.mNotificationManager.cancelAll();
        }
    }


    public void close(int id) {
        if (this.mNotificationManager != null) {
            this.mNotificationManager.cancel(id);
        }
    }


    @SuppressLint("WrongConstant")
    public static void collapseStatusBar(Context context) {// statusbar
        Object service = context.getSystemService("statusbar");
        if (null == service) {
            return;
        }
        try {
            Class<?> clazz = Class.forName("android.app.StatusBarManager");
            int sdkVersion = Build.VERSION.SDK_INT;
            Method collapse = null;
            if (sdkVersion <= 16) {
                collapse = clazz.getMethod("collapse", new Class[0]);
            } else {
                collapse = clazz.getMethod("collapsePanels", new Class[0]);
            }
            collapse.setAccessible(true);
            collapse.invoke(service, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @SuppressLint("WrongConstant")
    private NotificationManager getNotificationManager(Context context) {
        if (this.mNotificationManager == null) {
            this.mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return this.mNotificationManager;
    }


    private Object makeBuilder(Context context, int ic, String title, String msg, RemoteViews view, PendingIntent pintent) {
        Object builder = null;


        if (Build.VERSION.SDK_INT >= 26) {
            builder = new NotificationCompat.Builder(context, this.CHANNEL_ID);

        } else if (Build.VERSION.SDK_INT >= 22) {
            builder = new NotificationCompat.Builder(context);

        } else if (Build.VERSION.SDK_INT >= 16) {
            builder = new Notification.Builder(context);
        } else {

            return null;
        }

        if (builder instanceof Notification.Builder) {
            if (ic != 0) {
                ((Notification.Builder) builder).setSmallIcon(ic);
            }
            if (title != null) {
                ((Notification.Builder) builder).setContentTitle(title);
            }
            if (msg != null) {
                ((Notification.Builder) builder).setContentText(msg);
            }
            if (view != null) {
                ((Notification.Builder) builder).setContent(view);
            }
            if (pintent != null) {
                ((Notification.Builder) builder).setContentIntent(pintent);
            }
        } else if (builder instanceof NotificationCompat.Builder) {
            if (ic != 0) {
                ((NotificationCompat.Builder) builder).setSmallIcon(ic);
            }
            if (title != null) {
                ((NotificationCompat.Builder) builder).setContentTitle(title);
            }
            if (msg != null) {
                ((NotificationCompat.Builder) builder).setContentText(msg);
            }
            if (view != null) {
                ((NotificationCompat.Builder) builder).setContent(view);
            }
            if (pintent != null) {
                ((NotificationCompat.Builder) builder).setContentIntent(pintent);
            }
        }


        return builder;
    }


    public int getNotifId() {
        return this.notifId;
    }


    public void setNotifId(int notifId) {
        this.notifId = notifId;
    }


    public String getCHANNEL_ID() {
        return this.CHANNEL_ID;
    }


    public void setCHANNEL_ID(String CHANNEL_ID) {
        this.CHANNEL_ID = CHANNEL_ID;
    }


    public RemoteViews getView() {
        return this.view;
    }


    public void setView(RemoteViews view) {
        this.view = view;
    }


    public PendingIntent getPintent() {
        return this.pintent;
    }


    public void setPintent(PendingIntent pintent) {
        this.pintent = pintent;
    }


    public int getImageResId() {
        return this.imageResId;
    }


    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }


    public boolean isKeep() {
        return this.isKeep;
    }


    public void setKeep(boolean keep) {
        this.isKeep = keep;
    }


    public String getChannelName() {
        return this.CHANNEL_NAME;
    }


    public void setChannelName(String channelName) {
        this.CHANNEL_NAME = channelName;
    }


    public String getTitle() {
        return this.title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getMsg() {
        return this.msg;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }
}
