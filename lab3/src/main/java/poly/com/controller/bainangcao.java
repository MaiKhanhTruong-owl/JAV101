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
import poly.com.model.Country;
import poly.com.model.Items;
@WebServlet("/nangcao")
public class bainangcao extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		
		ArrayList<Items> items = new ArrayList<>();
		items.add(new Items("Nokia 2020",  "1.jpg", 500, 0.1));
		items.add(new Items("Samsung Xyz", "2.jpg",  700, 0.15));
		items.add(new Items("iPhone Xy",   "3.jpg",  900, 0.25));
		items.add(new Items("Sony Erricson","4.jpg",  55, 0.3));
		items.add(new Items("Seamen",       "5.jpg",  70, 0.5));
		items.add(new Items("Oppp 2021",    "6.jpg", 200, 0.2));

		req.setAttribute("items", items);

	    req.getRequestDispatcher("nangcao.jsp").forward(req, resp);
	}
}
