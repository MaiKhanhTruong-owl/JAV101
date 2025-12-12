<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <title>Employee Management</title>
  <style>
    /* ===== RESET + ROOT ===== */
*{
  box-sizing: border-box;
  margin:0;
  padding:0;
  font-family: "Segoe UI", Arial, sans-serif;
}

:root{
  --bg:#f2f4f7;
  --card:#ffffff;
  --accent:#2563eb;
  --accent-dark:#1d4ed8;
  --text:#1e293b;
  --border:#dce1e8;
  --radius:10px;
  --shadow:0 3px 10px rgba(0,0,0,0.08);
}

body{
  background: var(--bg);
  padding:30px;
  color:var(--text);
}

/* ===== TITLE ===== */
h2{
  margin-bottom:20px;
  font-size:28px;
  font-weight:700;
  color:var(--accent-dark);
}

h3{
  margin:25px 0 15px;
  font-size:20px;
  font-weight:600;
}

/* ===== FORM CARD ===== */
form{
  background:var(--card);
  padding:20px;
  border-radius:var(--radius);
  box-shadow:var(--shadow);
  margin-bottom:25px;
}

form div{
  margin-bottom:15px;
  display:flex;
  align-items:center;
}

label{
  width:150px;
  font-weight:600;
  color:#334155;
}

input[type=text],
input[type=date],
input[type=number],
input[type=file]{
  padding:10px;
  border:1px solid var(--border);
  border-radius:var(--radius);
  width:280px;
  transition:0.2s;
}

input:focus{
  border-color:var(--accent);
  outline:none;
  box-shadow:0 0 0 3px rgba(37,99,235,0.25);
}

/* ===== BUTTON ===== */
.btn, button{
  padding:10px 18px;
  border:none;
  cursor:pointer;
  background:var(--accent);
  color:white;
  border-radius:var(--radius);
  font-size:14px;
  font-weight:600;
  margin-right:8px;
  transition:0.2s;
}

.btn:hover, button:hover{
  background:var(--accent-dark);
}

a.btn{
  text-decoration:none;
  display:inline-block;
}

/* Delete button màu đỏ */
a[href*="delete"]{
  background:#dc2626 !important;
}
a[href*="delete"]:hover{
  background:#b91c1c !important;
}

/* ===== TABLE ===== */
table{
  width:100%;
  border-collapse:collapse;
  background:var(--card);
  box-shadow:var(--shadow);
  border-radius:var(--radius);
  overflow:hidden;
}

thead{
  background:var(--accent);
  color:white;
}

th,td{
  padding:12px 15px;
  border-bottom:1px solid var(--border);
}

tbody tr:hover{
  background:#eef2ff;
}

td a{
  color:var(--accent);
  font-weight:600;
  text-decoration:none;
}

td a:hover{
  text-decoration:underline;
}

/* ===== IMAGE ===== */
img.photo{
  width:70px;
  height:70px;
  object-fit:cover;
  border-radius:var(--radius);
  border:1px solid var(--border);
}

  </style>
</head>
<body>
  <h2>Employee Management</h2>

  <h3>Add New Employee/update</h3>
  <form action="${pageContext.request.contextPath}/employees" method="post" enctype="multipart/form-data">
    <div>
      <label>ID:</label><input type="text" name="id" value="${editEmp.id}"/>
    </div>
    <div>
      <label>Password:</label><input type="text" name="password" value="${editEmp.password}"/>
    </div>
    <div>
      <label>Full Name:</label><input type="text" name="fullname" value="${editEmp.fullname}"/>
    </div>
    <div>
      <label>Gender:</label><input type="checkbox" name="gender" ${editEmp.gender ? "checked":""}/>
    </div>
    <div>
      <label>Birthday:</label><input type="date" name="birthday" value="${editEmp.birthday}"/>
    </div>
    <div>
      <label>Salary:</label><input type="number" step="0.01" name="salary" value="${editEmp.salary}"/>
    </div>
    <div>
      <label>Department ID:</label><input type="text" name="departmentId" value="${editEmp.departmentId}"/>
    </div>
    <div>
      <label>Photo:</label><input type="file" name="photo"/>
      <c:if test="${not empty editEmp.photo}">
        <img src="${pageContext.request.contextPath}${editEmp.photo}" class="photo"/>
      </c:if>
    </div>

    <div>
      <button class="btn" name="action" value="add">Add Employee</button>
      <button class="btn" name="action" value="update">Update Employee</button>
      <a href="${pageContext.request.contextPath}/employees?action=delete&id=${editEmp.id}" class="btn" onclick="return confirm('Delete?');">Delete Employee</a>
    </div>
  </form>

  <h3>Search Employee</h3>
  <form action="${pageContext.request.contextPath}/employees" method="get">
    <label>Employee ID to Search:</label>
    <input type="text" name="keyword" />
    <button type="submit" name="action" value="search">Search</button>
  </form>

  <h3>Employee List</h3>
  <table>
    <thead>
      <tr><th>ID</th><th>Full Name</th><th>Gender</th><th>Birthday</th><th>Salary</th><th>Department ID</th><th>Photo</th><th>Actions</th></tr>
    </thead>
    <tbody>
      <c:forEach var="e" items="${employees}">
        <tr>
          <td>${e.id}</td>
          <td>${e.fullname}</td>
          <td><c:choose><c:when test="${e.gender}">Male</c:when><c:otherwise>Female</c:otherwise></c:choose></td>
          <td>${e.birthday}</td>
          <td>${e.salary}</td>
          <td>${e.departmentId}</td>
          <td>
            <c:if test="${not empty e.photo}">
              <img src="${pageContext.request.contextPath}${e.photo}" class="photo"/>
            </c:if>
          </td>
          <td>
            <a href="${pageContext.request.contextPath}/employees?action=edit&id=${e.id}">Edit</a> |
            <a href="${pageContext.request.contextPath}/employees?action=delete&id=${e.id}" onclick="return confirm('Delete?');">Delete</a>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</body>
</html>
