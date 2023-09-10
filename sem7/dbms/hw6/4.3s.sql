SELECT
	StudentName, CourseName
FROM
	Students, Courses
WHERE
	EXISTS (
		SELECT
			*
		FROM
			Plan
		WHERE
			Plan.GroupId = Students.GroupId
			AND
			Courses.CourseId = Plan.CourseId
	)
	AND
	NOT EXISTS (
		SELECT
			*
		FROM
			Marks
		WHERE
			Marks.StudentId = Students.StudentId
			AND
			Marks.CourseId = Courses.CourseId
			AND
			Mark > 2
	);