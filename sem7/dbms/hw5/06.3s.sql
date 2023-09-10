SELECT DISTINCT
    StudentId
FROM
    Marks
WHERE
    1 = 1
EXCEPT
SELECT DISTINCT
    StudentId
FROM
    (
        SELECT DISTINCT
            StudentId, CourseId
        FROM
            (
                SELECT DISTINCT
                    StudentId
                FROM
                    Marks
                WHERE
                    1 = 1
            ) StudentsSubquery
        CROSS JOIN
            (
                SELECT
                    CourseId
                FROM
                    Plan
                NATURAL JOIN
                    Lecturers
                WHERE
                    LecturerName = :LecturerName
            ) CrossJoinSubquery
        WHERE
            1 = 1
        EXCEPT
        SELECT
            StudentId, CourseId
        FROM
            Marks
        WHERE
            1 = 1
    ) ExtraStudents
WHERE
    1 = 1;