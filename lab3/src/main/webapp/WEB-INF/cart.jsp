<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Danh sách sản phẩm</h1>
	<table border="1">
		<thead>
			<tr>
				<th>Tên sản phẩm</th>
				<th>Hình ảnh</th>
				<th>Giá</th>
				<th>Khuyến mãi</th>
				<th>Thao tác</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${items}">
				<tr>
					<td>${item.name}</td>

					<td><img
						src="${pageContext.request.contextPath}/img/${item.image}"
						alt="${item.name}" width="100%"></td>

					<td><fmt:formatNumber value="${item.price}" type="currency"
							currencySymbol="$" /></td>

					<td><fmt:formatNumber value="${item.discount * 100}"
							type="number" />%</td>

					<td>
						<form action="CartController" method="get">
							<input type="hidden" name="action" value="add"> <input
								type="hidden" name="itemName" value="${item.name}"> <label>Số
								lượng:</label> <input type="number" name="quantity" min="1" required
								value="1"> <input type="submit" value="Thêm vào giỏ">
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>