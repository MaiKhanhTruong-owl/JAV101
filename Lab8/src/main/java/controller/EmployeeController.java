package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

import DAO.EmployeesDAO;
import model.Employees;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(name = "EmployeeController", urlPatterns = { "/employees", "/employees/*" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB
		maxFileSize = 5 * 1024 * 1024, // 5MB
		maxRequestSize = 10 * 1024 * 1024 // 10MB
)
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeesDAO dao;

	@Override
	public void init() throws ServletException {
		dao = new EmployeesDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Ensure UTF-8
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
			searchEmployees(request, response);
			break;
		case "list":
		default:
			listEmployees(request, response);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Ensure UTF-8
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		if (action == null)
			action = "";

		switch (action) {
		case "insert":
			insertEmployee(request, response);
			break;
		case "update":
			updateEmployee(request, response);
			break;
		case "delete":
			deleteEmployee(request, response);
			break;
		default:
			response.sendRedirect(request.getContextPath() + "/employees?action=list");
			break;
		}
	}

	/* Handlers */

	private void listEmployees(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Employees> list = dao.findAll();
		request.setAttribute("employeeList", list);
		request.setAttribute("employee", null);
		request.setAttribute("formAction", "insert");
		request.getRequestDispatcher("/employee_Management.jsp").forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("employee", new Employees());
		request.setAttribute("formAction", "insert");
		request.setAttribute("employeeList", dao.findAll());
		request.getRequestDispatcher("/employee_Management.jsp").forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id == null || id.trim().isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/employees?action=list");
			return;
		}
		Employees e = dao.findById(id);
		if (e == null) {
			request.setAttribute("error", "Không tìm thấy nhân viên ID = " + id);
			listEmployees(request, response);
			return;
		}
		request.setAttribute("employee", e);
		request.setAttribute("formAction", "update");
		request.setAttribute("employeeList", dao.findAll());
		request.getRequestDispatcher("/employee_Management.jsp").forward(request, response);
	}

	private void searchEmployees(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		List<Employees> result;
		if (keyword == null || keyword.trim().isEmpty()) {
			result = dao.findAll();
		} else {
			result = dao.search(keyword);
		}
		request.setAttribute("employeeList", result);
		request.setAttribute("formAction", "insert");
		request.getRequestDispatcher("/employee_Management.jsp").forward(request, response);
	}

	private void insertEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// multipart form
		String id = safe(request.getParameter("id"));
		String password = safe(request.getParameter("password"));
		String fullname = safe(request.getParameter("fullname"));
		String gender = safe(request.getParameter("gender"));
		String birthDay = safe(request.getParameter("birthDay"));
		String salary = safe(request.getParameter("salary"));
		String departmentId = safe(request.getParameter("departmentId"));

		// handle file part
		String photoFileName = "";
		Part photoPart = request.getPart("photoFile");
		if (photoPart != null && photoPart.getSize() > 0) {
			photoFileName = saveUploadedFile(photoPart, request);
		}

		if (id.isEmpty() || password.isEmpty() || fullname.isEmpty()) {
			request.setAttribute("error", "ID, mật khẩu và họ tên là bắt buộc.");
			Employees e = new Employees(id, password, fullname, photoFileName, gender, birthDay, salary, departmentId);
			request.setAttribute("employee", e);
			request.setAttribute("formAction", "insert");
			request.setAttribute("employeeList", dao.findAll());
			request.getRequestDispatcher("/employee_Management.jsp").forward(request, response);
			return;
		}

		Employees e = new Employees(id, password, fullname, photoFileName, gender, birthDay, salary, departmentId);
		boolean ok = dao.insert(e);
		if (!ok) {
			request.setAttribute("error", "Thêm nhân viên thất bại. Có thể ID đã tồn tại.");
			request.setAttribute("employee", e);
			request.setAttribute("formAction", "insert");
			request.setAttribute("employeeList", dao.findAll());
			request.getRequestDispatcher("/employee_Management.jsp").forward(request, response);
			return;
		}
		response.sendRedirect(request.getContextPath() + "/employees?action=list");
	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = safe(request.getParameter("id"));
		String password = safe(request.getParameter("password"));
		String fullname = safe(request.getParameter("fullname"));
		String gender = safe(request.getParameter("gender"));
		String birthDay = safe(request.getParameter("birthDay"));
		String salary = safe(request.getParameter("salary"));
		String departmentId = safe(request.getParameter("departmentId"));

		// existing photo
		String existingPhoto = safe(request.getParameter("existingPhoto"));

		Part photoPart = request.getPart("photoFile");
		String photoFileName = existingPhoto;
		if (photoPart != null && photoPart.getSize() > 0) {
			photoFileName = saveUploadedFile(photoPart, request);
			if (existingPhoto != null && !existingPhoto.isEmpty() && !existingPhoto.equals(photoFileName)) {
				deleteUploadedFile(existingPhoto, request);
			}
		}

		if (id.isEmpty() || fullname.isEmpty()) {
			request.setAttribute("error", "ID và Họ tên là bắt buộc.");
			Employees e = new Employees(id, password, fullname, photoFileName, gender, birthDay, salary, departmentId);
			request.setAttribute("employee", e);
			request.setAttribute("formAction", "update");
			request.setAttribute("employeeList", dao.findAll());
			request.getRequestDispatcher("/employee_Management.jsp").forward(request, response);
			return;
		}

		Employees e = new Employees(id, password, fullname, photoFileName, gender, birthDay, salary, departmentId);
		boolean ok = dao.update(e);
		if (!ok) {
			request.setAttribute("error", "Cập nhật thất bại.");
			request.setAttribute("employee", e);
			request.setAttribute("formAction", "update");
			request.setAttribute("employeeList", dao.findAll());
			request.getRequestDispatcher("/employee_Management.jsp").forward(request, response);
			return;
		}
		response.sendRedirect(request.getContextPath() + "/employees?action=list");
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id == null || id.trim().isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/employees?action=list");
			return;
		}
		// attempt to delete DB record and remove image file if present
		Employees e = dao.findById(id);
		if (e != null && e.getPhoto() != null && !e.getPhoto().isEmpty()) {
			deleteUploadedFile(e.getPhoto(), request);
		}
		dao.delete(id);
		response.sendRedirect(request.getContextPath() + "/employees?action=list");
	}

	// Helper to save uploaded file; returns filename stored
	private String saveUploadedFile(Part part, HttpServletRequest request) throws IOException {
		String submitted = getFileName(part);
		String uploadsPath = request.getServletContext().getRealPath("/uploads");
		File uploadsDir = new File(uploadsPath);
		if (!uploadsDir.exists())
			uploadsDir.mkdirs();

		String ext = "";
		int i = submitted.lastIndexOf('.');
		if (i > 0)
			ext = submitted.substring(i);
		String filename = UUID.randomUUID().toString() + ext;

		File file = new File(uploadsDir, filename);
		try (InputStream in = part.getInputStream()) {
			Files.copy(in, file.toPath());
		}
		return filename;
	}

	// Helper to delete old uploaded file
	private void deleteUploadedFile(String filename, HttpServletRequest request) {
		if (filename == null || filename.isEmpty())
			return;
		String uploadsPath = request.getServletContext().getRealPath("/uploads");
		File f = new File(uploadsPath, filename);
		if (f.exists()) {
			f.delete();
		}
	}

	// extract submitted file name
	private String getFileName(Part part) {
		String cd = part.getHeader("content-disposition");
		if (cd == null)
			return "";
		for (String sub : cd.split(";")) {
			sub = sub.trim();
			if (sub.startsWith("filename")) {
				String filename = sub.substring(sub.indexOf('=') + 1).trim().replace("\"", "");
				return filename
						.substring(Math.max(filename.lastIndexOf('/'), filename.lastIndexOf(File.separator)) + 1);
			}
		}
		return "";
	}

	/* helper */
	private String safe(String s) {
		return s == null ? "" : s.trim();
	}
}
