package university.studentData.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import university.studentData.model.Classes;
import university.studentData.model.Enrollments;
import university.studentData.model.Students;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {
	ServiceImplementation service = new ServiceImplementation();
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    
	@GET
	@Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    @GET
    @Path("getStudents")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Students> getStudents(){
    	 return service.getStudents();
    }
    @GET
    @Path("getStudent/{studentID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Students getStudent(@PathParam("studentID") String studentID){
    	System.out.println(studentID);
   	   return service.getStudentById(studentID);
   }
   
    @DELETE
    @Path("removeStudent/{studentID}")
    @Produces(MediaType.TEXT_PLAIN)
    public String removeStudent(@PathParam("studentID")String student){
   	   return service.removeStudent(student);
   }
    @GET
    @Path("getEnrollmentsById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Enrollments> getEnrollmentsById(@PathParam("id")String id){
   	   return service.getEnrollmentsById(id);
   }
    @GET
    @Path("getEnrollmentsByClassId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Enrollments> getEnrollmentsByClassId(@PathParam("id")String id){
   	   return service.getEnrollmentsByClassId(id);
   }
    @GET
    @Path("getClassByClassId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Classes> getClassByClassId(@PathParam("id")String id){
   	   return service.getClassByClassId(id);
   }
}
