/*     */ package com.heneng.quicknoti;
/*     */ 
/*     */ import android.app.Notification;
/*     */ import android.app.NotificationChannel;
/*     */ import android.app.NotificationManager;
/*     */ import android.app.PendingIntent;
/*     */ import android.content.Context;
/*     */ import android.os.Build;
/*     */ import android.widget.RemoteViews;
/*     */ import androidx.core.app.NotificationCompat;
/*     */ import java.lang.reflect.Method;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class QNotif
/*     */ {
/*     */   NotificationManager mNotificationManager;
/*     */   Context context;
/*  30 */   int notifId = 1;
/*  31 */   String CHANNEL_ID = "通知";
/*  32 */   String CHANNEL_NAME = "默认通知";
/*     */   
/*  34 */   RemoteViews view = null;
/*  35 */   PendingIntent pintent = null;
/*     */   
/*  37 */   int imageResId = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isKeep = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public QNotif(Context context, int notifId) {
/*  46 */     this.context = context;
/*  47 */     this.notifId = notifId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public void showNotification(String title, String msg) { showNotification(this.imageResId, title, msg, this.isKeep, this.view, this.pintent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void showNotification(int ic, String title, String msg, boolean fal, RemoteViews view, PendingIntent pintent) {
/*  71 */     NotificationManager notificationManager = getNotificationManager(this.context);
/*     */     
/*  73 */     Notification notification = null;
/*     */ 
/*     */     
/*  76 */     if (Build.VERSION.SDK_INT >= 26) {
/*  77 */       notificationManager.createNotificationChannel(new NotificationChannel(this.CHANNEL_ID, this.CHANNEL_NAME, 4));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  83 */     Object builder = makeBuilder(this.context, ic, title, msg, view, pintent);
/*     */ 
/*     */     
/*  86 */     if (builder == null) {
/*     */       return;
/*     */     }
/*  89 */     if (builder instanceof Notification.Builder) {
/*     */       
/*  91 */       notification = ((Notification.Builder)builder).build();
/*     */     }
/*  93 */     else if (builder instanceof NotificationCompat.Builder) {
/*     */       
/*  95 */       notification = ((NotificationCompat.Builder)builder).build();
/*     */     } 
/*     */     
/*  98 */     if (fal) {
/*  99 */       notification.flags |= 0x42;
/*     */     }
/*     */ 
/*     */     
/* 103 */     notificationManager.notify(this.notifId, notification);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/* 110 */     if (this.mNotificationManager != null) {
/* 111 */       this.mNotificationManager.cancelAll();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close(int id) {
/* 118 */     if (this.mNotificationManager != null) {
/* 119 */       this.mNotificationManager.cancel(id);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void collapseStatusBar(Context context) {
/* 128 */     Object service = context.getSystemService("statusbar");
/* 129 */     if (null == service) {
/*     */       return;
/*     */     }
/*     */     try {
/* 133 */       Class<?> clazz = Class.forName("android.app.StatusBarManager");
/* 134 */       int sdkVersion = Build.VERSION.SDK_INT;
/* 135 */       Method collapse = null;
/* 136 */       if (sdkVersion <= 16) {
/* 137 */         collapse = clazz.getMethod("collapse", new Class[0]);
/*     */       } else {
/* 139 */         collapse = clazz.getMethod("collapsePanels", new Class[0]);
/*     */       } 
/* 141 */       collapse.setAccessible(true);
/* 142 */       collapse.invoke(service, new Object[0]);
/* 143 */     } catch (Exception e) {
/* 144 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private NotificationManager getNotificationManager(Context context) {
/* 154 */     if (this.mNotificationManager == null) {
/* 155 */       this.mNotificationManager = (NotificationManager)context.getSystemService("notification");
/*     */     }
/* 157 */     return this.mNotificationManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Object makeBuilder(Context context, int ic, String title, String msg, RemoteViews view, PendingIntent pintent) {
/* 172 */     Object builder = null;
/*     */ 
/*     */     
/* 175 */     if (Build.VERSION.SDK_INT >= 26) {
/* 176 */       builder = new NotificationCompat.Builder(context, this.CHANNEL_ID);
/*     */     
/*     */     }
/* 179 */     else if (Build.VERSION.SDK_INT >= 22) {
/* 180 */       builder = new NotificationCompat.Builder(context);
/*     */     
/*     */     }
/* 183 */     else if (Build.VERSION.SDK_INT >= 16) {
/* 184 */       builder = new Notification.Builder(context);
/*     */     } else {
/*     */       
/* 187 */       return null;
/*     */     } 
/*     */     
/* 190 */     if (builder instanceof Notification.Builder) {
/* 191 */       if (ic != 0) {
/* 192 */         ((Notification.Builder)builder).setSmallIcon(ic);
/*     */       }
/* 194 */       if (title != null) {
/* 195 */         ((Notification.Builder)builder).setContentTitle(title);
/*     */       }
/* 197 */       if (msg != null) {
/* 198 */         ((Notification.Builder)builder).setContentText(msg);
/*     */       }
/* 200 */       if (view != null) {
/* 201 */         ((Notification.Builder)builder).setContent(view);
/*     */       }
/* 203 */       if (pintent != null) {
/* 204 */         ((Notification.Builder)builder).setContentIntent(pintent);
/*     */       }
/*     */     }
/* 207 */     else if (builder instanceof NotificationCompat.Builder) {
/* 208 */       if (ic != 0) {
/* 209 */         ((NotificationCompat.Builder)builder).setSmallIcon(ic);
/*     */       }
/* 211 */       if (title != null) {
/* 212 */         ((NotificationCompat.Builder)builder).setContentTitle(title);
/*     */       }
/* 214 */       if (msg != null) {
/* 215 */         ((NotificationCompat.Builder)builder).setContentText(msg);
/*     */       }
/* 217 */       if (view != null) {
/* 218 */         ((NotificationCompat.Builder)builder).setContent(view);
/*     */       }
/* 220 */       if (pintent != null) {
/* 221 */         ((NotificationCompat.Builder)builder).setContentIntent(pintent);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 227 */     return builder;
/*     */   }
/*     */ 
/*     */   
/* 231 */   public int getNotifId() { return this.notifId; }
/*     */ 
/*     */ 
/*     */   
/* 235 */   public void setNotifId(int notifId) { this.notifId = notifId; }
/*     */ 
/*     */ 
/*     */   
/* 239 */   public String getCHANNEL_ID() { return this.CHANNEL_ID; }
/*     */ 
/*     */ 
/*     */   
/* 243 */   public void setCHANNEL_ID(String CHANNEL_ID) { this.CHANNEL_ID = CHANNEL_ID; }
/*     */ 
/*     */ 
/*     */   
/* 247 */   public RemoteViews getView() { return this.view; }
/*     */ 
/*     */ 
/*     */   
/* 251 */   public void setView(RemoteViews view) { this.view = view; }
/*     */ 
/*     */ 
/*     */   
/* 255 */   public PendingIntent getPintent() { return this.pintent; }
/*     */ 
/*     */ 
/*     */   
/* 259 */   public void setPintent(PendingIntent pintent) { this.pintent = pintent; }
/*     */ 
/*     */ 
/*     */   
/* 263 */   public int getImageResId() { return this.imageResId; }
/*     */ 
/*     */ 
/*     */   
/* 267 */   public void setImageResId(int imageResId) { this.imageResId = imageResId; }
/*     */ 
/*     */ 
/*     */   
/* 271 */   public boolean isKeep() { return this.isKeep; }
/*     */ 
/*     */ 
/*     */   
/* 275 */   public void setKeep(boolean keep) { this.isKeep = keep; }
/*     */ 
/*     */ 
/*     */   
/* 279 */   public String getChannelName() { return this.CHANNEL_NAME; }
/*     */ 
/*     */ 
/*     */   
/* 283 */   public void setChannelName(String channelName) { this.CHANNEL_NAME = channelName; }
/*     */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quicknoti-1.0.2.aar!\classes.jar!\com\heneng\quicknoti\QNotif.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */