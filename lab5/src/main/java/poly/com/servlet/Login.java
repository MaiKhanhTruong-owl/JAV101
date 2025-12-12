package poly.com.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.com.util.Cookies;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public Login(){
		super();
		//TODO
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = Cookies.get("username1", req);
		String password = Cookies.get("password1", req);
		req.setAttribute("username", username );
		req.setAttribute("password", password );
		
		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");
		
		if (!username.equals("mngoc")) {
			req.setAttribute("message", "Username invaild!");
		}
		else if (!password.equals("456")) {
			req.setAttribute("message", "Password invaild!");
		}
		else {
			req.setAttribute("message", "Login success!");
			int hours = (remember==null)?0:1;
			Cookies.add("username1",username, hours, resp);
			Cookies.add("password1",password, hours, resp);
		}
		req.setAttribute("username", username);
		req.setAttribute("password", password);
		
		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	}
}
