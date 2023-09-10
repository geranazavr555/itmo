UPDATE
	Students
SET
	Marks = (
		SELECT
			COUNT(*)
		FROM
			Marks
		WHERE
			Marks.StudentId = :StudentId
	)
WHERE
	StudentId = :StudentId;