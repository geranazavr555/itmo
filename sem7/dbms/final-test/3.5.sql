UPDATE
	Runs
SET
	Accepted = 1
WHERE
	NOT EXISTS (
		SELECT
			*
		FROM
			Runs AS R
		WHERE
			Runs.SubmitTime < R.SubmitTime
			AND
			Runs.SessionId = R.SessionId
			AND
			Runs.Letter = R.Letter
	);