set serveroutput on;
Create or replace procedure show_courses is
   cursor c1 is
   select dept_code,course_no,title from Courses;
   c1_rec Courses%rowtype;
begin
   open c1;
   fetch c1 into c1_rec;
   while c1%found loop
   dbms_output.put_line(c1_rec.dept_code || ',' || c1_rec.course_no || ',' || c1_rec.title);
   fetch c1 into c1_rec;
   end loop;
   close c1;
end;
/