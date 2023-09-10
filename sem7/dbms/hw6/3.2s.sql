SELECT
	StudentName, CourseName
FROM
	Students, Courses
WHERE
	EXISTS (
		SELECT
			*
		FROM
			Marks
		WHERE
			Marks.StudentId = Students.StudentId
			AND
			Marks.CourseId = Courses.CourseId
	)
	OR
	EXISTS (
		SELECT
			*
		FROM
			Plan
		WHERE
			Students.GroupId = Plan.GroupId
			AND
			Courses.CourseId = Plan.CourseId
	);