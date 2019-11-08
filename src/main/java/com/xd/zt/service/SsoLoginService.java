package com.xd.zt.service;


import com.ym.sso.supervisor.common.bean.SsoLogin;
import com.ym.sso.supervisor.common.bean.SsoTicket;

import javax.servlet.http.HttpSession;


/**
 * 登录服务
 *
 * @author 张江波
 * @version 2019-10-02 10:35
 */
public interface SsoLoginService
{
    /**
     * 检查门票是否存在
     *
     * @param ssoTicket
     *            输入参数
     * @return 门票是否存在 true 是 false 否
     */
    SsoTicket checkTicket(SsoTicket ssoTicket);

    /**
     * 登录方法
     *
     * @param session
     *            HttpSession
     * @param ssoTicket
     *            输入参数
     */
    SsoTicket login(HttpSession session, SsoTicket ssoTicket);

    /**
     * 注销的方法
     *
     * @return 注销的结果
     */
    SsoLogin logout(String sessionId);

    /**
     * 检查用户是否登录
     *
     * @param sessionId
     *            session的id
     * @return 用户是否登录 true 是 false 否
     */
    SsoLogin checkSession(String sessionId);

    /**
     * 获取登录数据
     *
     * @param sessionId
     *            session的id
     * @return 登录数据
     */
    SsoLogin getLogParam(String sessionId);
}
