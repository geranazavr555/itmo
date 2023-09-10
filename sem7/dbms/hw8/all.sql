-- ДЗ-5.1.1. Получить информацию о студентах с заданным идентификатором (StudentId, StudentName, GroupId по :StudentId).

SELECT StudentId, StudentName, GroupId
FROM Students
WHERE StudentId = :StudentId;

-- ДЗ-5.1.2. Получить информацию о студентах с заданным ФИО (StudentId, StudentName, GroupId по :StudentName).

SELECT StudentId, StudentName, GroupId
FROM Students
WHERE StudentId = :StudentId;

-- ДЗ-5.2.1. Получить полную информацию о студентах с заданным идентификатором (StudentId, StudentName, GroupName по :StudentId).

SELECT
    StudentId, StudentName, GroupName
FROM
    Students
NATURAL JOIN
    Groups
WHERE
    StudentId = :StudentId;

-- ДЗ-5.2.2. Получить полную информацию о студентах с заданным ФИО (StudentId, StudentName, GroupName по :StudentName).

SELECT
    StudentId, StudentName, GroupName
FROM
    Students
NATURAL JOIN
    Groups
WHERE
    StudentName = :StudentName;

-- ДЗ-5.3.1. Получить информацию о студентах с заданной оценкой по дисциплине с заданным идентификатором (StudentId, StudentName, GroupId по :Mark, :CourseId).

SELECT
    StudentId, StudentName, GroupId
FROM
    Students
NATURAL JOIN
    Marks
WHERE
    Mark = :Mark AND CourseId = :CourseId;

-- ДЗ-5.3.2. Получить информацию о студентах с заданной оценкой по дисциплине с заданным названием (StudentId, StudentName, GroupId по :Mark, :CourseName).

SELECT
    StudentId, StudentName, GroupId
FROM
    Students
NATURAL JOIN
    Marks
NATURAL JOIN
    Courses
WHERE
    Mark = :Mark AND CourseName = :CourseName;

-- ДЗ-5.3.3. Получить информацию о студентах с заданной оценкой по дисциплине которую у него вёл лектор заданный идентификатором (StudentId, StudentName, GroupId по :Mark, :LecturerId).

SELECT
    StudentId, StudentName, GroupId
FROM
    Students
NATURAL JOIN
    Marks
NATURAL JOIN
    Plan
WHERE
    Mark = :Mark AND LecturerId = :LecturerId;

-- ДЗ-5.3.4. Получить информацию о студентах с заданной оценкой по дисциплине которую у него вёл лектор, заданный ФИО (StudentId, StudentName, GroupId по :Mark, :LecturerName).

SELECT
    StudentId, StudentName, GroupId
FROM
    Students
NATURAL JOIN
    Marks
NATURAL JOIN
    Plan
NATURAL JOIN
    Lecturers
WHERE
    Mark = :Mark AND LecturerName = :LecturerName;

-- ДЗ-5.3.5. Получить информацию о студентах с заданной оценкой по дисциплине которую вёл лектор, заданный идентификатором (StudentId, StudentName, GroupId по :Mark, :LecturerId).

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

-- ДЗ-5.3.6. Получить информацию о студентах с заданной оценкой по дисциплине которую вёл лектор, заданный ФИО (StudentId, StudentName, GroupId по :Mark, :LecturerName).

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

-- ДЗ-5.4.1. Получить информацию о студентах не имеющих оценки по дисциплине среди всех студентов (StudentId, StudentName, GroupId по :CourseName).

SELECT
    StudentId, StudentName, GroupId
FROM
    Students
WHERE
    1 = 1
EXCEPT
SELECT
    StudentId, StudentName, GroupId
FROM
    Students
NATURAL JOIN
    Courses
NATURAL JOIN
    Marks
WHERE
    CourseName = :CourseName;

-- ДЗ-5.4.2. Получить информацию о студентах не имеющих оценки по дисциплине среди студентов, у которых есть эта дисциплина (StudentId, StudentName, GroupId по :CourseName).

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

-- ДЗ-5.5.1. Получить для каждого студента ФИО и названия дисциплин которые у него есть по плану (StudentName, CourseName).

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

-- ДЗ-5.5.2. Получить для каждого студента ФИО и названия дисциплин которые у него есть по плану, но у него нет оценки (StudentName, CourseName).

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

-- ДЗ-5.5.3. Получить для каждого студента ФИО и названия дисциплин которые у него есть по плану, но у него не 4 или 5 (StudentName, CourseName).

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

