package university.studentData.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="classes")
public class Classes {
	@Id
	private String classid;
	private int sect_no;
	private int year;
	private String semester;
	private int class_limit;
	private int class_size;
//	@Column(name="dept_code")
//	private String dept_code;
//	@Column(name="course_no")
//	private int course_no;
	@ManyToOne 
	private Courses course;
	public String getClassid() {
		return classid;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}
	public int getSect_no() {
		return sect_no;
	}
	public void setSect_no(int sect_no) {
		this.sect_no = sect_no;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public int getClass_limit() {
		return class_limit;
	}
	public void setClass_limit(int class_limit) {
		this.class_limit = class_limit;
	}
	public int getClass_size() {
		return class_size;
	}
	public void setClass_size(int class_size) {
		this.class_size = class_size;
	}
	public Courses getCourse() {
		return course;
	}
	public void setCourse(Courses course) {
		this.course = course;
	}


}
