package com.wyu.xjxy.controller;

import com.alibaba.fastjson.JSON;
import com.wyu.xjxy.entity.DeptInf;
import com.wyu.xjxy.service.DeptInfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class DeptInfController {
    @Autowired
    private DeptInfService deptInfService;
    @RequestMapping(value = "/AddDept",method = RequestMethod.POST)
    public void queryDept(HttpServletRequest request, HttpServletResponse response){
        int in = deptInfService.insertDept(request.getParameter("deptname"),
                request.getParameter("remark"));
        try {
            response.getWriter().append(in + "");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("添加失败！");
        }
    }

    @RequestMapping(value = "/DeptInfoSelect",method = RequestMethod.POST)
    public void DeptInfoSelect(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        int pagesize = Integer.parseInt(request.getParameter("rows"));
        int currentpage = (Integer.parseInt(request.getParameter("page"))-1)*pagesize;
        System.out.println(currentpage+""+pagesize);
        List<DeptInf> list = deptInfService.selectDeptBypage(currentpage, pagesize);
        // 将list集合封装成json数据
        String jsonlist = JSON.toJSONString(list);
        int total = deptInfService.selectTotal();
        String json = "{\"total\":" + total + ",\"rows\":" + jsonlist + "}";
        // easyui的分页一定要有两个json key ：total,rows;
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 删除部门
     */
    @RequestMapping(value = "/DeleteDept",method = RequestMethod.GET)
    public void DeleteDept(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");//设置响应编码，避免乱码
        Integer id = Integer.parseInt(request.getParameter("id"));
        int delete = deptInfService.deleteDeptById(id);
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
     * 修改部门信息
     */
    @RequestMapping(value = "/UpdateDept",method = RequestMethod.POST)
    public void UpdateDept(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        //获得数据：
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String remark = request.getParameter("remark");
        DeptInf dept = new DeptInf();
        dept.setId(Integer.parseInt(id));
        dept.setName(name);
        dept.setRemark(remark);
        int in = deptInfService.updateDept(dept);
        try {
            response.getWriter().write(in+"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