-- ДЗ-5.6.1. Получить идентификаторы студентов по преподавателю имеющих хотя бы одну оценку у преподавателя (StudentId по :LecturerName).

SELECT DISTINCT
    StudentId
FROM
    Students
NATURAL JOIN
    Marks
NATURAL JOIN
    Plan
NATURAL JOIN
    Lecturers
WHERE
    LecturerName = :LecturerName;

-- ДЗ-5.6.2. Получить идентификаторы студентов по преподавателю не имеющих ни одной оценки у преподавателя (StudentId по :LecturerName).

SELECT
    StudentId
FROM
    Students
WHERE
    1 = 1
EXCEPT
SELECT
    StudentId
FROM
    Students
NATURAL JOIN
    Marks
NATURAL JOIN
    Plan
NATURAL JOIN
    Lecturers
WHERE
    LecturerName = :LecturerName;

-- ДЗ-5.6.3. Получить идентификаторы студентов по преподавателю имеющих оценки по всем дисциплинам преподавателя (StudentId по :LecturerName).

SELECT DISTINCT
    StudentId
FROM
    Marks
WHERE
    1 = 1
EXCEPT
SELECT DISTINCT
    StudentId
FROM
    (
        SELECT DISTINCT
            StudentId, CourseId
        FROM
            (
                SELECT DISTINCT
                    StudentId
                FROM
                    Marks
                WHERE
                    1 = 1
            ) StudentsSubquery
        CROSS JOIN
            (
                SELECT
                    CourseId
                FROM
                    Plan
                NATURAL JOIN
                    Lecturers
                WHERE
                    LecturerName = :LecturerName
            ) CrossJoinSubquery
        WHERE
            1 = 1
        EXCEPT
        SELECT
            StudentId, CourseId
        FROM
            Marks
        WHERE
            1 = 1
    ) ExtraStudents
WHERE
    1 = 1;

-- ДЗ-5.6.4. Получить идентификаторы студентов по преподавателю имеющих оценки по всем дисциплинам преподавателя, которые он вёл у этого студента (StudentId по :LecturerName).

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

-- ДЗ-5.7.1. Получить группы и дисциплины, такие что все студенты группы имеют оценку по этой дисциплине (Идентификаторы) (GroupId, CourseId).

SELECT
    CourseId, GroupId
FROM
    (
        SELECT
            CourseId
        FROM
            Marks
        WHERE
            1 = 1
    ) MarksProjection1
CROSS JOIN
    (
        SELECT
            GroupId
        FROM
            Students
        WHERE
            1 = 1
    ) StudentsProjection1
WHERE
    1 = 1
EXCEPT
SELECT
    CourseId, GroupId
FROM
    (
        SELECT
            CourseId, StudentId, GroupId
        FROM
            (
                SELECT
                    CourseId
                FROM
                    Marks
                WHERE
                    1 = 1
            ) MarksProjection2
        CROSS JOIN
            (
                SELECT
                    StudentId, GroupId
                FROM
                    Students
                WHERE
                    1 = 1
            ) StudentsGroups
        WHERE
            1 = 1
        EXCEPT
        SELECT
            CourseId, StudentId, GroupId
        FROM
            Marks
        NATURAL JOIN
            Students
        WHERE
            1 = 1
 
    ) RightPart
WHERE 1 = 1;

-- ДЗ-5.7.2. Получить группы и дисциплины, такие что все студенты группы имеют оценку по этой дисциплине (Названия) (GroupName, CourseName).

SELECT
    GroupName, CourseName
FROM
    (
        SELECT
            CourseId, GroupId
        FROM
            (
                SELECT
                    CourseId
                FROM
                    Marks
                WHERE
                    1 = 1
            ) MarksProjection1
        CROSS JOIN
            (
                SELECT
                    GroupId
                FROM
                    Students
                WHERE
                    1 = 1
            ) StudentsProjection1
        WHERE
            1 = 1
        EXCEPT
        SELECT
            CourseId, GroupId
        FROM
            (
                SELECT
                    CourseId, StudentId, GroupId
                FROM
                    (
                        SELECT
                            CourseId
                        FROM
                            Marks
                        WHERE
                            1 = 1
                    ) MarksProjection2
                CROSS JOIN
                    (
                        SELECT
                            StudentId, GroupId
                        FROM
                            Students
                        WHERE
                            1 = 1
                    ) StudentsGroups
                WHERE
                    1 = 1
                EXCEPT
                SELECT
                    CourseId, StudentId, GroupId
                FROM
                    Marks
                NATURAL JOIN
                    Students
                WHERE
                    1 = 1
 
            ) RightPart
        WHERE
            1 = 1
    ) IdsSubquery
