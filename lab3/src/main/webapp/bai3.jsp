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
<body class="bai3">

<jsp:include page="index.jsp"></jsp:include>

<ul> 
 <li>Name: ${item.name}</li> 
 <li>Price:  
 <fmt:formatNumber value="${item.price}" pattern="#,###.00"/> 
 </li> 
 <li>Date:  
 <fmt:formatDate value="${item.date}" pattern="MMM dd, yyyy"/> 
 </li> 
</ul> 
</body>
</html>