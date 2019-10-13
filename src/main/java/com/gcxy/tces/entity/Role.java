package com.gcxy.tces.entity;

import java.util.List;

/**
 * @author Rain
 * @date 2019/10/8
 */
public class Role {

    private String roleId;
    private String roleName;
    //角色对应的权限
    private List<Auth> auths;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Auth> getAuths() {
        return auths;
    }

    public void setAuths(List<Auth> auths) {
        this.auths = auths;
    }
}
