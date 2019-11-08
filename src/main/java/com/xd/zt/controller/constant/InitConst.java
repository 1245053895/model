package com.xd.zt.controller.constant;


import javax.servlet.http.HttpSession;
import java.util.Map;


/**
 * 初始化内存加载
 *
 * @author 张江波
 * @version 2019-10-01 20:16
 */
public class InitConst
{
    /**
     * session的集合
     */
    private static Map<String, HttpSession> SESSION_MAP = null;

    public Map<String, HttpSession> getSessionMap()
    {
        return SESSION_MAP;
    }

    public void setSessionMap(Map<String, HttpSession> sessionMap)
    {
        SESSION_MAP = sessionMap;
    }
}
