SELECT
    StudentName, CourseName
FROM
(
    SELECT DISTINCT
        StudentId, CourseId
    FROM
        (
            SELECT
                Students.StudentId,
                Students.StudentName,
                Students.GroupId,
                Plan.CourseId,
                Plan.LecturerId,
                Courses.CourseName,
                Marks.Mark
            FROM
                Students
            NATURAL JOIN
                Plan
            NATURAL JOIN
                Courses
            LEFT JOIN
                Marks
                ON Students.StudentId = Marks.StudentId AND Courses.CourseId = Marks.CourseId
            WHERE
                1 = 1
            EXCEPT
            SELECT
                Students.StudentId,
                Students.StudentName,
                Students.GroupId,
                Plan.CourseId,
                Plan.LecturerId,
                Courses.CourseName,
                Marks.Mark
            FROM
                Students
            NATURAL JOIN
                Plan
            NATURAL JOIN
                Courses
            NATURAL JOIN
                Marks
            WHERE
                Mark = 4 OR Mark = 5
        ) HasMarkNot4Or5
    WHERE
        1 = 1
) StudentIdCourseId
NATURAL JOIN
    Students
NATURAL JOIN
    Courses
WHERE
    1 = 1;