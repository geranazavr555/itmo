-- Отсутствие оценки считается как ноль
SELECT
	(CAST(Mark AS DOUBLE)) / (
		SELECT
			CAST(COUNT(DISTINCT StudentId) AS DOUBLE)
		FROM
			Students
		WHERE
			GroupId IN (
				SELECT
					GroupId
				FROM
					Groups
				WHERE
					GroupName = :GroupName
			)
	) AS AvgMark
FROM
	Marks
WHERE
	StudentId IN (
		SELECT
			StudentId
		FROM
			Students
		WHERE
			GroupId IN (
				SELECT
					GroupId
				FROM
					Groups
				WHERE
					GroupName = :GroupName
			)
	)
	AND
	CourseId IN (
		SELECT
			CourseId
		FROM
			Courses
		WHERE
			CourseName = :CourseName
	);


CREATE UNIQUE INDEX Groups_GroupName_GroupId ON Groups USING BTREE (GroupName, GroupId);

-- Этот индекс можно заменить на покрывающий GroupId, StudentId
CREATE INDEX Students_GroupId ON Students USING HASH (GroupId);

CREATE UNIQUE INDEX Courses_CourseName_CourseId ON Courses USING BTREE (CourseName, CourseId);

CREATE UNIQUE INDEX Marks_CourseId_StudentId_Mark ON Marks USING BTREE (CourseId, StudentId, Mark);