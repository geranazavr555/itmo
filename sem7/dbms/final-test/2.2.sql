SELECT
	TeamName
FROM
	Teams
WHERE
	TeamId IN (
		SELECT
			DISTINCT TeamId
		FROM
			Sessions
		WHERE
			ContestId = :ContestId
			AND
			SessionId IN (
				SELECT
					SessionId
				FROM
					Runs
				WHERE
					Accepted = 1
					AND
					Letter = :Letter
			)
	);