CREATE OR REPLACE TRIGGER updateclasses_enroll_insert
after insert on enrollments
  for each row
  begin
   update classes set class_size=class_size+1
   where classid = :old.classid;  
  end;
/