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
    <div class="middle">
        <c:if test="${requestScopr.errors!=null}">
            <p id="errors">
                Error(s)!
                <url>
                    <c:forEach var="error" items="${requestScope.errors}">
                        <li>${error}</li>
                    </c:forEach>
                </url>
            </p>
        </c:if>
        <form action="/savePerson" method="post" enctype="multipart/form-data">

            <p>
                <label for="name">姓名:</label>
                <input type="text" id="name" name="name" tabindex="1" style=" position: absolute;
    left: 50%;">
            </p>
            <p>
                <label for="grade">入学年份:</label>
                <input type="text" id="grade" name="grade" tabindex="2" style=" position: absolute;
    left: 50%;">
            </p>
            <p>
                <label for="description">描述:</label>
                <input type="text" id="description" name="description" tabindex="3" style="position: absolute;
    left: 50%;">
            </p>
            <p>
                <label for="id">ID:</label>
                <input type="text" id="id" name="id" tabindex="4" style=" position: absolute;
    left: 50%;">
            </p>
            <p>
                <label for="mail">邮箱:</label>
                <input type="text" id="mail" name="mail" tabindex="5" style=" position: absolute;
    left: 50%;">
            </p>
            <p>
                <label for="qq">QQ:</label>
                <input type="text" id="qq" name="qq" tabindex="6"  value="0" style=" position: absolute;
    left: 50%;">
            </p>
            <p>
                <label for="phone">电话:</label>
                <input type="text" id="phone" name="phone" tabindex="7" value="0" style=" position: absolute;
    left: 50%;">
            </p>
            <p>
                <label>上传照片</label>
                <input id="file" type="file" name="file" tabindex="10" style=" position: absolute;
    left: 40%;">
            </p>
            <p>
                <input id="reset" type="reset" tabindex="8" value="重置">
                <input id="submit" type="submit" tabindex="9" value="提交">
            </p>
        </form>
    </div>
</div>
</body>
</html>
