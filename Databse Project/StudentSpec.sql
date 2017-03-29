create or replace package StudentRegistration as 
type ref_cursor1 is ref cursor;
type ref_cursor4 is ref cursor;
type ref_cursor6 is ref cursor;

type ref_cursor2 is ref cursor;
type ref_cursor3 is ref cursor;
type ref_cursor5 is ref cursor;
type ref_cursor7 is ref cursor;
type ref_cursor8 is ref cursor;

invalid_student_id exception;
--student_not_enrolled exception;
invalid_class_id exception;
no_student_enrolled exception; 
student_not_enrolled exception;
student_drop_violation exception;

  -- invalid_student_id exception;
  -- invalid_class_id exception;
   class_full exception;
   student_already_in_class exception;
   three_class exception;
   prereq_not_complete exception;
   overload exception;

function show_students 
return ref_cursor1;

function show_enrollments 
return ref_cursor2;

function show_prereq 
return ref_cursor3;

function show_courses 
return ref_cursor5;

function show_classes 
return ref_cursor7;

function show_logs 
return ref_cursor8;

--Query 3

PROCEDURE insertInStudents(
	   psid IN  students.SID%TYPE,
	   pfirstname IN  students.FIRSTNAME%TYPE,
	   plastname IN students.LASTNAME%TYPE,
	   pstatus IN students.STATUS%TYPE,
	   pgpa IN students.GPA%TYPE,
	   pemail IN students.EMAIL%TYPE);
	   
	   --procedure show_enrollments;

--Query4
function showfunction_query4(student_sid in Students.sid%type)
return ref_cursor4;
--Query6
function showfunction_query6(pclassidIn IN  classes.classid%TYPE)
return ref_cursor6;
--Query8
procedure show_query8(student_sid in Students.sid%type,class_id in Classes.classid%type);
--Query9
procedure deleteStudent(student_sid in Students.sid%type);
--Query7
procedure student_enroll(student_sid in Students.sid%type, classid_ref in Classes.classid%type);
end;
/