NATURAL JOIN
    Groups
NATURAL JOIN
    Courses
WHERE
    1 = 1;

-- ДЗ-5.8.1. Получить суммарный балл одного студента (SumMark по :StudentId).

SELECT
    SUM(Mark) AS SumMark
FROM
    Marks
WHERE
    StudentId = :StudentId;

-- ДЗ-5.8.2. Получить суммарный балл каждого студента (StudentName, SumMark).

SELECT 
    StudentName, SumMark
FROM
    Students
LEFT JOIN
(
    SELECT
        StudentId, SUM(Mark) AS SumMark
    FROM
        Marks
    WHERE
        1 = 1
    GROUP BY
        StudentId
) StudentIdSumMark
ON Students.StudentId = StudentIdSumMark.StudentId
WHERE
    1 = 1;

-- ДЗ-5.8.3. Получить суммарный балл каждой группы (GroupName, SumMark).

SELECT
    GroupName, SumMark
FROM
    Groups
LEFT JOIN
(
    SELECT 
        GroupId, SUM(InnerSumMark) AS SumMark
    FROM
        Students
    NATURAL JOIN
    (
        SELECT
            StudentId, SUM(Mark) AS InnerSumMark
        FROM
            Marks
        WHERE
            1 = 1
        GROUP BY
            StudentId
    ) StudentIdSumMark
    WHERE
        1 = 1
    GROUP BY
        GroupId
) GroupIdSumMark
ON Groups.GroupId = GroupIdSumMark.GroupId
WHERE
    1 = 1;

-- ДЗ-5.9.1. Получить средний балл одного студента (AvgMark по :StudentId).

SELECT
    AVG(CAST(Mark AS DOUBLE)) AS AvgMark
FROM
    Marks
WHERE
    StudentId = :StudentId;

-- ДЗ-5.9.2. Получить средний балл каждого студента (StudentName, AvgMark).

SELECT 
    StudentName, AvgMark
FROM
    Students
LEFT JOIN
(
    SELECT
        StudentId, AVG(CAST(Mark AS DOUBLE)) AS AvgMark
    FROM
        Marks
    WHERE
        1 = 1
    GROUP BY
        StudentId
) StudentIdAvgMark
ON Students.StudentId = StudentIdAvgMark.StudentId
WHERE
    1 = 1;

-- ДЗ-5.9.3. Получить средний балл каждой группы (GroupName, AvgMark).

SELECT
    GroupName, AvgMark
FROM
    Groups
LEFT JOIN
(
    SELECT 
        GroupId, SUM(CAST(SumMark AS DOUBLE)) / SUM(CAST(CountMark AS DOUBLE)) AS AvgMark
    FROM
        Students
    NATURAL JOIN
    (
        SELECT
            StudentId, SUM(Mark) AS SumMark, COUNT(Mark) AS CountMark
        FROM
            Marks
        WHERE
            1 = 1
        GROUP BY
            StudentId
    ) StudentSumCount
    WHERE
        1 = 1
    GROUP BY
        GroupId
) GroupIdAvgMark
ON Groups.GroupId = GroupIdAvgMark.GroupId
WHERE
    1 = 1;

-- ДЗ-5.9.4. Получить средний балл средних баллов студентов каждой группы (GroupName, AvgAvgMark).

SELECT
    GroupName, AvgAvgMark
FROM
    Groups
LEFT JOIN
(
    SELECT 
        GroupId, AVG(AvgMark) AS AvgAvgMark
    FROM
        Students
    NATURAL JOIN
    (
        SELECT
            StudentId, AVG(CAST(Mark AS DOUBLE)) AS AvgMark
        FROM
            Marks
        WHERE
            1 = 1
        GROUP BY
            StudentId
    ) StudentIdAvgMark
    WHERE
        1 = 1
    GROUP BY
        GroupId
) GroupIdAvgAvgMark
ON Groups.GroupId = GroupIdAvgAvgMark.GroupId
WHERE
    1 = 1;

