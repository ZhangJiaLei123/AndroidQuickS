/*    */ package com.blxt.quickview.viewpager;
/*    */ 
/*    */ import androidx.fragment.app.Fragment;
/*    */ import androidx.fragment.app.FragmentManager;
/*    */ import androidx.fragment.app.FragmentPagerAdapter;
/*    */ import java.util.List;
/*    */ 
/*    */ public class AppFragmentPageAdapter
/*    */   extends FragmentPagerAdapter
/*    */ {
/*    */   public List<Fragment> mFragmentList;
/*    */   
/*    */   public AppFragmentPageAdapter(FragmentManager fm, List<Fragment> fragmentList) {
/* 14 */     super(fm);
/* 15 */     this.mFragmentList = fragmentList;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 21 */   public Fragment getItem(int position) { return (this.mFragmentList == null) ? null : this.mFragmentList.get(position); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 26 */   public int getCount() { return (this.mFragmentList == null) ? 0 : this.mFragmentList.size(); }
/*    */ }


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\viewpager\AppFragmentPageAdapter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */