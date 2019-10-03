package com.wyu.xjxy.dao;

import com.wyu.xjxy.entity.DeptInf;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptInfDao {
    List<DeptInf> selectAll();
    List<DeptInf> selectDeptBypage(@Param("currentpage") int currentpage, @Param("pagesize") int pagesize);
    int selectTotal();
    int deleteDeptById(int id);
    int insertDept(@Param("name") String name,@Param("remark") String remark);
    int updateDept(DeptInf dept);
}
