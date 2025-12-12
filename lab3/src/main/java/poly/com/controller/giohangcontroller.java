package poly.com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import poly.com.model.CartItem1;
import poly.com.model.Country;
import poly.com.model.Items;
@WebServlet("/giohang")
public class giohangcontroller extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 HttpSession session = req.getSession(); // Lấy session
	        List<CartItem1> cartItems = (List<CartItem1>) session.getAttribute("cartItems"); // Lấy giỏ hàng từ session

	        // Nếu giỏ hàng chưa có, khởi tạo mới
	        if (cartItems == null) {
	            cartItems = new ArrayList<>();
	        }

	        // Tạo danh sách sản phẩm
	        List<Items> items = new ArrayList<>();
	        items.add(new Items("Nokia 2020", "1.jpg", 500, 0.1));
	        items.add(new Items("Samsung Xyz", "2.jpg", 700, 0.15));
	        items.add(new Items("iPhone Xy", "3.jpg", 900, 0.25));
	        items.add(new Items("Sony Erricson", "4.jpg", 55, 0.3));

	        req.setAttribute("items", items);
		req.getRequestDispatcher("cart.jsp").forward(req, resp);
	}

}