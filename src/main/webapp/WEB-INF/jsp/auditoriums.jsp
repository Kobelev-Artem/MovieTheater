<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Auditoriums</title>
</head>
<body>
Existing auditoriums:<br/>

<c:forEach items="${auditoriums}" var="auditorium">
    <tr>
        <td>${auditorium.name}</td>
        <td>${auditorium.numberOfSeats}</td>
        <td>${auditorium.vipSeats}</td>
        <br/>
    </tr>
</c:forEach>

</body>
</html>
