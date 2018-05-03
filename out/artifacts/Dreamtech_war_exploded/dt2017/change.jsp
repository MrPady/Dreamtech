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
<div>
    <div>
        <form:form  commandName="person" action="/saveEditedPerson" method="post" enctype="multipart/form-data">
            <p>
                <label>Id:</label>
                <label>${person.id}</label>
            </p>
            <p>
                <label>姓名:</label>
                <label>${person.name}</label>
            </p>
            <p>
                <label>电话:</label>
                <form:input path="phone"/>
            </p>
            <p>
                <label>QQ:</label>
                <form:input path="qq"/>
            </p>
            <p>
                <label>邮箱:</label>
                <form:input path="mail"/>
            </p>
            <p>
                <label>电话:</label>
                <form:textarea path="description"/>
            </p>
            <p>
                <label>更改密码:</label>
                <form:input path="description"/>
                <%--这里再加一个确认输入的--%>
            </p>
            <p>
                <label>上传照片</label>
                <input id="file" type="file" >
            </p>
            <input type="submit" value="提交">
        </form:form>
    </div>
</div>
</body>
</html>
