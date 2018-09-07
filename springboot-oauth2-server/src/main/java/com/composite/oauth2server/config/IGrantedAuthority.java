package com.composite.oauth2server.config;

import org.springframework.security.core.GrantedAuthority;

public class IGrantedAuthority implements GrantedAuthority {
    private String roleId;

    private String menuId;

    @Override
    public String getAuthority() {
        return roleId + "&" + menuId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public IGrantedAuthority(String roleId, String menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }
}
