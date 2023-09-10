SELECT
	Students.StudentId,
	COALESCE(TotalNullable, 0) AS Total,
	COALESCE(PassedNullable, 0) AS Passed,
	COALESCE(TotalNullable, 0) - COALESCE(PassedNullable, 0) AS Failed
FROM
	Students
LEFT JOIN
	(
		SELECT
			GroupId, COUNT(DISTINCT CourseId) AS TotalNullable
		FROM
			Plan
		WHERE
			1 = 1
		GROUP BY
			GroupId
	) TotalSubquery
	ON TotalSubquery.GroupId = Students.GroupId
LEFT JOIN
	(
		SELECT
			Marks.StudentId, COUNT(DISTINCT CourseId) AS PassedNullable
		FROM
			Marks
		NATURAL JOIN
			Students
		NATURAL JOIN
			Plan
		WHERE
			1 = 1
		GROUP BY
			Marks.StudentId
	) PassedSubquery
	ON PassedSubquery.StudentId = Students.StudentId
WHERE
	1 = 1;