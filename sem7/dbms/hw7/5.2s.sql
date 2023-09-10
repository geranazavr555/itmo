CREATE VIEW
    AllMarks (StudentId, Marks)
AS
	SELECT
		StudentId, Marks + NewMarks AS Marks
	FROM
	(
	    SELECT
	    	StudentId,
	    	(
	    		SELECT
		            COUNT(*)
		        FROM
		            Marks
		        WHERE
		            Marks.StudentId = Students.StudentId
			) AS Marks,
			(
	    		SELECT
		            COUNT(*)
		        FROM
		            NewMarks
		        WHERE
		            NewMarks.StudentId = Students.StudentId
			) AS NewMarks
		FROM
			Students
		WHERE
			1 = 1
	) StudentMarkNewMark
	WHERE
		1 = 1;