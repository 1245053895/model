package com.xd.zt.controller.sso;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xd.zt.controller.constant.InitConst;
import com.xd.zt.domain.analyse.Algorithm;
import com.xd.zt.domain.model.Programme;
import com.xd.zt.domain.userlog.SysRoleUser;
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
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;

@RequestMapping("/sso")
@Controller
@Slf4j
public class SsoController {
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private ModelService modelService;
    @Autowired
    private BusinessModelService businessModelService;
    @Autowired
    private AlgorithmDebugService algorithmDebugService;
    @Autowired
    private SysUserMenuMapper sysUserMenuMapper;

    private SsoLoginService ssoLoginService;
    private String sessionId;

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
        String ssoSupServerUrl = "http://10.101.201.184:9092";
        ssoTicket.setSsoSupServerUrl(ssoSupServerUrl);
        ssoTicket = ssoLoginService.checkTicket(ssoTicket);
        ssoTicket.setSsoSupServerUrl(ssoSupServerUrl);
        HttpSession session = request.getSession();
        if (TicketResultEnum.SSO_TICKET_SUCCESS.getNo().equals(ssoTicket.getResult())) {
            ssoTicket.setSessionKey(sessionKey);
            ssoTicket = ssoLoginService.login(session, ssoTicket);
            // ssoTicket.getResult()
            //测试
                String sessionId = request.getRequestedSessionId();
                System.out.printf("\n\nLoginSessionId:" + sessionId);
                session.setAttribute("SessionId", sessionId);
                model.addAttribute("SessionId", sessionId);
        }

