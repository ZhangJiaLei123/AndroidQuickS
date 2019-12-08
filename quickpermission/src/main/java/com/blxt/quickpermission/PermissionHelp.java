package com.blxt.quickpermission;

import android.content.Context;

import androidx.core.content.PermissionChecker;

/**
 * 权限帮助工具类,
 * 在这里实现权限检查等工具
 */
public class PermissionHelp {

    /**
     * 权限检查.判断是否有该权限
     * @param context
     * @param permiss
     * @return true 有权限 false 没有权限
     */
    public static boolean check(Context context,
                                @PermissionChecker.PermissionResult String ... permiss){
        boolean fal = false;

        return fal;
    }


}
