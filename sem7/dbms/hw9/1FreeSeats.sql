CREATE PROCEDURE FreeSeats (IN FlightId_ INT)
LANGUAGE SQL
READS SQL DATA
SQL SECURITY DEFINER
SELECT
	SeatNo
FROM
	Seats
WHERE
	PlaneId = (
		SELECT
			PlaneId
		FROM
			Flights
		WHERE
			FlightId = FlightId_
	)
	AND
	SeatId NOT IN (
		SELECT
			SeatId
		FROM
			OccupiedSeats
		WHERE
			FlightId = FlightId_
	);
