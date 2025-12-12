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
<title>Danh sách sản phẩm</title>
</head>
<body>

<jsp:include page="index.jsp"></jsp:include>

<h2>Danh sách sản phẩm</h2>

<table border="1">
  <thead>
    <tr>
      <th>Tên sản phẩm</th>
      <th>Hình ảnh</th>
      <th>Giá</th>
      <th>Khuyến mãi</th>
      <th>Chi tiết</th>
    </tr>
  </thead>

  <tbody>
    <c:forEach var="item" items="${items}">
      <tr>
        <!-- TÊN -->
        <td>${item.name}</td>

        <!-- ẢNH -->
        <td>
          <img src="${pageContext.request.contextPath}/img/${item.image}"
               alt="${item.name}" width="100"/>
        </td>

        <!-- GIÁ -->
        <td>
          <fmt:formatNumber value="${item.price}" type="currency" currencySymbol="$"/>
        </td>

        <!-- KHUYẾN MÃI -->
        <td>
          <fmt:formatNumber value="${item.discount * 100}" type="number"/>%
        </td>

        <!-- LINK CHI TIẾT -->
        <td>
          <a href="${pageContext.request.contextPath}/ProductDetailServlet?id=${item.name}">
            Xem chi tiết
          </a>
        </td>

      </tr>
    </c:forEach>
  </tbody>
</table>

</body>
</html>
