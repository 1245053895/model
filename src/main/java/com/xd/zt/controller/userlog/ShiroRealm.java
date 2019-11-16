package com.xd.zt.controller.userlog;



import com.xd.zt.domain.userlog.SysMenu;
import com.xd.zt.domain.userlog.SysUser;
import com.xd.zt.service.userlog.SysUserMenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
/**
 * Author:ykr
 *
 * Date:2019/11/11
 *
 * Description:认证授权
 */
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private SysUserMenuService sysUserMenuService;
    @Override   /*授权*/
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //获得当前登录的用户及用户权限
        Integer id= (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");
        System.out.println("..........: "+id);
     SysUser sysUser= (SysUser) SecurityUtils.getSubject().getSession().getAttribute("id");
/*        Subject subject= SecurityUtils.getSubject();*/
    /*  SysUser sysUser=  (SysUser) subject.getPrincipal();*/
      Integer user_id=sysUser.getId();
     List<SysMenu> sysMenuList= sysUserMenuService.selectMenuByUserId(user_id);
    for (SysMenu sysMenu:sysMenuList)
      info.addStringPermission(sysMenu.getName());
      return info;
    }

    @Override  /*认证*/
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;  //编写shiro，判断用户名和密码
      SysUser sysUser= sysUserMenuService.getSysUserByName(token.getUsername());
        // 将用户信息存入session中,方便程序获取,此处可以将根据登录账号查询出的用户信息放到session中
        SecurityUtils.getSubject().getSession().setAttribute("sysUser", sysUser);
    /*    UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;  //编写shiro，判断用户名和密码
          UserInfo userInfo=  userInfoService.selectUserByName(token.getUsername());
        if (userInfo==null){
            return null;  //shiro底层会抛出UnknowAccountException
        }
        //判断密码
        return new SimpleAuthenticationInfo(userInfo,userInfo.getPassword(),"");*/
        return new SimpleAuthenticationInfo(sysUser,sysUser.getPassword(),"");
    }
}
