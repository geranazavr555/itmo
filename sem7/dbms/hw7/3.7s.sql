UPDATE
    Students
SET
    Debts = (
        SELECT
            COUNT(DISTINCT CourseId)
        FROM
            Plan
        WHERE
            Plan.GroupId = Students.GroupId
            AND
            NOT EXISTS (
                SELECT
                    *
                FROM
                    Marks
                WHERE
                    Marks.StudentId = Students.StudentId
                    AND
                    Marks.CourseId = Plan.CourseId
            )
    )
WHERE
    GroupId IN (
        SELECT
            GroupId
        FROM
            Groups
        WHERE
            GroupName = :GroupName
    );