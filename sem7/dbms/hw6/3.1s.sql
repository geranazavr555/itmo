SELECT
	StudentId, CourseId
FROM
	Marks
WHERE
	1 = 1
UNION
SELECT
	StudentId, CourseId
FROM
	Students, Plan
WHERE
	Students.GroupId = Plan.GroupId;