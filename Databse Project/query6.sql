Create or replace procedure show_query6(pclassidIn IN  classes.classid%TYPE)
is
cursor testc6 is
Select s.sid,s.lastname,c.classid,r.title,c.semester,c.year from students s, enrollments e,classes c, courses r 
where s.sid=e.sid and e.classid=c.classid and
c.dept_code=r.dept_code and c.course_no=r.course_no and c.classid=pclassidIn;
type query6_record_type is record
(psid Students.sid%type,
plname Students.lastname%type,
pclassid enrollments.classid%type,
ptitle courses.title%type,
psemester classes.semester%type,
pyear classes.year%type
);
query6rec query6_record_type;
cursor c2 is 
   select classid from classes where 
   classid = pclassidIn;
   c2_rec classes.classid%type;
   cursor c3 is 
   select classid from Enrollments where 
   Enrollments.classid = pclassidIn;
   c3_rec Enrollments.classid%type;
   invalid_class_id exception;
   no_student_enrolled exception;
   begin
   open c2;
   open c3;
   open testc6;
   fetch c2 into c2_rec;
   fetch c3 into c3_rec;
   fetch testc6 into query6rec;
   if(c2%notfound) then 
   raise invalid_class_id;
   end if;
   if(c3%notfound) then 
   raise no_student_enrolled;
   end if;
   while testc6%found loop
  dbms_output.put_line(query6rec.psid ||','|| query6rec.plname ||','||query6rec.pclassid||','||query6rec.ptitle ||','||query6rec.psemester ||','||query6rec.pyear);
   fetch testc6 into query6rec;
   end loop;
   close c2;
   close c3;
   close testc6;
exception
   when no_student_enrolled  then 
   dbms_output.put_line('No student is enrolled in the class');
   when invalid_class_id then 
   dbms_output.put_line('The classid is invalid');
end;
/