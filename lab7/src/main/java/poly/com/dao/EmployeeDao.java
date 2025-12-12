package poly.com.dao;

import poly.com.model.Employee;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    public void insert(Employee e) {
        String sql = "INSERT INTO Employees(Id, Password, Fullname, Gender, Birthday, Salary, DepartmentId, Photo) VALUES(?,?,?,?,?,?,?,?)";
        try (Connection conn = Connectdao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getId());
            ps.setString(2, e.getPassword());
            ps.setString(3, e.getFullname());
            ps.setBoolean(4, e.isGender());
            ps.setDate(5, e.getBirthday() == null ? null : Date.valueOf(e.getBirthday()));
            ps.setDouble(6, e.getSalary());
            ps.setString(7, e.getDepartmentId());
            ps.setString(8, e.getPhoto());
            ps.executeUpdate();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    public void update(Employee e) {
        String sql = "UPDATE Employees SET Password=?, Fullname=?, Gender=?, Birthday=?, Salary=?, DepartmentId=?, Photo=? WHERE Id=?";
        try (Connection conn = Connectdao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getPassword());
            ps.setString(2, e.getFullname());
            ps.setBoolean(3, e.isGender());
            ps.setDate(4, e.getBirthday() == null ? null : Date.valueOf(e.getBirthday()));
            ps.setDouble(5, e.getSalary());
            ps.setString(6, e.getDepartmentId());
            ps.setString(7, e.getPhoto());
            ps.setString(8, e.getId());
            ps.executeUpdate();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    public void delete(String id) {
        String sql = "DELETE FROM Employees WHERE Id=?";
        try (Connection conn = Connectdao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    public Employee findById(String id) {
        String sql = "SELECT * FROM Employees WHERE Id=?";
        try (Connection conn = Connectdao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (Exception ex) { ex.printStackTrace(); }
        return null;
    }

    public List<Employee> getAll() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM Employees ORDER BY Id";
        try (Connection conn = Connectdao.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (Exception ex) { ex.printStackTrace(); }
        return list;
    }

    public List<Employee> searchById(String keyword) {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM Employees WHERE Id LIKE ? ORDER BY Id";
        try (Connection conn = Connectdao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + (keyword==null?"":keyword) + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (Exception ex) { ex.printStackTrace(); }
        return list;
    }

    private Employee mapRow(ResultSet rs) throws SQLException {
        Employee e = new Employee();
        e.setId(rs.getString("Id"));
        e.setPassword(rs.getString("Password"));
        e.setFullname(rs.getString("Fullname"));
        e.setGender(rs.getBoolean("Gender"));
        Date d = rs.getDate("Birthday");
        e.setBirthday(d == null ? null : d.toLocalDate());
        e.setSalary(rs.getDouble("Salary"));
        e.setDepartmentId(rs.getString("DepartmentId"));
        e.setPhoto(rs.getString("Photo"));
        return e;
    }
}
