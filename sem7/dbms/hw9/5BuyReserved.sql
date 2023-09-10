DELIMITER $$

CREATE PROCEDURE BuyReserved (IN UserId_ INT, IN Pass_ VARCHAR(64), IN FlightId_ INT, IN SeatNo_ VARCHAR(4), OUT Success BOOL)
LANGUAGE SQL
MODIFIES SQL DATA
SQL SECURITY DEFINER
BEGIN
	DECLARE _SeatId INT;
	DECLARE _BookingId INT;
	DECLARE EXIT HANDLER FOR SQLSTATE '45000'
	BEGIN
		SET Success = 0;
		RESIGNAL SQLSTATE '01000';
	END;

	CALL AuthUser(UserId_, Pass_);
	CALL EnsureFlightId(FlightId_);
	CALL GetSeatId(FlightId_, SeatNo_, _SeatId);
	CALL GetBookingId(UserId_, FlightId_, _SeatId, _BookingId);

	SET Success = 0;

	INSERT INTO Tickets (TicketId, FlightId, SeatId) VALUES
		(GetNextTicketId(), FlightId_, _SeatId);
	DELETE FROM Bookings WHERE BookingId = _BookingId;

	SET Success = 1;
	SELECT Success;
END$$

DELIMITER ;