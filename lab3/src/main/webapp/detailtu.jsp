<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi tiết sản phẩm</title>

    <style>
        /* ===== STYLE TỐI ƯU CHO TRANG CHI TIẾT ===== */
        body {
            font-family: Arial, sans-serif;
            color: white;
            background: #111;
            margin: 0;
            padding: 0;
            text-align: center;
        }

        .detail-container {
            max-width: 900px;
            margin: 40px auto;
            background: #1a1a1a;
            padding: 25px;
            border-radius: 12px;
            box-shadow: 0 0 15px rgba(0,0,0,0.5);
        }

        .card {
            display: flex;
            gap: 30px;
            justify-content: center;
            align-items: center;
            flex-wrap: wrap;
        }

        .card img {
            border-radius: 10px;
            max-width: 350px;
            height: auto;
            box-shadow: 0 0 10px rgba(255,255,255,0.1);
        }

        .card-content {
            max-width: 400px;
            text-align: left;
        }

        .card-title {
            font-size: 28px;
            font-weight: bold;
            margin-bottom: 15px;
            color: #4da3ff;
        }

        .price-box {
            font-size: 20px;
            margin-bottom: 20px;
        }

        strike {
            opacity: 0.6;
            margin-right: 10px;
        }

        a.back-btn {
            display: inline-block;
            padding: 10px 18px;
            background: #4da3ff;
            color: black;
            border-radius: 6px;
            text-decoration: none;
            font-weight: bold;
        }

        a.back-btn:hover {
            background: #2b78d4;
            transform: translateY(-2px);
        }
    </style>
</head>

<body>

    <jsp:include page="index.jsp"></jsp:include>

    <div class="detail-container">

        <c:forEach var="item" items="${selectedItems}">
            <div class="card">

                <img src="/lab3/img/${item.image}" alt="${item.name}">

                <div class="card-content">

                    <h5 class="card-title">${item.name}</h5>

                    <div class="price-box">
                        <strike>
                            <fmt:formatNumber value="${item.price}" type="currency" currencySymbol="$" />
                        </strike>

                        <span style="color:#0f0;">
                            <fmt:formatNumber value="${item.price * (1 - item.discount)}" type="currency" currencySymbol="$" />
                        </span>
                    </div>

                    <a href="/lab3/nangcao" class="back-btn">← Trở về</a>

                </div>
            </div>
        </c:forEach>

    </div>

</body>
</html>
