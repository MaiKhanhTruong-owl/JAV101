package poly.com.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@MultipartConfig
@WebServlet("/mail")
public class Mail extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Chuyển hướng về trang form nhập liệu
        request.getRequestDispatcher("/views/mail.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            // 1. Cấu hình Mail Server (Gmail)
            Properties p = new Properties();
            p.put("mail.smtp.auth", "true");
            p.put("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.ssl.protocols", "TLSv1.2");
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.port", "587");
            p.put("mail.debug", "true"); // Bật log để xem quá trình gửi

            // 2. Đăng nhập
            String username = "tmngoc732@gmail.com"; // <-- Thay email của bạn
            String password = "mdmd dhqe gkbd uzly"; // <-- Thay mật khẩu ứng dụng

            Session s = Session.getInstance(p, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            // 3. Tạo nội dung Email
            MimeMessage msg = new MimeMessage(s);
            msg.setFrom(new InternetAddress(username));
            
            // Lấy thông tin từ Form
            String to = request.getParameter("to");
            String subject = request.getParameter("subject");
            String body = request.getParameter("body");

            // Xử lý người nhận (hỗ trợ gửi nhiều mail ngăn cách bởi dấu phẩy)
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject(subject);

            // 4. Xử lý đính kèm file (Multipart)
            MimeMultipart multipart = new MimeMultipart();

            // Phần 1: Nội dung chữ (Body)
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(body, "utf-8");
            multipart.addBodyPart(textPart);

            // Phần 2: File đính kèm (nếu có)
            Part filePart = request.getPart("photo_file");
            if (filePart != null && filePart.getSize() > 0) {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                
                // Lưu file tạm vào server để gửi đi
                String fileName = File.separator + filePart.getSubmittedFileName();
                String uploadPath = request.getServletContext().getRealPath("/uploads");
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdir();
                
                String filePath = uploadPath + fileName;
                filePart.write(filePath);
                
                // Đính kèm file vào mail
                attachmentPart.attachFile(new File(filePath));
                multipart.addBodyPart(attachmentPart);
            }

            // Gán nội dung vào mail
            msg.setContent(multipart);

            // 5. Gửi mail
            Transport.send(msg);
            
            request.setAttribute("message", "Gửi mail thành công!");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Gửi mail thất bại: " + e.getMessage());
        }

        // Quay lại trang form
        request.getRequestDispatcher("/views/mail.jsp").forward(request, response);
    }
}