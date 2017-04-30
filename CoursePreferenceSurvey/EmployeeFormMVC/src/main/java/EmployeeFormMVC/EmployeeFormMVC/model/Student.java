package EmployeeFormMVC.EmployeeFormMVC.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {
	@Override
	public String toString() {
		return "Student [studentID=" + studentID + ", email=" + email + ", name=" + name + ", password=" + password
				+ "]";
	}
	@Id
	 private int studentID;
	 private String email;
	 private String name;
	 private String password;
 public int getStudentID() {
		return studentID;
	}
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
