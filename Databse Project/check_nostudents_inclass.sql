CREATE OR REPLACE TRIGGER check_nostudents_inclass
after update on classes
for each row
declare no_student_left exception;
begin
   if (:new.class_size < 1) then raise no_student_left;
   end if;
exception
   when no_student_left then
      dbms_output.put_line('The class now has no students');
end;
/