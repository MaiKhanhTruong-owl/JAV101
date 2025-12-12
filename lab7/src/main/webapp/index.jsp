<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Trang chủ Lab 7</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: #0a0a0a;
            color: white;
            padding: 60px;
            text-align: center;
            font-family: Arial;
        }
        .btn-custom {
            width: 280px;
            padding: 14px;
            font-size: 18px;
            margin: 10px;
        }
    </style>
</head>
<body>

    <h1 class="mb-4">Chào mừng đến Lab 7</h1>
    <h4 class="mb-5">Chọn chức năng bên dưới:</h4>

    <!-- Nút 1 → Departments -->
    <a href="${pageContext.request.contextPath}/departments2"
       class="btn btn-primary btn-custom">
        ➤ Danh sách Phòng Ban
    </a>

    <br>

    <!-- Nút 2 → Employees -->
    <a href="${pageContext.request.contextPath}/employees"
       class="btn btn-success btn-custom">
        ➤ Quản lý Nhân Viên
    </a>

</body>
</html>
