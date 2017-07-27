<%--

    View for @RequestMapping /Calls

--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="CSS/jquery-ui.css">
    <link href="CSS/styles.css" type="text/css" rel="stylesheet">
    <title>Calls</title>
    <style>
        body {
            background: url(images/bg.jpg) no-repeat;
            -moz-background-size: 100%; /* Firefox 3.6+ */
            -webkit-background-size: 100%; /* Safari 3.1+ и Chrome 4.0+ */
            -o-background-size: 100%; /* Opera 9.6+ */
            background-size: 100%; /* Современные браузеры */
        }
    </style>
</head>
<script src="jquery/jquery-1.12.4.js"></script>
<script src="jquery/jquery-ui.js"></script>
<script>
    $(function () {
        $("#datepicker").datepicker({
            dateFormat: 'yy-mm-dd'
        })
    });
    $(function () {
        $("#datepicker2").datepicker({
            dateFormat: 'yy-mm-dd'
        })
    });
</script>

<body>
<div id="menuUser">
    <ul>
        <li>${changePass}</li>
        <li><a href="logout">Выход</a></li>
    </ul>
</div>
<div id="wrapper">
    <div id="statusBar">
        <div id="logo">
            <img src="images/LC_trans.png">
        </div>
        <div id="menu">
            <ul>
                <li>Ваш логин: <span>${userJSP.login}</span>&nbsp;&nbsp;&nbsp;&nbsp;Ваш IP: ${ipAddress}</li>
            </ul>
        </div>
    </div>

    <div id="content">
        <fieldset id="filter">
            <legend>Фильтр поиска</legend>
            <form action="calls" method="get" name="form1">

                <label>Начальная дата <input id="datepicker" type="text" name="beginDate" value="${beginDate}"
                                             size="10px"/></label>&nbsp;&nbsp;&nbsp;

                <label>Конечная дата <input id="datepicker2" type="text" name="endDate" value="${endDate}" size="10px"/></label>&nbsp;&nbsp;&nbsp;

                <label>Направление <select name="criteria" id="criteriaId">
                    <option value=""></option>
                    <option value="src">Источник</option>
                    <option value="dst">Назначения</option>
                </select><label> <input type="text" name="critValue"/></label></label>&nbsp;&nbsp;&nbsp;

                <input type="hidden" name="page" value="0">
                <input type="submit" name="submit" value="Применить">
            </form>
        </fieldset>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th id="tableDate">Дата и время</th>
                <th>Источник</th>
                <th>Назначение</th>
                <th id="tableBillsec">Длина(сек)</th>
                <th id="tableDisposition">Статус</th>
                <th>Запись</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="call" items="${callList}" varStatus="i">
                <c:choose>
                    <c:when test="${(i.count) % 2 == 0}">
                        <tr class="even">
                    </c:when>
                    <c:otherwise>
                        <tr class="odd">
                    </c:otherwise>
                </c:choose>

                <td data-column="ID">${i.count}</td>
                <td data-column="Дата и время">${call.callDate}</td>
                <td data-column="Источник">${call.src}</td>
                <td data-column="Назначение">${call.dst}</td>
                <td data-column="Длительность(сек)">${call.billsec}</td>
                <td data-column="Статус">${call.disposition}</td>
                <td data-column="Запись"><a href="play?path=${call.recordingfile}"
                                            onclick="window.open(this.href, 'mywin','left=40,top=40,width=310,height=15,toolbar=0,resizable=0'); return false;">Файл</a>
                </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div id="pageHref">${pageNumberPrev}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${pageNumberNext}</div>
    </div>
</div>
</body>
</html>
