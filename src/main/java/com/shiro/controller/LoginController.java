package com.shiro.controller;

import com.shiro.configure.MyRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private DefaultWebSecurityManager securityManager;

    @GetMapping("/index")
    public String index(){
        return "login";
    }

    @GetMapping("/err")
    public String err(){
        return "403";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request){
        String username = request.getParameter("name");
        String password = request.getParameter("password");


        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try{
            subject.login(token);
        } catch (IncorrectCredentialsException e){
            return "403";
        } catch (UnknownAccountException e){
            return "";
        }

        return "success";

    }
}
