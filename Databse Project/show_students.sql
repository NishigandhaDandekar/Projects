set serveroutput on;
Create or replace procedure show_students is
   cursor c1 is
   select sid, firstname, lastname,status,gpa,email from Students;
   c1_rec Students%rowtype;
begin
   open c1;
   fetch c1 into c1_rec;
   while c1%found loop
   dbms_output.put_line(c1_rec.sid || ',' || c1_rec.firstname || ',' || c1_rec.lastname||',' ||c1_rec.status||',' ||c1_rec.gpa||',' ||c1_rec.email);
   fetch c1 into c1_rec;
   end loop;
   close c1;
end;
/
