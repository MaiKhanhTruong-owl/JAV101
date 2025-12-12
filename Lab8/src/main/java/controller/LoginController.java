package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.Cookies;

import model.Users;
import DAO.UsersDAO;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String usernameCookie = Cookies.get("username1", req);
		// nếu có cookie thì prefill và đánh dấu checked
		if (usernameCookie != null && !usernameCookie.isEmpty()) {
			req.setAttribute("username", usernameCookie);
			req.setAttribute("rememberChecked", "checked");
		} else {
			req.setAttribute("username", "");
			req.setAttribute("rememberChecked", "");
		}
		req.setAttribute("message", req.getAttribute("message")); // giữ message nếu có
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String usernameip = req.getParameter("username");
		String passwordip = req.getParameter("password");

		UsersDAO dao = new UsersDAO();
		Users user = dao.login(usernameip, passwordip); // trả về User nếu đúng, null nếu sai

		String remember = req.getParameter("rememberMe");
		int countFalseLogin = 0;

		// Lấy số lần sai đăng nhập từ session
		Object countObj = req.getSession().getAttribute("countFalseLogin");
		if (countObj != null) {
			try {
				countFalseLogin = (int) countObj;
			} catch (Exception e) {
				countFalseLogin = 0;
			}
		}

		// =============================
//		        LOGIN THÀNH CÔNG
		// =============================
		if (user != null) {

			// Lưu object user vào session
			req.getSession().setAttribute("user", user);

			// Reset số lần sai
			req.getSession().setAttribute("countFalseLogin", 0);

			// ---- Remember Username ----
			if (remember != null) {
				int days30 = 60 * 60 * 24 * 30;
				Cookies.add("username1", user.getUsername(), days30, resp);
			} else {
				Cookies.add("username1", "", 0, resp);
			}

			// Thông báo
			req.setAttribute("message", "Đăng nhập thành công!");

			// Điều hướng theo role
			if (user.getRole() == 1) {
				// admin
				resp.sendRedirect(req.getContextPath() + "/index.jsp");

			} else {
				// khách hàng
				resp.sendRedirect(req.getContextPath() + "/customer.jsp");

			}
			return;
		}

		// =============================
//		        LOGIN THẤT BẠI
		// =============================
		countFalseLogin++;
		req.getSession().setAttribute("countFalseLogin", countFalseLogin);

		req.setAttribute("message", "Sai username hoặc password! (Lần sai: " + countFalseLogin + "/5)");

		if (countFalseLogin >= 5) {
			req.getRequestDispatcher("false_login.jsp").forward(req, resp);
			return;
		}

		// Prefill username để tiện nhập lại
		req.setAttribute("username", usernameip == null ? "" : usernameip);

		// Check cookie
		req.setAttribute("rememberChecked", (Cookies.get("username1", req) != null) ? "checked" : "");

		// Quay lại trang login
		req.getRequestDispatcher("login.jsp").forward(req, resp);
		return;

	}
}
