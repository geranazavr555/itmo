-- MariaDB 10.6.5
-- https://mariadb.com/kb/en/create-trigger/

CREATE TRIGGER
	PreserveMarks
BEFORE UPDATE
ON
	Marks
FOR EACH ROW
	SET NEW.Mark = GREATEST(NEW.Mark, OLD.Mark);