SELECT
	GroupName, SumMark
FROM
	Groups
LEFT JOIN
(
	SELECT 
		GroupId, SUM(InnerSumMark) AS SumMark
	FROM
		Students
	NATURAL JOIN
	(
		SELECT
		    StudentId, SUM(Mark) AS InnerSumMark
		FROM
		    Marks
		WHERE
		    1 = 1
		GROUP BY
		    StudentId
	) StudentIdSumMark
	WHERE
		1 = 1
	GROUP BY
		GroupId
) GroupIdSumMark
ON Groups.GroupId = GroupIdSumMark.GroupId
WHERE
	1 = 1;