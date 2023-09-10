SELECT
    StudentId, StudentName, GroupId
FROM
    Students
NATURAL JOIN
    Marks
NATURAL JOIN
    (
        SELECT
            GroupId AS PlanGroupId, CourseId, LecturerId
        FROM
            Plan
        NATURAL JOIN
            Lecturers
        WHERE
            LecturerName = :LecturerName
    ) LecturerPlan
WHERE
    Mark = :Mark;