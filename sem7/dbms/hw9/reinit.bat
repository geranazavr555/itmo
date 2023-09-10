mariadb -u root -p < drop.sql
mariadb -u root -p < init.sql
mariadb -u root -p airlines < 01tablesANdView.sql
mariadb -u root -p airlines < 02miscProcedures.sql
mariadb -u root -p airlines < 1FreeSeats.sql
mariadb -u root -p airlines < 2Reserve.sql
mariadb -u root -p airlines < 3ExtendReservation.sql
mariadb -u root -p airlines < 4BuyFree.sql
mariadb -u root -p airlines < 5BuyReserved.sql
mariadb -u root -p airlines < 6FlightsStatistics.sql
mariadb -u root -p airlines < 7FlightStat.sql
mariadb -u root -p airlines < 8CompressSeats.sql
mariadb -u root -p airlines < initData.sql