-- MariaDB 10.6.5
-- https://mariadb.com/kb/en/create-trigger/
-- https://mariadb.com/kb/en/trigger-overview/
-- https://mariadb.com/kb/en/begin-end/
-- https://mariadb.com/kb/en/if/
-- https://mariadb.com/kb/en/signal/
-- https://mariadb.com/kb/en/sqlstate/
-- https://mariadb.com/kb/en/stored-procedure-overview/
-- https://mariadb.com/kb/en/create-procedure/

DELIMITER $$

CREATE PROCEDURE
	CheckSameMarks()
LANGUAGE SQL
READS SQL DATA
BEGIN
	IF EXISTS (
		SELECT
			*
		FROM
			Students S1
		INNER JOIN
			Students S2
		ON
			S1.GroupId = S2.GroupId
		WHERE
			EXISTS (
				SELECT
					*
				FROM
					Marks M1
				WHERE
					M1.StudentId = S1.StudentId
					AND
					NOT EXISTS (
						SELECT
							*
						FROM
							Marks M2
						WHERE
							M2.StudentId = S2.StudentId
							AND
							M2.CourseId = M1.CourseId
					)
			)
	)
	THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'SameMarks constaint failed';
	END IF;
END$$

CREATE TRIGGER
	SameMarksInsertMark
AFTER INSERT
ON
	Marks
FOR EACH ROW
BEGIN
	CALL CheckSameMarks();
END$$

CREATE TRIGGER
	SameMarksUpdateMark
AFTER UPDATE
ON
	Marks
FOR EACH ROW
BEGIN
	CALL CheckSameMarks();
END$$

CREATE TRIGGER
	SameMarksDeleteMark
AFTER DELETE
ON
	Marks
FOR EACH ROW
BEGIN
	CALL CheckSameMarks();
END$$

DELIMITER ;