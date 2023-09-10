SELECT
	CourseId, GroupId
FROM
	(
		SELECT
			CourseId
		FROM
			Marks
		WHERE
			1 = 1
	) MarksProjection1
CROSS JOIN
	(
		SELECT
			GroupId
		FROM
			Students
		WHERE
			1 = 1
	) StudentsProjection1
WHERE
	1 = 1
EXCEPT
SELECT
	CourseId, GroupId
FROM
	(
		SELECT
			CourseId, StudentId, GroupId
		FROM
			(
				SELECT
					CourseId
				FROM
					Marks
				WHERE
					1 = 1
			) MarksProjection2
		CROSS JOIN
			(
				SELECT
					StudentId, GroupId
				FROM
					Students
				WHERE
					1 = 1
			) StudentsGroups
		WHERE
			1 = 1
		EXCEPT
		SELECT
			CourseId, StudentId, GroupId
		FROM
			Marks
		NATURAL JOIN
			Students
		WHERE
			1 = 1

	) RightPart
WHERE 1 = 1;