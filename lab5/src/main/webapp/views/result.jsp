<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Staff Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f0f2f5; padding-top: 50px; }
        .card-profile { max-width: 500px; margin: auto; border: none; box-shadow: 0 10px 20px rgba(0,0,0,0.1); border-radius: 15px; overflow: hidden; }
        .card-header-img { background: #0d6efd; height: 100px; }
        .avatar-container { margin-top: -50px; text-align: center; }
        .avatar { width: 120px; height: 120px; object-fit: cover; border-radius: 50%; border: 5px solid white; box-shadow: 0 5px 10px rgba(0,0,0,0.2); background: #fff; }
    </style>
</head>
<body>

<div class="card card-profile">
    <div class="card-header-img"></div> <div class="card-body">
        <div class="avatar-container">
             <c:choose>
                <c:when test="${not empty bean.photo_file}">
                    <img src="/lab5/file/${bean.photo_file}" class="avatar" alt="Avatar">
                </c:when>
                <c:otherwise>
                    <img src="https://cdn-icons-png.flaticon.com/512/3135/3135715.png" class="avatar" alt="Default">
                </c:otherwise>
            </c:choose>
        </div>

        <h3 class="text-center mt-3">${bean.fullname}</h3>
        <p class="text-center text-muted">${bean.country}</p>

        <hr>

        <ul class="list-group list-group-flush">
            <li class="list-group-item d-flex justify-content-between">
                <strong>Birthday:</strong> <span>${bean.birthday}</span>
            </li>
            <li class="list-group-item d-flex justify-content-between">
                <strong>Gender:</strong> 
                <span>${bean.gender ? 'Male' : 'Female'}</span>
            </li>
            <li class="list-group-item d-flex justify-content-between">
                <strong>Status:</strong> 
                <span class="badge ${bean.married ? 'text-bg-warning' : 'text-bg-success'}">
                    ${bean.married ? 'Married' : 'Single'}
                </span>
            </li>
            <li class="list-group-item">
                <strong>Hobbies:</strong><br>
                <c:forEach items="${bean.hobbies}" var="h">
                    <span class="badge bg-secondary me-1">${h}</span>
                </c:forEach>
            </li>
            <li class="list-group-item">
                <strong>Note:</strong> <br> <i>${bean.note}</i>
            </li>
        </ul>

        <div class="d-grid mt-3">
            <a href="/lab5" class="btn btn-outline-primary">Back to Home</a>
        </div>
    </div>
</div>

</body>
</html>