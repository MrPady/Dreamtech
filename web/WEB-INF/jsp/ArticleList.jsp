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
        <c:forEach var="field" items="${fields}">
            <a style="display: block" class="left_item" href="/showArticles/${field.id}">${field.name}</a>
        </c:forEach>
        <a href="/showFields">编辑主题</a>
    </div>
    <div id="list_right">
        <p><a href="<c:url value="/addArticle"/> ">添加</a></p>
        <table>
            <tr>
                <th style="padding-left: 10px;width: 100px;text-align: left">文章标题</th>
                <th style="padding-left: 10px;width: 100px">作者</th>
                <th style="padding-left: 10px;width: 200px">时间</th>
                <th style="padding-left: 10px;">&nbsp;</th>
            </tr>
            <c:forEach items="${articles}" var="article">
                <tr>
                    <td style="padding-left: 10px;width: 200px">${article.title}</td>
                    <td style="padding-left: 10px;width: 100px">${article.writer}</td>
                    <td style="padding-left: 10px;width: 200px">${article.timeString}</td>
                    <td style="padding-left: 10px;"><a href="/deleteArticle/${article.id}">删除</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
