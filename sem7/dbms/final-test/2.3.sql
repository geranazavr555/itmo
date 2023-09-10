SELECT
	DISTINCT TeamId
FROM
	Sessions
WHERE
	ContestId = :ContestId
	AND
	EXISTS (
		SELECT
			*
		FROM
			Runs
		WHERE
			Sessions.SessionId = Runs.SessionId
			AND
			Accepted = 1
	);