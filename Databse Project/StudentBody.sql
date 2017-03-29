create or replace package body StudentRegistration as 
function show_students 
   return ref_cursor1 is
   rc1 ref_cursor1;
begin
   open rc1 for 
     select sid, firstname, lastname,status,gpa,email from Students;
   return rc1;
   close rc1;
end;

function show_enrollments 
   return ref_cursor2 is
   rc2 ref_cursor2;
begin
   open rc2 for 
     select sid,classid,lgrade from enrollments;
   return rc2;
   close rc2;
end;

function show_prereq 
   return ref_cursor3 is
   rc3 ref_cursor3;
begin
   open rc3 for 
    select dept_code,course_no,pre_dept_code,pre_course_no from Prerequisites;
   return rc3;
   close rc3;
end;

function show_courses 
   return ref_cursor5 is
   rc5 ref_cursor5;
begin
   open rc5 for 
     select dept_code,course_no,title from Courses;
   return rc5;
   close rc5;
end;


function show_classes
   return ref_cursor7 is
   rc7 ref_cursor7;
begin
   open rc7 for 
     select classid,dept_code,course_no,sect_no,year,semester,limit,class_size from classes;
   return rc7;
   close rc7;
end;


function show_logs
   return ref_cursor8 is
   rc8 ref_cursor8;
begin
   open rc8 for 
     select * from logs;
   return rc8;
   close rc8;
end;

PROCEDURE insertInStudents(
     psid IN  students.SID%TYPE,
     pfirstname IN  students.FIRSTNAME%TYPE,
     plastname IN students.LASTNAME%TYPE,
     pstatus IN students.STATUS%TYPE,
     pgpa IN students.GPA%TYPE,
     pemail IN students.EMAIL%TYPE)
AS
BEGIN
  INSERT INTO STUDENTS(SID,FIRSTNAME,LASTNAME,STATUS,GPA,EMAIL)
  VALUES (psid,pfirstname ,plastname ,pstatus,pgpa ,pemail );
END;

-- procedure show_enrollments is
--    cursor c1 is
--    select sid,classid,lgrade from enrollments;
--    c1_rec enrollments%rowtype;
-- begin
--    open c1;
--    fetch c1 into c1_rec;
--    while c1%found loop
--    dbms_output.put_line(c1_rec.sid||','||c1_rec.classid||','||c1_rec.lgrade);
--    fetch c1 into c1_rec;
--    end loop;
--    close c1;
-- end;


--Query4 BODY
function showfunction_query4(student_sid in Students.sid%type) 
   return ref_cursor4 is
   cursor c2 is 
   select sid from Students where 
   Students.sid = student_sid;
   c2_rec Students.sid%type;
   cursor c3 is 
   select sid from Enrollments where 
   Enrollments.sid = student_sid;
   c3_rec Enrollments.sid%type; 
     rc4 ref_cursor4;
begin
  open c2;
   open c3;
   fetch c2 into c2_rec;
   fetch c3 into c3_rec;
   if(c2%notfound) then 
   raise invalid_student_id;
   end if;
   if(c3%notfound) then 
   raise student_not_enrolled;
   end if;
   open rc4 for 
 select Students.sid, lastname, status, Classes.classid, Classes.dept_code||''||Classes.course_no deptCourse, title, year, semester from 
  Students, Enrollments, Classes, Courses where 
  Students.sid=Enrollments.sid and
  Enrollments.classid= Classes.classid and 
  Classes.dept_code=Courses.dept_code and 
  Classes.course_no=Courses.course_no and 
  Students.sid = student_sid and 
  Enrollments.sid = student_sid;
     return rc4;
   close rc4;
close c2;
close c3;

exception
when student_not_enrolled then 
  raise_application_error(-20001, 'Student has not taken any courses');
   dbms_output.put_line('Student has not taken any courses');
   when invalid_student_id then 
raise_application_error(-20002, 'Student sid is invalid');
   dbms_output.put_line('Student sid is invalid');
end;

--Query 6

function showfunction_query6(pclassidIn IN  classes.classid%TYPE) 
   return ref_cursor6 is
cursor c2 is 
   select classid from classes where 
   classid = pclassidIn;
   c2_rec classes.classid%type;
   cursor c3 is 
   select classid from Enrollments where 
   Enrollments.classid = pclassidIn;
   c3_rec Enrollments.classid%type;
   rc6 ref_cursor6;
