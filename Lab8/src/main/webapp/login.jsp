<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css">
</head>
<body>


    <div class="page-center">
        <div class="form_login" role="main" aria-labelledby="login-title">

            <!-- simple brand -->
            

            <!-- message -->
            <c:if test="${not empty message}">
                <div class="msg error">${message}</div>
            </c:if>

            <!-- form -->
            <form action="login" method="post" autocomplete="off" novalidate>
                <p>Nhập username:</p>
                <input type="text" name="username" value="${username}" placeholder="Nhập username">

                <p>Nhập password:</p>
                <input type="password" name="password" placeholder="Nhập password">

                <!-- checkbox inline (KEEP name="rememberMe") -->
                <div class="row">
                    <label class="checkbox">
                                         <span class="chk-label">Remember me?</span>       <input type="checkbox" name="rememberMe" ${rememberChecked}/>

                    </label>


                </div>

                <button type="submit">Login</button>


            </form>
        </div>
    </div>
</body>
</html>
