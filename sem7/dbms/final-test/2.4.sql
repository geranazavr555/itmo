SELECT
	ContestId, Letter
FROM
	Problems
WHERE
	NOT EXISTS (
		SELECT
			*
		FROM
			Runs
		WHERE
			Runs.Accepted = 1
			AND
			Runs.Letter = Problems.Letter
			AND
			Runs.SessionId IN (
				SELECT
					SessionId
				FROM
					Sessions
				WHERE
					Sessions.ContestId = Problems.ContestId
			)
	);