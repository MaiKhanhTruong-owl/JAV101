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
<body class="bai4">

<jsp:include page="index.jsp"></jsp:include>

<ul> 
 <li>Title: ${fn:toUpperCase(item.title)}</li> 
 <li>Content:  
  <c:choose> 
   <c:when test="${fn:length(item.content) > 100}"> 
    ${fn:substring(item.content, 0, 100)}... 
   </c:when> 
   <c:otherwise>${item.content}</c:otherwise> 
  </c:choose> 
 </li> 
</ul>

</body>
</html>