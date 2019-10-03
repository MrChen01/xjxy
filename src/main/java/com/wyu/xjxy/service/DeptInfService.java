package com.wyu.xjxy.service;

import com.wyu.xjxy.entity.DeptInf;

import java.util.List;

public interface DeptInfService {
    List<DeptInf> selectAll();
    List<DeptInf> selectDeptBypage(int currentpage, int pagesize);
    int selectTotal();
    int deleteDeptById(int id);
    int insertDept(String name,String remark);
    int updateDept(DeptInf dept);
}