begin
   open c2;
   open c3;
   fetch c2 into c2_rec;
   fetch c3 into c3_rec;

   if(c2%notfound) then 
   raise invalid_class_id;
   end if;

   if(c3%notfound) then 
   raise no_student_enrolled;
   end if;

    open rc6 for 
Select s.sid,s.lastname,c.classid,r.title,c.semester,c.year from students s, enrollments e,classes c, courses r 
where s.sid=e.sid and e.classid=c.classid and
c.dept_code=r.dept_code and c.course_no=r.course_no and c.classid=pclassidIn;
     return rc6;

   close rc6;
   close c2;
   close c3;
exception
   when no_student_enrolled  then 
   raise_application_error(-20001, 'No student is enrolled in the class');
   dbms_output.put_line('No student is enrolled in the class');
   when invalid_class_id then 
   raise_application_error(-20002, 'The classid is invalid');
   dbms_output.put_line('The classid is invalid');
end;



--Query8 

procedure show_query8(student_sid in Students.sid%type,class_id in Classes.classid%type) is
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
      raise_application_error(-20001, 'The sid is invalid');
   dbms_output.put_line('The sid is invalid');
   when invalid_class_id then 
      raise_application_error(-20002, 'The class is invalid');
   dbms_output.put_line('The class is invalid');
    when student_not_enrolled then 
       raise_application_error(-20003, 'The student is not enrolled in the class');
   dbms_output.put_line('The student is not enrolled in the class');
   when student_drop_violation then
      raise_application_error(-20004, 'The drop is not permitted as another class uses it as a prerequisite');
   dbms_output.put_line('The drop is not permitted as another class uses it as a prerequisite');
end;

--Query 9 

PROCEDURE deleteStudent(student_sid in Students.sid%type)
IS
cursor c2 is 
   select sid from Students where 
   Students.sid = student_sid;
   c2_rec Students.sid%type;

BEGIN
  open c2;
   fetch c2 into c2_rec;
   if(c2%notfound) then 
   raise invalid_student_id;
   end if; 
  DELETE from students where sid = student_sid;
  close c2;
exception
   when invalid_student_id then 
   raise_application_error(-20001, 'The sid is invalid');
   dbms_output.put_line('The sid is invalid');
END;

--Query7
procedure student_enroll(student_sid in Students.sid%type, classid_ref in Classes.classid%type) is

   cursor c1 is 
   select Enrollments.sid, Enrollments.classid, Classes.dept_code, Classes.course_no, Classes.year, Classes.semester,
   Classes.class_size, Classes.limit , Classes.sect_no from Enrollments,Classes where
   Enrollments.sid=student_sid and 
   Enrollments.classid=classid_ref and 
   Enrollments.classid=Classes.classid;
   c1_rec c1%rowtype;
   
   cursor c2 is 
   select Enrollments.sid, Enrollments.classid, Classes.year, Classes.semester from  Enrollments, Classes where 
   Enrollments.sid = student_sid and 
   Enrollments.classid = Classes.classid;
   c2_rec c2%rowtype;
   
   cursor c3 is 
   select sid from Enrollments where 
   Enrollments.sid = student_sid and 
   Enrollments.classid=classid_ref;
   c3_rec Enrollments.sid%type; 
   
   cursor c4 is 
   select * from Classes where classid=classid_ref; 
   c4_rec Classes%rowtype;
   
   cursor c5 is 
   select Prerequisites.pre_dept_code, Prerequisites.pre_course_no, year, semester, Classes.classid from Classes, Prerequisites where 
   Classes.classid = classid_ref and 
   Classes.course_no=Prerequisites.course_no and 
   Classes.dept_code=Prerequisites.dept_code;
   c5_rec c5%rowtype;
   
   cursor c6 is 
   select Enrollments.sid, Enrollments.lgrade, Classes.classid, Classes.dept_code, Classes.course_no from Classes, Enrollments where 
   Enrollments.sid = student_sid and 
   Enrollments.classid=Classes.classid;
   c6_rec c6%rowtype;
   
   cursor c7 is 
   select pre_dept_code, pre_course_no, Prerequisites.dept_code, Prerequisites.course_no from Prerequisites, Classes where 
   Classes.classid=classid_ref and 
   Classes.dept_code = Prerequisites.dept_code and 
   Classes.course_no = Prerequisites.course_no;
   c7_rec c7%rowtype;
 
   
   temp_year Classes.year%type;
   temp_semester Classes.semester%type;
   temp_classid Classes.classid%type;
   temp_count number;
   temp_lgrade Enrollments.lgrade%type;
   temp_class_size Classes.class_size%type;
   temp_limit Classes.limit%type;
   flag boolean;
   

