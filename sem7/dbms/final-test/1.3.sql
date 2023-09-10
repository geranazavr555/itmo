SELECT
	RunId, SessionId, Letter, SubmitTime
FROM
	Runs
WHERE
	Accepted = 1
	AND
	SessionId IN (
		SELECT
			SessionId
		FROM
			Sessions
		WHERE
			ContestId = :ContestId
	);