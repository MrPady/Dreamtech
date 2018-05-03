<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <form:form acceptCharset="utf-8" commandName="article" action="/saveArticle" method="post">
            <p style="margin-left: 10% ;margin-top: 5px ;text-align: left">
                <label for="title">文章标题:</label>
                <form:input type="text" id="title" path="title" tabindex="1" style="width:300px"/>
                <label for="field">类别:</label>
                <form:select id="field" path="field">
                    <c:forEach var="field1" items="${fields}">
                        <form:option value="${field1.id}">${field1.name}</form:option>
                    </c:forEach>
                </form:select>
                <label for="writer">作者:</label>
                <form:input id="writer" path="writer"/>
            </p>
            <p>
                <label for="body"></label>
                <form:textarea id="body" path="body" rows="22" cols="110" tabindex="4"></form:textarea>
            </p>
            <input id="submit" type="submit" tabindex="9" value="提交" style="margin-left: 60%">
        </form:form>
    </div>
</div>

</body>
</html>
