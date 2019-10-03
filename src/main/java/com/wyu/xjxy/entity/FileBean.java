package com.wyu.xjxy.entity;


public class FileBean {
    private String filepath;//文件路径
    private String filename;//文件名字
    public String getFilepath() {
        return filepath;
    }
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public FileBean(String filepath, String filename) {
        super();
        this.filepath = filepath;
        this.filename = filename;
    }
    public FileBean() {
        super();
    }
    @Override
    public String toString() {
        return "FileBean [filepath=" + filepath + ", filename=" + filename + "]";
    }


}
