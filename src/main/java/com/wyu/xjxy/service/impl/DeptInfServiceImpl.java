package com.wyu.xjxy.service.impl;

import com.wyu.xjxy.dao.DeptInfDao;
import com.wyu.xjxy.entity.DeptInf;
import com.wyu.xjxy.service.DeptInfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeptInfServiceImpl implements DeptInfService {
    @Autowired
    private DeptInfDao deptInfDao;
    public List<DeptInf> selectAll() {
        return deptInfDao.selectAll();
    }

    public List<DeptInf> selectDeptBypage(int currentpage, int pagesize) {
        return deptInfDao.selectDeptBypage(currentpage,pagesize);
    }

    public int selectTotal() {
        return deptInfDao.selectTotal();
    }

    public int deleteDeptById(int id) {
        return deptInfDao.deleteDeptById(id);
    }

    public int insertDept(String name,String remark) {
        return deptInfDao.insertDept(name,remark);
    }

    public int updateDept(DeptInf dept) {
        return deptInfDao.updateDept(dept);
    }
}
