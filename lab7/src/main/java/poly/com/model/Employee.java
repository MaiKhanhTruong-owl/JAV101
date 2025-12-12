package poly.com.model;

import java.time.LocalDate;

public class Employee {
    private String id;
    private String password;
    private String fullname;
    private boolean gender;
    private LocalDate birthday;
    private double salary;
    private String departmentId;
    private String photo; // đường dẫn tương đối (/uploads/...)

    public Employee() {}

    public Employee(String id, String password, String fullname, boolean gender,
                    LocalDate birthday, double salary, String departmentId, String photo) {
        this.id = id; this.password = password; this.fullname = fullname;
        this.gender = gender; this.birthday = birthday; this.salary = salary;
        this.departmentId = departmentId; this.photo = photo;
    }

    // getters + setters (omitted for brevity) — hãy generate hoặc copy
    // ... 
    public String getId(){return id;} public void setId(String id){this.id=id;}
    public String getPassword(){return password;} public void setPassword(String p){this.password=p;}
    public String getFullname(){return fullname;} public void setFullname(String n){this.fullname=n;}
    public boolean isGender(){return gender;} public void setGender(boolean g){this.gender=g;}
    public java.time.LocalDate getBirthday(){return birthday;} public void setBirthday(java.time.LocalDate b){this.birthday=b;}
    public double getSalary(){return salary;} public void setSalary(double s){this.salary=s;}
    public String getDepartmentId(){return departmentId;} public void setDepartmentId(String d){this.departmentId=d;}
    public String getPhoto(){return photo;} public void setPhoto(String p){this.photo=p;}
}
