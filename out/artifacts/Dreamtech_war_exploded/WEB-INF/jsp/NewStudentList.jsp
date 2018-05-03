<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>账目列表</title>
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
        <a class="part" href="/showProjects">项目管理</a>
        <a class="part" href="/showNewStudents/">新生申请</a>
        <a class="part" href="/mainController">主控制器</a>
    </div>
</div>
<div class="main">
    <div class="middle" style="margin-left: auto; margin-right: auto; vertical-align:middle;text-align:center;">
        <table>
            <tr>
                <th style="padding-left: 10px">姓名</th>
                <th style="padding-left: 10px; width: 200px">qq</th>
                <th style="padding-left: 10px; width: 200px">电话</th>
                <th style="padding-left: 10px; width: 200px">班级</th>
                <th style="padding-left: 10px; width: 200px">描述</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${newStudents}" var="newStudent">
            <tr>
                <td>${newStudent.name}</td>
                <td style="padding-left: 10px ;width: 200px">${newStudent.qq}</td>
                <td style="padding-left: 10px; width: 200px">${newStudent.phone}</td>
                <td style="padding-left: 10px; width: 300px">${newStudent.nClass}</td>
                <td style="padding-left: 10px; width: 300px">${newStudent.description}</td>
                <td style="padding-left: 10px"><a href="/readNewStudent/${newStudent.id}">${newStudent.readString}</a></td>
                <td style="padding-left: 10px"><a href="/deleteNewStudent/${newStudent.id}">删除</a></td>
            </tr>
            </c:forEach>
    </div>
</div>

</body>
</html>
