package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Department;
import util.DBConnect;

public class DepartmentDAO {

    // =============================
    // 1. Tìm tất cả phòng ban
    // =============================
    public List<Department> findAll() {
        List<Department> list = new ArrayList<>();
        String sql = "SELECT * FROM Departments";

        try (
            Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {

            while (rs.next()) {
                Department d = new Department();
                d.setId(rs.getString("id"));
                d.setName(rs.getString("Name"));
                d.setDescription(rs.getString("Description"));

                list.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // =============================
    // 2. Tìm theo ID
    // =============================
    public Department findById(String id) {
        String sql = "SELECT * FROM Departments WHERE id = ?";
        Department d = null;

        try (
            Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                d = new Department();
                d.setId(rs.getString("id"));
                d.setName(rs.getString("Name"));
                d.setDescription(rs.getString("Description"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return d;
    }

    // =============================
    // 3. Thêm mới
    // =============================
    public boolean insert(Department d) {
        String sql = "INSERT INTO Departments (id, Name, Description) VALUES (?, ?, ?)";

        try (
            Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {

            ps.setString(1, d.getId());
            ps.setString(2, d.getName());
            ps.setString(3, d.getDescription());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // =============================
    // 4. Cập nhật
    // =============================
    public boolean update(Department d) {
        String sql = "UPDATE Departments SET Name = ?, Description = ? WHERE id = ?";

        try (
            Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {

            ps.setString(1, d.getName());
            ps.setString(2, d.getDescription());
            ps.setString(3, d.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // =============================
    // 5. Xóa theo ID
    // =============================
    public boolean delete(String id) {
        String sql = "DELETE FROM Departments WHERE id = ?";

        try (
            Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {

            ps.setString(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    // =============================
    // 6. Tìm kiếm theo ID / Name / Description (LIKE)
    // =============================
    public List<Department> search(String keyword) {
        List<Department> list = new ArrayList<>();

        String sql = "SELECT * FROM Departments "
                   + "WHERE id LIKE ? OR Name LIKE ? OR Description LIKE ?";

        try (
            Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {

            String kw = "%" + (keyword == null ? "" : keyword.trim()) + "%";

            ps.setString(1, kw);
            ps.setString(2, kw);
            ps.setString(3, kw);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Department d = new Department();
                d.setId(rs.getString("id"));
                d.setName(rs.getString("Name"));
                d.setDescription(rs.getString("Description"));

                list.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}

