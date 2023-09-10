INSERT INTO
	Sessions (TeamId, ContestId, Start)
SELECT
	TeamId, :ContestId, CURRENT_TIMESTAMP
FROM
	Teams
WHERE
	NOT EXISTS (
		SELECT
			*
		FROM
			Sessions
		WHERE
			Sessions.ContestId = :ContestId
			AND
			Sessions.TeamId = Teams.TeamId
	);