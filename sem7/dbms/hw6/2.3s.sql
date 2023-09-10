SELECT
	StudentId, StudentName, GroupName
FROM
	Students, Groups
WHERE
	Students.GroupId = Groups.GroupId
	AND
	StudentId NOT IN (
		SELECT
			StudentId
		FROM
			Marks
		WHERE
			CourseId IN (
				SELECT
					CourseId
				FROM
					Courses
				WHERE
					CourseName = :CourseName
			)
	);