<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Account Page</title>
</head>
<body>
    <c:set var="user" value="${sessionScope.get('user')}"/>
    <h1>
        Hello, ${user.firstName}
    </h1>

    Last ticket price: ${sessionScope.get('totalPrice')}<br/><br/>

    My Tickets: <br/>
    <c:forEach var = "ticket" items="${user.tickets}">
        Ticket = ${ticket.event}, seat - ${ticket.seat}, date - ${ticket.dateTime} <br/>
    </c:forEach>
</body>
</html>
