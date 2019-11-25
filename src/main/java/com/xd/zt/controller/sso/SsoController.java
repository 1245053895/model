package com.xd.zt.controller.sso;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xd.zt.controller.constant.InitConst;
import com.xd.zt.domain.analyse.Algorithm;
import com.xd.zt.domain.model.Programme;
import com.xd.zt.domain.userlog.SysUser;
import com.xd.zt.mapper.userinfo.SysUserMenuMapper;
import com.xd.zt.service.SsoLoginService;
import com.xd.zt.service.algorithm.AlgorithmDebugService;
import com.xd.zt.service.business.BusinessModelService;
import com.xd.zt.service.dataManager.OpenTsdbDataService;
import com.xd.zt.service.model.ModelService;
import com.xd.zt.util.analyse.HttpCientPostWithHeader;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/sso")
@Controller
@Slf4j
public class SsoController {
    @Autowired
    private ModelService modelService;
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
    public ModelAndView login(HttpServletRequest request, SsoTicket ssoTicket, Model model) {
    String idnumber = ssoTicket.getIdNumber();
    SysUser sysUser = sysUserMenuMapper.getSysUserByIdNumber(idnumber);
/*    SecurityUtils.getSubject().getSession().setAttribute("sysUser", sysUser); //将用户信息存入session中*/
 /*   Subject subject = SecurityUtils.getSubject();
    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
    subject.login(token);*/
 if (sysUser!=null){
     String username = sysUser.getUsername();
     String password = sysUser.getPassword();
     Integer id=sysUser.getId();
     String sessionId = request.getRequestedSessionId();
     /*       if (sessionId==null){}*/
     String ssoSupServerUrl = "http://10.101.201.154:9092";
     ssoTicket.setSsoSupServerUrl(ssoSupServerUrl);
     ssoTicket = ssoLoginService.checkTicket(ssoTicket);
     ssoTicket.setSsoSupServerUrl(ssoSupServerUrl);
     HttpSession session = request.getSession();

     session.setAttribute("id",id);
     System.out.println("session sysuser为: "+ id);
     if (TicketResultEnum.SSO_TICKET_SUCCESS.getNo().equals(ssoTicket.getResult())) {
         ssoTicket.setSessionKey(sessionKey);
         ssoTicket = ssoLoginService.login(session, ssoTicket);
         // ssoTicket.getResult()
     }
     String[] HeaderName = new String[]{"Content-Type", "Authorization"};
     String[] HeaderValue = new String[]{"application/x-www-form-urlencoded; charset=UTF-8", "Basic d2ViQXBwOndlYkFwcA=="};
     try {
         String result = HttpCientPostWithHeader.restPost("http://10.101.201.173:9900/api-uaa/oauth/openId/token?openId="+idnumber, null, HeaderName, HeaderValue);
         JSONObject resultJson = JSON.parseObject(result);
         System.out.printf(resultJson.getString("datas"));

         session.setAttribute("token",resultJson.getString("datas"));


         if (resultJson.getInteger("resp_code") == 0) {

             model.addAttribute("token", resultJson.getString("datas"));
         }
     } catch (Exception e) {
         e.printStackTrace();
     }
     String algorithmlabel = "行业通用";
     List<Algorithm> algorithmListGeneral = algorithmDebugService.selectAlgorithmCommon(algorithmlabel);


     String algorithmlabel1 = "行业专用";
     List<Algorithm> algorithmListSpecial = algorithmDebugService.selectAlgorithmProcess(algorithmlabel1);

     String algorithmlabe2 = "人工智能";
     List<Algorithm> algorithmListIntelligence = algorithmDebugService.selectAlgorithmLogical(algorithmlabe2);


     List<Algorithm> algorithmListAll = algorithmDebugService.selectAlgorithm();
     List<Programme> programmeList = modelService.selectAllModelByType("勘察设计");
     model.addAttribute("programmeList", programmeList);
     List<Programme> programmeList1 = modelService.selectAllModelByType("工程施工");
     model.addAttribute("programmeList1", programmeList1);
     List<Programme> programmeList2 = modelService.selectAllModelByType("运营维护");
     model.addAttribute("programmeList2", programmeList2);
     List<Programme> programmeList3 = modelService.selectAllModelByType("智慧城市");
     model.addAttribute("programmeList3", programmeList3);

     model.addAttribute("algorithmListGeneral", algorithmListGeneral);
     model.addAttribute("algorithmListSpecial", algorithmListSpecial);
     model.addAttribute("algorithmListIntelligence", algorithmListIntelligence);
     model.addAttribute("algorithmListAll", algorithmListAll);
     model.addAttribute("businessModels", businessModelService.selectbusinessmodel());
     return new ModelAndView("zthtml/pages/ZT", "Modelmodel", model);
 }else {
     return new ModelAndView("userlog/permission", "Modelmodel", model);
 }
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
        String localUrl="http://10.101.201.173";
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
    @GetMapping(value = "/session")
    public ModelAndView session(Model model,@RequestParam("sessionId") String sessionId, HttpServletResponse response) {
//        log.info("session={}:{}", sessionKey, sessionId);
        System.out.printf(sessionId);
        MySessionContext myc= MySessionContext.getInstance();
        HttpSession sess = myc.getSession(sessionId);
        System.out.printf("\n\n\n\n\n\n\n\n\n");
        System.out.printf(sess.getAttribute("token").toString());
        Cookie name = new Cookie(sessionKey, sessionId);
        name.setPath("/");
        name.setMaxAge(2592000);
        response.addCookie(name);
        System.out.printf(response.toString());


        String algorithmlabel = "行业通用";
        List<Algorithm> algorithmListGeneral = algorithmDebugService.selectAlgorithmCommon(algorithmlabel);


        String algorithmlabel1 = "行业专用";
        List<Algorithm> algorithmListSpecial = algorithmDebugService.selectAlgorithmProcess(algorithmlabel1);

        String algorithmlabe2 = "人工智能";
        List<Algorithm> algorithmListIntelligence = algorithmDebugService.selectAlgorithmLogical(algorithmlabe2);


        List<Algorithm> algorithmListAll = algorithmDebugService.selectAlgorithm();
        List<Programme> programmeList = modelService.selectAllModelByType("勘察设计");
        model.addAttribute("programmeList", programmeList);
        List<Programme> programmeList1 = modelService.selectAllModelByType("工程施工");
        model.addAttribute("programmeList1", programmeList1);
        List<Programme> programmeList2 = modelService.selectAllModelByType("运营维护");
        model.addAttribute("programmeList2", programmeList2);
        List<Programme> programmeList3 = modelService.selectAllModelByType("智慧城市");
        model.addAttribute("programmeList3", programmeList3);

        model.addAttribute("algorithmListGeneral", algorithmListGeneral);
        model.addAttribute("algorithmListSpecial", algorithmListSpecial);
        model.addAttribute("algorithmListIntelligence", algorithmListIntelligence);
        model.addAttribute("algorithmListAll", algorithmListAll);
        model.addAttribute("businessModels", businessModelService.selectbusinessmodel());

        model.addAttribute("token",sess.getAttribute("token").toString());
        return new ModelAndView("zthtml/pages/ZT", "Modelmodel", model);
    }

//    @RequestMapping("/session")
//    public String session(){
//        return "sso/session";
//    }
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
        String localUrl="http://10.101.201.173/ZT";
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
