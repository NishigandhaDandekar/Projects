set serveroutput on 
Create or replace procedure student_enroll(student_sid in Students.sid%type, classid_ref in Classes.classid%type) is

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
   
   invalid_student_id exception;
   invalid_class_id exception;
   class_full exception;
   student_already_in_class exception;
   three_class exception;
   prereq_not_complete exception;
   overload exception;
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
   dbms_output.put_line('The class is closed');   
   when invalid_student_id then 
   dbms_output.put_line('The sid is invalid');
   when invalid_class_id then 
   dbms_output.put_line('The classid is invalid.');
   when student_already_in_class then 
   dbms_output.put_line('The student is already in the class.');
   when three_class then 
   dbms_output.put_line('Students cannot be enrolled in more than three classes in the same semester.');
   when prereq_not_complete then 
   dbms_output.put_line('Prerequisite courses have not been completed.');
   when overload then 
   dbms_output.put_line('You are overloaded.');
end;
/
