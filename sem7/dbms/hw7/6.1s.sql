-- MariaDB 10.6.5
-- https://mariadb.com/kb/en/create-trigger/
-- https://mariadb.com/kb/en/trigger-overview/
-- https://mariadb.com/kb/en/begin-end/
-- https://mariadb.com/kb/en/if/
-- https://mariadb.com/kb/en/signal/
-- https://mariadb.com/kb/en/sqlstate/

DELIMITER $$
CREATE TRIGGER
	NoExtraMarksInsertMark
BEFORE INSERT
ON
	Marks
FOR EACH ROW
BEGIN
	IF
		NOT EXISTS (
			SELECT
				*
			FROM
				Plan
			NATURAL JOIN
				Students
			WHERE
				StudentId = NEW.StudentId
				AND
				CourseId = NEW.CourseId
		)
	THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Attempt to insert mark for a course that student has not listened';
	END IF;
END$$

CREATE TRIGGER
	NoExtraMarksUpdateMark
BEFORE UPDATE
ON
	Marks
FOR EACH ROW
BEGIN
	IF
		NOT EXISTS (
			SELECT
				*
			FROM
				Plan
			NATURAL JOIN
				Students
			WHERE
				StudentId = NEW.StudentId
				AND
				CourseId = NEW.CourseId
		)
	THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Attempt to update mark for a course that student has not listened';
	END IF;
END$$

CREATE TRIGGER
	NoExtraMarksRemoveCourseFromPlan
BEFORE DELETE
ON
	Plan
FOR EACH ROW
BEGIN
	IF
		EXISTS (
			SELECT
				*
			FROM
				Marks
			NATURAL JOIN
				Students
			WHERE
				GroupId = OLD.GroupId
				AND
				CourseId = OLD.CourseId
		)
	THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Attempt to delete course that already have marks';
	END IF;
END$$

CREATE TRIGGER
	NoExtraMarksChangePlan
BEFORE UPDATE
ON
	Plan
FOR EACH ROW
BEGIN
	IF
		EXISTS (
			SELECT
				*
			FROM
				Marks
			NATURAL JOIN
				Students
			WHERE
				GroupId = OLD.GroupId
				AND
				CourseId = OLD.CourseId
		)
		AND
		(NEW.GroupId != OLD.GroupId OR NEW.CourseId != OLD.CourseId)
	THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Attempt to change course that already have marks';
	END IF;
END$$
DELIMITER ;