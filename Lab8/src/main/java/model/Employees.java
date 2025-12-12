package model;

public class Employees {
	public String id;
	public String password;
	public String fullname;
	public String photo;
	public String gender;
	public String birthDay;
	public String salary;
	public String departmentId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public Employees(String id, String password, String fullname, String photo, String gender, String birthDay,
			String salary, String departmentId) {

		this.id = id;
		this.password = password;
		this.fullname = fullname;
		this.photo = photo;
		this.gender = gender;
		this.birthDay = birthDay;
		this.salary = salary;
		this.departmentId = departmentId;
	}

	public Employees() {

	}

}
