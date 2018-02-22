<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="style.css" />
<link type="text/css" rel="stylesheet" href="bootstrap.min.css" />
<title>Welcome !</title>
</head>
<body>
<div id="main-page">
	<div id="content" class="rounded">
	<c:choose>
    <c:when test="${ empty sessionScope.user }">
    	<a href="<c:url value = "register"/>">Register</a> &nbsp;|&nbsp;<a href="<c:url value = "login"/>">Login</a>
    </c:when>
    <c:otherwise>
    	 <c:out value="${ user }" />&nbsp;|&nbsp;<a href="<c:url value = "logout"/>">Logout</a>
    </c:otherwise>
</c:choose>
	</div>
</div>

</body>
</html>