package poly.com.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/bai3","/form/ud","/form/themmoi"})
public class bai3 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// doGet
		Map<String, Object> map = new HashMap<>();
		map.put("fullname", "Nguyễn Văn Tèo");
		map.put("gender", true);
		map.put("country", "VN");
		req.setAttribute("user", map);
		req.setAttribute("editable", true);
		req.getRequestDispatcher("/form/form.jsp").forward(req, resp);

	}
	// doPost
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = req.getRequestURI();

		if (uri.contains("/form/ud")) {
			String fullname = req.getParameter("fullname");
			System.out.print(fullname);
			String gender = req.getParameter("gender");
			String country = req.getParameter("country");

			Map<String, Object> map = new HashMap<>();
			map.put("fullname", fullname);
			map.put("gender", gender);
			map.put("country", country);

			req.setAttribute("user", map);
			req.setAttribute("canphat", "update success");
			req.getRequestDispatcher("/form/form.jsp").forward(req, resp);

		} else if (uri.contains("/form/themmoi")) {
			String fullname = req.getParameter("fullname");
			String gender = req.getParameter("gender");
			String country = req.getParameter("country");

			Map<String, Object> map = new HashMap<>();
			map.put("fullname", fullname);
			map.put("gender", gender);
			map.put("country", country);
			req.setAttribute("user", map);

			req.getRequestDispatcher("/form/themmoi.jsp").forward(req, resp);

		}
	}

}
