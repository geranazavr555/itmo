SELECT
    StudentName, CourseName
FROM
    Students
NATURAL JOIN
(
    SELECT DISTINCT
        StudentId,  CourseId
    FROM
        Students
    NATURAL JOIN
        Plan
    WHERE
        1 = 1
) StudentIdCourseName
NATURAL JOIN
    Courses
WHERE
    1 = 1;
