package com.wyu.xjxy.controller;

import com.alibaba.fastjson.JSON;
import com.wyu.xjxy.entity.Student;
import com.wyu.xjxy.service.StudentService;
import com.wyu.xjxy.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 查询学生
     * @return
     */
    @RequestMapping(value = "/SelectStudents",method = RequestMethod.POST)
    public void SelectStudents(HttpServletRequest request,HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        // 从浏览器获得数据：
        int pagesize = Integer.parseInt(request.getParameter("rows"));
        int currentpage = (Integer.parseInt(request.getParameter("page"))-1)*pagesize;

        List<Student> list = studentService.selectBypage(currentpage, pagesize);
        // 将list集合封装成json数据
        String jsonlist = JSON.toJSONString(list);
        int total = studentService.getTotal();
        String json = "{\"total\":" + total + ",\"rows\":" + jsonlist+"}";
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/AddStudent",method = RequestMethod.POST)
    public void AddStudent(HttpServletRequest request, HttpServletResponse response){
        Student stu = new Student(request.getParameter("userName"),
                request.getParameter("number"),
                SecurityUtil.getMD5(request.getParameter("password")),
                request.getParameter("classinfo"));
        int in = studentService.insertStudent(stu);
        // 返回操作的信息
        try {
            response.getWriter().append(in + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/DeleteStu",method = RequestMethod.GET)
    public void DeleteStu(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");// 响应
        String number = request.getParameter("number");
        int delete = studentService.deleteStudent(number);
        String deleteinfo = "删除失败！！";
        if(delete!=0) {
            deleteinfo = "删除成功！";
        }
        try {
            response.getWriter().write(deleteinfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/UpdateStu",method = RequestMethod.POST)
    public void UpdateStu(HttpServletResponse response,HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        //将数据封装到对象中
        Student stu = new Student(request.getParameter("number"),
                request.getParameter("username"),
                request.getParameter("password"),
                request.getParameter("classinfo"));
        int in = studentService.updateStudent(stu);
        //通知浏览器结果
        try {
            response.getWriter().write(in+"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
