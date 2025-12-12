package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Locale;

@WebServlet("/lang")
public class LanguageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String lang = req.getParameter("lang");
        HttpSession session = req.getSession();

        if (lang != null && !lang.isBlank()) {
            // lưu Locale vào session để fmt:setLocale sử dụng
            Locale locale = Locale.forLanguageTag(lang);
            session.setAttribute("lang", locale);
        }

        // Lấy trang trước đó (Referer). Nếu không có -> fallback về /dashboard
        String referer = req.getHeader("Referer");
        if (referer == null || referer.isBlank()) {
            // sửa đường dẫn mặc định nếu bạn muốn khác (ví dụ login.jsp)
            String fallback = req.getContextPath() + "/dashboard";
            resp.sendRedirect(fallback);
        } else {
            // redirect trả về chính xác trang trước (giữ query string)
            resp.sendRedirect(referer);
        }
    }

    // Nếu bạn muốn cũng support POST (không bắt buộc)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
