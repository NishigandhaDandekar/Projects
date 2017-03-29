CREATE OR REPLACE TRIGGER updateEnroll_studDelete
before delete on students
  for each row
  begin
   delete from enrollments
   where sid = :old.sid;  
  end;
/ 