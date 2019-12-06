package com.xd.zt.serviceImpl.sso;

import com.xd.zt.mapper.sso.MySessionCookieConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.ServletContext;
/**  *  * @author yjy  * 2018-06-08 14:53  */
@Slf4j
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600, redisNamespace = "center")
public class RedisSessionConfig implements ApplicationContextAware {
    @Bean
public CookieSerializer cookieSerializer(ServletContext servletContext, MySessionCookieConfig sessionCookieConfig) {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        if (servletContext != null) {
            if (sessionCookieConfig != null) {
                if (sessionCookieConfig.getName() != null)
                    cookieSerializer.setCookieName(sessionCookieConfig.getName());
                if (sessionCookieConfig.getDomain() != null)
                    cookieSerializer.setDomainName(sessionCookieConfig.getDomain());
                if (sessionCookieConfig.getPath() != null)
                    cookieSerializer.setCookiePath(sessionCookieConfig.getPath());
                if (sessionCookieConfig.getMaxAge() != -1)
                    cookieSerializer.setCookieMaxAge(sessionCookieConfig.getMaxAge());
                if (sessionCookieConfig.getDomainPattern() != null)
                    cookieSerializer.setDomainNamePattern(sessionCookieConfig.getDomainPattern());
                if (sessionCookieConfig.getJvmRoute() != null)
                    cookieSerializer.setJvmRoute(sessionCookieConfig.getJvmRoute());
                cookieSerializer.setUseSecureCookie(sessionCookieConfig.isSecure());
                cookieSerializer.setUseBase64Encoding(sessionCookieConfig.isUseBase64Encoding());
                cookieSerializer.setUseHttpOnlyCookie(sessionCookieConfig.isHttpOnly());
            }
        }
        if (ClassUtils.isPresent("org.springframework.security.web.authentication.RememberMeServices", null)) {
            boolean usesSpringSessionRememberMeServices = !ObjectUtils.isEmpty(this.context.getBeanNamesForType(SpringSessionRememberMeServices.class));
            if (usesSpringSessionRememberMeServices) {
                cookieSerializer.setRememberMeRequestAttribute(
                        SpringSessionRememberMeServices.REMEMBER_ME_LOGIN_ATTR);
            }
        }
        return cookieSerializer;
    }
    private ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}