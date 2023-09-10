-- Найти студентов из заданной группы с заданной фамилией
-- (Сначала поиск по группе, затем по префиксу имени)
SELECT
	StudentId, StudentName, GroupId
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
	AND
	StudentName LIKE CONCAT(:LastName, ' %');

-- Используем дерево, чтобы поддерживать запросы на префиксе
CREATE INDEX Students_GroupId_StudentName_StudentId ON Students USING BTREE (GroupId, StudentName, StudentId);

-- Удаляем индекс, потому что теперь можно использовать префикс
-- индекса Students_GroupId_StudentName_StudentId
DROP INDEX Students_GroupId;





-- Найти максимальную и минимальную оценку по курсу
SELECT
	MAX(Mark), MIN(Mark)
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

-- Важна упорядоченность, поэтому дерево
CREATE INDEX Marks_CourseId_Mark ON Marks USING BTREE (CourseId, Mark);




-- Найти курсы, которые вел у заданной группы заданный лектор
SELECT
	CourseName
FROM
	Courses
WHERE
	CourseId IN (
		SELECT
			CourseId
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
			GroupId IN (
				SELECT
					GroupId
				FROM
					Groups
				WHERE
					GroupName = :GroupName
			)
	);

-- Тут мы просто хотим уметь быстро делать IN, поэтому хеш.
CREATE INDEX Plan_GroupId_LecturerId ON Plan USING HASH (GroupId, LecturerId);