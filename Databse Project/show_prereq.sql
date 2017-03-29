set serveroutput on;
Create or replace procedure show_prerequisites is
   cursor c1 is
   select dept_code,course_no,pre_dept_code,pre_course_no from Prerequisites;
   c1_rec Prerequisites%rowtype;
begin
   open c1;
   fetch c1 into c1_rec;
   while c1%found loop
   dbms_output.put_line(c1_rec.dept_code || ',' || c1_rec.course_no || ',' || c1_rec.pre_dept_code||','||c1_rec.pre_course_no);
   fetch c1 into c1_rec;
   end loop;
   close c1;
end;
/
