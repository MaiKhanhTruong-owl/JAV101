package poly.com.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import poly.com.dao.DeparmentDao;
import poly.com.model.Departments;

@WebServlet("/departments")
public class DepartmentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private DeparmentDao dao = new DeparmentDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // đảm bảo encoding nếu cần
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "edit":
                    String idEdit = req.getParameter("id");
                    Departments edit = dao.findById(idEdit);
                    req.setAttribute("editDept", edit);
                    break;
                case "delete":
                    String idDelete = req.getParameter("id");
                    dao.deleteDepartment(idDelete);
                    // sau khi xóa, redirect để tránh repost khi reload
                    resp.sendRedirect(req.getContextPath() + "/departments");
                    return;
                case "list":
                default:
                    // nothing special, sẽ load danh sách bên dưới
                    break;
            }
        } catch (Exception e) {
            // log / xử lý lỗi
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
        }

        // load danh sách và forward tới JSP (dùng đường dẫn tuyệt đối)
        List<Departments> list = dao.getAll();
        req.setAttribute("departments", list);
        req.getRequestDispatcher("/departments.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // xử lý POST với UTF-8
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String action = req.getParameter("action");

        try {
            if ("add".equals(action)) {
                dao.insertDepartment(id, name, description);
            } else if ("update".equals(action)) {
                dao.updateDepartment(id, name, description);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // bạn có thể set thông báo lỗi lên request và forward lại
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/departments.jsp").forward(req, resp);
            return;
        }

        // redirect về danh sách (dùng context path để an toàn)
        resp.sendRedirect(req.getContextPath() + "/departments");
    }
}
