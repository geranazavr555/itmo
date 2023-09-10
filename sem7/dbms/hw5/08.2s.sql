SELECT 
	StudentName, SumMark
FROM
	Students
LEFT JOIN
(
	SELECT
	    StudentId, SUM(Mark) AS SumMark
	FROM
	    Marks
	WHERE
	    1 = 1
	GROUP BY
	    StudentId
) StudentIdSumMark
ON Students.StudentId = StudentIdSumMark.StudentId
WHERE
	1 = 1;