-- ДЗ-5.10.1. Для каждого студента получить число дисциплин, которые у него были, число сданных дисциплин и число несданных дисциплин (StudentId, Total, Passed, Failed).

SELECT
    Students.StudentId,
    COALESCE(TotalNullable, 0) AS Total,
    COALESCE(PassedNullable, 0) AS Passed,
    COALESCE(TotalNullable, 0) - COALESCE(PassedNullable, 0) AS Failed
FROM
    Students
LEFT JOIN
    (
        SELECT
            GroupId, COUNT(DISTINCT CourseId) AS TotalNullable
        FROM
            Plan
        WHERE
            1 = 1
        GROUP BY
            GroupId
    ) TotalSubquery
    ON TotalSubquery.GroupId = Students.GroupId
LEFT JOIN
    (
        SELECT
            Marks.StudentId, COUNT(DISTINCT CourseId) AS PassedNullable
        FROM
            Marks
        NATURAL JOIN
            Students
        NATURAL JOIN
            Plan
        WHERE
            1 = 1
        GROUP BY
            Marks.StudentId
    ) PassedSubquery
    ON PassedSubquery.StudentId = Students.StudentId
WHERE
    1 = 1;

----------------------------------------------------------------------

-- ДЗ-6.1.1. Получить информацию о студентах с заданным ФИО (StudentId, StudentName, GroupId по :StudentName).

SELECT
    StudentId, StudentName, GroupId
FROM
    Students
WHERE
    StudentName = :StudentName;

-- ДЗ-6.1.2. Получить информацию о студентах, учащихся в заданной группе (StudentId, StudentName, GroupId по :GroupName).

SELECT
    StudentId, StudentName, GroupId
FROM
    Students
WHERE
    GroupId IN (
        SELECT
            GroupId
        FROM
            Groups
        WHERE
            GroupName = :GroupName
    );

-- ДЗ-6.1.3. Получить информацию о студентах с заданной оценкой по дисциплине, заданной идентификатором (StudentId, StudentName, GroupId по :Mark, :CourseId).

SELECT
    StudentId, StudentName, GroupId
FROM
    Students
WHERE
    StudentId IN (
        SELECT
            StudentId
        FROM
            Marks
        WHERE
            Mark = :Mark AND CourseId = :CourseId
    );

-- ДЗ-6.1.4. Получить информацию о студентах с заданной оценкой по дисциплине, заданной названием (StudentId, StudentName, GroupId по :Mark, :CourseName).

SELECT
    StudentId, StudentName, GroupId
FROM
    Students
WHERE
    StudentId IN (
        SELECT
            StudentId
        FROM
            Marks
        WHERE
            Mark = :Mark AND CourseId IN (
                SELECT
                    CourseId
                FROM
                    Courses
                WHERE
                    CourseName = :CourseName
            )
    );

-- ДЗ-6.2.1. Получить полную информацию о всех студентах (StudentId, StudentName, GroupName).

SELECT
    StudentId, StudentName, GroupName
FROM
    Students, Groups
WHERE
    Students.GroupId = Groups.GroupId;

-- ДЗ-6.2.2. Получить полную информацию о студентах, не имеющих оценки по дисциплине, заданной идентификатором (StudentId, StudentName, GroupName по :CourseId).

SELECT
    StudentId, StudentName, GroupName
FROM
    Students, Groups
WHERE
    Students.GroupId = Groups.GroupId
    AND
    StudentId NOT IN (
        SELECT
            StudentId
        FROM
            Marks
        WHERE
            CourseId = :CourseId
    );

-- ДЗ-6.2.3. Получить полную информацию о студентах, не имеющих оценки по дисциплине, заданной названием (StudentId, StudentName, GroupName по :CourseName).

SELECT
    StudentId, StudentName, GroupName
FROM
    Students, Groups
WHERE
    Students.GroupId = Groups.GroupId
    AND
    StudentId NOT IN (
        SELECT
            StudentId
        FROM
            Marks
        WHERE
            CourseId IN (
                SELECT
                    CourseId
                FROM
                    Courses
                WHERE
                    CourseName = :CourseName
            )
    );

-- ДЗ-6.2.4. Получить полную информацию о студентах, не имеющих оценки по дисциплине, у которых есть эта дисциплина (StudentId, StudentName, GroupName по :CourseId).

SELECT
    StudentId, StudentName, GroupName
FROM
    Students, Groups
