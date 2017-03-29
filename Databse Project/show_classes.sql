set serveroutput on;
Create or replace procedure show_classes is
   cursor c1 is
   select classid,dept_code,course_no,sect_no,year,semester,limit,class_size from classes;
   c1_rec classes%rowtype;
begin
   open c1;
   fetch c1 into c1_rec;
   while c1%found loop
   dbms_output.put_line(c1_rec.classid||','||c1_rec.dept_code||','||c1_rec.course_no||','||
   c1_rec.sect_no||','||c1_rec.year||','||c1_rec.semester||','||c1_rec.limit||','||c1_rec.class_size);
   fetch c1 into c1_rec;
   end loop;
   close c1;
end;
/