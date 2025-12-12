<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>Dashboard - MOSHIMOSHI</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/base.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/menu.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/index.css" />
<!-- override inputs last to force styles -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/override-fields.css" />

</head>
<body>
	<jsp:include page="menu.jsp" />

	<main class="main-app">
		<div class="page-wrap">
			<header class="top-header">
				<h1>Dashboard</h1>
				<p class="subtitle">Tổng quan hệ thống — TS02418_Lab06</p>
			</header>

			<section class="cards">
				<article class="card glass stat">
					<div class="stat-title">Phòng ban</div>
					<div class="stat-value">3</div>
					<div class="stat-desc">Tổng số phòng ban</div>
				</article>

				<article class="card glass stat">
					<div class="stat-title">Nhân viên</div>
					<div class="stat-value">3</div>
					<div class="stat-desc">Tổng số nhân viên</div>
				</article>
			</section>

			<section class="panel card glass">
				<h2>Giới thiệu</h2>
				<p>Đây là Lab 6+7 môn JAV101 — project TS02418_Lab06.</p>
			</section>
		</div>
	</main>
</body>
</html>