WHERE
    Students.GroupId = Groups.GroupId
    AND
    StudentId NOT IN (
        SELECT
            StudentId
        FROM
            Marks
        WHERE
            CourseId = :CourseId
    )
    AND
    Students.GroupId IN (
        SELECT
            GroupId
        FROM
            Plan
        WHERE
            CourseId = :CourseId
    );

-- ДЗ-6.2.5. Получить полную информацию о студентах, не имеющих оценки по дисциплине, у которых есть эта дисциплина (StudentId, StudentName, GroupName по :CourseName).

SELECT
    StudentId, StudentName, GroupName
FROM
    Students, Groups
WHERE
    Students.GroupId = Groups.GroupId
    AND
    StudentId NOT IN (
        SELECT
            StudentId
        FROM
            Marks
        WHERE
            CourseId IN (
                SELECT
                    CourseId
                FROM
                    Courses
                WHERE
                    CourseName = :CourseName
            )
    )
    AND
    Students.GroupId IN (
        SELECT
            GroupId
        FROM
            Plan
        WHERE
            CourseId IN (
                SELECT
                    CourseId
                FROM
                    Courses
                WHERE
                    CourseName = :CourseName
            )
    );

-- ДЗ-6.3.1. Получить студентов и дисциплины, такие что у студента была дисциплина (по плану или есть оценка) (Идентификаторы) (StudentId, CourseId).

SELECT
    StudentId, CourseId
FROM
    Marks
WHERE
    1 = 1
UNION
SELECT
    StudentId, CourseId
FROM
    Students, Plan
WHERE
    Students.GroupId = Plan.GroupId;

-- ДЗ-6.3.2. Получить студентов и дисциплины, такие что у студента была дисциплина (по плану или есть оценка) (Имя и название) (StudentName, CourseName).

SELECT
    StudentName, CourseName
FROM
    Students, Courses
WHERE
    EXISTS (
        SELECT
            *
        FROM
            Marks
        WHERE
            Marks.StudentId = Students.StudentId
            AND
            Marks.CourseId = Courses.CourseId
    )
    OR
    EXISTS (
        SELECT
            *
        FROM
            Plan
        WHERE
            Students.GroupId = Plan.GroupId
            AND
            Courses.CourseId = Plan.CourseId
    );

-- ДЗ-6.4.1. Получить студентов и дисциплины, такие что дисциплина есть в его плане, и у студента долг по этой дисциплине, если долгом считается отсутствие оценки (StudentName, CourseName).

SELECT
    StudentName, CourseName
FROM
    Students, Courses
WHERE
    EXISTS (
        SELECT
            *
        FROM
            Plan
        WHERE
            Plan.GroupId = Students.GroupId
            AND
            Courses.CourseId = Plan.CourseId
    )
    AND
    NOT EXISTS (
        SELECT
            *
        FROM
            Marks
        WHERE
            Marks.StudentId = Students.StudentId
            AND
            Marks.CourseId = Courses.CourseId
    );

-- ДЗ-6.4.2. Получить студентов и дисциплины, такие что дисциплина есть в его плане, и у студента долг по этой дисциплине, если долгом считается оценка не выше 2 (StudentName, CourseName).

SELECT
    StudentName, CourseName
FROM
    Students, Courses
WHERE
    EXISTS (
        SELECT
            *
        FROM
            Plan
        WHERE
            Plan.GroupId = Students.GroupId
            AND
            Courses.CourseId = Plan.CourseId
    )
    AND
    EXISTS (
        SELECT
            *
        FROM
            Marks
        WHERE
            Marks.StudentId = Students.StudentId
            AND
            Marks.CourseId = Courses.CourseId
            AND
            Mark <= 2
    );

-- ДЗ-6.4.3. Получить студентов и дисциплины, такие что дисциплина есть в его плане, и у студента долг по этой дисциплине, если долгом считается отсутствие оценки или оценка не выше 2 (StudentName, CourseName).

SELECT
    StudentName, CourseName
FROM
    Students, Courses
WHERE
    EXISTS (
        SELECT
            *
        FROM
            Plan
        WHERE
            Plan.GroupId = Students.GroupId
            AND
            Courses.CourseId = Plan.CourseId
    )
    AND
    NOT EXISTS (
        SELECT
            *
        FROM
            Marks
        WHERE
            Marks.StudentId = Students.StudentId
            AND
            Marks.CourseId = Courses.CourseId
            AND
            Mark > 2
    );

-- ДЗ-6.5.1. Поулчить идентификаторы студентов по преподавателю, имеющих хотя бы одну оценку у преподавателя (StudentId по :LecturerName).

SELECT
    StudentId
FROM
    Students
WHERE
    EXISTS (
        SELECT
            *
        FROM
            Marks
        WHERE
            Students.StudentId = Marks.StudentId
            AND
            EXISTS (
                SELECT
                    *
                FROM
                    Plan
                WHERE
                    LecturerId IN (
                        SELECT
                            LecturerId
                        FROM
                            Lecturers
                        WHERE
                            LecturerName = :LecturerName
                    )
                    AND
                    Plan.CourseId = Marks.CourseId
                    AND
                    Plan.GroupId = Students.GroupId
            )
    );

-- ДЗ-6.5.2. Поулчить идентификаторы студентов по преподавателю, не имеющих ни одной оценки у преподавателя (StudentId по :LecturerName).

SELECT
    StudentId
FROM
    Students
WHERE
    NOT EXISTS (
        SELECT
            *
        FROM
            Marks
        WHERE
            Students.StudentId = Marks.StudentId
            AND
            EXISTS (
                SELECT
                    *
                FROM
                    Plan
                WHERE
                    LecturerId IN (
                        SELECT
                            LecturerId
                        FROM
                            Lecturers
                        WHERE
                            LecturerName = :LecturerName
                    )
                    AND
                    Plan.CourseId = Marks.CourseId
                    AND
                    Plan.GroupId = Students.GroupId
            )
    )

-- ДЗ-6.5.3. Поулчить идентификаторы студентов по преподавателю, имеющих оценки по всем дисциплинам преподавателя (StudentId по :LecturerName).

SELECT
    StudentId
FROM
    Students
WHERE
    NOT EXISTS (
        SELECT
            *
        FROM
            Plan
        WHERE
            LecturerId IN (
                SELECT
                    LecturerId
                FROM
                    Lecturers
                WHERE
                    LecturerName = :LecturerName
            )
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
    );

-- ДЗ-6.5.4. Поулчить идентификаторы студентов по преподавателю, имеющих оценки по всем дисциплинам преподавателя, которые он вёл у этого студента (StudentId по :LecturerName).

SELECT
    StudentId
FROM
    Students
WHERE
    NOT EXISTS (
        SELECT
            *
        FROM
            Plan
        WHERE
            LecturerId IN (
                SELECT
                    LecturerId
                FROM
                    Lecturers
                WHERE
                    LecturerName = :LecturerName
            )
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
            AND
            Plan.GroupId = Students.GroupId
    );

-- ДЗ-6.6.1. Получить группы и дисциплины, такие что все студенты группы имеют оценку по предмету (Идентификаторы) (GroupId, CourseId).

SELECT
    GroupId, CourseId
FROM
    Groups, Courses
WHERE
    NOT EXISTS (
        SELECT
            *
        FROM
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
                    Marks.CourseId = Courses.CourseId
 
            )
            AND
            Students.GroupId = Groups.GroupId
    );

-- ДЗ-6.6.2. Получить группы и дисциплины, такие что все студенты группы имеют оценку по предмету (Названия) (GroupName, CourseName).

SELECT
    GroupName, CourseName
FROM
    Groups, Courses
WHERE
    NOT EXISTS (
        SELECT
            *
        FROM
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
                    Marks.CourseId = Courses.CourseId
 
            )
            AND
            Students.GroupId = Groups.GroupId
    );

-- ДЗ-7.1.1. Напишите запросы, удаляющие студентов учащихся в группе, заданной идентификатором (GroupId).

DELETE FROM
    Students
WHERE
    GroupId = :GroupId;

-- ДЗ-7.1.2. Напишите запросы, удаляющие студентов учащихся в группе, заданной названием (GroupName).

DELETE FROM
    Students
WHERE
    GroupId IN (
        SELECT
            GroupId
        FROM
            Groups
        WHERE
            GroupName = :GroupName
    );

-- ДЗ-7.1.3. Напишите запросы, удаляющие студентов Без оценок.

DELETE FROM
    Students
WHERE
    StudentId NOT IN (
        SELECT
            StudentId
        FROM
            Marks
        WHERE
            1 = 1
    );

-- ДЗ-7.1.4. Напишите запросы, удаляющие студентов имеющих 3 и более оценки.

DELETE FROM
    Students
WHERE
    StudentId IN
    (
        SELECT
            StudentId
        FROM
            Marks
        WHERE
            1 = 1
        GROUP BY
            StudentId
        HAVING
            COUNT(CourseId) >= 3
    );

-- ДЗ-7.1.5. Напишите запросы, удаляющие студентов имеющих 3 и менее оценки.

DELETE FROM
    Students
WHERE
    StudentId NOT IN
    (
        SELECT
            StudentId
        FROM
            Marks
        WHERE
            1 = 1
        GROUP BY
            StudentId
        HAVING
            COUNT(CourseId) > 3
    );

-- ДЗ-7.1.6. Напишите запросы, удаляющие студентов c долгами (здесь и далее — по отсутствию оценки).

DELETE FROM
    Students
WHERE
    EXISTS (
        SELECT
            DISTINCT CourseId
        FROM
            Plan
        WHERE
            Plan.GroupId = Students.GroupId 
        EXCEPT
        SELECT
            DISTINCT CourseId
        FROM
            Marks
        WHERE
            Marks.StudentId = Students.StudentId
    );

-- ДЗ-7.1.7. Напишите запросы, удаляющие студентов имеющих 2 и более долга.

DELETE FROM
    Students
WHERE
    StudentId IN (
        SELECT
            Students.StudentId
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
                    Plan.CourseId = Marks.CourseId
                    AND
                    Marks.StudentId = Students.StudentId
            )
        GROUP BY
            Students.StudentId
        HAVING
            COUNT(DISTINCT CourseId) >= 2
    );

-- ДЗ-7.1.8. Напишите запросы, удаляющие студентов имеющих не более 2 долгов.

DELETE FROM
    Students
WHERE
    StudentId NOT IN (
        SELECT
            Students.StudentId
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
                    Plan.CourseId = Marks.CourseId
                    AND
                    Marks.StudentId = Students.StudentId
            )
        GROUP BY
            Students.StudentId
        HAVING
            COUNT(DISTINCT CourseId) > 2
    );

-- ДЗ-7.2.1. Изменение имени студента (StudentId, StudentName).

UPDATE
    Students
SET
    StudentName = :StudentName
WHERE
    StudentId = :StudentId;

-- ДЗ-7.2.2. Перевод студента из группы в группу по индентификаторам (StudentId, GroupId, FromGroupId).

UPDATE
    Students
SET
    GroupId = :GroupId
WHERE
    StudentId = :StudentId
    AND
    GroupId = :FromGroupId;

-- ДЗ-7.2.3. Перевод всех студентов из группы в группу по идентификаторам (GroupId, FromGroupId).

UPDATE
    Students
SET
    GroupId = :GroupId
WHERE
    GroupId = :FromGroupId;

-- ДЗ-7.2.4. Перевод всех студентов из группы в группу по названиям (GroupName, FromGroupName).

UPDATE
    Students
SET
    GroupId = (
        SELECT
            GroupId
        FROM
            Groups
        WHERE
            GroupName = :GroupName
    )
WHERE
    GroupId IN (
        SELECT
            GroupId
        FROM
            Groups
        WHERE
            GroupName = :FromGroupName
    );

-- ДЗ-7.2.5. Перевод всех студентов из группы в группу, только если целевая группа существует (GroupName, FromGroupName).

UPDATE
    Students
SET
    GroupId = (
        SELECT
            GroupId
        FROM
            Groups
        WHERE
            GroupName = :GroupName
    )
WHERE
    GroupId IN (
        SELECT
            GroupId
        FROM
            Groups
        WHERE
            GroupName = :FromGroupName
    )
    AND
    EXISTS (
        SELECT
            *
        FROM
            Groups
        WHERE
            GroupName = :GroupName
    );

-- ДЗ-7.3.1. Подсчет числа оценок студента (столбец Students.Marks) (StudentId).

UPDATE
    Students
SET
    Marks = (
        SELECT
            COUNT(*)
        FROM
            Marks
        WHERE
            Marks.StudentId = :StudentId
    )
WHERE
    StudentId = :StudentId;

-- ДЗ-7.3.2. Подсчет числа оценок каждого студента (столбец Students.Marks).

UPDATE
    Students
SET
    Marks = (
        SELECT
            COUNT(*)
        FROM
            Marks
        WHERE
            Marks.StudentId = Students.StudentId
    )
WHERE
    1 = 1;

-- ДЗ-7.3.3. Перерасчет числа оценок каждого студента, с учётом новых оценок из таблицы NewMarks, структура которой такая же как у таблицы Marks (столбец Students.Marks).

