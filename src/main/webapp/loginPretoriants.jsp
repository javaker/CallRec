<%--

    View for @RequestMapping /pretoriants

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hack</title>
    <link rel="stylesheet" type="text/css" href="CSS/login.css">
    <style>
        body {
            background: url(images/bg_pr.jpg) center center fixed no-repeat;
            -moz-background-size: 100%; /* Firefox 3.6+ */
            -webkit-background-size: 100%; /* Safari 3.1+ и Chrome 4.0+ */
            -o-background-size: 100%; /* Opera 9.6+ */
            background-size: cover; /* Современные браузеры */
        }
    </style>
</head>
<body>
<form action="pretoriants" method="post" name="form1">
    <div id="userPassPr">
        <br/>
        <label>Пароль: <input type="password" name="password" size="10px"></label>
        <br/>
        <br/>
        <input type="submit" name="submitButton" value="Hack" id="loginBt">
        <br/>
        <label id="passError">${error}</label>
    </div>
</form>
</body>
</html>
