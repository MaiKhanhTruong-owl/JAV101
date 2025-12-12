package controller;

import java.io.IOException;
import java.util.List;

import DAO.DepartmentDAO;
import DAO.EmployeesDAO;
import model.Department;
import model.Employees;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DepartmentDAO departmentDAO;
	private EmployeesDAO employeesDAO;

	@Override
	public void init() throws ServletException {
		departmentDAO = new DepartmentDAO();
		employeesDAO = new EmployeesDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// charset
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// get lists and counts (simple, using findAll().size())
		List<Department> deps = departmentDAO.findAll();
		List<Employees> emps = employeesDAO.findAll();

		int deptCount = deps == null ? 0 : deps.size();
		int empCount = emps == null ? 0 : emps.size();

		request.setAttribute("deptCount", deptCount);
		request.setAttribute("empCount", empCount);

		// You may also set sample stats here (avg salary etc.) later

		// forward to JSP dashboard page
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
}
