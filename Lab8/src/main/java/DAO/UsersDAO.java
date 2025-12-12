package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Users;

import util.DBConnect;

public class UsersDAO {

	// Insert (Create)
	public boolean insertUser(Users user) {
		String sql = "INSERT INTO Users (username, password, fullname, role, email, address, sdt) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFullname());
			ps.setInt(4, user.getRole());
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getAddress());
			ps.setString(7, user.getSdt());

			int rows = ps.executeUpdate();
			return rows > 0;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	// Read - get by username (primary key)
	public Users getUserByUsername(String username) {
		String sql = "SELECT username, password, fullname, role, email, address, sdt "
				+ "FROM Users WHERE username = ?";
		try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, username);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Users u = new Users();
					u.setUsername(rs.getString("username"));
					u.setPassword(rs.getString("password"));
					u.setFullname(rs.getString("fullname"));
					u.setRole(rs.getInt("role"));
					u.setEmail(rs.getString("email"));
					u.setAddress(rs.getString("address"));
					u.setSdt(rs.getString("sdt"));
					return u;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	// Read - get all users
	public List<Users> getAllUsers() {
		List<Users> list = new ArrayList<>();
		String sql = "SELECT username, password, fullname, role, email, address, sdt FROM Users";
		try (Connection conn = DBConnect.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Users u = new Users();
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setFullname(rs.getString("fullname"));
				u.setRole(rs.getInt("role"));
				u.setEmail(rs.getString("email"));
				u.setAddress(rs.getString("address"));
				u.setSdt(rs.getString("sdt"));
				list.add(u);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return list;
	}

	// Update
	public boolean updateUser(Users user) {
		String sql = "UPDATE Users SET password = ?, fullname = ?, role = ?, email = ?, address = ?, sdt = ? "
				+ "WHERE username = ?";
		try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, user.getPassword());
			ps.setString(2, user.getFullname());
			ps.setInt(3, user.getRole());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getAddress());
			ps.setString(6, user.getSdt());
			ps.setString(7, user.getUsername());

			int rows = ps.executeUpdate();
			return rows > 0;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	// Delete
	public boolean deleteUser(String username) {
		String sql = "DELETE FROM Users WHERE username = ?";
		try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, username);
			int rows = ps.executeUpdate();
			return rows > 0;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	// Login - returns User if success, otherwise null
	public Users login(String username, String password) {
		String sql = "SELECT username, password, fullname, role, email, address, sdt "
				+ "FROM Users WHERE username = ? AND password = ?";
		try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, username);
			ps.setString(2, password);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Users u = new Users();
					u.setUsername(rs.getString("username"));
					u.setPassword(rs.getString("password"));
					u.setFullname(rs.getString("fullname"));
					u.setRole(rs.getInt("role"));
					u.setEmail(rs.getString("email"));
					u.setAddress(rs.getString("address"));
					u.setSdt(rs.getString("sdt"));
					return u;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	// Optional helper - check username exists
	public boolean usernameExists(String username) {
		String sql = "SELECT username FROM Users WHERE username = ?";
		try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, username);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
