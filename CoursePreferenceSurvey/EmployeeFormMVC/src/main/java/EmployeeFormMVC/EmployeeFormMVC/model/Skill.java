package EmployeeFormMVC.EmployeeFormMVC.model;

import java.util.List;

public class Skill {
	public  String skillName;
	public  List<String> synonyms;
	
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public List<String> getSynonyms() {
		return synonyms;
	}
	public void setSynonyms(List<String> synonyms) {
		this.synonyms = synonyms;
	}
}
