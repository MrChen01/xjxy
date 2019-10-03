package com.wyu.xjxy.service.impl;

import com.wyu.xjxy.dao.EmployeeDao;
import com.wyu.xjxy.entity.Employee;
import com.wyu.xjxy.service.EmployeeService;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;
    public List<Employee> selectEmpBypage(int currentpage, int pagesize) {
        return employeeDao.selectEmpBypage(currentpage,pagesize);
    }

    public int selectTotal() {
        return employeeDao.selectTotal();
    }

    public int updateEmp(Employee employee) {
        return employeeDao.updateEmp(employee);
    }

    public int deleteEmpById(int id) {
        return employeeDao.deleteEmpById(id);
    }

    public int insertEmp(Employee employee) {
        return employeeDao.insertEmp(employee);
    }

    public void exportAls(FileInputStream fileInputStream, ServletOutputStream outputStream) {
        XSSFWorkbook book = null;

        try {
            book = new XSSFWorkbook(fileInputStream);
        } catch (IOException var18) {
            var18.printStackTrace();
        }

        XSSFSheet sheet = book.getSheetAt(0);
        List<Employee> list = employeeDao.findAll();
        System.out.println(list.size());
        int rowIndex = 1;
        XSSFCell cell = null;

        for(Iterator var9 = list.iterator(); var9.hasNext(); ++rowIndex) {
            Employee employee = (Employee)var9.next();
            XSSFRow row = sheet.createRow(rowIndex);
            cell = row.createCell(0);
            Integer id = employee.getId();
            if (id != null) {
                cell.setCellValue(id);
            }
            cell = row.createCell(1);
            String name = employee.getName();
            if (name != null) {
                cell.setCellValue(name);
            }
            cell = row.createCell(2);
            String address = employee.getAddress();
            if (address != null) {
                cell.setCellValue(address);
            }

            cell = row.createCell(3);
            String phone = employee.getPhone();
            if (phone != null) {
                cell.setCellValue(phone);
            }
            cell = row.createCell(6);
            String email = employee.getEmail();
            if (email != null) {
                cell.setCellValue(email);
            }
            cell = row.createCell(3);
            String descCount = employee.getEducation();
            if (descCount != null) {
                cell.setCellValue(descCount);
            }

            cell = row.createCell(3);
            String speciality = employee.getSpeciality();
            if (speciality != null) {
                cell.setCellValue(speciality);
            }
            cell = row.createCell(3);
            String hobby = employee.getHobby();
            if (hobby != null) {
                cell.setCellValue(hobby);
            }
        }
        try {
            book.write(outputStream);
        } catch (IOException var17) {
            var17.printStackTrace();
        }

    }
}
