-- Использую мою реализацию из дз 9.
-- Идея: проверяем пользователя, проверяем Flight, находим SeatId, проверяем что оно свободно, добавляем бронь. 

START TRANSACTION ISOLATION LEVEL READ COMMITED;

-- Аномалий косой записи нет, так как у нас один insert. При параллельном исполнении 
-- завершится успешно только один из-за constrints.

-- Фантомных записей и неповторяемых чтений нет, так как таблички,
-- которые читаются несколько раз (например Seats) не могут поменяться нашими процедурами.

-- Так как у нас read-write, то read uncommited не подходит.