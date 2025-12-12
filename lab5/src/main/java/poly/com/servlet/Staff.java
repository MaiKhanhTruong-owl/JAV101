package poly.com.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig
@WebServlet("/add")
public class Staff extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public Staff() {
		super();
		//TODO
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("/views/form.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("utf-8");

			// 1. Cấu hình DateConverter
			DateTimeConverter dtc = new DateConverter(new Date());
			dtc.setPattern("dd/MM/yyyy");
			ConvertUtils.register(dtc, Date.class);

			// 2. KHỞI TẠO MODEL (Quan trọng: Phải dùng poly.com.model.Staff)
			// Nếu chỉ viết 'Staff staff = new Staff();' nó sẽ hiểu lầm là Servlet này
			poly.com.model.Staff staff = new poly.com.model.Staff();

			// 3. Đổ dữ liệu từ Form vào Model
			BeanUtils.populate(staff, req.getParameterMap());

			// 4. Xử lý lưu file ảnh
			File dir = new File(req.getServletContext().getRealPath("/file"));
			if (!dir.exists()) {
				dir.mkdir();
			}

			Part photo = req.getPart("photo_file");
			// Kiểm tra nếu người dùng có chọn ảnh thì mới lưu
			if (photo != null && photo.getSize() > 0) {
				File photoFile = new File(dir, photo.getSubmittedFileName());
				photo.write(photoFile.getAbsolutePath());
				
				// Gán tên file vào Model (Chữ P viết hoa theo Model đã sửa)
				staff.setPhoto_file(photoFile.getName());
			}

			// 5. Gửi Model sang JSP
			req.setAttribute("bean", staff);

			// 6. Xử lý chuỗi sở thích (Hobbies)
			if (staff.getHobbies() != null) {
				String lay = Arrays.toString(staff.getHobbies());
				// Cắt bỏ dấu ngoặc vuông []
				String catchuoi = lay.substring(1, lay.lastIndexOf("]")) + ".";
				req.setAttribute("st", catchuoi);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		req.getRequestDispatcher("/views/result.jsp").forward(req, resp);
	}
}
