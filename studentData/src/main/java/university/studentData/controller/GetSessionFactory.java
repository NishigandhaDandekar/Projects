package university.studentData.controller;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetSessionFactory {
	private static GetSessionFactory sessionFactory;
	private static SessionFactory factory;
	private GetSessionFactory(){
		
	}
	public static SessionFactory getFactory(){
		if(sessionFactory==null)
			sessionFactory=new GetSessionFactory();
		if(factory ==null)
			factory = sessionFactory.getSession();
		return factory;
	}
	private SessionFactory getSession(){
		return new  Configuration().configure().buildSessionFactory();
	}	
}
