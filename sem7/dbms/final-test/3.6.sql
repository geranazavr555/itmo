INSERT INTO
	Runs (SessionId, Letter, SubmitTime, Accepted)
SELECT
	SessionId, Letter, SubmitTime + 1 AS SubmitTime, 1 AS Accepted
FROM
	Runs
WHERE
	SessionId IN (
		SELECT
			SessionId
		FROM
			Sessions
		WHERE
			ContestId = :ContestId
	)
	AND
	NOT EXISTS (
		SELECT
			*
		FROM
			Runs AS R
		WHERE
			Runs.SessionId = R.SessionId
			AND
			Runs.Letter = R.Letter
			AND
			R.Accepted = 1
	)
	AND
	NOT EXISTS (
		SELECT
			*
		FROM
			Runs AS R1
		WHERE
			Runs.SessionId = R1.SessionId
			AND
			Runs.Letter = R1.Letter
			AND
			Runs.SubmitTime < R1.SubmitTime
	);