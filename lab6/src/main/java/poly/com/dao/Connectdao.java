package poly.com.dao;

import java.sql.Connection;
import java.sql.DriverManager;



public class Connectdao {

    protected static Connection conn;

    public Connectdao() {
        try {
        	String url = "jdbc:sqlserver://localhost:1433;databaseName=TS01506_Lab06;encrypt=true;trustServerCertificate=true";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, "sa", "123456789");
            System.out.println("Kết nối thành công!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
