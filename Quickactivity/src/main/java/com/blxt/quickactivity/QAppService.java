/*    */ package com.blxt.quickactivity;
/*    */ 
/*    */ import android.app.Service;
/*    */ import java.util.Stack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class QAppService
/*    */ {
/*    */   private static Stack<Service> m_stack_service;
/*    */   private static QAppService instance;
/*    */   
/*    */   public static QAppService getAppManager() {
/* 31 */     if (instance == null) {
/* 32 */       instance = new QAppService();
/*    */     }
/* 34 */     return instance;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addActivity(Service activity) {
/* 41 */     if (m_stack_service == null) {
/* 42 */       m_stack_service = new Stack<>();
/*    */     }
/* 44 */     m_stack_service.add(activity);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Service currentActivity() {
/* 52 */     Service activity = m_stack_service.lastElement();
/* 53 */     return activity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void finishService() {
/* 60 */     Service bean = m_stack_service.lastElement();
/* 61 */     finishService(bean);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void finishService(Service activity) {
/* 68 */     if (activity != null) {
/* 69 */       m_stack_service.remove(activity);
/* 70 */       activity.onDestroy();
/* 71 */       activity = null;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void finishService(Class<?> cls) {
/* 79 */     for (Service bean : m_stack_service) {
/* 80 */       if (bean.getClass().equals(cls)) {
/* 81 */         finishService(bean);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void finishAllService() {
/* 90 */     for (int i = 0, size = m_stack_service.size(); i < size; i++) {
/* 91 */       if (null != m_stack_service.get(i)) {
/* 92 */         ((Service)m_stack_service.get(i)).onDestroy();
/*    */       }
/*    */     } 
/* 95 */     m_stack_service.clear();
/*    */   }
/*    */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickactivity-1.1.2.aar!\classes.jar!\com\blxt\quickactivity\QAppService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */