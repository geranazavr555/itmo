CREATE VIEW
    Debts (StudentId, Debts)
AS
	SELECT
        StudentId, COUNT(DISTINCT CourseId) AS Debts
    FROM
        Plan
    NATURAL JOIN
    	Students
    WHERE
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
    GROUP BY
    	StudentId;