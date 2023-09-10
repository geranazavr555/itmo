MERGE INTO
	Sessions
USING
	Teams
ON
	Sessions.TeamId = Teams.TeamId
WHEN MATCHED AND Sessions.ContestId = :ContestId THEN
	UPDATE SET Start = CURRENT_TIMESTAMP
WHEN NOT MATCHED AND Sessions.ContestId = :ContestId THEN
	INSERT
		(TeamId, ContestId, Start)
	VALUES
		(Teams.TeamId, :ContestId, CURRENT_TIMESTAMP);