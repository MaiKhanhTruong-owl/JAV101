<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%-- set locale from session (LanguageFilter lưu Locale vào sessionScope.lang) --%>
<fmt:setLocale value="${sessionScope.lang}" scope="session" />
<fmt:setBundle basename="i18n/global" />

<c:set var="uri" value="${pageContext.request.requestURI}" />

<nav class="sidebar">
	<div class="brand">
		<div class="logo">🧭</div>
		<div class="brand-text">
			<strong>Hệ thống văn phòng</strong> <small>Admin Panel</small>
		</div>
	</div>

	<ul class="menu">
		<li class="menu-item ${fn:contains(uri,'/dashboard') ? 'active' : ''}">
			<a href="${pageContext.request.contextPath}/dashboard"><fmt:message
					key="menu.home" /></a>
		</li>

		<li
			class="menu-item ${fn:contains(uri,'/departments') || fn:contains(uri,'department_Management') ? 'active' : ''}">
			<a href="${pageContext.request.contextPath}/departments?action=list"><fmt:message
					key="menu.department" /></a>
		</li>

		<li
			class="menu-item ${fn:contains(uri,'/employees') || fn:contains(uri,'employee_Management') ? 'active' : ''}">
			<a href="${pageContext.request.contextPath}/employees?action=list"><fmt:message
					key="menu.user" /></a>
		</li>
	</ul>

	<div class="sidebar-footer">
		<h3>
			<a href="${pageContext.request.contextPath}/login.jsp"><fmt:message
					key="menu.logout" /></a>
		</h3>
		<small>Đăng nhập: <strong>admin</strong></small>

		<div class="lang-switch" style="margin-top: 12px;">
			<a href="${pageContext.request.contextPath}/lang?lang=vi"
				class="lang-btn ${sessionScope.lang != null && sessionScope.lang.language == 'vi' ? 'active' : ''}">
				TIẾNG VIỆT </a> | <a
				href="${pageContext.request.contextPath}/lang?lang=en"
				class="lang-btn ${sessionScope.lang != null && sessionScope.lang.language == 'en' ? 'active' : ''}">
				ENGLISH </a>
		</div>

	</div>
</nav>
