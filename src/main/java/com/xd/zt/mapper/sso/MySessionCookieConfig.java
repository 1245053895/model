package com.xd.zt.mapper.sso;

import javax.servlet.SessionCookieConfig;

public interface MySessionCookieConfig extends SessionCookieConfig {
    String getDomainPattern();
    void setDomainPattern(String domainPattern);
    String getJvmRoute();
    void setJvmRoute(String jvmRoute);
    boolean isUseBase64Encoding();
    void setUseBase64Encoding(boolean useBase64Encoding);
}