package poly.com.controller;

import poly.com.dao.DeparmentDao;
import poly.com.model.Departments;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/departments2")
public class DepartmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DeparmentDao dao = new DeparmentDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "edit":
                String idEdit = req.getParameter("id");
                req.setAttribute("editDept", dao.findById(idEdit));
                break;
            case "delete":
                String idDelete = req.getParameter("id");
                dao.deleteDepartment(idDelete);
                resp.sendRedirect(req.getContextPath() + "/departments2");
                return;
            case "search":
                String keyword = req.getParameter("keyword");
                req.setAttribute("departments", dao.searchByName(keyword));
                req.getRequestDispatcher("/departments2.jsp").forward(req, resp);
                return;
            case "list":
            default:
                break;
        }

        List<Departments> list = dao.getAll();
        req.setAttribute("departments", list);
        req.getRequestDispatcher("/departments2.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String description = req.getParameter("description");

        if ("add".equals(action)) {
            dao.insertDepartment(id, name, description);
        } else if ("update".equals(action)) {
            dao.updateDepartment(id, name, description);
        }

        resp.sendRedirect(req.getContextPath() + "/departments2");
    }
}
