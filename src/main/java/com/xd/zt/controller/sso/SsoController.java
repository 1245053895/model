package com.xd.zt.controller.sso;

import com.xd.zt.controller.constant.InitConst;
import com.xd.zt.domain.analyse.Algorithm;
import com.xd.zt.domain.userlog.SysUser;
import com.xd.zt.mapper.userinfo.SysUserMenuMapper;
import com.xd.zt.service.SsoLoginService;
import com.xd.zt.service.algorithm.AlgorithmDebugService;
import com.xd.zt.service.business.BusinessModelService;
import com.ym.sso.supervisor.common.bean.SsoLogin;
import com.ym.sso.supervisor.common.bean.SsoTicket;
import com.ym.sso.supervisor.common.constant.TicketResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/sso")
@Controller
@Slf4j
public class SsoController {
    @Autowired
    private BusinessModelService businessModelService;
    @Autowired
    private AlgorithmDebugService algorithmDebugService;
    @Autowired
    private SysUserMenuMapper sysUserMenuMapper;

    private SsoLoginService ssoLoginService;

    @Autowired
    public SsoController(SsoLoginService ssoLoginService) {
        this.ssoLoginService = ssoLoginService;

    }
    @Value("${server.servlet.session.cookie.name}")
    private String sessionKey;

    private InitConst initConst = new InitConst();

    /**
     * 登录服务
     */

    @RequestMapping("/login")
    public String login(HttpServletRequest request, SsoTicket ssoTicket, Model model) {
        String idnumber=ssoTicket.getIdNumber();
      SysUser sysUser= sysUserMenuMapper.getSysUserByIdNumber(idnumber);
      String username=sysUser.getUsername();
      String password=sysUser.getPassword();
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        subject.login(token);
      /*  String idnumber=ssoTicket.getIdNumber();
      UserInfo userInfo= userInfoMapper.selectUserInfoByIdNumber(idnumber);
      if (userInfo!=null){*/
          String sessionId = request.getRequestedSessionId();
          /*       if (sessionId==null){}*/
          String ssoSupServerUrl = "http://10.101.201.154:9092";
          ssoTicket.setSsoSupServerUrl(ssoSupServerUrl);
          ssoTicket = ssoLoginService.checkTicket(ssoTicket);
          ssoTicket.setSsoSupServerUrl(ssoSupServerUrl);
          if (TicketResultEnum.SSO_TICKET_SUCCESS.getNo().equals(ssoTicket.getResult())) {
              HttpSession session = request.getSession();
              ssoTicket.setSessionKey(sessionKey);
              ssoTicket = ssoLoginService.login(session, ssoTicket);
              // ssoTicket.getResult()
          }
          String algorithmlabel = "行业通用";
          List<Algorithm> algorithmListGeneral = algorithmDebugService.selectAlgorithmCommon(algorithmlabel);
          String algorithmlabel1 = "行业专用";
          List<Algorithm> algorithmListSpecial = algorithmDebugService.selectAlgorithmProcess(algorithmlabel1);

          String algorithmlabe2 = "人工智能";
          List<Algorithm> algorithmListIntelligence = algorithmDebugService.selectAlgorithmLogical(algorithmlabe2);
          List<Algorithm> algorithmListAll = algorithmDebugService.selectAlgorithm();
          model.addAttribute("algorithmListGeneral", algorithmListGeneral);
          model.addAttribute("algorithmListSpecial", algorithmListSpecial);
          model.addAttribute("algorithmListIntelligence", algorithmListIntelligence);
          model.addAttribute("algorithmListAll", algorithmListAll);
          model.addAttribute("businessModels", businessModelService.selectbusinessmodel());
          return "zthtml/pages/ZT";
    /*      return new ModelAndView("zthtml/pages/ZT", "Modelmodel", model);*/
  /*    }else {
          return "redirect:http://10.101.201.154:9092/sso/login.html?ssoClientUrl=http://10.101.201.173:7008";
      }*/

    }

    /**
     * 登出接口
     *
     * @param request
     *            HttpServletRequest
     * @return 登出的结果
     */
    @ResponseBody
    @GetMapping(value = "/logout")
    public SsoLogin logout(HttpServletRequest request)
    {
        String sessionId = request.getRequestedSessionId();
/*        log.debug("logout:sessionId={}",sessionId);*/
        return ssoLoginService.logout(sessionId);
    }


    /**
     * 获取登录数据
     *
     * @param request
     *            HttpServletRequest
     * @return 登录数据
     */
    @GetMapping(value = "getLogParam")
    public ModelAndView getLogParam(HttpServletRequest request)
    {
       /* log.debug("getLogParam");*/
        String ssoSupServerUrl="http://10.101.201.154:9092";
        String localUrl="http://10.101.201.173:7008";
        String sessionId = request.getRequestedSessionId();
        SsoLogin ssoLogin = ssoLoginService.getLogParam(sessionId);
        ssoLogin.setSsoClientUrl(localUrl);
        ssoLogin.setSsoSupServerUrl(ssoSupServerUrl);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("sessionId",sessionId);
        modelAndView.addObject("idNumber",ssoLogin.getIdNumber());
        modelAndView.setViewName("sso/logout");
       return modelAndView;
    }


    @RequestMapping("/session")
    public String session(){
        return "sso/session";
    }


    /**
     * 检查session的是否存在
     *
     * @param request
     *            HttpServletRequest
     * @return session的是否存在的返回值
     */
    @GetMapping(value = "/checkSession")
    @ResponseBody
    public SsoLogin checkSession(HttpServletRequest request)
    {
        String ssoSupServerUrl="http://10.101.201.154:9092";
        String localUrl="http://10.101.201.173:7008";
        /*log.debug("checkSession");*/
        String sessionId = request.getRequestedSessionId();
        SsoLogin ssoLogin = ssoLoginService.checkSession(sessionId);
        ssoLogin.setSsoSupServerUrl(ssoSupServerUrl);
        ssoLogin.setSsoClientUrl(localUrl);
        return ssoLogin;
    }



    @RequestMapping("/permission")
    public String permission(){
        return "userlog/permission";
    }



}
