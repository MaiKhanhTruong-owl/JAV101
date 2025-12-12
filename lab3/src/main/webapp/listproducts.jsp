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
  <jsp:include page="index.jsp"></jsp:include>
<center>
<table border="1">
    <thead>
        <tr>
            <th>Tên sản phẩm</th>
            <th>Hình ảnh</th>
            <th>Giá</th>
            <th>Khuyến mãi</th>
            <th>Chi tiết </th>
            <th>Button </th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${items}">
            <tr>
                <td>${item.name}</td> <!-- Tên sản phẩm -->
                <td>
                    <img onClick="hamchitiet('${item.name}')" src="${pageContext.request.contextPath}/img/${item.image}" alt="${item.name}" width="100" />
                </td> <!-- Hình ảnh -->
                <td><fmt:formatNumber value="${item.price}" type="currency" currencySymbol="$" /></td> <!-- Giá -->
                <td><fmt:formatNumber value="${item.discount * 100}" type="number" />%</td> <!-- Khuyến mãi -->
                <td><a href="ProductDetailServlet?id=${item.name}">chi tiết </a> </td>
                <td>
				   <form method="get">
	    			<input type="hidden" name="id" value="${item.name}">
	    			<button formaction="/Lab3/ProductDetailServlet">view details</button>
					</form>
					
					<form method="post">
					<h2></h2><button formaction="/Lab3/nutthem" >khánh trường</button></h2>
					</form>
				</td> 
            </tr>
        </c:forEach>
    </tbody>
</table>

<table>
    <thead>
        <tr>
            <th>Số thứ tự</th>
            <th>Tên</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="ten" items="${ds}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td> <!-- Số thứ tự bắt đầu từ 1 -->
                <td>${ten}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<hr/>

</center>
<script>
    function hamchitiet(itemId) {
        window.location.href = "ProductDetailServlet?id=" + itemId;
    }
  
  
</script>
</body>
</html>