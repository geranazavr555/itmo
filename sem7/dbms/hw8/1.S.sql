-- ДЗ-5.2.1. Получить полную информацию о студентах с заданным 
-- идентификатором
-- ДЗ-6.1.3. Получить информацию о студентах с заданной оценкой по 
-- дисциплине, заданной идентификатором
-- ДЗ-7.2.1. Изменение имени студента
-- Хеш, так как нам точно нужен индекс по primary key, а 
-- упорядоченность дерева выглядит бессмысленно (странно было бы 
-- сравнивать два StudentId. По крайней мере таких запросов в прошлых 
-- дз нет.
CREATE UNIQUE INDEX Students_StudentId ON Students USING HASH (StudentId);

-- ДЗ-5.1.2. Получить информацию о студентах с заданным ФИО
-- ДЗ-5.2.2. Получить полную информацию о студентах с заданным ФИО
-- Хеш, потому что нам тут не нужна упорядоченность
CREATE INDEX Students_StudentName ON Students USING HASH (StudentName);

-- ДЗ-6.1.2. Получить студентов- учащихся в заданной группе
-- ДЗ-6.2.1. Получить полную информацию о всех студентах 
-- (StudentId, StudentName, GroupName)
-- ДЗ-6.3.1. Получить студентов и дисциплины, такие что у студента 
-- была дисциплина (по плану или есть оценка) (Идентификаторы) 
-- Хеш, так как упорядоченность по группам нам не важна. Ускоряет
-- запросы в которых есть джойн по группе.
CREATE INDEX Students_GroupId ON Students USING HASH (GroupId);