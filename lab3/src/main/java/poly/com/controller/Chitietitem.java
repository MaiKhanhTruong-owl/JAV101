package poly.com.controller;

import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.com.model.Items;
@WebServlet("/ProductDetailServlet")
public class Chitietitem extends HttpServlet
{
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
		 String name = req.getParameter("id");
		 selectById(name, req);
		 req.getRequestDispatcher("/detailtu.jsp").forward(req, resp);
		}
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	 String name = req.getParameter("id");
	 selectById(name, req);
	 req.getRequestDispatcher("/detailtu.jsp").forward(req, resp);
	 
}

public void selectById(String name, HttpServletRequest request) throws ServletException, IOException 
{
{
    ArrayList<Items> items = new ArrayList<>();
    items.add(new Items("Nokia 2020", "1.jpg", 500, 0.1));
    items.add(new Items("Samsung Xyz", "2.jpg", 700, 0.15));
    items.add(new Items("iPhone Xy", "3.jpg", 900, 0.25));
    items.add(new Items("Sony Erricson", "4.jpg", 55, 0.3));
    items.add(new Items("Redminode 9","5.png",800,0.1));
    items.add(new Items("redminode9s","6.png",900,0.2));

    ArrayList<Items> selectedItems = new ArrayList<>();
    for (Items item : items) {
        if (item.getName().equalsIgnoreCase(name)) {
            selectedItems.add(item); //1 sản phẩm
            
        }
    }

    // Lưu danh sách sản phẩm đã chọn vào request để chuyển tiếp tới trang JSP
    request.setAttribute("selectedItems", selectedItems);
}

}


}


