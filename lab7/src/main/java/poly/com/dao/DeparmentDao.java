package poly.com.dao;

import poly.com.model.Departments;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeparmentDao {

    public void insertDepartment(String id, String name, String description) {
        String sql = "INSERT INTO Departments(id, Name, Description) VALUES(?, ?, ?)";
        try (Connection conn = Connectdao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, description);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateDepartment(String id, String name, String description) {
        String sql = "UPDATE Departments SET Name = ?, Description = ? WHERE id = ?";
        try (Connection conn = Connectdao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, id);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteDepartment(String id) {
        String sql = "DELETE FROM Departments WHERE id = ?";
        try (Connection conn = Connectdao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Departments findById(String id) {
        String sql = "SELECT * FROM Departments WHERE id = ?";
        try (Connection conn = Connectdao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Departments(rs.getString("id"), rs.getString("Name"), rs.getString("Description"));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Departments> getAll() {
        List<Departments> list = new ArrayList<>();
        String sql = "SELECT * FROM Departments ORDER BY id";
        try (Connection conn = Connectdao.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Departments(rs.getString("id"), rs.getString("Name"), rs.getString("Description")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Departments> searchByName(String keyword) {
        List<Departments> list = new ArrayList<>();
        String sql = "SELECT * FROM Departments WHERE Name LIKE ? ORDER BY id";
        try (Connection conn = Connectdao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + (keyword == null ? "" : keyword) + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Departments(rs.getString("id"), rs.getString("Name"), rs.getString("Description")));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
