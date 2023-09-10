SELECT
	TeamName
FROM
	Teams
WHERE
	NOT EXISTS (
		SELECT
			*
		FROM
			Sessions
		WHERE
			Sessions.TeamId = Teams.TeamId
	)
	OR
	NOT EXISTS (
		SELECT
			*
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
			Sessions.TeamId = Teams.TeamId
			)
	);