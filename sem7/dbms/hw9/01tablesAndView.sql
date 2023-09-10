-- Самолеты. В запросах не используется, нужен только для foreign keys.
CREATE TABLE Planes (
	PlaneId INT NOT NULL,

	CONSTRAINT Planes_pk PRIMARY KEY (PlaneId)
);

-- Рейсы
CREATE TABLE Flights (
	FlightId INT NOT NULL,
	FlightTime TIMESTAMP NOT NULL DEFAULT 0,
	PlaneId INT NOT NULL,

	CONSTRAINT Flights_pk PRIMARY KEY (FlightId),
	CONSTRAINT Flights_Planes FOREIGN KEY (PlaneId) REFERENCES Planes (PlaneId)
);

-- Посадочные места.
-- SeatNo имеет вид 1A, 22C, 123F
CREATE TABLE Seats (
	PlaneId INT NOT NULL,
	SeatNo VARCHAR(4) NOT NULL,
	SeatId INT NOT NULL,

	CONSTRAINT Seats_pk PRIMARY KEY (SeatId),
	CONSTRAINT Seats_Planes FOREIGN KEY (PlaneId) REFERENCES Planes (PlaneId),
	CONSTRAINT Seats_unique_PlaneId_SeatNo UNIQUE KEY (PlaneId, SeatNo)
);

-- Пользователи
CREATE TABLE Users (
	UserId INT NOT NULL,
	Password VARCHAR(224) NOT NULL,
	Salt VARCHAR(64) NOT NULL,

	CONSTRAINT Users_pk PRIMARY KEY (UserId)
);

-- Бронирования
CREATE TABLE Bookings (
	BookingId INT NOT NULL,
	UserId INT NOT NULL,
	FlightId INT NOT NULL,
	SeatId INT NOT NULL,
	BookingTime TIMESTAMP NOT NULL DEFAULT 0,
	ExpiredTime TIMESTAMP NOT NULL DEFAULT 0,

	CONSTRAINT Bookings_pk PRIMARY KEY (BookingId),
	CONSTRAINT Bookings_Users FOREIGN KEY (UserId) REFERENCES Users (UserId),
	CONSTRAINT Bookings_Flights FOREIGN KEY (FlightId) REFERENCES Flights (FlightId),
	CONSTRAINT Bookings_Seats FOREIGN KEY (SeatId) REFERENCES Seats (SeatId),
	CONSTRAINT Bookings_unique_FlightId_SeatId UNIQUE KEY (FlightId, SeatId)
);

-- Билеты
CREATE TABLE Tickets (
	TicketId INT NOT NULL,
	FlightId INT NOT NULL,
	SeatId INT NOT NULL,

	CONSTRAINT Tickets_pk PRIMARY KEY (TicketId),
	CONSTRAINT Tickets_Flights FOREIGN KEY (FlightId) REFERENCES Flights (FlightId),
	CONSTRAINT Tickets_Seats FOREIGN KEY (SeatId) REFERENCES Seats (SeatId)
);

-- Занятые (забронированные или купленные) места.
CREATE VIEW OccupiedSeats AS
SELECT
	Flights.FlightId AS FlightId,
	Seats.PlaneId AS PlaneId,
	Seats.SeatNo AS SeatNo,
	Seats.SeatId AS SeatId
FROM
	Flights
NATURAL JOIN
	Seats
WHERE
	EXISTS (
		SELECT
			*
		FROM
			Bookings
		WHERE
			Bookings.FlightId = Flights.FlightId
			AND
			Bookings.SeatId = Seats.SeatId
			AND
			Bookings.ExpiredTime > NOW()
	)
	OR
	EXISTS (
		SELECT
			*
		FROM
			Tickets
		WHERE
			Tickets.FlightId = Flights.FlightId
			AND
			Tickets.SeatId = Seats.SeatId
	);

-- Id просроченных бронирований.
CREATE VIEW ExpiredBookingsIds AS
SELECT
	BookingId
FROM
	Bookings
WHERE
	ExpiredTime > NOW();

-- Ещё не улетевшие рейсы
CREATE VIEW FutureFlights AS
SELECT
	FlightId, FlightTime, PlaneId
FROM
	Flights
WHERE
	FlightTime > NOW();