package Feedback.CoursePreferenceFeedback.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name="Student")
public class Employee {
	@NotEmpty(message="Enter a value for name")
	private String name;
	@Id@GeneratedValue(strategy=GenerationType.AUTO) 
	private int studentID;
	@NotEmpty(message="Enter a value for email")@Email(message="Enter a valid email address") 
	private String email;
	 @NotNull(message="Select at least option")
	private ArrayList<String> skills;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<String> getSkills() {
		return skills;
	}
	public void setSkills(ArrayList<String> skills) {
		this.skills = skills;
	}
	
	
}
