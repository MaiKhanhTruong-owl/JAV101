<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>

<html>
<head>
    <title>Danh sách Phòng ban</title>
    <style>
        table { width: 100%; border-collapse: collapse; }
        th, td { border: 1px solid #ddd; padding: 8px; }
        th { background: #f4f4f4; }
        .btn { padding: 5px 10px; border: none; cursor: pointer; }
        .edit { background: orange; }
        .delete { background: red; color: white; }
        .add { background: blue; color: white; }
    </style>
</head>

<body>
<h2>Danh sách Phòng ban</h2>

<form action="${pageContext.request.contextPath}/departments" method="post">

    ID: <input name="id" value="${editDept.id}">
    Tên: <input name="name" value="${editDept.name}">
    Mô tả: <input name="description" value="${editDept.description}">

    <c:if test="${editDept == null}">
        <button class="btn add" name="action" value="add">Thêm</button>
    </c:if>

    <c:if test="${editDept != null}">
        <button class="btn edit" name="action" value="update">Cập nhật</button>
    </c:if>
</form>

<br>

<table>
    <tr>
        <th>ID</th>
        <th>Tên</th>
        <th>Mô tả</th>
        <th>Hành động</th>
    </tr>

    <c:forEach var="d" items="${departments}">
        <tr>
            <td>${d.id}</td>
            <td>${d.name}</td>
            <td>${d.description}</td>
            <td>
                <a href="departments?action=edit&id=${d.id}" class="btn edit">Sửa</a>
                <a href="departments?action=delete&id=${d.id}" class="btn delete">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
