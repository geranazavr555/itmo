SELECT
    StudentName, CourseName
FROM
(
    SELECT DISTINCT
        StudentId, CourseId
    FROM
        (
            SELECT
                StudentId, StudentName, GroupId, CourseId, LecturerId, CourseName
            FROM
                Students
            NATURAL JOIN
                Plan
            NATURAL JOIN
                Courses
            WHERE 1 = 1
            EXCEPT
            SELECT
                StudentId, StudentName, GroupId, CourseId, LecturerId, CourseName
            FROM
                Students
            NATURAL JOIN
                Plan
            NATURAL JOIN
                Courses
            NATURAL JOIN
                Marks
            WHERE
                1 = 1
        ) StudentsPlanCoursesNoMarks
    WHERE 1 = 1
) StudentIdCourseId
NATURAL JOIN
    Students
NATURAL JOIN
    Courses
WHERE
    1 = 1;