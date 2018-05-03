<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Title</title>
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
        <p><a href="/addPhoto/${gallery}" style="left: 100px">添加照片</a></p>
        <div class="galleries">
            <c:forEach items="${photos}" var="photo">
                <div class="gallery">
                    <img src="/src/pic/smallGalleries/${photo.id}_middle.jpg" class="small_pic" alt="${photo.name}"
                         style="width: 300px;height: 180px;top:0;left: 0">
                    <span class="ghostButton">${photo.name}</span>
                    <a class="ghostButton1" href="/deletePhoto/${photo.id}/${photo.gallery}">删除</a>
                    <a class="ghostButton2" href="/editPhoto/${photo.id}">编辑</a>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

</body>
</html>
