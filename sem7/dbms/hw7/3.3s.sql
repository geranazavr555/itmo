UPDATE
	Students
SET
	Marks = Marks + (
		SELECT
			COUNT(*)
		FROM
			NewMarks
		WHERE
			NewMarks.StudentId = Students.StudentId
	)
WHERE
	1 = 1;