package com.wyu.xjxy.controller;

import com.alibaba.fastjson.JSON;
import com.wyu.xjxy.entity.Employee;
import com.wyu.xjxy.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/selectEmployees",method = RequestMethod.POST)
    public void selectEmployees(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        int pagesize = Integer.parseInt(request.getParameter("rows"));
        int currentpage = (Integer.parseInt(request.getParameter("page"))-1)*pagesize;
        List<Employee> list = employeeService.selectEmpBypage(currentpage, pagesize);
        // 将list集合封装成json数据
        String jsonlist = JSON.toJSONString(list);
        System.out.println(jsonlist);
        int total = employeeService.selectTotal();
        String json = "{\"total\":" + total + ",\"rows\":" + jsonlist + "}";
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 修改员工信息
     */
    @RequestMapping(value = "/UpdateEmp",method = RequestMethod.POST)
    public void UpdateDept(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        //获得数据：
        Integer id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String race = request.getParameter("race");
        String education = request.getParameter("education");
        String speciality = request.getParameter("speciality");
        String hobby = request.getParameter("hobby");
        Employee employee = new Employee(id,name,address,phone,
                email,race,
                education,speciality,
                hobby);
        int in = employeeService.updateEmp(employee);
        try {
            response.getWriter().write(in+"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 删除员工
     */
    @RequestMapping(value = "/DeleteEmp",method = RequestMethod.GET)
    public void DeleteDept(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");//设置响应编码，避免乱码
        Integer id = Integer.parseInt(request.getParameter("id"));
        int delete = employeeService.deleteEmpById(id);
        String deleteinfo = "删除失败！！";
        if (delete != 0) {
            deleteinfo = "删除成功！";
        }
        try {
            response.getWriter().write(deleteinfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加员工
     * @param request
     * @param response
     */
    @RequestMapping(value = "/AddEmp",method = RequestMethod.POST)
    public void queryDept(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String race = request.getParameter("race");
        String education = request.getParameter("education");
        String speciality = request.getParameter("speciality");
        String hobby = request.getParameter("hobby");
        Employee employee = new Employee(name,address,phone,
                email,race,
                education,speciality,
                hobby);
        int in = employeeService.insertEmp(employee);
        try {
            response.getWriter().append(in + "");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("添加失败！");
        }
    }

    /**
     * 未登录状态下的找回密码
     */
    @RequestMapping(value = "/findPsd",method = RequestMethod.GET)
    public void findPsd(HttpServletResponse response,HttpServletRequest request){}
    /**
     * 登录状态下的修改密码
     */
}
