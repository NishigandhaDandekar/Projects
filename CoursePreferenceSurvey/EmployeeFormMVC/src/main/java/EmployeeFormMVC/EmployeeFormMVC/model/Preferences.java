package EmployeeFormMVC.EmployeeFormMVC.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="preferences")

public class Preferences {
	@Id @Column(name="id")
	private int p_id;
	private String name;
	private int number_of_hits;
//	private int survey_id=1;
	
	
//	public int getSurvey_id() {
//		return survey_id;
//	}
//	public void setSurvey_id(int survey_id) {
//		this.survey_id = survey_id;
//	}
	@Override
	public String toString() {
		return "Preferences [p_id=" + p_id + ", name=" + name + ", number_of_hits=" + number_of_hits + "]";
	}
	public int getNumber_of_hits() {
		return number_of_hits;
	}
	public void setNumber_of_hits(int number_of_hits) {
		this.number_of_hits = number_of_hits;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


}
