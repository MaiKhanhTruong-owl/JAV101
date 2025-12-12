<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>


<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
<title>Insert title here</title>
</head>
<body class="bai1">

<jsp:include page="index.jsp"></jsp:include>
<select name="Country">
<c:forEach var="ct" items="${countries}">
<option value="${ct.id}">${ct.name}</option>
</c:forEach>
</select>
</body>
</html>