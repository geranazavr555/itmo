SELECT 
    StudentName, AvgMark
FROM
    Students
LEFT JOIN
(
    SELECT
        StudentId, AVG(CAST(Mark AS DOUBLE)) AS AvgMark
    FROM
        Marks
    WHERE
        1 = 1
    GROUP BY
        StudentId
) StudentIdAvgMark
ON Students.StudentId = StudentIdAvgMark.StudentId
WHERE
    1 = 1;