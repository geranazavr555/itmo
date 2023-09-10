SELECT DISTINCT
    StudentId
FROM
    (
        SELECT
            MarkStudentId, StudentId
        FROM
            (
                SELECT DISTINCT
                    StudentId AS MarkStudentId
                FROM
                    Marks
                WHERE
                    1 = 1
            ) StudentFromMark1
        CROSS JOIN
            (
                SELECT DISTINCT
                    StudentId
                FROM
                    Students
                NATURAL JOIN
                    Plan
                NATURAL JOIN
                    Lecturers
                WHERE
                    LecturerName = :LecturerName
            ) StudentByLecturer1
        WHERE
            1 = 1
        EXCEPT
        SELECT
            MarkStudentId, StudentId
        FROM
            (
                SELECT
                    MarkStudentId, CourseId, StudentId
                FROM
                    (
                        SELECT DISTINCT
                            StudentId AS MarkStudentId
                        FROM
                            Marks
                        WHERE
                            1 = 1
                    ) StudentFromMark2
                CROSS JOIN
                (
                    SELECT
                        CourseId, StudentId
                    FROM
                        Students
                    NATURAL JOIN
                        Plan
                    NATURAL JOIN
                        Lecturers
                    WHERE
                        LecturerName = :LecturerName
                ) CourseAndStudentByLecturer1
                WHERE
                    1 = 1
                EXCEPT
                SELECT
                    Marks.StudentId AS MarkStudentId, Marks.CourseId, Marks.StudentId
                FROM
                    Marks
                INNER JOIN 
                    (
                        SELECT
                            CourseId, StudentId
                        FROM
                            Students
                        NATURAL JOIN
                            Plan
                        NATURAL JOIN
                            Lecturers
                        WHERE
                            LecturerName = :LecturerName
                    ) CourseAndStudentByLecturer2
                ON CourseAndStudentByLecturer2.CourseId = Marks.CourseId
            ) MarkStudentCourseAndStudent   
        WHERE
            1 = 1
    ) MarkStudentAndStudent
WHERE
    MarkStudentId = StudentId;