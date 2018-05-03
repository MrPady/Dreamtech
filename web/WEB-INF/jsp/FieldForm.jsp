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
        <form:form  commandName="field" action="/saveField" method="post">
            <p style="padding-top: 30px">
                <label for="name" style="padding-left: 300px">名称:</label>
                <form:input path="name" name="name" tabindex="1"/>
            </p>
            <p>
                <label for="fatherId" style="padding-left: 300px">所属:</label>
                    <form:select id="fatherId" path="fatherId">
                        <c:forEach var="field1" items="${fields}">
                            <form:option value="${field1.id}">${field1.name}</form:option>
                        </c:forEach>
                    </form:select>
            </p>
            <p>
                <label>更改描述：</label>
                <form:textarea id="body" path="description" tabindex="4"></form:textarea></p>
            <p><input id="file" type="file" name="file" tabindex="10" value="更改图片" style=" position: absolute;
    left: 40%;"/></p>
            <p>
                <input id="submit" type="submit" tabindex="3" value="提交" style="left: 300px"/>
            </p>
        </form:form>
    </div>
</div>

</body>
</html>
