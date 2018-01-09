<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Movie Theater</title>
</head>
<body>
<h2>Greetings on movie theater!</h2>

<c:choose>
    <c:when test='${sessionScope.get("user") ne null}'>
        <c:url value="/logout" var="logoutURI"/>
        <a href="${logoutURI}" >Logout</a>
    </c:when>
    <c:otherwise>
        <form action="/login" method="post">
            Email: <input type="text" name="userEmail" value=""> <br/>
            Password: <input type="password" name="password" value="" /> <br/>
            <button type="submit" value="Login" >Log in</button>
        </form>
    </c:otherwise>
</c:choose>

</body>
</html>
