<%--

    View for @RequestMapping /pretoriants

--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Login</th>
        <th>Password</th>

    </tr>
    <c:forEach var="user" items="${userList}">
    <tr>
        <td>${user.id}</td>
        <td>${user.login}</td>
        <td>${user.password}</td>
    </tr>
    </c:forEach>
</body>
</html>
