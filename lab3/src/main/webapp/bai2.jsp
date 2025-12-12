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
<body>
<jsp:include page="index.jsp"></jsp:include>

<table> 
 <thead> 
  <tr> 
   <th>No.</th> 
   <th>Id</th> 
   <th>Name</th> 
  </tr> 
 </thead> 
 <tbody> 
  <c:forEach var="ct" items="${countries}" varStatus="vs"> 
  <tr> 
   <td>${vs.count}</td> 
   <td>${ct.id}</td> 
   <td>${ct.name}</td> 
  </tr> 
  </c:forEach> 
 </tbody> 
</table>
</body>
</html>