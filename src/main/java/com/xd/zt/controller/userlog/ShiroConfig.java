package com.xd.zt.controller.userlog;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/*shiro的核心配置类，包含三个重要的方法*/
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 过滤链定义，从上向下顺序执行，一般将/*放在最下边
        //设置拦截器 认证登录
        Map<String,String> filterMap=new LinkedHashMap<>();
//        filterMap.put("/login","anon"); /* 无需认证(登录)即可访问*/
        filterMap.put("/model/designList","perms[user:kancha]");
        filterMap.put("/model/constructList","perms[user:gongcheng]");
        filterMap.put("/model/maintainList","perms[user:yunying]");
        filterMap.put("/model/cityList","perms[user:zhihui]");

        //配置退出过滤器
      /*  filterMap.put("/logout","logout");*/
        /*filterMap.put("/*","authc");  *//* 必须认证才可访问*/
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        shiroFilterFactoryBean.setLoginUrl("/login");
        filterMap.put("/login","authc");  /* 必须认证才可访问*/
        shiroFilterFactoryBean.setLoginUrl("http://10.101.201.154:9092/sso/login.html?ssoClientUrl=http://10.101.201.173:7008");
        shiroFilterFactoryBean.setUnauthorizedUrl("/ss0/permission");
        return shiroFilterFactoryBean;
    }
    // 2、创建DefaultWebSecurityManager  管理realm，去连接外部数据。
    // 该方法用于创建securityManager。这个方法用于关联realm
@Bean(name="securityManager")
public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("shiroRealm") ShiroRealm shiroRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm);    // 关联Realm
        return securityManager;
}
    // 3、创建Realm
@Bean(name = "shiroRealm") //Bean的作用将返回值放入spring的环境中，以便使上面的方法能得到该函数的返回值
public ShiroRealm getShiroRealm(){
        return new ShiroRealm();
}
/*配置ShiroDialect,用于thymeleaf和shiro标签配合使用*/
    @Bean //将返回的shiroDialect对象交给spring的容器
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

}
