SELECT
	StudentName, SUM(Mark) AS SumMark
FROM
	Marks
NATURAL JOIN
	Students
WHERE
	1 = 1
GROUP BY
	StudentName;