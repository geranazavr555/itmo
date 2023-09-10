SELECT
	TeamName
FROM
	Sessions
NATURAL JOIN
	Teams
WHERE
	ContestId = :ContestId
	AND
	SessionId IN (
		SELECT
			SessionId
		FROM
			Runs
		WHERE
			Accepted = 1
			AND
			Letter = :Letter
	);