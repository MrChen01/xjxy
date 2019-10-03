<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css"
          href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <script type="text/javascript" src="easyui/jquery.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="ueditor/ueditor.all.min.js">
    </script>
     <script type="text/javascript" charset="utf-8"
            src="ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
<form action="/AddNotice" method="post">
    <table cellpadding="7">
        <tr>
            <td>公告名称:</td>
            <td><input class="easyui-textbox" type="text" name="noticename"
                       data-options="required:true" style="width: 300px; height: 20px"></td>
        </tr>
        <tr>
            <td>公告主题:</td>
            <td><input class="easyui-textbox" type="text" name="title"
                       style="width: 300px; height: 20px" data-options="required:true"></td>
        </tr>
    </table>
    <textarea id="container" name="container"
              style="width: 100%; height: 400px;"> </textarea>

    <br>
    <input type="submit" value="保存">
</form>
</body>

<script type="text/javascript">
    var ue = UE.getEditor("container");

    setTimeout(function() {
        editor.execCommand('drafts')
    }, 2000);//自动保存
</script>
</html>