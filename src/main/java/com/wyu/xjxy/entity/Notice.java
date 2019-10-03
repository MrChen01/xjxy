package com.wyu.xjxy.entity;


import java.io.Serializable;

public class Notice implements Serializable {

    private int id;
    private String noticename;
    private String title;
    private String time;
    private String stuname;
    private String content;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNoticename() {
        return noticename;
    }
    public void setNoticename(String noticename) {
        this.noticename = noticename;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getStuname() {
        return stuname;
    }
    public void setStuname(String stuname) {
        this.stuname = stuname;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    @Override
    public String toString() {
        return "Notice [id=" + id + ", noticename=" + noticename + ", title=" + title + ", time=" + time + ", stuname="
                + stuname + ", content=" + content + "]";
    }

}
