package com.wyu.xjxy.controller;


import com.wyu.xjxy.service.StudentService;
import com.wyu.xjxy.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/index")
    public String toLogin(){
        return "index";
    }

    /**
     * 登录校验
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/toLogin")
    public String tovalidate(HttpServletRequest request, Model model){
        String username = request.getParameter("username");
        Integer num = studentService.selectByNameAndPass(username,
                SecurityUtil.getMD5(request.getParameter("password")));
        System.out.println(SecurityUtil.getMD5(request.getParameter("password")));
        if(num>0){
            request.getSession().setAttribute("username",username);
            return "main";
        }else {
            model.addAttribute("loginfo","登录失败！");
            return "redirect:index";
        }
    }
    @RequestMapping("/quit")
    public String quit(HttpServletRequest request){
        request.getSession().setAttribute("username",null);
        return "index";
    }
    /**
     * 验证码
     */


}
