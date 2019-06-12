<%--
  Created by IntelliJ IDEA.
  User: Егор
  Date: 09.06.2019
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="style.css">
    <%--<link rel="stylesheet" href="css/assets.css" media="all">--%>
    <title>Users</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2><p>Рацион</p></h2>
<table align="center">
    <tr>
        <th>Дата/время</th>
        <th>Описание</th>
        <th>Калории</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach var="meal" items="${meals}">
        <c:if test="${meal.excess eq true}">
            <tr class="lineexcess">
                <td>${meal.getDateTime().toString().replace("T", " ")}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td>
                    <%--<a href="meals?action=edit&mealId=<c:out value="${meal.id}"/>">edit</a>--%>
                        edit
                </td>
                <td>
                    <%--<a href="meals?action=delete&mealId=<c:out value="${meal.id}"/>">delete</a>--%>
                        delete
                </td>
            </tr>
        </c:if>
        <c:if test="${meal.excess eq false}">
            <tr class="linenotexcess">
                <td>${meal.getDateTime().toString().replace("T", " ")}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td>
                    <a href="/edit/${film.id}">edit</a>
                </td>
                <td>
                    <a href="/delete/${film.id}">delete</a>
                </td>
            </tr>
        </c:if>
    </c:forEach>
</table>
<br>


<div align="center">
    <h2>Дополнить рацион</h2>
    <form>
        <label>Дата/Время</label>
        <br>
        <input type="text" class="required" placeholder="Дата/Время">
        <br>
        <br>
        <label>Описание</label>
        <br>
        <input type="text" class="required" placeholder="Описание">
        <br>
        <br>
        <label>Калории</label>
        <br>
        <input type="text" min="0" class="required" placeholder="Калории">
        <br>
        <br>
        <input type="submit" value="Добавить" class="send">
    </form>
    ​
</div>
</body>
</html>
