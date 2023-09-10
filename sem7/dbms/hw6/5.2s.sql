SELECT
	StudentId
FROM
	Students
WHERE
	NOT EXISTS (
		SELECT
			*
		FROM
			Marks
		WHERE
			Students.StudentId = Marks.StudentId
			AND
			EXISTS (
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
					Plan.CourseId = Marks.CourseId
					AND
					Plan.GroupId = Students.GroupId
			)
	)