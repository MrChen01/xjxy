package com.wyu.xjxy.service;

import com.wyu.xjxy.entity.Student;

import java.util.List;

public interface StudentService {
    Student selectById(Integer id);
    // 用户登录的方法：0.表示用户不存在，1.表示登录成功，2.密码错误
    int selectByNameAndPass(String username,String password);

    // 添加用户的方法
    int insertStudent(Student stu);

    // 3.查询学生信息
    List<Student> getStudents();

    // 4.删除学生
    int deleteStudent(String number);

    // 5.分页查询
    List<Student> selectBypage(int currentpage, int pagesize);

    // 6.查询总记录数
    int getTotal();

    // 7.修改学生信息
    int updateStudent(Student stu);
    List<Student> selectAll();
}