        String sessionId = ssoTicket.getSessionId();
        System.out.printf("\n\nLoginSessionId:" + sessionId);
        session.setAttribute("SessionId", sessionId);
        model.addAttribute("SessionId", sessionId);
        String UserName = new String();
        if (sysUser != null) {
            UserName = sysUser.getUsername();
            System.out.printf("\n\n存在用户");
            session.setAttribute("UserName", UserName);
            List<SysRoleUser> sysRoleUserList = sysUserMenuMapper.selectRoleUserByUserId(sysUser.getId());
            if (sysRoleUserList.size() != 0) {
//
//        //将用户信息存入session中
//        SecurityUtils.getSubject().getSession().setAttribute("sysUser", sysUser);
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(UserName, sysUser.getPassword());
//        subject.login(token);

                System.out.printf("\n\n存在用户,有权限");
                session.setAttribute("Status", "true");

                String password = sysUser.getPassword();
                Integer id = sysUser.getId();
//                String sessionId = request.getRequestedSessionId();
                /*       if (sessionId==null){}*/
//        String ssoSupServerUrl = "http://10.101.201.184:9092";
//        ssoTicket.setSsoSupServerUrl(ssoSupServerUrl);
//        ssoTicket = ssoLoginService.checkTicket(ssoTicket);
//        ssoTicket.setSsoSupServerUrl(ssoSupServerUrl);
//        HttpSession session = request.getSession();
//
//        session.setAttribute("id", id);
//        System.out.println("session sysuser为: " + id);
//        if (TicketResultEnum.SSO_TICKET_SUCCESS.getNo().equals(ssoTicket.getResult())) {
//            ssoTicket.setSessionKey(sessionKey);
//            ssoTicket = ssoLoginService.login(session, ssoTicket);
//            // ssoTicket.getResult()
//        }
                session.setAttribute("id", id);
                System.out.println("session sysuser为: " + id);
                String[] HeaderName = new String[]{"Content-Type", "Authorization"};
                String[] HeaderValue = new String[]{"application/x-www-form-urlencoded; charset=UTF-8", "Basic d2ViQXBwOndlYkFwcA=="};
                try {
                    String result = HttpCientPostWithHeader.restPost("http://10.101.201.173:9900/api-uaa/oauth/openId/token?openId=" + idnumber, "", HeaderName, HeaderValue);
                    JSONObject resultJson = JSON.parseObject(result);
                    System.out.printf(resultJson.getString("datas"));

                    session.setAttribute("token", resultJson.getString("datas"));


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
                model.addAttribute("UserName", UserName);
                model.addAttribute("Status", "true");
                return new ModelAndView("zthtml/pages/ZT", "Modelmodel", model);
            } else {
                System.out.printf("\n\n存在用户,没有权限");
                session.setAttribute("Status", "false");
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
                model.addAttribute("UserName", UserName);
                model.addAttribute("Status", "false");
                model.addAttribute("token", "null");
                session.setAttribute("token", "null");
                return new ModelAndView("userlog/permission", "Modelmodel", model);
            }
        } else {
            System.out.printf("\n\n不存在用户");
            session.setAttribute("UserName", "");
            session.setAttribute("Status", "false");
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
            model.addAttribute("token", "null");
            model.addAttribute("UserName", UserName);
            model.addAttribute("Status", "false");
            session.setAttribute("token", "null");
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
        System.out.printf("\n\nLogout:"+sessionId);
        //测试
//        HttpSession session = request.getSession();
//        session.setAttribute("SessionId","Logout");//
//        MySessionContext myc = MySessionContext.getInstance();
//        HttpSession sess = myc.getSession(sessionId);
//        String token = sess.getAttribute("token").toString();
        return ssoLoginService.logout(sessionId);
    }


    /**
     * 获取登录数据
     *
     * @param request
     *            HttpServletRequest
     * @return 登录数据
     */
    @GetMapping(value = "/getLogParam")
    public ModelAndView getLogParam(HttpServletRequest request)
    {
       /* log.debug("getLogParam");*/
        System.out.printf("\n\ngetLogParam");
        String ssoSupServerUrl="http://10.101.201.184:9092";
        String localUrl="http://10.101.201.173";
        String sessionId = request.getRequestedSessionId();
        System.out.printf("\n\n自己登出sessionId:"+sessionId);
        SsoLogin ssoLogin = ssoLoginService.getLogParam(sessionId);
        ssoLogin.setSsoClientUrl(localUrl);
        ssoLogin.setSsoSupServerUrl(ssoSupServerUrl);
        System.out.printf("\n\nssoLogin:"+ssoLogin);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("sessionId",sessionId);
        modelAndView.addObject("idNumber",ssoLogin.getIdNumber());
        modelAndView.setViewName("sso/logout");
       return modelAndView;
    }
    @GetMapping(value = "/session")
    public ModelAndView session(Model model,@RequestParam("sessionId") String sessionId, HttpServletResponse response) {
        //测试
//        MySessionContext myc = MySessionContext.getInstance();
//        HttpSession sess = myc.getSession(sessionId);
        Session session = sessionRepository.findById(sessionId);
        System.out.printf("\n\nsession的sessionId:"+sessionId);
        //测试
        try{
            String SessionId = session.getAttribute("SessionId");
            System.out.printf("\n\n检查sessionId："+SessionId);
            model.addAttribute("SessionId",SessionId);
        }
        catch (Exception e){
//            return "redirect:http://10.101.201.154:8080/#/home/index";
            System.out.printf("\n\n检查sessionId不存在");
            return new ModelAndView(new RedirectView("http://10.101.201.173:80/login"));
        }

            System.out.printf("\n\n");
            System.out.printf("token:" + session.getAttribute("token").toString());

            String UserName = session.getAttribute("UserName").toString();
            System.out.printf("\n\nusername:" + UserName);
            String Status = session.getAttribute("Status").toString();
            System.out.printf("\n\nstatus:" + Status);

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
            model.addAttribute("UserName", UserName);
            model.addAttribute("token", session.getAttribute("token").toString());
            if (Status.equals("true")) {
                model.addAttribute("Status", "true");
                System.out.printf("\n\n" + Status);
                return new ModelAndView("zthtml/pages/ZT", "Modelmodel", model);
//                return "zthtml/pages/ZT";
            } else {
                model.addAttribute("Status", "false");
                System.out.printf("\n\n" + Status);
                return new ModelAndView("userlog/permission", "Modelmodel", model);
//                return "userlog/permission";
            }

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
        Enumeration names = request.getHeaderNames();
        System.out.println("===================================================================");
        while(names.hasMoreElements()){
            String name = (String) names.nextElement();
            System.out.println(name + ":" + request.getHeader(name));
        }
        System.out.println("===================================================================");

        String ssoSupServerUrl="http://10.101.201.184:9092";
        String localUrl="http://10.101.201.173";
        /*log.debug("checkSession");*/
        String sessionId = request.getRequestedSessionId();
        System.out.printf("\n\nCheckssion:"+sessionId);
        SsoLogin ssoLogin = ssoLoginService.checkSession(sessionId);
        ssoLogin.setSsoSupServerUrl(ssoSupServerUrl);
        ssoLogin.setSsoClientUrl(localUrl);
        System.out.printf("\n\nssoLogin:"+ssoLogin);
        return ssoLogin;
    }



    @RequestMapping("/permission")
    public ModelAndView permission(Model model){
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
        return new ModelAndView("userlog/permission", "Modelmodel", model);
    }



}
