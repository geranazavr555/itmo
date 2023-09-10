DELETE FROM
	Students
WHERE
	StudentId NOT IN (
		SELECT
			Students.StudentId
		FROM
			Plan
		NATURAL JOIN
			Students
		WHERE
			NOT EXISTS (
				SELECT
					*
				FROM
					Marks
				WHERE
					Plan.CourseId = Marks.CourseId
					AND
					Marks.StudentId = Students.StudentId
			)
		GROUP BY
			Students.StudentId
		HAVING
			COUNT(DISTINCT CourseId) > 2
	);