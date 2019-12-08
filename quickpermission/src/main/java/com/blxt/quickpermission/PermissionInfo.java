package com.blxt.quickpermission;

public class PermissionInfo {
    /**
     * 权限字段
     */
    public String permission;
    /**
     * 权限描述
     */
    public String info;

    public PermissionInfo(String permission, String info) {
        this.permission = permission;
        this.info = info;
    }
}
