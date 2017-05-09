package university.studentData.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="grades")
public class Grades {
	@Id
	private int id;
	private char lgrade;
	private int ngrade;
	public char getLgrade() {
		return lgrade;
	}
	public void setLgrade(char lgrade) {
		this.lgrade = lgrade;
	}
	public int getNgrade() {
		return ngrade;
	}
	public void setNgrade(int ngrade) {
		this.ngrade = ngrade;
	}
}
