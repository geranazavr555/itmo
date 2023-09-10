INSERT INTO Students (studentId, studentName) VALUES
	(1, 'Георгий Назаров'),
	(2, 'Максим Загранкин'),
	(3, 'Равиль Галиев'),
	(4, 'Артём Шашуловский'),
	(5, 'Ярослав Ильин'),
	(6, 'Илона Боже');

INSERT INTO Groups (groupId, groupName) VALUES
	(1, 'M34391'),
	(2, 'M34371'),
	(3, 'M33331');

INSERT INTO StudentGroups (studentId, groupId) VALUES
	(1, 2),
	(2, 2),
	(3, 3),
	(4, 3),
	(5, 1),
	(6, 1);

INSERT INTO Lecturers (lecturerId, lecturerName) VALUES
	(1, 'Георгий Корнеев'),
	(2, 'Виталий Аксёнов');

INSERT INTO Courses (courseId, courseName) VALUES
	(1, 'Введение в базы данных'),
	(2, 'Продвинутые параллельные алгоритмы'),
	(3, 'Язык программирования Java');

INSERT INTO GroupCourseLecturers (groupId, courseId, lecturerId) VALUES
	(1, 1, 1),
	(2, 1, 1),
	(1, 2, 2),
	(2, 2, 2),
	(3, 3, 1);

INSERT INTO Marks (studentId, courseId, mark) VALUES
	(1, 1, 59),
	(2, 1, 59),
	(1, 2, 61),
	(2, 2, 61),
	(3, 3, 100),
	(4, 3, 100);