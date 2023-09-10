SELECT
	Teams.TeamId AS TeamId,
	SUM(SolvedInContest) AS Solved
FROM
	Teams
NATURAL JOIN
	Sessions
