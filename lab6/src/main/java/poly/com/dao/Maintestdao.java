package poly.com.dao;

public class Maintestdao {

    public static void main(String[] args) {

        DeparmentDao Dedao = new DeparmentDao();
        Dedao.insertDepartment("005", "Phòng CNTT", "Phòng công nghệ thông tin"); // thêm
        Dedao.deleteDepartment("002"); //xóa
        Dedao.updateDepartment("001", "bla bla bla", "alu alu alu"); // sửa
        Dedao.findDepartmentById("001");// tìm kiếm
        Dedao.printAllDepartments();
        
    }
}
