package com.wyu.xjxy.service.impl;

import com.wyu.xjxy.dao.StudentDao;
import com.wyu.xjxy.entity.Student;
import com.wyu.xjxy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    public int selectByNameAndPass(String username, String password) {
        return studentDao.selectByNameAndPass(username,password);
    }

    public Student selectById(Integer id) {
        return studentDao.selectById(id);
    }


    public int insertStudent(Student stu) {
        return studentDao.insertStudent(stu);
    }

    public List<Student> getStudents() {
        return studentDao.selectAll();
    }

    public int deleteStudent(String number) {
        return studentDao.deleteStudent(number);
    }

    public List<Student> selectBypage(int currentpage, int pagesize) {
        return studentDao.selectBypage(currentpage,pagesize);
    }

    public int getTotal() {
        return studentDao.getTotal();
    }

    public int updateStudent(Student stu) {
        return studentDao.updateStudent(stu);
    }

    public List<Student> selectAll() {
        return studentDao.selectAll();
    }
}
