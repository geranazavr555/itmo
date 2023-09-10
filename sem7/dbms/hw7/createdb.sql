DROP DATABASE University;

CREATE DATABASE University;
USE University;

CREATE TABLE Students (
	StudentId INT NOT NULL,
	StudentName VARCHAR(256) NOT NULL,
	GroupId INT NOT NULL
);

CREATE TABLE Groups (
	GroupId INT NOT NULL,
	GroupName VARCHAR(256) NOT NULL
);

CREATE TABLE Courses (
	CourseId INT NOT NULL,
	CourseName VARCHAR(256) NOT NULL
);

CREATE TABLE Lecturers (
	LecturerId INT NOT NULL,
	LecturerName VARCHAR(256) NOT NULL
);

CREATE TABLE Plan (
	GroupId INT NOT NULL,
	CourseId INT NOT NULL,
	LecturerId INT NOT NULL
);

CREATE TABLE Marks (
	StudentId INT NOT NULL,
	CourseId INT NOT NULL,
	Mark INT NOT NULL
);

INSERT INTO Students(StudentId, StudentName, GroupId) VALUES
    (1        , 'Иванов И.И.'     , 1      ),
    (2        , 'Петров П.П.'     , 1      ),
    (3        , 'Петров П.П.'     , 2      ),
    (4        , 'Сидров С.С.'     , 2      ),
    (5        , 'Неизвестный Н.Н.', 3      ),
    (6        , 'Безымянный Б.Б'  , 4 );

INSERT INTO Groups(GroupId, GroupName) VALUES
	(    1      , 'M3435'  ),
   ( 2      , 'M3439'  ),
   ( 3      , 'M3238'  ),
   ( 4      , 'M3239'  );

INSERT INTO Courses(CourseId, CourseName) VALUES
    (1       , 'Базы данных'          ),
    (2       , 'Управление проектами' ),
    (3       , 'ППО'                  ),
    (4       , 'Теория информации'    ),
    (6       , 'Математический анализ'),
    (7       , 'Технологии Java'   );


INSERT INTO Lecturers(LecturerId, LecturerName) VALUES
    (1         , 'Корнеев Г.А.'  ),
    (2         , 'Будин Н.А.'),
    (3         , 'Кузнецова Е.М.'),
    (4         , 'Киракозов А.Х.'),
    (6         , 'Трофимюк Г.А.' ),
    (7         , 'Беляев Е.А.' ),
    (8         , 'Кохась К.П.'   );

INSERT INTO Plan(GroupId, CourseId, LecturerId) VALUES
	(    1      , 1       , 2         ),
    (2      , 1       , 1         ),
    (1      , 2       , 3         ),
    (1      , 3       , 4         ),
    (2      , 3       , 4         ),
    (2      , 4       , 6         ),
    (1      , 4       , 7         ),
    (2      , 4       , 7         ),
    (4      , 6       , 8         ),
    (1      , 7       , 1         ),
    (2      , 7       , 1         ),
    (3      , 7       , 1         ),
    (4      , 7       , 1 );

INSERT INTO Marks(StudentId, CourseId, Mark) VALUES
    (1        , 1       , 5   ),
    (2        , 1       , 4   ),
    (3        , 1       , 3   ),
    (2        , 2       , 3   ),
    (3        , 2       , 4   ),
    (4        , 2       , 5   ),
    (7        , 1       , 5   ),
    (8        , 1       , 5   ),
    (7        , 7       , 5   ),
    (8        , 7       , 5   ),
    (5        , 7       , 5   ),
    (6        , 7       , 5   ),
    (3        , 3       , 3   );

