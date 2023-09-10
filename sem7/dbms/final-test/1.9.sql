SELECT
	TeamName
FROM
	Teams
WHERE
	EXISTS (
		SELECT
			*
		FROM
			Sessions
		WHERE
			Sessions.TeamId = Teams.TeamId
			AND
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
							Runs.Letter = Problems.Letter
							AND
							Runs.SessionId = Sessions.SessionId
							AND
							Accepted = 1
					)
			)
	);