<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../index.jsp"></jsp:include>
<c:url value="/calculate" var="cal"/>

<form method="post">
    <input name="a"><br>
    <input name="b"><br>

    <button formaction="${cal}/add">+</button>
    <button formaction="${cal}/sub">-</button>
</form>

${message}

</body>
</html>