package com.wyu.xjxy.dao;

import com.wyu.xjxy.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {
    Student selectById(Integer id);
    int selectByNameAndPass(@Param("username") String username, @Param("password") String password);
    List<Student> selectAll();
    int insertStudent(Student stu);
    int deleteStudent(String number);
    List<Student> selectBypage(@Param("currentpage") int currentpage, @Param("pagesize") int pagesize);
    int getTotal();
    int updateStudent(Student stu);

}
