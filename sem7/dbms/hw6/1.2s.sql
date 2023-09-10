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
	);