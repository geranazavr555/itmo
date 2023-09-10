DELIMITER $$

CREATE PROCEDURE FlightsStatistics (
	IN UserId_ INT,
	IN Pass_ VARCHAR(64),
	OUT CanBook BOOL,
	OUT CanBuy BOOL,
	OUT FreeCount INT,
	OUT BookedCount INT,
	OUT TicketsCount INT
)
LANGUAGE SQL
READS SQL DATA
SQL SECURITY DEFINER
BEGIN
	DECLARE _FreeCount INT;
	DECLARE _BookedCount INT;
	DECLARE _MyBookedCount INT;
	DECLARE _TicketsCount INT;
	DECLARE EXIT HANDLER FOR SQLSTATE '45000' SET CanBook = 0;
	
	SELECT
		COUNT(*) INTO _FreeCount
	FROM
		FutureFlights
	NATURAL JOIN 
		Seats
	WHERE
		NOT EXISTS (
			SELECT
				*
			FROM
				OccupiedSeats
			WHERE
				Seats.SeatId = OccupiedSeats.SeatId
				AND
				FutureFlights.FlightId = OccupiedSeats.FlightId
		);

	SELECT
		COUNT(*) INTO _BookedCount
	FROM
		FutureFlights
	NATURAL JOIN
		Bookings
	WHERE
		ExpiredTime > NOW();

	SELECT
		COUNT(*) INTO _TicketsCount
	FROM
		FutureFlights
	NATURAL JOIN
		Tickets;

	SET TicketsCount = _TicketsCount;
	SET BookedCount = _BookedCount;
	SET FreeCount = _FreeCount;

	SET CanBuy = _FreeCount > 0;

	CALL AuthUser(UserId_, Pass_);

	SELECT
		COUNT(*) INTO _MyBookedCount
	FROM
		FutureFlights
	NATURAL JOIN
		Bookings
	WHERE
		ExpiredTime > NOW()
		AND
		UserId = UserId_;

	SET CanBuy = _FreeCount > 0 OR _MyBookedCount > 0;
	SET CanBook = _FreeCount > 0;
END$$

DELIMITER ;