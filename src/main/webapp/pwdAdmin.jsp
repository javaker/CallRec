<%--

    View for @RequestMapping /pwdAdmin

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
    <form action="pwdAdmin" method="post" name="form">

        <select name="login" class="cs-select cs-skin-slide">
            <option value="Trapeza">Трапеза</option>
            <option value="Demetra">Деметра</option>
            <option value="Admin">Admin</option>
        </select>
        <br/>
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
