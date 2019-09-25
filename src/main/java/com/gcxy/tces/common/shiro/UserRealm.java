package com.gcxy.tces.common.shiro;

import com.gcxy.tces.entity.User;
import com.gcxy.tces.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Rain
 * @date 2019/9/24
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 授权处理回调方法：获取当前用户的权限信息，用于用户授权操作
     * 用户权限信息包括role（角色）和permission（权限）两部分
     * @param principals 用户名信息collection
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //principals.getPrimaryPrincipal()获取当前用户名
        //进行为空性判断
        if(principals == null || principals.getPrimaryPrincipal() == null){
            return null;
        }

        //SimpleAuthorizationInfo对象保存用户的权限与角色信息
        //SimpleAuthorizationInfo构造方法接收一个权限set
        // 返回该对象后shiro会进行用户授权操作
        return new SimpleAuthorizationInfo();
    }


    /**
     * 认证处理回调方法：获取当前用户的认证信息，用户用户的认证操作
     * 实现登录的业务逻辑
     * @param token login时传入的用户令牌（username+password）
     * @throws AuthenticationException exception
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //对token数据进行校验
        if(token == null || "".equals(token.getPrincipal())){
            return null;
        }

        //进行认证（登录）的逻辑判断
        User user = userService.getUserByCode((String)token.getPrincipal());
        //判断用户名是否存在
        if(user == null){
            return null; //返回null底层自动抛出UnknownAccountException异常
        }

        //SimpleAuthenticationInfo保存用户正确认证信息的对象，包括数据库中的用户名，密码

        //返回认证信息对象后会与token中用户的信息进行比较，进行认证操作
        //密码不正确抛出：IncorrectCredentialsException凭证不匹配异常
        return new SimpleAuthenticationInfo(user.getUserCode(), user.getUserPass(), this.getName());
    }
}
