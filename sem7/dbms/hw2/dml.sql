USE University;

INSERT INTO Persons (personId, firstName, lastName, birthday, passportSeries, passportNumber) VALUES
	(1, 'Денис', 'Воркожоков', '2001-05-31', '0715', '886979'),
	(2, 'Анатолий', 'Шалыто', '1948-05-28', '4002', '348687'),
	(3, 'Илона', 'Боже', '2001-11-28', '3415', '685489'),
	(4, 'Александр', 'Софрыгин', '2002-01-01', '0716', '154688'),
	(5, 'Георгий', 'Корнеев', '1981-02-23', '4004', '541154');

INSERT INTO Teachers (personId) VALUES
	(1),
	(2),
	(5);

INSERT INTO Groups (groupId, name) VALUES
	(1, 'M34371'),
	(2, 'M34391');

INSERT INTO Students (personId, groupId) VALUES
	(1, 2),
	(3, 2),
	(4, 1);

INSERT INTO Disciplines (disciplineId, name) VALUES
	(1, 'Проектирование баз данных'),
	(2, 'Автоматное программирование'),
	(3, 'Язык программирования C++');

INSERT INTO Courses (groupId, disciplineId, teacherId) VALUES
	(1, 2, 2),
	(1, 3, 1),
	(2, 1, 5),
	(1, 1, 5);

INSERT INTO Grades (groupId, disciplineId, studentId, grade, score) VALUES
	(2, 1, 1, 'E', 67),
	(1, 1, 4, 'C', 79);