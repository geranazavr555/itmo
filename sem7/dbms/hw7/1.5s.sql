DELETE FROM
	Students
WHERE
	StudentId NOT IN
	(
		SELECT
			StudentId
		FROM
			Marks
		WHERE
			1 = 1
		GROUP BY
			StudentId
		HAVING
			COUNT(CourseId) > 3
	);