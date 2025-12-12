package poly.com.model;

import java.util.Date;

public class Staff {
    private String fullname;
    private Date birthday;
    private boolean gender;
    private String[] hobbies;
    private String country;
    private double salary;
    
    // --- Các thuộc tính thêm mới để sửa lỗi ---
    private String photo_file; // Phải đặt tên đúng như này để khớp với ${bean.photo_file}
    private boolean married;
    private String note;

    // Constructor mặc định
    public Staff() {
    }

    // --- Getter và Setter cho tất cả thuộc tính ---

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // --- Getter và Setter cho các thuộc tính mới (Quan trọng) ---

    public String getPhoto_file() {
        return photo_file;
    }

    public void setPhoto_file(String photo_file) {
        this.photo_file = photo_file;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}