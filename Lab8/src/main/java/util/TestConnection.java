/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author LENOVO
 */
import java.sql.Connection;
import java.sql.SQLException;
//import util.DBConnect;

public class TestConnection {

	public static void main(String[] args) {
		try (Connection conn = DBConnect.getConnection()) {
			if (conn != null) {
				System.out.println("Connect thành công với DB: " + DBConnect.DBNAME + "!");
			} else {
				System.out.println("Connect thất bại với DB: " + DBConnect.DBNAME + "!");
			}
		} catch (SQLException e) {
			System.out.println("Lỗi kết nối: " + e.getMessage());
		}
	}
}
