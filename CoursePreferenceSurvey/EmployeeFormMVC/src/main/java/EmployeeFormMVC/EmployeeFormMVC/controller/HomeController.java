package EmployeeFormMVC.EmployeeFormMVC.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import EmployeeFormMVC.EmployeeFormMVC.model.Admin;
import EmployeeFormMVC.EmployeeFormMVC.model.Preferences;
import EmployeeFormMVC.EmployeeFormMVC.model.Skill;
import EmployeeFormMVC.EmployeeFormMVC.model.Student;
import EmployeeFormMVC.EmployeeFormMVC.model.SurveyTaken;

@Controller
public class HomeController {
	Student student;
	Admin admin;
	Set<String> inputSkillSet;
	 public String stem(String input) {
         if(input.endsWith("'s")) {
             return input.substring(0,input.length()-2)+"s";
         }
         if(input.endsWith("s'")) {
             return input.substring(0,input.length()-2)+"s";
         }
         if((!input.endsWith("aies") && !input.endsWith("eies"))) {
             if(input.endsWith("ies"))
                 return input.substring(0,input.length()-3)+"y";
         }
         if((!input.endsWith("us") && !input.endsWith("ss"))) {
             if(input.endsWith("s"))
                 return input.substring(0,input.length()-1);
         }
         if(input.endsWith("ing")) {
             return input.substring(0,input.length()-3);
         }
             return input;
 }
	 public  String generateHash(String input){
	        try {
	            input = input +"First hashing project";
	            MessageDigest md = MessageDigest.getInstance("SHA");
	            md.update(input.getBytes());
	            byte[] b = md.digest();
	            StringBuffer sb= new StringBuffer();
	            for(byte b1:b){
	                sb.append(Integer.toHexString(b1 & 0xFF));
	            }
	            return sb.toString();
	        } catch (NoSuchAlgorithmException ex) {
	            
	        }
	        return null;
	    }
	public String[] parseInput(String[] inputString) {
		Set<String> inputSet = new HashSet<String>();
		inputSet.add("Computer");
		inputSet.add("Computers");
		inputSet.add("Technologies");
		inputSet.add("Technology");
		for(int i=0;i<inputString.length;i++){
			inputString[i]=stem(inputString[i]);
			for(String sample:inputSet){
				if(inputString[i].contains(sample)){
					inputString[i]=inputString[i].replaceAll(sample, "");
					break;
				}
			}
		}
		return inputString;
	}
	public boolean compareSkills(String input, List<Preferences> results){
		for(Preferences result:results){
			if(input.trim().equalsIgnoreCase(result.getName()))
				return true;
		}
		return false;
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ModelAndView save( @RequestParam("skills") ArrayList<String> skills,@RequestParam("skillInput")String inputSkill) throws IOException {	
	
	
		if(student!=null){
			inputSkillSet = new HashSet<String>();
			inputSkillSet.addAll(skills);
			boolean flag=false;
			SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = factory.openSession();
			session.beginTransaction();
				String hql = "FROM Preferences";
				org.hibernate.Query query = session.createQuery(hql);
				List<Preferences> results = query.list();		
			session.getTransaction().commit();
			session.close();
			factory.close();
			if(inputSkill.trim().length()>0){
				String[] skillInput = inputSkill.split(",");
				List<String> finalSkillInput = new ArrayList<String>();
				for(String input: skillInput){
					if(!compareSkills(input, results)){
						finalSkillInput.add(input);
					}
				}
				
				Skill[] skill = new Skill[finalSkillInput.size()];
				for(int i=0;i<skill.length;i++){
					skill[i]=new Skill();
					((Skill)skill[i]).setSkillName(finalSkillInput.get(i));
				}
				String parsedInput[] = parseInput(skillInput);

				for(int i=0;i<skill.length;i++){
					String[] temp = parsedInput[i].trim().split(" ");
					for(String tempString:temp){
						System.out.println("tempString"+tempString);
						for(Preferences resultPref:results) {
							if(resultPref.getName().contains(tempString)){
								System.out.println("result name"+resultPref.getName());
								if(skill[i].getSynonyms()!=null){
									List<String> list= skill[i].getSynonyms();
									list.add(resultPref.getName());
									skill[i].setSynonyms(list);
								}
								else{
									List <String> list = new ArrayList<String>();
									list.add(resultPref.getName());
									skill[i].setSynonyms(list);
								}
							}
						}
					}
					System.out.println(skill[i].getSkillName()+" "+skill[i].getSynonyms());
				}
			    
				List<Skill> skillList = new LinkedList<Skill>(Arrays.asList(skill));

				for(Skill skillTemp:skillList) {
					if(skillTemp.getSynonyms()==null){
						skillList.remove(skillTemp);
						inputSkillSet.add(skillTemp.getSkillName());
					}
				}
				System.out.println("SkillList"+skillList);
				if(skillList.isEmpty()){
					surveyExtended(new HashSet<String>(inputSkillSet));
					return new ModelAndView("surveyComplete");
				}
				else{
					return new ModelAndView("surveyExtended").addObject("results", skillList);
				}
			}
			else{
				surveyExtended(new HashSet<String>(inputSkillSet));
				return new ModelAndView("surveyComplete");
			}
			
			
					
			//	}	
			}
		return new ModelAndView("home1");
	}
	@RequestMapping(value="/surveyExtended")
	public ModelAndView surveyExtended(@RequestParam("skills") HashSet<String> skills) throws IOException{
		
		if(student!=null){
			inputSkillSet.addAll(skills);
			SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = factory.openSession();
			session.beginTransaction();
				String hql = "FROM Preferences";
				Query query = session.createQuery(hql);
				List<Preferences> results = query.list();
				int id=results.size()+1;
				for(Preferences result:results){
					if(inputSkillSet.contains(result.getName())){
						inputSkillSet.remove(result.getName());
						String update = "UPDATE Preferences SET number_of_hits="+(result.getNumber_of_hits()+1)+"WHERE name='"+result.getName()+"'";
						Query sqlQuery = session.createQuery(update);
						sqlQuery.executeUpdate();
					}
				}	
				if(!inputSkillSet.isEmpty()){
					for(String insertInput: inputSkillSet){
							Preferences p = new Preferences();
						//	p.setSurvey_id(1);
							p.setP_id(id);
							p.setName(insertInput);
							p.setNumber_of_hits(1);
							session.save(p);
							id++;
					}
				}
			session.getTransaction().commit();
			session.close();
			factory.close();
			return new ModelAndView("surveyComplete");
		}
		
		return new ModelAndView("home1");
	
	}
	@RequestMapping(value="/surveyComplete")
	public ModelAndView surveyComplete() throws IOException{
		if(student!=null)
		return new ModelAndView("surveyComplete");
		return new ModelAndView("home1");
	}
	@RequestMapping(value="/home")
	public ModelAndView home() throws IOException{
	    student=null;
	    admin=null;
		return new ModelAndView("home1");
	}
	@RequestMapping(value="/survey")
	public ModelAndView survey() throws IOException {	
	//	if(student!=null){
			System.out.println(student);
			boolean flag=false;
			SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = factory.openSession();
			session.beginTransaction();
			String hql = "FROM SurveyTaken";
			Query query = session.createQuery(hql);
			List<SurveyTaken> results = query.list();
			String hqlPreference = "FROM Preferences";
			Query queryPreference = session.createQuery(hqlPreference);
			List<Preferences> resultPreference = queryPreference.list();
			
			if(results!=null) {
				for(SurveyTaken result:results){
					if(student.getStudentID()==result.getStudentID())
						flag=true;
				}			
			}
			if(flag==false){
				SurveyTaken taken=new SurveyTaken();
				taken.setStudentID(student.getStudentID());
				taken.setSurvey_id(1);
				taken.setSurvey_taken("yes");
				session.save(taken);
			}
			session.getTransaction().commit();
			session.close();
			factory.close();
			if(flag==true)
				return new ModelAndView("surveyMenu").addObject("surveyFailure", "You have already submitted survey");
			else{	
				return new ModelAndView("survey").addObject("results", resultPreference);
			}
				
		//}
		//	return new ModelAndView("home1");
		
		}
	@RequestMapping(value="/adminLogin")
	public ModelAndView adminLogin() throws IOException {	
			admin=null;
			return new ModelAndView("adminLogin");
		}
	@RequestMapping(value="/studentLogin")
	public ModelAndView studentLogin() throws IOException {	
			student=null;
			return new ModelAndView("studentLogin");
		}
	@RequestMapping(value="/Logout")
	public ModelAndView logout(HttpSession httpSession) throws IOException {	
			student=null;
			System.out.println(httpSession);
			httpSession.invalidate();
			return new ModelAndView("home1");
		}
	@RequestMapping(value="/view", method=RequestMethod.POST)
	public ModelAndView survey(@RequestParam("uname")String username,@RequestParam("psw")String password,HttpSession httpSession) throws IOException {	
	//	if(admin!=null){
			SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = factory.openSession();
			session.beginTransaction();
			String hql = "from Admin";
			Query query = session.createQuery(hql);
			List<Admin> results = query.list();
			String resultQuery="from Preferences";
			Query result_query=session.createQuery(resultQuery);
			List<Preferences> result_pref=result_query.list();
			session.getTransaction().commit();
			session.close();
			factory.close();
			for(Admin result:results) {
				if(username.equals(result.getUserID())&&generateHash(password).equals(result.getPassword())){
					admin=result;
					httpSession.setAttribute("admin", result);
					int max=result_pref.get(0).getNumber_of_hits();
					List<String> popularCourses = new ArrayList<String>();
					for(Preferences p:result_pref){
						if(p.getNumber_of_hits()>max)
							max= p.getNumber_of_hits();
					}
					for(Preferences p:result_pref){
						if(p.getNumber_of_hits()==max)
							popularCourses.add(p.getName());
					}
					return new ModelAndView("view").addObject("results", result_pref).addObject("resultCourses", popularCourses);
				}	
			}
			return new ModelAndView("adminLogin").addObject("adminLoginFailure", "Incorrect username or password");
	//	}
	//	return new ModelAndView("home1");
	}
	/*Student Login page*/
	@RequestMapping(value="/studentHome", method=RequestMethod.POST)
	public ModelAndView studentHome(@RequestParam("uname")String username,@RequestParam("psw")String password, HttpSession httpSession) throws IOException {	
	//	if(student!=null){
			SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = factory.openSession();
			session.beginTransaction();
			String hql = "from Student";
			Query query = session.createQuery(hql);
			List<Student> results = query.list();
			session.getTransaction().commit();
			session.close();
			factory.close();
			for(Student result:results){
				if(username.equals(result.getEmail())&&generateHash(password).equals(result.getPassword())){
					student=result;
					httpSession.setAttribute("student", result);
					return new ModelAndView("surveyMenu");
				}
				
			}
			return new ModelAndView("studentLogin").addObject("studentLoginFailure", "Incorrect username or password");
	//	}
	//	return new ModelAndView("home1");
	}
}
