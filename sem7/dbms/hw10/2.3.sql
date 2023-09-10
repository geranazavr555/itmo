-- Выкуп брони
START TRANSACTION ISOLATION LEVEL REPEATABLE READ;
CALL BuyReserved(@UserId, @Pass, @FlightId, @SeatNo, @Success);
COMMIT;

-- Бронь
START TRANSACTION ISOLATION LEVEL READ COMMITED;
Reserve (@UserId, @Pass, @FlightId, @SeatNo, @Success);
COMMIT;

-- Покупка билета
START TRANSACTION ISOLATION LEVEL READ COMMITED;
BuyFree (@FlightId, @SeatNo, @Success);
COMMIT;