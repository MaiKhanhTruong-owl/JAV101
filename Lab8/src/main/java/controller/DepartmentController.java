package controller;

import java.io.IOException;
import java.util.List;

import DAO.DepartmentDAO;
import model.Department;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/departments")
public class DepartmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DepartmentDAO dao;

	@Override
	public void init() throws ServletException {
		dao = new DepartmentDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Ensure UTF-8 for Vietnamese
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		if (action == null)
			action = "list";

		switch (action) {
		case "new":
			showNewForm(request, response);
			break;
		case "edit":
			showEditForm(request, response);
			break;
		case "search":
			searchDepartments(request, response);
			break;
		default:
			listDepartments(request, response);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Ensure UTF-8 for form submissions
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		if (action == null)
			action = "";

		switch (action) {
		case "insert":
			insertDepartment(request, response);
			break;
		case "update":
			updateDepartment(request, response);
			break;
		case "delete":
			deleteDepartment(request, response);
			break;
		default:
			response.sendRedirect(request.getContextPath() + "/departments");
			break;
		}
	}

	/* ========== Handlers ========== */

	private void listDepartments(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Department> list = dao.findAll();
		request.setAttribute("departmentList", list);
		// clear form state
		request.setAttribute("department", null);
		request.setAttribute("formAction", "insert");
		request.getRequestDispatcher("/department_Management.jsp").forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("department", new Department());
		request.setAttribute("formAction", "insert");
		List<Department> list = dao.findAll();
		request.setAttribute("departmentList", list);
		request.getRequestDispatcher("/department_Management.jsp").forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id == null || id.trim().isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/departments");
			return;
		}

		Department d = dao.findById(id);
		if (d == null) {
			request.setAttribute("error", "Không tìm thấy phòng ban có ID = " + id);
			listDepartments(request, response);
			return;
		}

		request.setAttribute("department", d);
		request.setAttribute("formAction", "update");
		List<Department> list = dao.findAll();
		request.setAttribute("departmentList", list);
		request.getRequestDispatcher("/department_Management.jsp").forward(request, response);
	}

	private void searchDepartments(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		List<Department> result;
		if (keyword == null || keyword.trim().isEmpty()) {
			result = dao.findAll();
		} else {
			// call search() from DAO (must exist)
			result = dao.search(keyword);
		}

		request.setAttribute("departmentList", result);
		request.setAttribute("formAction", "insert");
		request.getRequestDispatcher("/department_Management.jsp").forward(request, response);
	}

	private void insertDepartment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = safe(request.getParameter("id"));
		String name = safe(request.getParameter("name"));
		String description = safe(request.getParameter("description"));

		if (id.isEmpty() || name.isEmpty()) {
			request.setAttribute("error", "ID và Tên là bắt buộc.");
			Department d = new Department(id, name, description);
			request.setAttribute("department", d);
			request.setAttribute("formAction", "insert");
			request.setAttribute("departmentList", dao.findAll());
			request.getRequestDispatcher("/department_Management.jsp").forward(request, response);
			return;
		}

		Department d = new Department(id, name, description);
		boolean ok = dao.insert(d);

		if (!ok) {
			request.setAttribute("error", "Thêm thất bại. Có thể ID đã tồn tại.");
			request.setAttribute("department", d);
			request.setAttribute("formAction", "insert");
			request.setAttribute("departmentList", dao.findAll());
			request.getRequestDispatcher("/department_Management.jsp").forward(request, response);
			return;
		}

		response.sendRedirect(request.getContextPath() + "/departments");
	}

	private void updateDepartment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = safe(request.getParameter("id"));
		String name = safe(request.getParameter("name"));
		String description = safe(request.getParameter("description"));

		if (id.isEmpty() || name.isEmpty()) {
			request.setAttribute("error", "ID và Tên là bắt buộc.");
			Department d = new Department(id, name, description);
			request.setAttribute("department", d);
			request.setAttribute("formAction", "update");
			request.setAttribute("departmentList", dao.findAll());
			request.getRequestDispatcher("/department_Management.jsp").forward(request, response);
			return;
		}

		Department d = new Department(id, name, description);
		boolean ok = dao.update(d);

		if (!ok) {
			request.setAttribute("error", "Cập nhật thất bại.");
			request.setAttribute("department", d);
			request.setAttribute("formAction", "update");
			request.setAttribute("departmentList", dao.findAll());
			request.getRequestDispatcher("/department_Management.jsp").forward(request, response);
			return;
		}

		response.sendRedirect(request.getContextPath() + "/departments");
	}

	private void deleteDepartment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id == null || id.trim().isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/departments");
			return;
		}

		dao.delete(id);
		response.sendRedirect(request.getContextPath() + "/departments");
	}

	/* ========== Helpers ========== */
	private String safe(String s) {
		return s == null ? "" : s.trim();
	}
}
