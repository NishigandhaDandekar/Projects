set serveroutput on 
Create or replace procedure show_query4(student_sid in Students.sid%type) is
   cursor c1 is
   select Students.sid, lastname, status, Classes.classid, Classes.dept_code||''||Classes.course_no deptCourse, title, year, semester from 
	Students, Enrollments, Classes, Courses where 
	Students.sid=Enrollments.sid and
	Enrollments.classid= Classes.classid and 
	Classes.dept_code=Courses.dept_code and 
	Classes.course_no=Courses.course_no and 
	Students.sid = student_sid and 
	Enrollments.sid = student_sid;
    c1_rec c1%rowtype;
   cursor c2 is 
   select sid from Students where 
   Students.sid = student_sid;
   c2_rec Students.sid%type;
   cursor c3 is 
   select sid from Enrollments where 
   Enrollments.sid = student_sid;
   c3_rec Enrollments.sid%type;	
   invalid_student_id exception;
   student_not_enrolled exception;
begin
   open c1;
   open c2;
   open c3;
   fetch c1 into c1_rec;
   fetch c2 into c2_rec;
   fetch c3 into c3_rec;
   if(c2%notfound) then 
   raise invalid_student_id;
   end if;
   if(c3%notfound) then 
   raise student_not_enrolled;
   end if;
   dbms_output.put_line('SID LASTNAME STATUS CLASSID DEPT_COURSE TITLE YEAR SEMESTER');
   while c1%found loop
   dbms_output.put_line(c1_rec.sid ||' '|| c1_rec.lastname ||' '|| c1_rec.status||' '|| c1_rec.classid||' '|| c1_rec.deptCourse ||' '|| c1_rec.title||' '|| c1_rec.year||' '|| 
c1_rec.semester);
   fetch c1 into c1_rec;
   end loop;
   close c1;
   close c2;
   close c3;
exception
   when student_not_enrolled then 
   dbms_output.put_line('Student has not taken any courses');
   when invalid_student_id then 
   dbms_output.put_line('Student sid is invalid');
end;
/
