DELIMITER $$

CREATE PROCEDURE BuyFree (IN FlightId_ INT, IN SeatNo_ VARCHAR(4), OUT Success BOOL)
LANGUAGE SQL
MODIFIES SQL DATA
SQL SECURITY DEFINER
BuyFreeLabel:
BEGIN
	DECLARE _SeatId INT;
	DECLARE EXIT HANDLER FOR SQLSTATE '45000'
	BEGIN
		SET Success = 0;
		RESIGNAL SQLSTATE '01000';
	END;

	CALL EnsureFlightId(FlightId_);
	CALL GetSeatId(FlightId_, SeatNo_, _SeatId);
	IF NOT IsSeatFree(FlightId_, SeatNo_) THEN
		SET Success = 0;
		SELECT Success;
		LEAVE BuyFreeLabel;
	END IF;

	SET Success = 0;

	INSERT INTO Tickets (TicketId, FlightId, SeatId) VALUES
		(GetNextTicketId(), FlightId_, _SeatId);

	SET Success = 1;
	SELECT Success;
END$$

DELIMITER ;