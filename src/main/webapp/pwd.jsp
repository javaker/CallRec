<%--

    View for @RequestMapping /pwd

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="CSS/styles.css" type="text/css" rel="stylesheet">
    <title>PWD</title>
</head>
<body>
<fieldset>
    <legend>Смена пароля</legend>
    <form action="pwd" method="post" name="form">
        <label>Ведите пароль: <input type="password" name="password"/></label>
        <br/>
        <label>Повторите пароль: <input type="password" name="repassword"/></label>
        <br/>
        <input type="reset" name="submitButton" value="Сброс">
        <input type="submit" name="submit" value="Сохранить">
    </form>
</fieldset>
<div id="errorPass">${passError}</div>
</body>
</html>
