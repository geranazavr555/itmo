SELECT
    StudentId, StudentName, GroupId
FROM 
    (
        SELECT
            StudentId, StudentName, GroupId, CourseId
        FROM
            Students
        NATURAL JOIN
            Plan
        NATURAL JOIN
            Courses
        WHERE
            CourseName = :CourseName
        EXCEPT
        SELECT
            StudentId, StudentName, GroupId, CourseId
        FROM
            Students
        NATURAL JOIN
            Marks
        NATURAL JOIN
            Courses
        WHERE
            CourseName = :CourseName
    ) StudentGroupCourse
WHERE
    1 = 1;