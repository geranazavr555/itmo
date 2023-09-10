CREATE VIEW
    StudentDebts (StudentId, Debts)
AS
	SELECT
        StudentId,
        (
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
        ) AS Debts
    FROM
        Students
    WHERE
        1 = 1;