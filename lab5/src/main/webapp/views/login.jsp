<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f0f2f5; display: flex; align-items: center; justify-content: center; height: 100vh; }
        .card { box-shadow: 0 4px 8px rgba(0,0,0,0.1); border: none; }
        .btn-primary { width: 100%; }
    </style>
</head>
<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-4">
            <div class="card p-4">
                <h2 class="text-center mb-4 text-primary">Login</h2>
                
                <c:if test="${not empty message}">
                    <div class="alert alert-danger" role="alert">${message}</div>
                </c:if>

                <form action="/lab5/login" method="post">
                    <div class="mb-3">
                        <label class="form-label">Username</label>
                        <input name="username" value="${username}" type="text" class="form-control" placeholder="Enter username" />
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Password</label>
                        <input name="password" value="${password}" type="password" class="form-control" placeholder="Enter password" />
                    </div>

                    <div class="mb-3 form-check">
                        <input name="remember" type="checkbox" class="form-check-input" id="chkRemember" />
                        <label class="form-check-label" for="chkRemember">Remember me?</label>
                    </div>

                    <button type="submit" class="btn btn-primary">Login</button>
                </form>
                <div class="text-center mt-3">
                    <a href="/lab5" class="text-decoration-none">← Quay lại trang chủ</a>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>