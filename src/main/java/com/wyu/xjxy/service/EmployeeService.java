package com.wyu.xjxy.service;

import com.wyu.xjxy.entity.Employee;

import javax.servlet.ServletOutputStream;
import java.io.FileInputStream;
import java.util.List;

public interface EmployeeService {
    List<Employee> selectEmpBypage(int currentpage, int pagesize);
    int selectTotal();
    int updateEmp(Employee employee);
    int deleteEmpById(int id);
    int insertEmp(Employee employee);
    void exportAls(FileInputStream fileInputStream, ServletOutputStream outputStream);
}
