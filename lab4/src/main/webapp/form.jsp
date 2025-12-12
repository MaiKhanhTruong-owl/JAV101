<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Form đăng ký + upload</title>
  <style>
    body{ font-family: Arial, Helvetica, sans-serif; background:#f4f6fb; padding:20px; }
    .container{ max-width:720px; margin:0 auto; background:#fff; padding:20px; border-radius:8px; box-shadow:0 6px 18px rgba(0,0,0,0.06); }
    h2{ color:#0b74de; margin-top:0; }
    .row{ display:flex; gap:12px; align-items:center; margin:10px 0; }
    .row label{ width:140px; font-weight:600; }
    .row input[type="text"], .row input[type="password"], .row select, .row textarea { flex:1; padding:8px; border:1px solid #e0e6ef; border-radius:6px; }
    .row textarea{ min-height:90px; }
    .small{ width:auto; font-weight:500; }
    .hobby{ display:flex; gap:12px; flex-wrap:wrap; }
    .btn{ background:#0b74de; color:#fff; padding:10px 14px; border:0; border-radius:6px; cursor:pointer; font-weight:600; }
    hr{ border:0; border-top:1px solid #eee; margin:18px 0; }
    @media(max-width:560px){ .row{ flex-direction:column; align-items:flex-start } .row label{ width:100% } }
  </style>
</head>
<body>
  <div class="container">
    <h2>Đăng Ký</h2>

    <c:url value="/add" var="addUrl" />

    <!-- enctype cần thiết để upload file -->
    <form action="${addUrl}" method="post" enctype="multipart/form-data">
      <div class="row">
        <label for="fullname">Tên đăng nhập:</label>
        <input id="fullname" name="fullname" type="text" value="nghiemm">
      </div>

      <div class="row">
        <label for="password">Mật khẩu:</label>
        <input id="password" name="password" type="password" value="123456">
      </div>

      <div class="row">
        <label>Giới tính:</label>
        <div class="small">
          <label><input type="radio" name="gender" value="Nam" checked> Nam</label>
          <label><input type="radio" name="gender" value="Nu"> Nữ</label>
        </div>
      </div>

      <div class="row">
        <label></label>
        <label class="small"><input type="checkbox" name="married"> Đã có gia đình?</label>
      </div>

      <div class="row">
        <label for="country">Quốc tịch:</label>
        <select id="country" name="country">
          <option>United States</option>
          <option>Vietnam</option>
          <option>Japan</option>
        </select>
      </div>

      <div class="row">
        <label>Sở thích:</label>
        <div class="hobby">
          <label><input type="checkbox" name="hobbies" value="Doc sach"> Đọc sách</label>
          <label><input type="checkbox" name="hobbies" value="Du lich" checked> Du lịch</label>
          <label><input type="checkbox" name="hobbies" value="Am nhac" checked> Âm nhạc</label>
          <label><input type="checkbox" name="hobbies" value="Khac"> Khác</label>
        </div>
      </div>

      <div class="row" style="align-items:flex-start;">
        <label for="note">Ghi chú:</label>
        <textarea id="note" name="note">Dang tim ban gai</textarea>
      </div>

      <hr>

      <h3>Upload ảnh đại diện</h3>
      <div class="row">
        <label for="photo">Chọn ảnh:</label>
        <!-- Tên field phải trùng với servlet: "photo_file" -->
        <input id="photo" name="photo_file" type="file" accept="image/*">
      </div>

      <div style="margin-top:12px;">
        <button type="submit" class="btn">Gửi</button>
      </div>
    </form>
  </div>
</body>
</html>
