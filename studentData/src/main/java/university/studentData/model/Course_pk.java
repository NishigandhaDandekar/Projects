package university.studentData.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.IdClass;


public class Course_pk implements Serializable{
	@Column(name="dept_code")
	private String dept_code;
	@Column(name="course_no")
	private int course_no;
	
}
