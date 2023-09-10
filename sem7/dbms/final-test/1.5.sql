SELECT
    TeamName
FROM
    Teams
WHERE
    TeamId IN (
        SELECT
            DISTINCT TeamId
        FROM
            Teams, Contests
        WHERE
            NOT EXISTS (
                SELECT
                    *
                FROM
                    Sessions
                WHERE
                    Sessions.TeamId = Teams.TeamId
                    AND
                    Sessions.ContestId = Contests.ContestId
                    AND
                    EXISTS (
                        SELECT
                            *
                        FROM
                            Runs
                        WHERE
                            Accepted = 1
                            AND
                            Runs.SessionId = Sessions.SessionId
                    )
               
            )
    );