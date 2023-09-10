DELETE FROM
	Runs
WHERE
	SessionId IN (
		SELECT
			SessionId
		FROM
			Sessions
		WHERE
			TeamId IN (
				SELECT
					TeamId
				FROM
					Teams
				WHERE
					TeamName = :TeamName
			)
	);