<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>项目列表</title>
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
    <div class="middle">
        <a href="<c:url value="/addProject"/> ">Add</a>
        <table>
            <tr>
                <th style="padding-left: 10px;width: 50px">项目id</th>
                <th style="padding-left: 10px;width: 50px">项目名字</th>
                <th style="padding-left: 10px;width: 40px">人员</th>
                <th  style="padding-left: 10px;width: 100px">负责人</th>
                <th  style="padding-left: 10px;width: 110px">地址</th>
                <th  style="padding-left: 10px;width: 110px">时间</th>
                <th style="padding-left: 10px;">&nbsp;</th>
            </tr>
            <c:forEach items="${projects}" var="project">
                <tr>
                    <td style="padding-left: 10px;width: 120px">${project.id}</td>
                    <td style="padding-left: 10px;width: 50px">${project.name}</td>
                    <td style="padding-left: 10px;width: 40px">${project.teamer}</td>
                    <td style="padding-left: 10px;width: 30px">${project.leader}</td>
                    <td style="padding-left: 10px;width: 100px">${project.url}</td>
                    <td style="padding-left: 10px;width: 100px">${project.time}</td>
                    <td style="padding-left: 10px;"><a href="/deleteProject/${project.id}">删除</a></td>
                </tr>

            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
