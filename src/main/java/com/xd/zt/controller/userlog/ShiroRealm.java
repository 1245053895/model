package com.xd.zt.controller.userlog;


import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShiroRealm extends AuthorizingRealm {
/*    @Autowired
    private PermissionService permissionService;
    @Autowired
    private UserInfoService userInfoService;*/
    @Override   /*授权*/
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
/*        //获得当前登录的用户及用户权限
        Subject subject= SecurityUtils.getSubject();
      UserInfo userInfo=(UserInfo) subject.getPrincipal();
      Integer userid=userInfo.getUserid();
    List<Permission> permissionList= permissionService.selectPermissionByUserId(userid);
    for (Permission permission:permissionList)
      info.addStringPermission(permission.getPermissionname());*/
      return info;

    }

    @Override  /*认证*/
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
    /*    UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;  //编写shiro，判断用户名和密码
          UserInfo userInfo=  userInfoService.selectUserByName(token.getUsername());
        if (userInfo==null){
            return null;  //shiro底层会抛出UnknowAccountException
        }
        //判断密码
        return new SimpleAuthenticationInfo(userInfo,userInfo.getPassword(),"");*/
    return null;
    }
}
