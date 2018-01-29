<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Tickets</title>
</head>
<body>
    <c:set var="user" value="${sessionScope.get('user')}"/>
    Greeting at tickets page! <br/>
    User = ${user} <br/>

    <form action="/tickets" method="post">
        Event Name: <input type="text" name="eventName" value=""> <br/>
        Seat: <input type="text" name="seat" value=""> <br/>
        <button type="submit" value="Buy ticket" >Buy ticket</button>
    </form>

    Tickets: <br/>
    <c:forEach var = "ticket" items="${user.tickets}">
        Ticket = ${ticket} <br/>
    </c:forEach>

</body>
</html>
