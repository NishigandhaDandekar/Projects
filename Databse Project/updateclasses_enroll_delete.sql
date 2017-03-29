CREATE OR REPLACE TRIGGER updateclasses_enroll_delete
after delete on enrollments
  for each row
  begin
   update classes set class_size=class_size-1
   where classid = :old.classid;  
  end;
/