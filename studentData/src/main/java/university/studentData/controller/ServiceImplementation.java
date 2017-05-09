package university.studentData.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import university.studentData.model.Classes;
import university.studentData.model.Enrollments;
import university.studentData.model.Students;

public class ServiceImplementation {
	Session session = null;

	public Students getStudentById(String id) {
		session = GetSessionFactory.getFactory().openSession();
		String getStudents = "from Students s where s.sid = :sid";
		session.beginTransaction();
		org.hibernate.Query query = session.createQuery(getStudents);
		query.setParameter("sid", id);
		List<Students> student = query.list();
		session.getTransaction().commit();
		session.close();
		return student.get(0);
	}
	public List<Enrollments> getEnrollmentsById(String id) {
		session = GetSessionFactory.getFactory().openSession();
		String getEnrollments = "from Enrollments e where e.sid = :sid";
		session.beginTransaction();
		org.hibernate.Query query = session.createQuery(getEnrollments);
		query.setParameter("sid", id);
		List<Enrollments> enrollments = query.list();
		session.getTransaction().commit();
		session.close();
		return enrollments;
	}
	public List<Enrollments> getEnrollmentsByClassId(String id) {
		session = GetSessionFactory.getFactory().openSession();
		String getEnrollments = "from Enrollments e where e.classid = :classid";
		session.beginTransaction();
		org.hibernate.Query query = session.createQuery(getEnrollments);
		query.setParameter("classid", id);
		List<Enrollments> enrollments = query.list();
		session.getTransaction().commit();
		session.close();
		return enrollments;
	}
	
	public List<Students> getStudents() {
		session = GetSessionFactory.getFactory().openSession();
		String getStudents = "From Students";
		session.beginTransaction();
		org.hibernate.Query query = session.createQuery(getStudents);
		List<Students> studentList = query.list();
		session.getTransaction().commit();
		session.close();
		return studentList;
		// factory.close();
	}
	
	public String addStudent(Students student) {
		session = GetSessionFactory.getFactory().openSession();
		session.beginTransaction();
		session.save(student);
		session.getTransaction().commit();
		session.close();
		return "Student added";
	}
	public String removeStudent(String sid) {
		session = GetSessionFactory.getFactory().openSession();
		String getStudents = "delete from Students s where s.sid = :sid";
		session.beginTransaction();
		org.hibernate.Query query = session.createQuery(getStudents);
		query.setParameter("sid", sid);
		int row = query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		return "Student removed by id "+sid+" Number of row(s) affected "+row;
	}
	public List<Classes> getClassByClassId(String id){
		session = GetSessionFactory.getFactory().openSession();
		String getClasses = "From Classes c where c.classid= :classid";
		session.beginTransaction();
		org.hibernate.Query query = session.createQuery(getClasses);
		query.setParameter("classid", id);
		List<Classes> classList = query.list();
		session.getTransaction().commit();
		session.close();
		return classList;
		// factory.close();
	}
}
