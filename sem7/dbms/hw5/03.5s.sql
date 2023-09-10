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
        WHERE
            LecturerId = :LecturerId
    ) LecturerPlan
WHERE
    Mark = :Mark;