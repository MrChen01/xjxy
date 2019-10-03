package com.wyu.xjxy.dao;

import com.wyu.xjxy.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeDao {
    List<Employee> selectEmpBypage(@Param("currentpage") int currentpage, @Param("pagesize") int pagesize);
    int selectTotal();
    int updateEmp(Employee employee);
    int deleteEmpById(int id);
    int insertEmp(Employee employee);
    List<Employee> findAll();
}
