<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/change.css">
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
        <form:form  commandName="photo" action="/saveEditedPhoto/${photo.id}/${photo.gallery}" method="post" enctype="multipart/form-data">
            <p>
                <label for="name">照片名称</label>
                <form:input path="name" id="name" placeholder="${photo.name}"/>
            </p>
            <p>
                <label>照片描述</label>
                <form:textarea path="description" placeholder="${photo.description}"/>
            </p>
            <input type="submit" value="更改">
        </form:form>
    </div>
</div>
</body>
</html>
