set serveroutput on;
Create or replace procedure show_enrollments is
   cursor c1 is
   select sid,classid,lgrade from enrollments;
   c1_rec enrollments%rowtype;
begin
   open c1;
   fetch c1 into c1_rec;
   while c1%found loop
   dbms_output.put_line(c1_rec.sid||','||c1_rec.classid||','||c1_rec.lgrade);
   fetch c1 into c1_rec;
   end loop;
   close c1;
end;
/