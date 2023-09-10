DELETE FROM
	Students
WHERE
	EXISTS (
		SELECT
			DISTINCT CourseId
		FROM
			Plan
		WHERE
			Plan.GroupId = Students.GroupId	
		EXCEPT
		SELECT
			DISTINCT CourseId
		FROM
			Marks
		WHERE
			Marks.StudentId = Students.StudentId
	);