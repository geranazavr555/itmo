SELECT
	RunId, SessionId, Letter, SubmitTime, Accepted
FROM
	Runs
WHERE
	SessionId IN (
		SELECT
			SessionId
		FROM
			Sessions
		WHERE
			TeamId = :TeamId
			AND
			ContestId = :ContestId
	);