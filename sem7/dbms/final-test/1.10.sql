SELECT
	ContestId, Letter
FROM
	Problems
WHERE
	NOT EXISTS (
		SELECT
			*
		FROM
			Sessions
		WHERE
			Sessions.ContestId = Problems.ContestId
			AND
			NOT EXISTS (
				SELECT
					*
				FROM
					Runs
				WHERE
					Runs.SessionId = Sessions.SessionId
					AND
					Accepted = 1
					AND
					Runs.Letter = Problems.Letter
			)
	);