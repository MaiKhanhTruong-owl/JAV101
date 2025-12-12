package poly.com.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession; // Thêm import cho HttpSession
import poly.com.model.CartItem1;
import poly.com.model.Items;

import java.util.ArrayList;
import java.util.List;

@WebServlet({"/CartController1", "/update", "/remove", "/add"})
public class CartController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CartController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(); // Lấy session
        List<CartItem1> cartItems = (List<CartItem1>) session.getAttribute("cartItems"); // Lấy giỏ hàng từ session

        // Nếu giỏ hàng chưa có, khởi tạo mới
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        // Tạo danh sách sản phẩm
        List<Items> items = new ArrayList<>();
        items.add(new Items("Nokia 2020", "1.jpg", 500, 0.1));
        items.add(new Items("Samsung Xyz", "2.jpg", 700, 0.15));
        items.add(new Items("iPhone Xy", "3.jpg", 900, 0.25));
        items.add(new Items("Sony Erricson", "4.jpg", 55, 0.3));

        request.setAttribute("items", items);

        String action = request.getParameter("action");
        	if (action != null) {
        String itemName = request.getParameter("itemName");
         ///
        String quantityParam = request.getParameter("quantity");
		        int quantity = 1; // Giá trị mặc định
		
		        if (quantityParam != null && !quantityParam.isEmpty()) {
		            try {
		                quantity = Integer.parseInt(quantityParam);
		            } catch (NumberFormatException e) {
		                // Xử lý khi giá trị không phải là số hợp lệ
		                System.out.println("Giá trị không hợp lệ cho quantity: " + quantityParam);
		                // Có thể thông báo cho người dùng hoặc gán giá trị mặc định
		            }
		        } else {
		            // Xử lý khi quantity là null hoặc rỗng
		            System.out.println("Giá trị quantity không được cung cấp.");
		            // Có thể thông báo cho người dùng hoặc gán giá trị mặc định
		        }
/////////////
            // Tìm kiếm sản phẩm dựa trên tên
            Items selectedItem = null;
            for (Items item : items) {
                if (item.getName().equals(itemName)) {
                    selectedItem = item;
                    break;
                }
            }
                    
            // Kiểm tra nếu sản phẩm được tìm thấy
            if (selectedItem != null) {
                // Kiểm tra hành động từ người dùng
                if (action.equals("add")) {
                    addItemToCart(cartItems, selectedItem, quantity);
                } else if (action.equals("remove")) {
                    removeItemFromCart(cartItems, itemName);
                } else if (action.equals("update")) {
                    updateItemQuantity(cartItems, itemName, quantity);
                }
            }
        }


   
        // Cập nhật giỏ hàng trong session
        session.setAttribute("cartItems", cartItems);

        // Tính tổng tiền và set lại các thuộc tính để hiển thị
        double totalPrice = calculateTotalPrice(cartItems);
        request.setAttribute("totalPrice", totalPrice);

      
        
        // Forward về trang JSP
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    
    private void addItemToCart(List<CartItem1> cartItems, Items item, int quantity) {
        for (CartItem1 cartItem : cartItems) {
            if (cartItem.getItem().getName().equals(item.getName())) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                return;
            }
        }
        cartItems.add(new CartItem1(item, quantity));
    }

    private void removeItemFromCart(List<CartItem1> cartItems, String itemName) {
        cartItems.removeIf(cartItem -> cartItem.getItem().getName().equals(itemName));
    }
    

  

    private void updateItemQuantity(List<CartItem1> cartItems, String itemName, int quantity) {
        for (CartItem1 cartItem : cartItems) {
            if (cartItem.getItem().getName().equals(itemName)) {
                cartItem.setQuantity(quantity);
                return;
            }
        }
    }

    private double calculateTotalPrice(List<CartItem1> cartItems) {
        double total = 0;
        for (CartItem1 cartItem : cartItems) {
            total += cartItem.getTotalPrice();
        }
        return total;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
  
    	  HttpSession session = req.getSession(); // Lấy session
          List<CartItem1> cartItems = (List<CartItem1>) session.getAttribute("cartItems"); // Lấy giỏ hàng từ session

          // Nếu giỏ hàng chưa có, khởi tạo mới
          if (cartItems == null) {
              cartItems = new ArrayList<>();
          }

          // Tạo danh sách sản phẩm
          List<Items> items = new ArrayList<>();
          items.add(new Items("Nokia 2020", "1.jpg", 500, 0.1));
          items.add(new Items("Samsung Xyz", "2.jpg", 700, 0.15));
          items.add(new Items("iPhone Xy", "3.jpg", 900, 0.25));
          items.add(new Items("Sony Erricson", "4.jpg", 55, 0.3));

          req.setAttribute("items", items);

          String action = req.getParameter("action");
          	if (action != null) {
          String itemName = req.getParameter("itemName");
          int quantity = Integer.parseInt(req.getParameter("quantity"));

              // Tìm kiếm sản phẩm dựa trên tên
              Items selectedItem = null;
              for (Items item : items) {
                  if (item.getName().equals(itemName)) {
                      selectedItem = item;
                      break;
                  }
              }

              // Kiểm tra nếu sản phẩm được tìm thấy
              if (selectedItem != null) {
                  // Kiểm tra hành động từ người dùng
                  if (action.equals("add")) {
                      addItemToCart(cartItems, selectedItem, quantity);
                  } else if (action.equals("remove")) {
                	  System.out.print(action.equals("remove"));
                      removeItemFromCart(cartItems, itemName);
                  } else if (action.equals("update")) {
                      updateItemQuantity(cartItems, itemName, quantity);
                  }
              }
          }

          // Cập nhật giỏ hàng trong session
          session.setAttribute("cartItems", cartItems);
          

          // Tính tổng tiền và set lại các thuộc tính để hiển thị
          double totalPrice = calculateTotalPrice(cartItems);
          req.setAttribute("totalPrice", totalPrice);

          // Forward về trang JSP
          req.getRequestDispatcher("cart.jsp").forward(req, resp);
    	
    }
}
