<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Employees"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>Quản lý nhân viên — MOSHIMOSHI</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/base.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/menu.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/index.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/employee_Management.css" />
<!-- override inputs last to force styles -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/override-fields.css" />

</head>
<body>
	<jsp:include page="menu.jsp" />

	<main class="main-app">
		<div class="page-wrap">
			<header class="top-header">
				<h1>Quản lý nhân viên</h1>
				<p class="subtitle">Thêm · Sửa · Xóa · Tìm kiếm — TS02418_Lab06</p>
			</header>

			<section class="layout">
				<aside class="card glass form-card">
					<%
					Employees formEmp = (Employees) request.getAttribute("employee");
					String formAction = (String) request.getAttribute("formAction");
					if (formAction == null) {
						formAction = (formEmp != null && formEmp.getId() != null && !formEmp.getId().isEmpty()) ? "update" : "insert";
					}
					%>

					<div class="card-head">
						<h2 class="card-title"><%="insert".equals(formAction) ? "Thêm nhân viên" : "Sửa nhân viên"%></h2>
						<span class="card-note"><%="insert".equals(formAction) ? "Thêm tài khoản nhân viên mới" : "Cập nhật thông tin nhân viên"%></span>
					</div>

					<c:if test="${not empty error}">
						<div class="alert error-pill">${error}</div>
					</c:if>

					<form method="post"
						action="${pageContext.request.contextPath}/employees"
						class="emp-form" enctype="multipart/form-data">
						<input type="hidden" name="action" value="<%=formAction%>" />

						<div class="field">
							<label for="id">ID</label> <input id="id" name="id"
								maxlength="20" required
								value="<%=formEmp != null && formEmp.getId() != null ? formEmp.getId() : ""%>"
								<%="update".equals(formAction) ? "readonly" : ""%> />
						</div>

						<div class="field">
							<label for="password">Mật khẩu</label> <input id="password"
								type="password" name="password" maxlength="50"
								value="<%=formEmp != null && formEmp.getPassword() != null ? formEmp.getPassword() : ""%>"
								<%="insert".equals(formAction) ? "required" : ""%> />
						</div>

						<div class="field">
							<label for="fullname">Họ và tên</label> <input id="fullname"
								name="fullname" maxlength="50" required
								value="<%=formEmp != null && formEmp.getFullname() != null ? formEmp.getFullname() : ""%>" />
						</div>

						<div class="field">
							<label for="photoFile">Ảnh hồ sơ</label> <input id="photoFile"
								type="file" name="photoFile" accept="image/*" />
							<c:if test="${formEmp != null && not empty formEmp.photo}">
								<div class="current-photo">
									<img
										src="${pageContext.request.contextPath}/uploads/${formEmp.photo}"
										alt="${formEmp.fullname}" />
								</div>
								<input type="hidden" name="existingPhoto"
									value="${formEmp.photo}" />
							</c:if>
						</div>

						<div class="field">
							<label for="gender">Giới tính</label> <select id="gender"
								name="gender" required>
								<option value="">-- Chọn --</option>
								<option value="1"
									${formEmp != null && "1".equals(formEmp.getGender()) ? "selected" : ""}>Nam</option>
								<option value="0"
									${formEmp != null && "0".equals(formEmp.getGender()) ? "selected" : ""}>Nữ</option>
							</select>
						</div>

						<div class="field">
							<label for="birthDay">Ngày sinh</label> <input id="birthDay"
								type="date" name="birthDay"
								value="<%=formEmp != null && formEmp.getBirthDay() != null ? formEmp.getBirthDay() : ""%>" />
						</div>

						<div class="field">
							<label for="salary">Lương</label> <input id="salary"
								type="number" step="0.01" name="salary"
								value="<%=formEmp != null && formEmp.getSalary() != null ? formEmp.getSalary() : ""%>" />
						</div>

						<div class="field">
							<label for="departmentId">Mã phòng ban</label> <input
								id="departmentId" name="departmentId" maxlength="3" required
								value="<%=formEmp != null && formEmp.getDepartmentId() != null ? formEmp.getDepartmentId() : ""%>" />
						</div>

						<div class="form-actions">
							<button type="submit" class="btn primary">
								<%="insert".equals(formAction) ? "Thêm" : "Cập nhật"%>
							</button>

							<c:if test="${formAction == 'update' || param.action == 'edit'}">
								<a class="btn neutral"
									href="${pageContext.request.contextPath}/employees?action=list">Hủy</a>
							</c:if>

							<button type="reset" class="btn ghost">Xóa rỗng</button>
						</div>
					</form>
				</aside>

				<div class="card glass table-card">
					<div class="table-head">
						<form method="get"
							action="${pageContext.request.contextPath}/employees"
							class="search-row">
							<input type="hidden" name="action" value="search" /> <input
								name="keyword" class="search-input"
								placeholder="Tìm theo id, tên, phòng ban..."
								value="${param.keyword != null ? param.keyword : ''}" />
							<div class="search-actions">
								<button type="submit" class="btn neutral">Tìm</button>
								<a class="btn ghost"
									href="${pageContext.request.contextPath}/employees?action=list">Reset</a>
							</div>
						</form>
					</div>

					<div class="table-wrap">
						<table class="table">
							<thead>
								<tr>
									<th class="col-id">ID</th>
									<th class="col-thumb">Ảnh</th>
									<th>Họ và tên</th>
									<th>Giới tính</th>
									<th>Ngày sinh</th>
									<th>Lương</th>
									<th>Phòng ban</th>
									<th class="col-actions">Hành động</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${empty employeeList}">
										<tr>
											<td colspan="8" class="empty">Không có dữ liệu</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach var="e" items="${employeeList}">
											<tr>
												<td class="kicker">${e.id}</td>
												<td class="thumb-cell"><c:choose>
														<c:when test="${not empty e.photo}">
															<img class="thumb"
																src="${pageContext.request.contextPath}/uploads/${e.photo}"
																alt="${e.fullname}" />
														</c:when>
														<c:otherwise>
															<div class="thumb placeholder">No</div>
														</c:otherwise>
													</c:choose></td>
												<td class="name-col">${e.fullname}</td>
												<td><c:out value="${e.gender == '1' ? 'Nam' : 'Nữ'}" /></td>
												<td>${e.birthDay}</td>
												<td>${e.salary}</td>
												<td>${e.departmentId}</td>

												<td class="actions-cell">
													<div class="action-group">
														<a class="btn edit"
															href="${pageContext.request.contextPath}/employees?action=edit&id=${e.id}">Sửa</a>
														<form method="post"
															action="${pageContext.request.contextPath}/employees"
															style="display: inline;">
															<input type="hidden" name="action" value="delete" /> <input
																type="hidden" name="id" value="${e.id}" />
															<button type="submit" class="btn delete">Xóa</button>
														</form>
													</div>
												</td>
											</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</div>
				</div>
			</section>
		</div>
	</main>
</body>
</html>
