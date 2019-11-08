package com.xd.zt.controller.userlog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*@RestController
@RequestMapping("/sso/login/*")*/
@Controller
public class UserLogController {


    @RequestMapping("/blank")
    public String blank(){
        return "userlog/blank";
    }


@RequestMapping("/doLogin")
    public String userLog(String username, String password, Model model){
        //编写登录页的认证处理
       /* Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            model.addAttribute("currentusername",token.getUsername());
            return "zthtml/pages/ZT";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","账号错误");
            return "userlog/login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "userlog/login";
        }*/
    return "zthtml/pages/ZT";
    }




    /*@RequestMapping("/doLogin")
    public String userLog(String username, String password, Model model){
        //编写登录页的认证处理
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            model.addAttribute("currentusername",token.getUsername());
            return "zthtml/pages/ZT";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","账号错误");
            return "userlog/login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "userlog/login";
        }
    }

    @RequestMapping("/permission")
    public String permission(){
        return "userlog/permission";
    }


    @RequestMapping("/login")
    public String login(){
        return "userlog/login";
    }

    *//*退出系统*//*
    @RequestMapping("/logout")
    public String logout(){
        //1、获取subject
        Subject subject=  SecurityUtils.getSubject();
        //2、执行注销
        try {
            subject.logout();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return "login";
        }

    }*/


}
