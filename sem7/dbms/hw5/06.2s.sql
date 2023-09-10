SELECT
    StudentId
FROM
    Students
WHERE
    1 = 1
EXCEPT
SELECT
    StudentId
FROM
    Students
NATURAL JOIN
    Marks
NATURAL JOIN
    Plan
NATURAL JOIN
    Lecturers
WHERE
    LecturerName = :LecturerName;