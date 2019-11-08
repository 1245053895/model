package com.xd.zt.controller.sso;

import com.ym.sso.supervisor.common.bean.SsoTicket;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/sso")
@Controller
public class SsoController {


    @RequestMapping("/login")
    public String login(HttpServletRequest request,SsoTicket ssoTicket){


        return "sso/login";
    }


    @RequestMapping("/session")
    public String session(){
        return "sso/session";
    }
}
