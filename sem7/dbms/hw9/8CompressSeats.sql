DELIMITER $$

CREATE PROCEDURE CompressSeats (IN FlightId_ INT)
LANGUAGE SQL
MODIFIES SQL DATA
SQL SECURITY INVOKER
BEGIN
	DECLARE _PlaneId INT;
	DECLARE _NewSeatId INT;

	CALL EnsureFlightId(FlightId_);

	SELECT PlaneId INTO _PlaneId FROM Flights WHERE FlightId = FlightId_;

	BEGIN
		DECLARE _BookingId INT;
		DECLARE _UserId INT;
		DECLARE _BookingTime TIMESTAMP;
		DECLARE _ExpiredTime TIMESTAMP;
		DECLARE _TicketId INT;
		DECLARE _StopLoop BOOL;
		DECLARE SeatsCursor CURSOR FOR
			SELECT
				SeatId
			FROM
				Seats
			WHERE
				PlaneId = _PlaneId
			ORDER BY
				NormalizeSeatNo(SeatNo);
		DECLARE TicketsSeatsCursor CURSOR FOR
			SELECT
				TicketId
			FROM
				Tickets
			WHERE
				FlightId = FlightId_;
		DECLARE BookingsSeatsCursor CURSOR FOR
			SELECT
				BookingId, UserId, BookingTime, ExpiredTime
			FROM
				Bookings
			WHERE
				FlightId = FlightId_;
		DECLARE CONTINUE HANDLER FOR NOT FOUND SET _StopLoop = 1;

		CREATE TEMPORARY TABLE TempTickets (
			TicketId INT NOT NULL,
			SeatId INT NOT NULL,

			CONSTRAINT TempTickets_pk PRIMARY KEY (TicketId)
		);

		CREATE TEMPORARY TABLE TempBookings (
			BookingId INT NOT NULL,
			UserId INT NOT NULL,
			SeatId INT NOT NULL,
			BookingTime TIMESTAMP NOT NULL DEFAULT 0,
			ExpiredTime TIMESTAMP NOT NULL DEFAULT 0,

			CONSTRAINT TempBookings_pk PRIMARY KEY (BookingId)
		);

		OPEN SeatsCursor;

		OPEN TicketsSeatsCursor;
		SET _StopLoop = 0;
		ticketsLoop: LOOP
			FETCH TicketsSeatsCursor INTO _TicketId;
			IF _StopLoop THEN
				LEAVE ticketsLoop;
			END IF;
			FETCH SeatsCursor INTO _NewSeatId;
			INSERT INTO TempTickets (TicketId, SeatId) VALUES (_TicketId, _NewSeatId);
		END LOOP ticketsLoop;
		CLOSE TicketsSeatsCursor;

		OPEN BookingsSeatsCursor;
		SET _StopLoop = 0;
		bookingsLoop: LOOP
			FETCH BookingsSeatsCursor INTO _BookingId, _UserId, _BookingTime, _ExpiredTime;
			IF _StopLoop THEN
				LEAVE bookingsLoop;
			END IF;
			FETCH SeatsCursor INTO _NewSeatId;
			INSERT INTO TempBookings (BookingId, UserId, SeatId, BookingTime, ExpiredTime) VALUES
				(_BookingId, _UserId, _NewSeatId, _BookingTime, _ExpiredTime);
		END LOOP bookingsLoop;
		CLOSE BookingsSeatsCursor;

		CLOSE SeatsCursor;

		SELECT * FROM TempTickets;
		SELECT * FROM TempBookings;

		UPDATE Tickets SET SeatId = (
			SELECT
				TempTickets.SeatId
			FROM
				TempTickets
			WHERE
				TempTickets.TicketId = Tickets.TicketId
		)
		WHERE TicketId IN (
			SELECT
				TicketId
			FROM
				TempTickets
		);

		UPDATE Bookings SET SeatId = (
			SELECT
				TempBookings.SeatId
			FROM
				TempBookings
			WHERE
				TempBookings.BookingId = Bookings.BookingId
		)
		WHERE BookingId IN (
			SELECT
				BookingId
			FROM
				TempBookings
		);
	END;
END$$

DELIMITER ;