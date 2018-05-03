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
        <form:form  commandName="project" action="/saveProject" method="post">
            <p>
                <label for="name" style="padding-left: 20%">项目名称:</label>
                <form:input path="name" name="name"/>
            </p>
            <p>
                <label for="teamer" style="padding-left: 20%">参与人员:</label>
                <form:textarea name="teamer" path="teamer" />
            </p>
            <p>
                <label for="leader" style="padding-left: 20%">项目负责:</label>
                <form:input name="leader" path="leader" />
            </p>
            <p>
                <label for="time" style="padding-left: 20%">项目时间:</label>
                <form:input name="time" path="timeString" value="${time}" />
            </p>
            <p>
                <label for="url" style="padding-left: 20%">项目地址</label>
                <form:input name="url" path="url" />
            </p>
            <input type="submit" value="提交">
        </form:form>
    </div>
</div>

</body>
</html>
