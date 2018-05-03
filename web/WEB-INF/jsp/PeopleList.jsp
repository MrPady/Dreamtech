<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>PeopleList</title>
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
    <div id="list_left">
        <c:forEach var="year" items="${years}">
            <a style="display: block" class="left_item" href="/showPeople/${year}">${year}</a>
        </c:forEach>
        <a href="/addGrade">添加年级</a>
    </div>
    <div id="list_right">
        <a href="<c:url value="/addPerson"/> ">Add</a>
        <table>
            <tr>
                <th style="padding-left: 10px;width: 50px">Name</th>
                <th style="padding-left: 10px;width: 40px">Grade</th>
                <th  style="padding-left: 10px;width: 100px">Id</th>
                <th  style="padding-left: 10px;width: 110px">QQ</th>
                <th  style="padding-left: 10px;width: 110px">phone</th>
                <th  style="padding-left: 10px;width: 120px">mail</th>
                <th style="padding-left: 10px;">&nbsp;</th>
                <th style="padding-left: 10px;">&nbsp;</th>
            </tr>
            <c:forEach items="${people}" var="person">
                <tr>
                    <td style="padding-left: 10px;width: 50px">${person.name}</td>
                    <td style="padding-left: 10px;width: 40px">${person.grade}</td>
                    <td style="padding-left: 10px;width: 30px">${person.id}</td>
                    <td style="padding-left: 10px;width: 100px">${person.qq}</td>
                    <td style="padding-left: 10px;width: 100px">${person.phone}</td>
                    <td style="padding-left: 10px;width: 120px">${person.mail}</td>
                    <td style="padding-left: 10px;"><a href="/deletePerson/${person.id}/${person.grade}">删除</a></td>
                    <td style="padding-left: 10px;"><a href="/showPerson/${person.id}/${person.grade}">${person.show}</a></td>
                </tr>

            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
