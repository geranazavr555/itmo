SELECT
	StudentId, StudentName, GroupId
FROM
	Students
WHERE
	1 = 1
EXCEPT
SELECT
	StudentId, StudentName, GroupId
FROM
	Students
NATURAL JOIN
	Courses
NATURAL JOIN
	Marks
WHERE
	CourseName = :CourseName;