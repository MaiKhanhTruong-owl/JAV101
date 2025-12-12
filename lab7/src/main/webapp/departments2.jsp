<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <title>Danh sách Phòng Ban</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: white;
            text-align: center;
        }

        h1 {
            margin-top: 30px;
            font-size: 32px;
            font-weight: bold;
        }

        input[type="text"] {
            padding: 8px;
            width: 250px;
            margin: 5px;
            border: 1px solid gray;
            border-radius: 3px;
        }

        button {
            padding: 8px 15px;
            margin: 5px;
            border: 1px solid gray;
            background: #eee;
            cursor: pointer;
            border-radius: 3px;
        }

        button:hover {
            background: #ddd;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            text-align: center;
        }

        th, td {
            padding: 12px;
            border: 1px solid #ccc;
        }

        th {
            background: #f7f7f7;
        }

        a {
            color: blue;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>

    <h1>Danh sách Phòng Ban</h1>

    <!-- FORM CHÍNH -->
    <form action="${pageContext.request.contextPath}/departments2" method="post">

        <div>
            <label>Mã Phòng ban:</label><br>
            <input type="text" name="id" value="${editDept.id}">
        </div>

        <div>
            <label>Tên phòng ban:</label><br>
            <input type="text" name="name" value="${editDept.name}">
        </div>

        <div>
            <label>Mô tả:</label><br>
            <input type="text" name="description" value="${editDept.description}">
        </div>

        <!-- ==== DÃY NÚT 1 (trên phần tìm kiếm) ===== -->
     

    </form>

    <!-- ==== FORM TÌM KIẾM + DÃY NÚT 2 ===== -->
    <form action="${pageContext.request.contextPath}/departments2" method="get">
        
        <input type="text" name="keyword" placeholder="tìm" style="width:200px;">
        
        <!-- DÃY NÚT 2 -->
        <div>
            <button name="action" value="update">Cập nhật</button>
            <button name="action" value="delete">Xóa</button>
            <button name="action" value="add">Thêm Mới</button>
            <button name="action" value="search">Tìm</button>
        </div>

    </form>

    <!-- BẢNG DỮ LIỆU -->
    <table>
        <thead>
            <tr>
                <th>Mã phòng ban</th>
                <th>Tên phòng ban</th>
                <th>Mô tả</th>
                <th>Hành động</th>
            </tr>
        </thead>

        <tbody>
            <c:forEach var="d" items="${departments}">
                <tr>
                    <td>${d.id}</td>
                    <td>${d.name}</td>
                    <td>${d.description}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/departments2?action=edit&id=${d.id}">Sửa</a> |
                        <a href="${pageContext.request.contextPath}/departments2?action=delete&id=${d.id}"
                           onclick="return confirm('Bạn chắc chắn muốn xóa?');">
                          Xóa
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
