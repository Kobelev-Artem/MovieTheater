<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Checkout Page</title>
</head>
<body>
    <c:set var="user" value="${sessionScope.get('user')}"/>
    <c:set var="price" value="${sessionScope.get('totalPrice')}"/>

    Greeting at tickets page! <br/>
    User = ${user.firstName} ${user.lastName} <br/>

    Total Price: ${price}
</body>
</html>
