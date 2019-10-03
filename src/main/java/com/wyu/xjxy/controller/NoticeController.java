package com.wyu.xjxy.controller;

import com.wyu.xjxy.entity.Notice;
import com.wyu.xjxy.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    /**
     * 添加公告
     */
    @RequestMapping(value = "/AddNotice",method = RequestMethod.POST)
    public void AddNotice(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");// 响应
        // 获得公告信息
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String noticename = request.getParameter("noticename");
        String title = request.getParameter("title");
        String container = request.getParameter("container");
        // 将信息封装到对象中
        Notice notice = new Notice();
        notice.setNoticename(noticename);
        // 将当前时间转换成字符串
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sf.format(new Date());
        notice.setTime(time);
        notice.setTitle(title);
        notice.setStuname(username);
        notice.setContent(container);
        noticeService.insertNotice(notice);
        request.setAttribute("content", container);
        try {
            request.getRequestDispatcher("ShowNotice.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
