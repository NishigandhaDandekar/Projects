set serveroutput on 
Create or replace procedure show_query8(student_sid in Students.sid%type,class_id in Classes.classid%type) is
   dept classes.dept_code%type;
   cno  classes.course_no%type;  
   cursor c1 is
   select * from enrollments for update;
   c1_rec enrollments%rowtype;
   cursor c2 is 
   select sid from Students where 
   Students.sid = student_sid;
   c2_rec Students.sid%type;
   cursor c3 is
   select classid from Classes where
   Classes.classid=class_id;
   c3_rec Classes.classid%type;
   cursor c4 is 
   select sid from Enrollments where 
   sid = student_sid and classid=class_id;
   c4_rec Enrollments.sid%type;	
   cursor c5 is
   Select e.sid from enrollments e where e.sid=student_sid and e.classid IN
   (Select c.classid from classes c,prerequisites p where c.dept_code=p.dept_code and c.course_no=p.course_no and pre_dept_code=dept and pre_course_no=cno) ;
   c5_rec enrollments.sid%type;
   invalid_student_id exception;
   invalid_class_id exception;
   student_not_enrolled exception;
   student_drop_violation exception;
   begin
   open c1;
   open c2;
   open c3;
   open c4;
   open c5;
   fetch c5 into c5_rec;
   fetch c1 into c1_rec;
   fetch c2 into c2_rec;
   fetch c3 into c3_rec;
   fetch c4 into c4_rec;
   
   if(c2%notfound) then 
   raise invalid_student_id;
   end if;
   if(c3%notfound) then 
   raise invalid_class_id;
   end if;
   if(c4%notfound) then 
   raise student_not_enrolled;
   end if;
   if(c5%notfound) then 
   raise student_drop_violation;
   end if;
   for c1_rec in c1 loop
       if (c1_rec.sid=student_sid and c1_rec.classid=class_id) then
           delete from enrollments
           where current of c1;
       end if;
   end loop;
   close c1;
   close c2;
   close c3;
   close c4;
   close c5;
exception
   when invalid_student_id then 
   dbms_output.put_line('The sid is invalid');
   when invalid_class_id then 
   dbms_output.put_line('The class is invalid');
    when student_not_enrolled then 
   dbms_output.put_line('The student is not enrolled in the class');
   when student_drop_violation then
   dbms_output.put_line('The drop is not permitted as another class uses it as a prerequisite');
end;
/
