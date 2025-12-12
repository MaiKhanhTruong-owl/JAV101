<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Department"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>Quản lý phòng ban — MOSHIMOSHI</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/base.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/menu.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/index.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/department_Management.css" />
<!-- override inputs last to force styles -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/override-fields.css" />

</head>
<body>
	<jsp:include page="menu.jsp" />

	<main class="main-app">
		<div class="page-wrap">
			<header class="top-header">
				<h1>Quản lý phòng ban</h1>
				<p class="subtitle">Thêm · Sửa · Xóa · Tìm kiếm — TS02418_Lab06</p>
			</header>

			<section class="layout">
				<aside class="card glass form-card">
					<%
					Department formDept = (Department) request.getAttribute("department");
					String formAction = (String) request.getAttribute("formAction");
					if (formAction == null) {
						formAction = (formDept != null && formDept.getId() != null && !formDept.getId().isEmpty()) ? "update" : "insert";
					}
					%>

					<div class="card-head">
						<h2 class="card-title"><%="insert".equals(formAction) ? "Thêm phòng ban" : "Sửa phòng ban"%></h2>
						<span class="card-note"><%="insert".equals(formAction) ? "Tạo phòng ban mới" : "Chỉnh sửa thông tin phòng ban"%></span>
					</div>

					<c:if test="${not empty error}">
						<div class="alert error-pill">${error}</div>
					</c:if>

					<form method="post"
						action="${pageContext.request.contextPath}/departments"
						class="dept-form">
						<input type="hidden" name="action" value="<%=formAction%>" />

						<div class="field">
							<label for="id">ID</label> <input id="id" name="id"
								maxlength="10" required
								value="<%=formDept != null && formDept.getId() != null ? formDept.getId() : ""%>"
								<%="update".equals(formAction) ? "readonly" : ""%> />
						</div>

						<div class="field">
							<label for="name">Tên phòng ban</label> <input id="name"
								name="name" maxlength="100" required
								value="<%=formDept != null && formDept.getName() != null ? formDept.getName() : ""%>" />
						</div>

						<div class="field">
							<label for="description">Mô tả</label>
							<textarea id="description" name="description" rows="5"
								maxlength="255"><%=formDept != null && formDept.getDescription() != null ? formDept.getDescription() : ""%></textarea>
						</div>

						<div class="form-actions">
							<button type="submit" class="btn primary">
								<%="insert".equals(formAction) ? "Thêm" : "Cập nhật"%>
							</button>

							<c:if test="${formAction == 'update' || param.action == 'edit'}">
								<a class="btn neutral"
									href="${pageContext.request.contextPath}/departments?action=list">Hủy</a>
							</c:if>

							<button type="reset" class="btn ghost">Làm mới</button>
						</div>
					</form>
				</aside>

				<div class="card glass table-card">
					<div class="table-head">
						<form method="get"
							action="${pageContext.request.contextPath}/departments"
							class="search-row">
							<input type="hidden" name="action" value="search" /> <input
								name="keyword" class="search-input"
								placeholder="Tìm theo id, tên hoặc mô tả"
								value="${param.keyword != null ? param.keyword : ''}" />
							<div class="search-actions">
								<button type="submit" class="btn neutral">Tìm</button>
								<a class="btn ghost"
									href="${pageContext.request.contextPath}/departments?action=list">Reset</a>
							</div>
						</form>
					</div>

					<div class="table-wrap">
						<table class="table">
							<thead>
								<tr>
									<th class="col-id">ID</th>
									<th>Tên</th>
									<th>Mô tả</th>
									<th class="col-actions">Hành động</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${empty departmentList}">
										<tr>
											<td colspan="4" class="empty">Không có dữ liệu</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach var="d" items="${departmentList}">
											<tr>
												<td class="kicker">${d.id}</td>
												<td class="name-col">${d.name}</td>
												<td class="muted-col">${d.description}</td>
												<td class="actions-cell">
													<div class="action-group">
														<a class="btn edit"
															href="${pageContext.request.contextPath}/departments?action=edit&id=${d.id}">Sửa</a>
														<form method="post"
															action="${pageContext.request.contextPath}/departments"
															style="display: inline;">
															<input type="hidden" name="action" value="delete" /> <input
																type="hidden" name="id" value="${d.id}" />
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
