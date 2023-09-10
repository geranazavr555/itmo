SELECT
    AVG(CAST(Mark AS DOUBLE)) AS AvgMark
FROM
    Marks
WHERE
    StudentId = :StudentId;