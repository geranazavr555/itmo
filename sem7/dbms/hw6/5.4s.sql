SELECT
	StudentId
FROM
	Students
WHERE
	NOT EXISTS (
		SELECT
			*
		FROM
			Plan
		WHERE
			LecturerId IN (
				SELECT
					LecturerId
				FROM
					Lecturers
				WHERE
					LecturerName = :LecturerName
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
					Marks.CourseId = Plan.CourseId
			)
			AND
			Plan.GroupId = Students.GroupId
	);