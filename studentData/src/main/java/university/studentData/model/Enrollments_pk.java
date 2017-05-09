package university.studentData.model;

import java.io.Serializable;

import javax.persistence.Column;

public class Enrollments_pk implements Serializable{
	@Column(name="sid")
	public String sid;
	@Column(name="classid ")
	public String classid;
}
