SELECT
	GroupName, CourseName
FROM
	Groups, Courses
WHERE
	NOT EXISTS (
		SELECT
			*
		FROM
			Students
		WHERE
			NOT EXISTS (
				SELECT
					*
				FROM
					Marks
				WHERE
					Marks.StudentId = Students.StudentId
					AND
					Marks.CourseId = Courses.CourseId

			)
			AND
			Students.GroupId = Groups.GroupId
	);