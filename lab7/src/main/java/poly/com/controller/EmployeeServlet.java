package poly.com.controller;

import poly.com.dao.EmployeeDao;
import poly.com.model.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.List;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,
    maxFileSize = 5 * 1024 * 1024,
    maxRequestSize = 10 * 1024 * 1024
)
@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
    private EmployeeDao dao = new EmployeeDao();

    // folder lưu file ảnh tương đối trong webapp
    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "edit":
                String idEdit = req.getParameter("id");
                req.setAttribute("editEmp", dao.findById(idEdit));
                break;
            case "delete":
                String idDel = req.getParameter("id");
                // xóa file ảnh nếu có
                Employee exist = dao.findById(idDel);
                if (exist != null && exist.getPhoto() != null) {
                    deletePhotoFile(req, exist.getPhoto());
                }
                dao.delete(idDel);
                resp.sendRedirect(req.getContextPath() + "/employees");
                return;
            case "search":
                String keyword = req.getParameter("keyword");
                req.setAttribute("employees", dao.searchById(keyword));
                req.getRequestDispatcher("/employees.jsp").forward(req, resp);
                return;
            case "list":
            default:
                break;
        }

        List<Employee> list = dao.getAll();
        req.setAttribute("employees", list);
        req.getRequestDispatcher("/employees.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");
        boolean gender = "on".equals(req.getParameter("gender")) || "1".equals(req.getParameter("gender"));
        String birthdayStr = req.getParameter("birthday");
        LocalDate birthday = (birthdayStr==null || birthdayStr.isEmpty()) ? null : LocalDate.parse(birthdayStr);
        double salary = 0; try { salary = Double.parseDouble(req.getParameter("salary")); } catch (Exception e) {}
        String deptId = req.getParameter("departmentId");

        // xử lý file upload
        Part photoPart = req.getPart("photo");
        String photoPath = null;
        if (photoPart != null && photoPart.getSize() > 0) {
            // lưu file vào webapp/uploads
            String fileName = Paths.get(photoPart.getSubmittedFileName()).getFileName().toString();
            String ext = "";
            int i = fileName.lastIndexOf('.');
            if (i > 0) ext = fileName.substring(i);
            String savedName = id + "_" + System.currentTimeMillis() + ext;
            String uploadPath = req.getServletContext().getRealPath("/") + UPLOAD_DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();
            photoPart.write(uploadPath + File.separator + savedName);
            photoPath = "/" + UPLOAD_DIR + "/" + savedName;
        } else {
            // nếu update mà không chọn ảnh mới, giữ ảnh cũ
            if ("update".equals(action)) {
                Employee existing = dao.findById(id);
                if (existing != null) photoPath = existing.getPhoto();
            }
        }

        Employee emp = new Employee(id, password, fullname, gender, birthday, salary, deptId, photoPath);

        if ("add".equals(action)) {
            dao.insert(emp);
        } else if ("update".equals(action)) {
            dao.update(emp);
        }

        resp.sendRedirect(req.getContextPath() + "/employees");
    }

    private void deletePhotoFile(HttpServletRequest req, String photoPath) {
        if (photoPath == null) return;
        String real = req.getServletContext().getRealPath(photoPath);
        try {
            Files.deleteIfExists(Paths.get(real));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
