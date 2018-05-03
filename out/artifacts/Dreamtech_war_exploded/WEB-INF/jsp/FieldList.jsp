<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Add Person Form</title>
    <link rel="stylesheet" type="text/css" href="/src/css/main.css">
</head>
<body>
<div class="head_with_hair">

    <div class="head">
        <h1 id="head-title">Dre@mTech后台管理界面</h1>
    </div>
    <div class="hair">
        <a class="part" href="/showPeople/${2000}">人员管理</a>
        <a class="part" href="/showArticles/${1}">文章管理</a>
        <a class="part" href="/showGalleries">相册管理</a>
        <a class="part" href="/showBills">账目查阅</a>
        <a class="part" href="/editMsg">群发短信</a>
        <a class="part" href="/showProjects/">项目管理</a>
        <a class="part" href="/showNewStudents/">新生申请</a>
        <a class="part" href="/mainController">主控制器</a>
    </div>
</div>
<div class="main">
    <div class="middle" style="margin-left: auto; margin-right: auto; vertical-align:middle;text-align:center;">
        <table>
            <tr>
                <th style="padding-left: 10px;width: 100px">名称</th>
                <th style="padding-left: 10px;width: 100px">id</th>
                <th style="padding-left: 10px;width: 100px">有无子id</th>
                <th style="padding-left: 10px;width: 100px">父id</th>
                <th style="padding-left: 10px;">&nbsp;</th>
                <th style="padding-left: 10px;">&nbsp;</th>
            </tr>
            <c:forEach items="${Fields}" var="field">
            <tr>
                <td style="padding-left: 10px;width: 100px">${field.name}</td>
                <td style="padding-left: 10px;width: 100px">${field.id}</td>
                <td style="padding-left: 10px;width: 100px">${field.hasChild}</td>
                <td style="padding-left: 10px;width: 100px">${field.fatherId}</td>
                <td style="padding-left: 10px;"><a href="/deleteField/${field.id}">删除</a></td>
                <td style="padding-left: 10px;"><a href="/editField/${field.id}">修改</a></td>
            </tr>
            </c:forEach>
            <a href="/addField">添加</a>
    </div>
</div>

</body>
</html>