UPDATE
    Students
SET
    Marks = Marks + (
        SELECT
            COUNT(*)
        FROM
            NewMarks
        WHERE
            NewMarks.StudentId = Students.StudentId
    )
WHERE
    1 = 1;

-- ДЗ-7.3.4. Подсчет числа сданных дисциплин каждого студента (столбец Students.Marks).

UPDATE
    Students
SET
    Marks = (
        SELECT
            COUNT(DISTINCT CourseId)
        FROM
            Marks
        WHERE
            Marks.StudentId = Students.StudentId
    )
WHERE
    1 = 1;

-- ДЗ-7.3.5. Подсчет числа долгов студента (столбец Students.Debts) (StudentId).

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
                    Marks.StudentId = :StudentId
                    AND
                    Marks.CourseId = Plan.CourseId
            )
    )
WHERE
    StudentId = :StudentId;

-- ДЗ-7.3.6. Подсчет числа долгов каждого студента (столбец Students.Debts).

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
    1 = 1;

-- ДЗ-7.3.7. Подсчет числа долгов каждого студента группы (столбец Students.Debts) (GroupName).

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

-- ДЗ-7.3.8. Подсчет числа оценок и долгов каждого студента (столбцы Students.Marks, Students.Debts).

UPDATE
    Students
SET
    Marks = (
        SELECT
            COUNT(*)
        FROM
            Marks
        WHERE
            Marks.StudentId = Students.StudentId
    ),
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
    1 = 1;

-- ДЗ-7.4.1. Напишите запрос, обновляющий оценку, с учетом данных из таблицы NewMarks, имеющей такую же структуру, как таблица Marks, проставляющий новую оценку только если ранее оценки не было.

INSERT INTO
    Marks (StudentId, CourseId, Mark)
SELECT
    StudentId, CourseId, Mark
FROM
    NewMarks
WHERE
    NOT EXISTS (
        SELECT
            *
        FROM
            Marks
        WHERE
            NewMarks.StudentId = Marks.StudentId
            AND
            NewMarks.CourseId = Marks.CourseId
    );

-- ДЗ-7.4.2. Напишите запрос, обновляющий оценку, с учетом данных из таблицы NewMarks, имеющей такую же структуру, как таблица Marks, проставляющий новую оценку только если ранее оценка была.

UPDATE
    Marks
SET
    Mark = (
        SELECT
            Mark
        FROM
            NewMarks
        WHERE
            NewMarks.StudentId = Marks.StudentId
            AND
            NewMarks.CourseId = Marks.CourseId          
    )
WHERE
    EXISTS (
        SELECT
            *
        FROM
            NewMarks
        WHERE
            NewMarks.StudentId = Marks.StudentId
            AND
            NewMarks.CourseId = Marks.CourseId
    )

-- ДЗ-7.4.3. Напишите запрос, обновляющий оценку, с учетом данных из таблицы NewMarks, имеющей такую же структуру, как таблица Marks, проставляющий максимум из старой и новой оценки только если ранее оценка была.

UPDATE
    Marks
SET
    Mark = (
        SELECT
            Mark
        FROM
            NewMarks
        WHERE
            NewMarks.StudentId = Marks.StudentId
            AND
            NewMarks.CourseId = Marks.CourseId          
    )
WHERE
    EXISTS (
        SELECT
            *
        FROM
            NewMarks
        WHERE
            NewMarks.StudentId = Marks.StudentId
            AND
            NewMarks.CourseId = Marks.CourseId
            AND
            NewMarks.Mark > Marks.Mark
    );

-- ДЗ-7.4.4. Напишите запрос, обновляющий оценку, с учетом данных из таблицы NewMarks, имеющей такую же структуру, как таблица Marks, проставляющий максимум из старой и новой оценки (если ранее оценки не было, то новую оценку).

MERGE INTO
    Marks
USING
    NewMarks
ON
    Marks.StudentId = NewMarks.StudentId
    AND
    Marks.CourseId = NewMarks.CourseId
WHEN MATCHED
    AND Marks.Mark < NewMarks.Mark
THEN
    UPDATE SET Mark = NewMarks.Mark
WHEN NOT MATCHED THEN
    INSERT
        (StudentId, CourseId, Mark)
    VALUES
        (NewMarks.StudentId, NewMarks.CourseId, NewMarks.Mark);
