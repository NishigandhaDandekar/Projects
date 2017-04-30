package EmployeeFormMVC.EmployeeFormMVC.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="takessurvey")
public class SurveyTaken {
	@Id
	private int studentID;
private String survey_taken;

private int survey_id;

public int getSurvey_id() {
	return survey_id;
}
public void setSurvey_id(int survey_id) {
	this.survey_id = survey_id;
}
public int getStudentID() {
	return studentID;
}
public void setStudentID(int studentID) {
	this.studentID = studentID;
}
public String getSurvey_taken() {
	return survey_taken;
}
public void setSurvey_taken(String survey_taken) {
	this.survey_taken = survey_taken;
}

}
