package com.xd.zt.serviceImpl;


import com.xd.zt.controller.constant.InitConst;
import com.xd.zt.controller.constant.LoginConst;
import com.xd.zt.domain.bean.User;
import com.xd.zt.domain.userlog.SysUser;
import com.xd.zt.service.SsoLoginService;
import com.ym.sso.supervisor.client.dao.SsoLoginDao;
import com.ym.sso.supervisor.client.dao.impl.SsoLoginDaoImpl;
import com.ym.sso.supervisor.common.bean.SsoLogin;
import com.ym.sso.supervisor.common.bean.SsoTicket;
import com.ym.sso.supervisor.common.constant.LoginResultEnum;
import com.ym.sso.supervisor.common.constant.TicketResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 登录服务
 *
 * @author 张江波
 * @version 2019-10-02 10:35
 */
@Slf4j
@Service
public class SsoLoginSerImpl implements SsoLoginService
{
    /**
     * 初始化内存加载
     */
    private InitConst initConst = new InitConst();

    /**
     * 登录服务
     */
    private SsoLoginDao ssoLoginDao = new SsoLoginDaoImpl();

    /**
     * 检查门票是否存在
     *
     * @param ssoTicket
     *            输入参数
     * @return 门票是否存在 true 是 false 否
     */
    public SsoTicket checkTicket(SsoTicket ssoTicket)
    {
  /*      log.debug("checkTicket:ssoTicket={}", ssoTicket);*/
        return ssoLoginDao.checkTicket(ssoTicket);
    }

    /**
     * 登录方法
     *
     * @param session
     *            HttpSession
     * @param ssoTicket
     *            输入参数
     * @return 返回信息
     */
    public SsoTicket login(HttpSession session, SsoTicket ssoTicket)
    {
      /*  log.debug("login:ssoTicket={}", ssoTicket);*/
        User user = new User();
        user.setUserName(LoginConst.SESSION_USER_NAME);
        user.setIdNumber(ssoTicket.getIdNumber());
        session.setAttribute(LoginConst.SESSION_USER_KEY, user);
        Map<String, HttpSession> sessionMap = initConst.getSessionMap();
        if (sessionMap == null)
        {
            sessionMap = new HashMap<>();
        }
        sessionMap.put(session.getId(), session);
        initConst.setSessionMap(sessionMap);


        ssoTicket.setSessionId(session.getId());

        System.out.printf("\n\nssoTicket的sessionId:"+session.getId());
        SsoTicket resultTicket = ssoLoginDao.receiveSessionId(ssoTicket);
        if (!TicketResultEnum.RECEIVE_ID_SUCCESS.getNo().equals(resultTicket.getResult()))
        {
            sessionMap.remove(session.getId());
            session.removeAttribute(LoginConst.SESSION_USER_KEY);
        }
        else
        {
           /* log.info("login:session.getId()={},session={}", session.getId(),*/
                    initConst.getSessionMap().get(session.getId()).getAttribute(
                            LoginConst.SESSION_USER_KEY)/*)*/;
        }
        return resultTicket;
    }

    /**
     * 注销的方法
     *
     * @return 注销的结果
     */
//    public SsoLogin logout(String sessionId)
//    {
//  /*      log.debug("logout:sessionId={}", sessionId);*/
//        Long time = new Date().getTime();
//        SsoLogin ssoLogin = new SsoLogin();
//        if (sessionId == null)
//        {
//            ssoLogin.setResult(LoginResultEnum.LOGIN_OUT_MISS_PARAM.getNo());
//            ssoLogin.setMessage(LoginResultEnum.LOGIN_OUT_MISS_PARAM.getMessage());
//            ssoLogin.setTime(time);
//            return ssoLogin;
//        }
//        Map<String, HttpSession> sessionMap = initConst.getSessionMap();
//        if (sessionMap == null)
//        {
//            ssoLogin.setResult(LoginResultEnum.LOGIN_OUT_SUCCESS.getNo());
//            ssoLogin.setMessage(LoginResultEnum.LOGIN_OUT_SUCCESS.getMessage());
//            ssoLogin.setTime(time);
//            return ssoLogin;
//        }
//        HttpSession session = sessionMap.get(sessionId);
//        if (session == null)
//        {
//            ssoLogin.setResult(LoginResultEnum.LOGIN_OUT_SUCCESS.getNo());
//            ssoLogin.setMessage(LoginResultEnum.LOGIN_OUT_SUCCESS.getMessage());
//            ssoLogin.setTime(time);
//            return ssoLogin;
//        }
//        Object userSession;
//        try
//        {
//            userSession = session.getAttribute(LoginConst.SESSION_USER_KEY);
//        }
//        catch (IllegalStateException ex)
//        {
//      /*      log.error("logout={}", ex.toString());*/
//            sessionMap.remove(sessionId);
//            initConst.setSessionMap(sessionMap);
//            ssoLogin.setResult(LoginResultEnum.LOGIN_OUT_SUCCESS.getNo());
//            ssoLogin.setMessage(LoginResultEnum.LOGIN_OUT_SUCCESS.getMessage());
//            ssoLogin.setTime(time);
//            return ssoLogin;
//        }
//        if (userSession == null)
//        {
//            ssoLogin.setResult(LoginResultEnum.LOGIN_OUT_SUCCESS.getNo());
//            ssoLogin.setMessage(LoginResultEnum.LOGIN_OUT_SUCCESS.getMessage());
//            ssoLogin.setTime(time);
//            return ssoLogin;
//        }
//        if (sessionMap.get(session.getId()) != null)
//        {
//          /*  log.info("logout:session.removeAttribute:sessionId={},session={}", sessionId,*/
//                    sessionMap.get(session.getId()).getAttribute(LoginConst.SESSION_USER_KEY)/*)*/;
//        }
//        session.removeAttribute(LoginConst.SESSION_USER_KEY);
//        ssoLogin.setResult(LoginResultEnum.LOGIN_OUT_SUCCESS.getNo());
//        ssoLogin.setMessage(LoginResultEnum.LOGIN_OUT_SUCCESS.getMessage());
//        ssoLogin.setTime(time);
//        return ssoLogin;
//    }


