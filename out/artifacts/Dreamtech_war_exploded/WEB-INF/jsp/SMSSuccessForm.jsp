<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Add Field Form</title>
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
        <h1>短信发送成功</h1>
        <label>发送数目为${successNumber}</label>
    </div>
</div>

</body>
</html>
