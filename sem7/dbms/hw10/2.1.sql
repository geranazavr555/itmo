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

-- Берет список броней пользователя на рейсте.
CREATE PROCEDURE GetBookings (IN UserId_ INT, IN Pass_ VARCHAR(64), IN FlightId_ INT)
LANGUAGE SQL
READS SQL DATA
BEGIN
	CALL AuthUser(UserId_, Pass_);

	SELECT
		BookingId, SeatNo
	FROM
		Bookings
	NATURAL JOIN
		Seats
	WHERE
		UserId = UserId_
		AND
		FlightId = FlightId_;
END$$

-- Проверка существования рейса для пункта 2 сценария.
-- Мы знаем, что Flights не меняются, поэтому можем себе позволить read uncommited.
START TRANSACTION READ ONLY ISOLATION LEVEL READ UNCOMMITED;
CALL EnsureFlightId(@FlightId);
COMMIT;

-- Берем список свободных мест для пункта 8 сценария.
START TRANSACTION READ ONLY ISOLATION LEVEL READ COMMITED;
CALL FreeSeats(@FlightId);
COMMIT;

-- Берем список броней юзера по рейсу.
START TRANSACTION READ ONLY ISOLATION LEVEL READ COMMITED;
CALL GetBookings(@UserId, @Pass, @FlightId);
COMMIT;