    public SsoLogin logout(String sessionId) {
//        log.info("logout:sessionId={}", sessionId);
        Long time = new Date().getTime();
        SsoLogin ssoLogin = new SsoLogin();
        if (sessionId == null) {
            ssoLogin.setResult(LoginResultEnum.LOGIN_OUT_MISS_PARAM.getNo());
            ssoLogin.setMessage(LoginResultEnum.LOGIN_OUT_MISS_PARAM.getMessage());
            ssoLogin.setTime(time);
            return ssoLogin;
        }
        Subject subject = SecurityUtils.getSubject();
//        SysUser user = ShiroUtils.getSysUser();
//        if (StringUtils.isNotNull(user)) {
//            cacheManager.getCache(ShiroConstants.SYS_USERCACHE).remove(user.getLoginName());
//        }
        subject.logout();
        ssoLogin.setResult(LoginResultEnum.LOGIN_OUT_SUCCESS.getNo());
        ssoLogin.setMessage(LoginResultEnum.LOGIN_OUT_SUCCESS.getMessage());
        ssoLogin.setTime(time);
        return ssoLogin;
    }
    /**
     * 检查用户是否登录
     *
     * @param sessionId
     *            session的id
     * @return 用户是否登录 true 是 false 否
     */
    public SsoLogin checkSession(String sessionId)
    {
/*        log.debug("checkSession:sessionId={}", sessionId);*/
        Long time = new Date().getTime();
        SsoLogin ssoLogin = new SsoLogin();
        if (sessionId == null)
        {
            ssoLogin.setResult(LoginResultEnum.CHECK_SESSION_MISS_PARAM.getNo());
            ssoLogin.setMessage(LoginResultEnum.CHECK_SESSION_MISS_PARAM.getMessage());
            ssoLogin.setTime(time);
            return ssoLogin;
        }
        Map<String, HttpSession> sessionMap = initConst.getSessionMap();
        if (sessionMap == null)
        {
            ssoLogin.setResult(LoginResultEnum.CHECK_SESSION_FAIL.getNo());
            ssoLogin.setMessage(LoginResultEnum.CHECK_SESSION_FAIL.getMessage());
            ssoLogin.setTime(time);
            return ssoLogin;
        }
        HttpSession session = sessionMap.get(sessionId);
        if (session == null)
        {
            ssoLogin.setResult(LoginResultEnum.CHECK_SESSION_FAIL.getNo());
            ssoLogin.setMessage(LoginResultEnum.CHECK_SESSION_FAIL.getMessage());
            ssoLogin.setTime(time);
            return ssoLogin;
        }
        Object userSession;
        try
        {
            userSession = session.getAttribute(LoginConst.SESSION_USER_KEY);
        }
        catch (IllegalStateException ex)
        {
          /*  log.error("checkSession={}", ex.toString());*/
            sessionMap.remove(sessionId);
            initConst.setSessionMap(sessionMap);
            ssoLogin.setResult(LoginResultEnum.CHECK_SESSION_FAIL.getNo());
            ssoLogin.setMessage(LoginResultEnum.CHECK_SESSION_FAIL.getMessage());
            ssoLogin.setTime(time);
            return ssoLogin;
        }
        if (userSession == null)
        {
            ssoLogin.setResult(LoginResultEnum.CHECK_SESSION_FAIL.getNo());
            ssoLogin.setMessage(LoginResultEnum.CHECK_SESSION_FAIL.getMessage());
            ssoLogin.setTime(time);
            return ssoLogin;
        }
      /*  log.info("checkSession:sessionId={},userSession={}", sessionId, userSession);*/
        ssoLogin.setResult(LoginResultEnum.CHECK_SESSION_SUCCESS.getNo());
        ssoLogin.setMessage(LoginResultEnum.CHECK_SESSION_SUCCESS.getMessage());
        ssoLogin.setTime(time);
        return ssoLogin;
    }

    /**
     * 获取登录数据
     *
     * @param sessionId
     *            session的id
     * @return 登录数据
     */
    public SsoLogin getLogParam(String sessionId)
    {
      /*  log.debug("getLogParam:sessionId={}", sessionId);*/
        SsoLogin ssoLogin = checkSession(sessionId);
        if (LoginResultEnum.CHECK_SESSION_SUCCESS.getNo().equals(ssoLogin.getResult()))
        {
            HttpSession session = initConst.getSessionMap().get(sessionId);
            User user = (User)session.getAttribute(LoginConst.SESSION_USER_KEY);
            ssoLogin.setIdNumber(user.getIdNumber());
            ssoLogin.setSessionId(sessionId);
        }
        return ssoLogin;
    }
}
