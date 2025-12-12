<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Kết quả đăng ký</title>

<style>
    body { font-family: Arial; background:#f6f8fb; padding:20px; }
    .box { max-width:720px; margin:0 auto; background:#fff; padding:18px;
           border-radius:8px; border:1px solid #eee; }
    h1 { color:#0b74de; }
    .row { margin:10px 0; }
    .label { font-weight:700; width:160px; display:inline-block; }
    img { border:1px solid #ddd; padding:6px; border-radius:6px; }
</style>
</head>

<body>
<div class="box">

    <h1>Kết quả đăng ký</h1>

    <p><strong>Tên Đăng Nhập:</strong> ${fullname}</p>
    <p><strong>Mật Khẩu:</strong> ${password}</p>

    <p>
        <strong>Giới Tính:</strong>
        <c:choose>
            <c:when test="${gender == 'Nam'}">Nam</c:when>
            <c:otherwise>Nữ</c:otherwise>
        </c:choose>
    </p>

    <p><strong>Đã có gia đình:</strong> ${married}</p>

    <p><strong>Quốc gia:</strong> ${country}</p>

    <p><strong>Sở thích:</strong>
        <c:if test="${not empty hobbies}">
            <ul>
                <c:forEach var="hobby" items="${hobbies}">
                    <li>${hobby}</li>
                </c:forEach>
            </ul>
        </c:if>

        <c:if test="${empty hobbies}">
            Không có sở thích được chọn
        </c:if>
    </p>

    <p><strong>Ghi chú:</strong>
        <c:if test="${not empty note}">
            ${note}
        </c:if>
        <c:if test="${empty note}">
            Không có ghi chú
        </c:if>
    </p>

    <p><strong>Ảnh đại diện:</strong></p>

    <c:if test="${not empty photoURL}">
        <img src="${pageContext.request.contextPath}/${photoURL}"
             alt="Ảnh đại diện" style="max-width:200px;" />
    </c:if>

    <c:if test="${empty photoURL}">
        <p>Không có ảnh được upload</p>
    </c:if>

    <br><br>

    <!-- Link quay lại -->
    <a href="${pageContext.request.contextPath}/form.jsp">Trở lại trang chủ</a>

</div>
</body>
</html>
