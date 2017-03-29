set serveroutput on
CREATE OR REPLACE PROCEDURE deleteStudent(student_sid in Students.sid%type)
IS
cursor c2 is 
   select sid from Students where 
   Students.sid = student_sid;
   c2_rec Students.sid%type;
   invalid_student_id exception;
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
   dbms_output.put_line('The sid is invalid');
END;
/