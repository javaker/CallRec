<%--

    View for @RequestMapping /login

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>CallRec</title>
    <link rel="stylesheet" type="text/css" href="CSS/login.css">
    <link rel="stylesheet" type="text/css" href="CSS/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="CSS/cs-select.css"/>
    <link rel="stylesheet" type="text/css" href="CSS/cs-skin-slide.css"/>
    <style>
        body {
            background: url(images/bgLogin.jpg) center center fixed no-repeat;
            -moz-background-size: 100%; /* Firefox 3.6+ */
            -webkit-background-size: 100%; /* Safari 3.1+ и Chrome 4.0+ */
            -o-background-size: 100%; /* Opera 9.6+ */
            background-size: cover; /* Современные браузеры */
        }
    </style>
    <script src="jquery/classie.js"></script>
    <script src="jquery/selectFx.js"></script>
</head>
<body>
<div class="parent">
    <div class="block">
        <form action="check-user" method="post" name="form1">
            <div id="userList">
                <select name="login1" class="cs-select cs-skin-slide">
                    <option value="Trapeza">Трапеза</option>
                    <option value="Demetra">Деметра</option>
                    <option value="admin">Admin</option>
                </select>
            </div>
            <div id="userPass">
                <br/>
                <label id="passField">Пароль: <input type="password" name="password" size="3px"></label>
                <br/>
                <br/>
                <input type="submit" name="submitButton" value="Войти" id="loginBt">
                <br/>
                <label id="passError">${error}</label>
            </div>
        </form>
        <div class="pretoriants">
            <a href="loginPretoriants.jsp"
               onclick="window.open(this.href, 'mywin','left=300,top=250,width=296,height=130,toolbar=0,resizable=no,location=no'); return false;">&#960;</a>
        </div>
    </div>
</div>

<script>
    (function () {
        [].slice.call(document.querySelectorAll('select.cs-select')).forEach(function (el) {
            new SelectFx(el);
        });
    })();
</script>
</body>
</html>
