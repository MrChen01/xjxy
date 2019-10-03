<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>员工查询</title>
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <script type="text/javascript" src="easyui/jquery.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>

<table id="dg" style="width: 100%; height: 500px"></table>

<div id="dlg" class="easyui-dialog" title="修改员工信息"
     style="width: 400px; height: 450px; padding: 10px"
     data-options="
				closed:true,
				iconCls: 'icon-edit',
				toolbar: '#dlg-toolbar',
				buttons: '#dlg-buttons'
			">
    <div>
        <form id="ff" class="easyui-form" method="post">
            <table cellpadding="10">
                <tr>
                    <td>id</td>
                    <td><input class="easyui-textbox" type="text" name="id"
                               id="id" data-options="required:true" disabled="disabled"></td>
                </tr>
                <tr>
                    <td>name:</td>
                    <td><input class="easyui-textbox" type="text" name="name"
                               id="name" data-options="required:true"></td>
                </tr>
                <tr>
                    <td>address:</td>
                    <td><input class="easyui-textbox" type="text" name="address"
                               id="address" data-options="required:true"></td>
                </tr>
                <tr>
                    <td>phone:</td>
                    <td><input class="easyui-textbox" type="text" name="phone"
                               id="phone" data-options="required:true"></td>
                </tr>
                <tr>
                    <td>email</td>
                    <td><input class="easyui-textbox" type="text" name="email"
                               id="email" data-options="required:true"></td>
                </tr>
                <tr>
                    <td>race:</td>
                    <td><input class="easyui-textbox" type="text" name="race"
                               id="race" data-options="required:true"></td>
                </tr>
                <tr>
                    <td>education:</td>
                    <td><input class="easyui-textbox" type="text" name="education"
                               id="education" data-options="required:true"></td>
                </tr>
                <tr>
                    <td>speciality:</td>
                    <td><input class="easyui-textbox" type="text" name="speciality"
                               id="speciality" data-options="required:true" ></td>
                </tr>
                <tr>
                    <td>hobby:</td>
                    <td><input class="easyui-textbox" type="text" name="hobby"
                               id="hobby" data-options="required:true"></td>
                </tr>
            </table>
        </form>
    </div>
</div>

<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton"
       onclick="save()">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton"
       onclick="javascript:$('#dlg').dialog('close')">取消</a>
</div>

</body>
<script type="text/javascript">
    function save() {
        //获得控件中的数据
        var id = $("#id").val();
        var name = $("#name").val();
        var address = $("#address").val();
        var phone = $("#phone").val();
        var email = $("#email").val();
        var race = $("#race").val();
        var education = $("#education").val();
        var speciality = $("#speciality").val();
        var hobby = $("#hobby").val();
        //将得到的学生信息构建成json数据
        var json = {
            "id" : id,
            "name" : name,
            "address" : address,
            "phone" : phone,
            "email" : email,
            "race" : race,
            "education" : education,
            "speciality" : speciality,
            "hobby" : hobby
        };
        //完成ajax操作
        $.post("/UpdateEmp", json, function(data) {
            var info = "对不起，修改失败！";
            var pic = "error"
            if (data == "1") {
                info = "恭喜，修改成功！！";
                pic = "info"
            }
            $.messager.alert('结果', info, pic, function() {
                window.location.reload();
            });
        });
    }

    $('#dg').datagrid({
        url : '/selectEmployees',
        title : '员工列表',
        border : false,
        rownumbers : true,
        toolbar : '#tb',
        pagination : true,
        pageSize : "10",
        pageList : [ 5, 10, 15, 20 ],
        singleSelect : true,
        columns : [ [ {
            field : 'id',
            value : 'number',
            checkbox : true,
            title : '选择',
            width : 50
        }, {
            field : 'name',
            title : '姓名',
            width : 120
        }, {
            field : 'address',
            title : '住址',
            width : 120
        },{
            field : 'phone',
            title : '手机号码',
            width : 120
        }, {
            field : 'email',
            title : '邮箱',
            width : 160
        }, {
            field : 'race',
            title : '民族',
            width : 120
        },{
            field : 'education',
            title : '学历',
            width : 120
        },{
            field : 'speciality',
            title : '专业',
            width : 120
        }, {
            field: 'hobby',
            title: '爱好',
            width: 120
        }] ]
    });

    $(function() {
        var pager = $('#dg').datagrid().datagrid('getPager');
        pager
            .pagination({
                buttons : [
                    {
                        iconCls : 'icon-no',
                        text : '删除',
                        handler : function() {
                            var row = $('#dg').datagrid('getSelected');
                            if (row != null) {
                                $.messager
                                    .confirm(
                                        '确认是否要删除',
                                        '确定要删除(' + row.name
                                        + ')吗?',
                                        function(r) {
                                            if (r) {
                                                $.get(
                                                    "DeleteEmp?id=" + row.id,
                                                    null,
                                                    function(data) {
                                                        $.messager.alert(
                                                            '结果',
                                                            data,
                                                            'info',
                                                            function() {
                                                                window.location.reload();
                                                            });
                                                    });
                                            }
                                        });
                            }
                        }
                    },
                    {
                        iconCls : 'icon-edit',
                        text : '修改',
                        handler : function() {
                            var row = $('#dg').datagrid('getSelected');
                            if (row != null) {
                                $('#dlg').dialog('open');
                                var row = $('#dg').datagrid(
                                    'getSelected');
                                if (row != null) {
                                    $("#id").textbox("setValue", row.id),

                                    $("#name").textbox('setValue',row.name);

                                    $("#address").textbox('setValue',row.address);

                                    $("#phone").textbox("setValue", row.phone),

                                    $("#email").textbox('setValue',row.email);

                                    $("#race").textbox('setValue',row.race);

                                    $("#education").textbox("setValue", row.education),

                                    $("#speciality").textbox('setValue',row.speciality);

                                    $("#hobby").textbox('setValue',row.hobby);

                                    $('#dlg').dialog('open');
                                }
                            }
                        }
                    } ]
            });
    })
</script>

</html>