begin
 temp_count:= 0;

 open c1;
 open c2;
 open c3;
 open c4;
 open c5;
 open c6;
 open c7;
 fetch c1 into c1_rec;
 fetch c2 into c2_rec;
 fetch c3 into c3_rec;
 fetch c4 into c4_rec;
 fetch c5 into c5_rec;
 fetch c6 into c6_rec; 
 fetch c7 into c7_rec;
 if(c2%notfound) then 
 raise invalid_student_id;
 end if;
 
 if(c4%notfound) then 
 raise invalid_class_id;
 end if;
 
  select Classes.year, Classes.semester, Classes.classid into temp_year, temp_semester, temp_classid from Classes where classid=classid_ref;
 select class_size, limit into temp_class_size, temp_limit from Classes where classid=classid_ref;

 if(temp_class_size = temp_limit) then 
 raise class_full;
 end if;
 
 if(c3%found) then 
 raise student_already_in_class;
 end if;
 
 while c2%found loop
    if(c2_rec.year = temp_year and c2_rec.semester=temp_semester and c2_rec.classid<>temp_classid) then
  temp_count:= temp_count +1;
    end if;
 fetch c2 into c2_rec;
 end loop;
 
 if(temp_count=2) then 
  insert into Enrollments(sid,classid,lgrade) values(student_sid, classid_ref, null);
raise overload;
 dbms_output.put_line('You are overloaded');
 end if;
  
 if(temp_count=3) then 
 raise three_class;
 end if;
 
 while c5%found loop
    if(c5_rec.year = temp_year and c5_rec.semester=temp_semester and c5_rec.classid<>temp_classid) then
  temp_count:= temp_count +1;
    end if;
 fetch c5 into c5_rec;
 end loop;
 
 
   while c7%found loop
   dbms_output.put_line(c7_rec.dept_code||' '||c7_rec.course_no||' '||c7_rec.pre_dept_code||' '||c7_rec.pre_course_no);
 
 for c in (select lgrade into temp_lgrade from Enrollments, Classes where 
 Enrollments.sid = student_sid and 
 Classes.dept_code = c7_rec.pre_dept_code and 
 Classes.course_no = c7_rec.pre_course_no and 
 Enrollments.classid = Classes.classid)
loop
temp_lgrade:=c.lgrade;
dbms_output.put_line(temp_lgrade);
 if(temp_lgrade<>'A' or temp_lgrade<>'B' or temp_lgrade<>'C' or temp_lgrade<>'D') then 
raise prereq_not_complete;
 end if;
end loop;
 fetch c7 into c7_rec;
 --temp_lgrade:=null;
 end loop;
  
 close c1;
 close c2;
 close c3;
 close c4; 
 close c5; 
 close c6; 
 close c7; 
 
 insert into Enrollments(sid,classid,lgrade) values(student_sid, classid_ref, null);
exception  
   when class_full then 
   raise_application_error(-20001, 'The class is closed');
 --  dbms_output.put_line('Class is closed');   
   when invalid_student_id then 
   raise_application_error(-20002, 'The sid is invalid');
 --  dbms_output.put_line('The student sid is invalid');
   when invalid_class_id then 
   raise_application_error(-20003, 'The classid is invalid');
--   dbms_output.put_line('Classid is invalid');
   when student_already_in_class then 
   raise_application_error(-20004, 'The student is already in the class.');
 --  dbms_output.put_line('The student is already in the class.');
   when three_class then 
   raise_application_error(-20005, 'Students cannot be enrolled in more than three classes in the same semester');
 --  dbms_output.put_line('Students cannot be enrolled in more than three classes in the same semester.');
   when prereq_not_complete then 
   raise_application_error(-20006, 'Prerequisite courses have not been completed');
 --  dbms_output.put_line('Prerequisite not complete');
   when overload then 
   raise_application_error(-20007, 'You are overloaded');
 --  dbms_output.put_line('You are overloaded');
end;

end;
/
