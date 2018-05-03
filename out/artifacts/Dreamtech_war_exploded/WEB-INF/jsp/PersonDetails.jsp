<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Add Person Form</title>
    <link rel="stylesheet" type="text/css" href="/src/css/change.css">
    <script src="/src/js/jquery-1.7.1.min.js"></script>
</head>
<body>
<div>
    <div>
        <form:form commandName="person" action="/saveEditedPerson" method="post" class="basic-grey" enctype="multipart/form-data">
            <h1>个人信息修改
                <span>请修改以下信息：</span>
            </h1>
            <label>
                <span>ID :</span>
                <form:input id="Id" path="id" type="text" name="Id" placeholder="${person.id}" readonly="true"/>
            </label>
            <label>
                <span>姓名 :</span>
                <input id="name" type="text" name="name" placeholder="${person.name}" readonly="true"/>
            </label>
            <label>
                <span>电话 :</span>
                <form:input path="phone" id="phone" type="text" name="phone" placeholder="Your Phone Number" />
            </label>
            <label>
                <span>QQ :</span>
                <form:input path="qq" id="qq" type="text" name="qq" placeholder="Your QQ Number" />
            </label>
            <label>
                <span>邮箱 :</span>
                <form:input path="mail" id="email" type="email" name="email" placeholder="Valid Email Address" />
            </label>
            <label>
                <span>修改密码 :</span>
                <form:input path="password" id="password" type="password" name="password" value="${person.password}" />
            </label>
            <label>
                <span>确认新密码 :</span>
                <input id="password_conf" type="password" name="password_conf" value="${person.password}"/>
            </label>
            <label>
                <span>个人简介 :</span>
                <form:textarea path="description" id="message" name="message" placeholder="Your Message to Us"></form:textarea>
            </label>
            <label>
                <span>上传照片：</span>
                <input type="text" size="20" name="upfile" id="upfile" style="width: 40%">
                <input type="button" class="button" value="浏览" onclick="path.click()">
                <input type="file" name="file" id="path" style="display:none" onchange="upfile.value=this.value">
            </label>
            <label>
                <span>&nbsp;</span>
                <input type="button" class="button confirm" value="确认">
                <input type="submit" id="submit" value="确认" style="display:none"/>
            </label>
        </form:form>
    </div>
</div>

<script>
    $('.confirm').click(function () {
        var password=$('#password').val();
        var pass_conf=$('#password_conf').val();
        console.log(password)
        if (password!=pass_conf){
            alert('两次密码不一致！');
        }else {
            submit.click();
        }
    })
</script>
</body>
</html>
