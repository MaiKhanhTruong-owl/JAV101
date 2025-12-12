package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/admin.jsp", "/department_Management.jsp","/employee_Management.jsp" })
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// Enable UTF-8
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");

		// Lấy session hiện tại nếu có (false = không tạo session mới)
		HttpSession session = req.getSession(false);

		// Kiểm tra có đăng nhập chưa
		boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

		if (isLoggedIn) {
			// Đã đăng nhập → cho đi tiếp
			chain.doFilter(request, response);
		} else {
			// Chưa đăng nhập → chuyển về trang login
			res.sendRedirect(req.getContextPath() + "/login.jsp");
		}
	}
}
