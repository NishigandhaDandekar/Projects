package university.studentData.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
@Entity
@Table(name="enrollments")
@IdClass(Enrollments_pk.class)
public class Enrollments {
	@Id
	public String sid;
	@Id
	public String classid;
	public String lgrade;
}
