package university.studentData.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="courses")
@IdClass(Course_pk.class)
public class Courses {
	@Id
	private String dept_code;
	@Id
	private int course_no;
	String title;
	@OneToMany
	private Collection<Classes> classes = new ArrayList<Classes>() ;

	public String getDept_code() {
		return dept_code;
	}
	public void setDept_code(String dept_code) {
		this.dept_code = dept_code;
	}
	public int getCourse_no() {
		return course_no;
	}
	public void setCourse_no(int course_no) {
		this.course_no = course_no;
	}
	public Collection<Classes> getClasses() {
		return classes;
	}
	public void setClasses(Collection<Classes> classes) {
		this.classes = classes;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
