package com.graduation.renthouse.rent.realm;

import com.graduation.renthouse.rent.user.domain.UserDO;
import com.graduation.renthouse.rent.user.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

public class UserRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;

    @Override
    public String getName() {
        return "UserRealm";
    }


    // 为当前登陆成功的用户授予权限和角色，已经登陆成功了
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal(); //获取用户名
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.getRoles(username));
        authorizationInfo.setStringPermissions(userService.getPermissions(username));
        System.out.println(userService.getPermissions(username));
        return authorizationInfo;
    }

    // 验证当前登录的用户，获取认证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal(); // 获取用户名
        System.out.println(username + "用户名");
        UserDO user = userService.getByUsername(username);
        if (user != null) {
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), "myRealm");
            return authcInfo;
        } else {
            return null;
        }
    }


}
