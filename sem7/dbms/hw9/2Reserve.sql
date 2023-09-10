DELIMITER $$

CREATE PROCEDURE Reserve (IN UserId_ INT, IN Pass_ VARCHAR(64), IN FlightId_ INT, IN SeatNo_ VARCHAR(4), OUT Success BOOL)
LANGUAGE SQL
MODIFIES SQL DATA
SQL SECURITY DEFINER
ReserveLabel:
BEGIN
	DECLARE _SeatId INT;
	DECLARE EXIT HANDLER FOR SQLSTATE '45000'
	BEGIN
		SET Success = 0;
		RESIGNAL SQLSTATE '01000';
	END;

	CALL AuthUser(UserId_, Pass_);
	CALL EnsureFlightId(FlightId_);
	CALL GetSeatId(FlightId_, SeatNo_, _SeatId);

	IF NOT IsSeatFree(FlightId_, SeatNo_) THEN
		SET Success = 0;
		SELECT Success;
		LEAVE ReserveLabel;
	END IF;

	SET Success = 0;

	INSERT INTO Bookings(BookingId, UserId, FlightId, SeatId, BookingTime, ExpiredTime) VALUES
		(GetNextBookingId(), UserId_, FlightId_, _SeatId, NOW(), NOW() + INTERVAL 3 DAY);

	SET Success = 1;
	SELECT Success;
END$$

DELIMITER ;