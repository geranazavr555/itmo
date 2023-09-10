SELECT
	DISTINCT TeamId
FROM
	Sessions
WHERE
	NOT EXISTS (
		SELECT
			*
		FROM
			Runs
		WHERE
			Runs.Accepted = 1
			AND
			Runs.SessionId = Sessions.SessionId
			AND
			EXISTS (
				SELECT
					*
				FROM
					Runs AS R
				WHERE
					R.SessionId IN (
						SELECT
							S.SessionId
						FROM
							Sessions AS S
						WHERE
							S.TeamId = :TeamId
							AND
							S.ContestId = Sessions.ContestId
					)
					AND
					R.Letter = Runs.Letter
					AND
					R.Accepted = 1
			)
	);