package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Employees;
import util.DBConnect;

public class EmployeesDAO {

    // 1. findAll
    public List<Employees> findAll() {
        List<Employees> list = new ArrayList<>();
        String sql = "SELECT * FROM Employees";

        try (
            Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                Employees e = mapRowToEmployee(rs);
                list.add(e);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    // 2. findById
    public Employees findById(String id) {
        String sql = "SELECT * FROM Employees WHERE Id = ?";
        Employees e = null;
        try (
            Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    e = mapRowToEmployee(rs);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return e;
    }

    // 3. insert
    public boolean insert(Employees e) {
        String sql = "INSERT INTO Employees (Id, Password, Fullname, Photo, Gender, Birthday, Salary, DepartmentId) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (
            Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, e.getId());
            ps.setString(2, e.getPassword());
            ps.setString(3, e.getFullname());
            ps.setString(4, e.getPhoto());

            // Gender: model stores "1"/"0" as String — convert to boolean for BIT column
            String gender = e.getGender();
            if (gender != null && (gender.equals("1") || gender.equalsIgnoreCase("true"))) {
                ps.setBoolean(5, true);
            } else if (gender != null && gender.equals("0")) {
                ps.setBoolean(5, false);
            } else {
                ps.setNull(5, java.sql.Types.BIT);
            }

            // Birthday
            String bd = e.getBirthDay();
            if (bd != null && !bd.trim().isEmpty()) {
                try {
                    Date d = Date.valueOf(bd.trim());
                    ps.setDate(6, d);
                } catch (IllegalArgumentException ex) {
                    ps.setNull(6, java.sql.Types.DATE);
                }
            } else {
                ps.setNull(6, java.sql.Types.DATE);
            }

            // Salary
            String sal = e.getSalary();
            if (sal != null && !sal.trim().isEmpty()) {
                try {
                    double v = Double.parseDouble(sal.trim());
                    ps.setDouble(7, v);
                } catch (NumberFormatException nfe) {
                    ps.setNull(7, java.sql.Types.DOUBLE);
                }
            } else {
                ps.setNull(7, java.sql.Types.DOUBLE);
            }

            ps.setString(8, e.getDepartmentId());

            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // 4. update
    public boolean update(Employees e) {
        String sql = "UPDATE Employees SET Password = ?, Fullname = ?, Photo = ?, Gender = ?, Birthday = ?, Salary = ?, DepartmentId = ? WHERE Id = ?";
        try (
            Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, e.getPassword());
            ps.setString(2, e.getFullname());
            ps.setString(3, e.getPhoto());

            String gender = e.getGender();
            if (gender != null && (gender.equals("1") || gender.equalsIgnoreCase("true"))) {
                ps.setBoolean(4, true);
            } else if (gender != null && gender.equals("0")) {
                ps.setBoolean(4, false);
            } else {
                ps.setNull(4, java.sql.Types.BIT);
            }

            String bd = e.getBirthDay();
            if (bd != null && !bd.trim().isEmpty()) {
                try {
                    Date d = Date.valueOf(bd.trim());
                    ps.setDate(5, d);
                } catch (IllegalArgumentException ex) {
                    ps.setNull(5, java.sql.Types.DATE);
                }
            } else {
                ps.setNull(5, java.sql.Types.DATE);
            }

            String sal = e.getSalary();
            if (sal != null && !sal.trim().isEmpty()) {
                try {
                    double v = Double.parseDouble(sal.trim());
                    ps.setDouble(6, v);
                } catch (NumberFormatException nfe) {
                    ps.setNull(6, java.sql.Types.DOUBLE);
                }
            } else {
                ps.setNull(6, java.sql.Types.DOUBLE);
            }

            ps.setString(7, e.getDepartmentId());
            ps.setString(8, e.getId());

            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // 5. delete
    public boolean delete(String id) {
        String sql = "DELETE FROM Employees WHERE Id = ?";
        try (
            Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // 6. search (id, fullname, departmentId)
    public List<Employees> search(String keyword) {
        List<Employees> list = new ArrayList<>();
        String sql = "SELECT * FROM Employees WHERE Id LIKE ? OR Fullname LIKE ? OR DepartmentId LIKE ?";
        String kw = "%" + (keyword == null ? "" : keyword.trim()) + "%";

        try (
            Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, kw);
            ps.setString(2, kw);
            ps.setString(3, kw);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Employees e = mapRowToEmployee(rs);
                    list.add(e);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    // Helper: map ResultSet row -> Employees object
    private Employees mapRowToEmployee(ResultSet rs) throws Exception {
        Employees e = new Employees();
        e.setId(rs.getString("Id"));
        e.setPassword(rs.getString("Password"));
        e.setFullname(rs.getString("Fullname"));
        e.setPhoto(rs.getString("Photo"));

        // Gender BIT -> "1"/"0"
        try {
            boolean g = rs.getBoolean("Gender");
            e.setGender(g ? "1" : "0");
        } catch (Exception ex) {
            String gStr = rs.getString("Gender");
            if (gStr != null && (gStr.equals("1") || gStr.equalsIgnoreCase("true"))) e.setGender("1");
            else e.setGender("0");
        }

        Date bd = rs.getDate("Birthday");
        e.setBirthDay(bd == null ? "" : bd.toString());

        // Salary as string
        try {
            double s = rs.getDouble("Salary");
            if (rs.wasNull()) e.setSalary("");
            else e.setSalary(String.valueOf(s));
        } catch (Exception ex) {
            e.setSalary(rs.getString("Salary"));
        }

        e.setDepartmentId(rs.getString("DepartmentId"));

        return e;
    }
}
