
DELIMITER $$

-- Генерирует случайную соль для пароля
CREATE FUNCTION GenSalt () RETURNS VARCHAR(64)
LANGUAGE SQL
CONTAINS SQL
NOT DETERMINISTIC
BEGIN
	RETURN MD5(RAND());
END$$

-- Считает солёный хэш пароля
CREATE FUNCTION GetHash (Password_ VARCHAR(64), Salt_ VARCHAR(64)) RETURNS VARCHAR(224)
LANGUAGE SQL
CONTAINS SQL
DETERMINISTIC
BEGIN
	RETURN SHA2(CONCAT(Password_, Salt_), 224);
END$$

-- Возвращает не занятый user id
CREATE FUNCTION GetNextUserId () RETURNS INT
LANGUAGE SQL
READS SQL DATA
BEGIN
	DECLARE _Result INT;

	SELECT COALESCE(MAX(UserId), 0) + 1 INTO _Result FROM Users;
	RETURN _Result;
END$$

-- Возвращает не занятый ticket id
CREATE FUNCTION GetNextTicketId () RETURNS INT
LANGUAGE SQL
READS SQL DATA
BEGIN
	DECLARE _Result INT;

	SELECT COALESCE(MAX(TicketId), 0) + 1 INTO _Result FROM Tickets;
	RETURN _Result;
END$$

-- Возвращает не занятый booking id
CREATE FUNCTION GetNextBookingId () RETURNS INT
LANGUAGE SQL
READS SQL DATA
BEGIN
	DECLARE _Result INT;

	SELECT COALESCE(MAX(BookingId), 0) + 1 INTO _Result FROM Bookings;
	RETURN _Result;
END$$

-- Регистрирует пользователя. Возвращает userId нового пользователя
CREATE PROCEDURE RegisterUser (IN Password_ VARCHAR(64), OUT UserId_ INT)
LANGUAGE SQL
MODIFIES SQL DATA
SQL SECURITY DEFINER
BEGIN
	DECLARE _UserId INT;
	DECLARE _Salt VARCHAR(64) DEFAULT GenSalt();
	DECLARE _Password VARCHAR(224);

	SET _UserId = GetNextUserId();
	SET _Password = GetHash(Password_, _Salt);
	INSERT INTO Users (UserId, Password, Salt) VALUES (_UserId, _Password, _Salt);
	SET UserId_ = _UserId;
END$$

-- Проверяет пароль пользователя. Если неверный или такого пользователя нет, кидает user defined error.
CREATE PROCEDURE AuthUser (IN UserId_ INT, IN Password_ VARCHAR(64))
LANGUAGE SQL
READS SQL DATA
BEGIN
	DECLARE _Salt VARCHAR(64) DEFAULT NULL;
	DECLARE _Password VARCHAR(224) DEFAULT NULL;

	SELECT Password, Salt INTO _Password, _Salt FROM Users WHERE UserId = UserId_;
	IF _Salt IS NULL THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No such user';
	END IF;

	IF _Password <> GetHash(Password_, _Salt) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Incorrect password';
	END IF;
END$$

-- Удаляет просроченные брони. Нигде не вызывается, предполагается, что оно будет вызываться сотрудниками или по крону.
CREATE PROCEDURE DeleteExpiredBookings ()
LANGUAGE SQL
MODIFIES SQL DATA
DELETE FROM
	Bookings
WHERE
	BookingId IN (
		SELECT
			BookingId
		FROM
			ExpiredBookingsIds
	)$$

-- Проверяет что место свободно, возвращает true/false.
CREATE FUNCTION IsSeatFree (FlightId_ INT, SeatNo_ VARCHAR(4)) RETURNS BOOL
LANGUAGE SQL
READS SQL DATA
BEGIN
	RETURN NOT EXISTS(
		SELECT
			*
		FROM
			OccupiedSeats
		WHERE
			FlightId = FlightId_
			AND
			SeatNo = SeatNo_
	);
END$$

-- Ищет seatId по flightId и seatNo. Если не нашел, кидает ошибку.
CREATE PROCEDURE GetSeatId (IN FlightId_ INT, IN SeatNo_ VARCHAR(4), OUT SeatId_ INT)
LANGUAGE SQL
READS SQL DATA
BEGIN
	DECLARE _SeatId INT DEFAULT NULL;

	SELECT
		SeatId INTO _SeatId
	FROM
		Flights
	NATURAL JOIN
		Seats
	WHERE
		FlightId = FlightId_
		AND
		SeatNo = SeatNo_;

	IF _SeatId IS NULL THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No such seat';
	END IF;

	SET SeatId_ = _SeatId;
END$$

-- Проверяет что заданный рейс существует. Если его нет, кидает ошибку.
CREATE PROCEDURE EnsureFlightId (IN FlightId_ INT)
LANGUAGE SQL
READS SQL DATA
BEGIN
	DECLARE _FlightId INT DEFAULT NULL;

	SELECT
		FlightId INTO _FlightId
	FROM
		Flights
	WHERE
		FlightId = FlightId_;

	IF _FlightId IS NULL THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No such flight';
	END IF;
END$$

-- Ищет номер брони по пользователю, рейсу и месту. Если не нашел, кидает ошибку
CREATE PROCEDURE GetBookingId (IN UserId_ INT, IN FlightId_ INT, IN SeatId_ INT, OUT BookingId_ INT)
LANGUAGE SQL
READS SQL DATA
BEGIN
	DECLARE _BookingId INT DEFAULT NULL;

	SELECT
		BookingId INTO _BookingId
	FROM
		Bookings
	WHERE
		FlightId = FlightId_
		AND
		SeatId = SeatId_
		AND
		UserId = UserId_
		AND
		ExpiredTime > NOW();

	IF _BookingId IS NULL THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No such booking';
	END IF;

	SET BookingId_ = _BookingId;
END$$

-- Приводит номера мест к виду 001A. Нужно для сортировки.
CREATE FUNCTION NormalizeSeatNo(SeatNo VARCHAR(4)) RETURNS VARCHAR(4)
LANGUAGE SQL
CONTAINS SQL
DETERMINISTIC
BEGIN
	DECLARE _SeatNo VARCHAR(4);

	SET _SeatNo = SeatNo;

	WHILE LENGTH(_SeatNo) < 4 DO
		SET _SeatNo = CONCAT('0', _SeatNo);
	END WHILE;

	RETURN _SeatNo;
END$$

DELIMITER ;