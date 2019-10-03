package com.wyu.xjxy.entity;


import java.io.Serializable;

public class Student implements Serializable {

    private int id;
    private String number;
    private String username;
    private String password;
    private String classinfo;
    private String remark;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getClassinfo() {
        return classinfo;
    }
    public void setClassinfo(String classinfo) {
        this.classinfo = classinfo;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Student() {

    }
    public Student(String number, String username, String password, String classinfo) {
        this.number = number;
        this.username = username;
        this.password = password;
        this.classinfo = classinfo;
    }
    public Student(int id,String number, String username, String password, String classinfo,String remark) {
        this.number = number;
        this.username = username;
        this.password = password;
        this.classinfo = classinfo;
        this.id=id;
        this.remark=remark;
    }
    @Override
    public String toString() {
        return "学生类 [id=" + id + ", number=" + number + ", username=" + username + ", password=" + password
                + ", classinfo=" + classinfo + ", remark=" + remark + "]";
    }

}
