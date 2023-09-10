-- Использую мою реализацию из дз 9.

START TRANSACTION READ ONLY ISOLATION LEVEL SERIALIZABLE;

-- Аномалий косой записи нет, так как read-only.

-- Фантомная запись -- может случиться, если паралелльно кто-то купит или забронирует билет.
-- Дальше зависит от того, насколько нас это волнует, но имхо если FreeCount + TicketsCount + BookingsCount !=
-- суммарному числу мест это выглядит странно (хоть и совпадает с реальным миром).

-- Так как я решил, что не хочу видеть ситуацию когда FreeCount + TicketsCount + BookingsCount !=
-- суммарному числу мест, то нам нужен как минимум snapshot. Но его нет ни в стандарте. ни в моей любимой субд, 
-- поэтому serializable.