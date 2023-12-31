-- ДЗ-5.5.1. Получить для каждого студента ФИО и названия дисциплин 
-- которые у него есть по плану (StudentName, CourseName).
-- ДЗ-6.3.2. Получить студентов и дисциплины, такие что у студента
-- была дисциплина (по плану или есть оценка) 
-- ДЗ-6.4.1. Получить студентов и дисциплины, такие что дисциплина 
-- есть в его плане, и у студента долг по этой дисциплине, если 
-- долгом считается отсутствие оценки
-- Хочется уметь быстро джойнить по обоим колонкам GroupId, CourseId.
-- Так же используется для поиска по GroupId.
-- Так как хотим уметь использовать часть индекса, то BTREE.
CREATE UNIQUE INDEX Plan_GroupId_CourseId ON Plan USING BTREE (GroupId, CourseId);

-- ДЗ-6.2.4. Получить полную информацию о студентах, не имеющих 
-- оценки по дисциплине, у которых есть эта дисциплина
-- ДЗ-6.2.5. Получить полную информацию о студентах, не имеющих
-- оценки по дисциплине, у которых есть эта дисциплина
-- ДЗ-7.1.7. Напишите запросы, удаляющие студентов имеющих 2 и более 
-- долга.
-- Хочется уметь быстро джойнить по обоим колонкам GroupId, CourseId.
-- Так же используется для поиска по CourseId и для джойна по нему.
-- Так как хотим уметь использовать часть индекса, то BTREE.
CREATE UNIQUE INDEX Plan_CourseId_GroupId ON Plan USING BTREE (CourseId, GroupId);

-- ДЗ-5.3.3. Получить информацию о студентах с заданной оценкой по 
-- дисциплине которую у него вёл лектор заданный идентификатором
-- ДЗ-5.3.4. Получить информацию о студентах с заданной оценкой по
-- дисциплине которую у него вёл лектор, заданный ФИО
-- ДЗ-5.3.6. Получить информацию о студентах с заданной оценкой по
-- дисциплине которую вёл лектор, заданный ФИО
-- Хеш, так как не нужна упорядоченность. Можно было бы сделать 
-- покрывающий индекс через дерево, но с ходу не очевидно, что он 
-- нужен. Надо бенчмаркать.
CREATE INDEX Plan_LecturerId ON Lecturers Plan HASH (LecturerId);