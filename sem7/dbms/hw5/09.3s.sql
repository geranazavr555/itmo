SELECT
    GroupName, AvgMark
FROM
    Groups
LEFT JOIN
(
    SELECT 
        GroupId, SUM(CAST(SumMark AS DOUBLE)) / SUM(CAST(CountMark AS DOUBLE)) AS AvgMark
    FROM
        Students
    NATURAL JOIN
    (
        SELECT
            StudentId, SUM(Mark) AS SumMark, COUNT(Mark) AS CountMark
        FROM
            Marks
        WHERE
            1 = 1
        GROUP BY
            StudentId
    ) StudentSumCount
    WHERE
        1 = 1
    GROUP BY
        GroupId
) GroupIdAvgMark
ON Groups.GroupId = GroupIdAvgMark.GroupId
WHERE
    1 = 1;