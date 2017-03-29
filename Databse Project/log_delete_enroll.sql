create or replace trigger log_delete_enroll
after delete on Enrollments 
for each row
begin
insert into Logs(logid,who,time,table_name,operation,key_value)values
  (log_sequence.NEXTVAL,(Select user from dual),(Select to_char(cast(SYSDATE as date),'hh24:mi:ss') from dual),'Enrollments','delete',:old.sid||' '||:old.classid);
end;
/
