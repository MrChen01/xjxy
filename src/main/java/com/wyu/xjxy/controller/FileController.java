package com.wyu.xjxy.controller;

import com.wyu.xjxy.entity.FileBean;
import com.wyu.xjxy.util.DownloadUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.UUID;

@Controller
public class FileController {

    @RequestMapping(value = "/Upload",method = RequestMethod.POST)
    public String Upload(HttpServletRequest request, HttpServletResponse response){
        // 创建磁盘文件项工厂类
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 创建核心解析对象
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 设置上传文件的文件名称的编码
        upload.setHeaderEncoding("UTF-8");
        String upinfo = "上传成功";
        try {
            // 解析request
            @SuppressWarnings("unchecked")
            List<FileItem> list = upload.parseRequest(request);
            // 循环遍历
            for (FileItem fileItem : list) {
                // fileItem有可能是普通的文本项，也有可能是一个文件上传项
                // 先判断当前fileItem是普通还是上传项
                // isFormField() 返回true，就是普通文本项，返回false，就是文件上传项
                if (fileItem.isFormField()) {
                    // 普通项
                    // 获取name的名称 filedesc password sex
                    String name = fileItem.getFieldName();
                    // 获取用户输入的值
                    String value = null;
                    try {
                        value = fileItem.getString("UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    System.out.println(name + " : " + value);
                } else {
                    // 文件上传项
                    // 先获取文件的名称
                    String filename = fileItem.getName();
                    // 你应该在数据库中把文件的原名称和UUID的名称都需要保存到数据库中。
                    // 获取唯一的字符串:通用唯一识别码
                    filename = UUID.randomUUID().toString() + "_" + filename;
                    System.out.println("文件名称：" + filename);
                    // 获取文件的输入流
                    InputStream in = null;
                    try {
                        in = new BufferedInputStream(fileItem.getInputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // 项某个文件中写入
                    // 项WebRoot/upload目录写入
                    String path = "E:\\upload";
                    System.out.println("保存的路径：" + path);
                    // 获取输出流
                    OutputStream os = new BufferedOutputStream(new FileOutputStream(path + "/" + filename));
                    // io拷贝
                    int len = 0;
                    byte[] b = new byte[1024];
                    while ((len = in.read(b)) != -1) {
                        try {
                            os.write(b, 0, len);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        in.close();
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // 删除临时文件（放流关闭后执行）:
                    // 用来清空FileItem类对象中存放的主体内容，如果主体内容被保存在临时文件中，delete方法将删除该临时文件。
                    // 管当FileItem对象被垃圾收集器收集时会自动清除临时文件，但及时调用delete方法可以更早的清除临时文件，释放系统存储资源。另外，当系统出现异常时，仍有可能造成有的临时文件被永久保存在了硬盘中
                    fileItem.delete();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        request.setAttribute("upinfo", upinfo);
        return "uploadFile";
    }

    @RequestMapping(value = "/FileNameList")
    public String FileNameList(HttpServletResponse response,HttpServletRequest request){
        // 定义要遍历的文件路径
        String rootPath = "E:\\upload";
        // 向队列存放File
        File root = new File(rootPath);
        // 创建队列
        Queue<File> queue = new LinkedList<File>();
        // 把根节点入队
        queue.offer(root);
        // 循环的条件，如果队列不为空，一直循环，目的是：将遍历出来的文件名字，和路径放到list，并且放到request域中，在jsp中显示
        List<FileBean> list = new LinkedList<FileBean>();
        while (!queue.isEmpty()) {
            // 先获取根节点
            File file = queue.poll();
            // 获取file文件下的所有子节点
            File[] files = file.listFiles();
            // 循环遍历
            for (File f : files) {
                // 拿到每一个File对象，判断当前File是文件还是文件夹
                if (f.isFile()) {
                    // 如果是一个文件，提供下载。显示到页面上。
                    // 自己定义了一个filebean类，封装两个值，文件的绝对路径，另一个是文件名字
                    FileBean bean = new FileBean();
                    try {
                        bean.setFilepath(f.getCanonicalPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String filename = f.getName();
                    if (filename.lastIndexOf("_") != -1) {
                        String[] split = filename.split("_");
                        if (split.length > 1) {
                            filename = split[1];
                        }
                    }
                    System.out.println("------" + filename);
                    bean.setFilename(filename);
                    list.add(bean);
                } else {
                    // 如果是一个文件夹
                    queue.offer(f);
                }
                // 将集合放到request域中
                request.setAttribute("list", list);
            }
        }
        // 转发到jsp页面显示
        return "filesDL";
    }
    @RequestMapping(value = "/Downloadlist",method = RequestMethod.POST)
    public void Downloadlist(HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 传入参数可能有中文了
        String path = request.getParameter("path");
        if (path != null) {
            System.out.println("path==" + path);
            // 自己截取文件的名称
            int index = path.lastIndexOf("\\");
            // 截取
            String filename = path.substring(index + 1);
            // 编写文件的下载
            // 获取MIME的类型
            String type = request.getServletContext().getMimeType(filename);
            // 两个头一个流
            response.setContentType(type);
            // 先获取浏览器的信息
            String agent = request.getHeader("User-Agent");

            // 判断是什么浏览器
            if (agent.contains("Firefox")) {
                // 使用Base64编码
                filename = DownloadUtil.base64EncodeFileName(filename);
            } else {
                // IE或者谷歌的浏览器 URL编码
                try {
                    filename = URLEncoder.encode(filename, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                // 把空格编码成+号
                filename = filename.replace("+", " ");
            }

            // 直接设置
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            // 一个输入流:读取硬盘中的文件
            InputStream in = new BufferedInputStream(new FileInputStream(path));
            // 输出流
            OutputStream os = null;
            try {
                os = new BufferedOutputStream(response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            int len = 0;
            byte[] b = new byte[1024];
            while ((len = in.read(b)) != -1) {
                try {
                    os.write(b, 0, len);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            in.close();
            os.close();
        }
    }
}
