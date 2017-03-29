CREATE OR REPLACE PROCEDURE insertInStudents(
	   psid IN  students.SID%TYPE,
	   pfirstname IN  students.FIRSTNAME%TYPE,
	   plastname IN students.LASTNAME%TYPE,
	   pstatus IN students.STATUS%TYPE,
	   pgpa IN students.GPA%TYPE,
	   pemail IN students.EMAIL%TYPE)
AS
BEGIN
  INSERT INTO STUDENTS(SID,FIRSTNAME,LASTNAME,STATUS,GPA,EMAIL)
  VALUES (psid,pfirstname ,plastname ,pstatus,pgpa ,pemail );
END;
/