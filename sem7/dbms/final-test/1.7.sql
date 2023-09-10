SELECT
	SessionId
FROM
	Sessions
WHERE
	NOT EXISTS (
		SELECT
			*
		FROM
			Problems
		WHERE
			Problems.ContestId = Sessions.ContestId
			AND
			NOT EXISTS (
				SELECT
					*
				FROM
					Runs
				WHERE
					Problems.Letter = Runs.Letter
					AND
					Runs.SessionId = Sessions.SessionId
			)
	);