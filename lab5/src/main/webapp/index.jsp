<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>


<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">

<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
/* Thanh menu */
.menu {
    text-align: center;
    margin: 20px 0;
    font-size: 18px;
}

/* Link */
.menu a {
    text-decoration: none;
    padding: 8px 14px;
    color: #2563eb;
    font-weight: 600;
    transition: 0.2s;
}

/* Hover */
.menu a:hover {
    color: #1e40af;
    text-decoration: underline;
}

/* Dấu phân cách */
.sep {
    margin: 0 6px;
    color: #6b7280;
}
</style>
</head>
<body>

<div class="menu">
  <a href="login">bài 1</a><span class="sep">|</span>
  <a href="add">bài 2</a><span class="sep">|</span>
  <a href="mail">bài 3</a><span class="sep">|</span>
</div>

</body>
</html>