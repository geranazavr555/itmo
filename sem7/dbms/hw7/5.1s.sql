CREATE VIEW
    StudentMarks (StudentId, Marks)
AS
    SELECT
    	StudentId,
    	(
    		SELECT
	            COUNT(*)
	        FROM
	            Marks
	        WHERE
	            Marks.StudentId = Students.StudentId
		) AS Marks
	FROM
		Students
	WHERE
		1 = 1;