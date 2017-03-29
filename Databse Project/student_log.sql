create or replace trigger logTrigger
after insert on students 
for each row
begin
insert into Logs(logid,who,time,table_name,operation,key_value)values
  (log_sequence.NEXTVAL,(Select user from dual),(Select to_char(cast(SYSDATE as date),'hh24:mi:ss') from dual),'students','insert',:new.sid);
end;
/
