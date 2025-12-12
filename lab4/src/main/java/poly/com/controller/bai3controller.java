package poly.com.controller;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;


@WebServlet("/add")
@MultipartConfig
public class bai3controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Thư mục lưu file trong context (ví dụ webapp/uploads)
    private static final String UPLOAD_DIRECTORY = "uploads";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Chuyển đến trang form
        req.getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin từ form
        String fullname = request.getParameter("fullname");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String married = request.getParameter("married");
        String country = request.getParameter("country");
        String[] hobbies = request.getParameterValues("hobbies");
        String note = request.getParameter("note");

        // Xử lý file ảnh đại diện (name của input phải là "photo_file")
        Part filePart = request.getPart("photo_file"); // tên field giống JSP
        String fileName = (filePart != null) ? filePart.getSubmittedFileName() : null;

        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String filePath = null;
        String photoURL = null;
        if (fileName != null && !fileName.isEmpty() && filePart != null) {
            filePath = uploadPath + File.separator + fileName;
            filePart.write(filePath);
            photoURL = UPLOAD_DIRECTORY + "/" + fileName; // để hiển thị trong JSP: contextPath + "/" + photoURL
        }

        // Đặt thông tin vào request để đưa sang ketqua.jsp
        request.setAttribute("fullname", fullname);
        request.setAttribute("password", password);
        request.setAttribute("gender", gender);
        request.setAttribute("married", married != null ? "Yes" : "No");
        request.setAttribute("country", country);
        request.setAttribute("hobbies", hobbies);
        request.setAttribute("note", note);
        request.setAttribute("photoURL", photoURL); // có thể null

        // Ghi log (tùy chọn)
        System.out.println("Ảnh được lưu tại: " + filePath);

        // Chuyển tiếp tới trang kết quả
        request.getRequestDispatcher("/ketqua.jsp").forward(request, response);
    }
}
