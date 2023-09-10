CREATE DATABASE University;
USE University;

CREATE TABLE Persons (
	personId INT NOT NULL AUTO_INCREMENT,
	firstName VARCHAR(32) NOT NULL,
	lastName VARCHAR(32) NOT NULL,
	birthday DATE NOT NULL,
	passportSeries VARCHAR(8) NOT NULL,
	passportNumber VARCHAR(16) NOT NULL,

	CONSTRAINT Persons_pk PRIMARY KEY (personId),
	CONSTRAINT Persons_passport UNIQUE KEY (passportSeries, passportNumber)
);

CREATE TABLE Teachers (
	personId INT NOT NULL,

	CONSTRAINT Teachers_pk PRIMARY KEY (personId),
	CONSTRAINT TeacherPerson FOREIGN KEY (personId) REFERENCES Persons (personId)
);

CREATE TABLE Groups (
	groupId INT NOT NULL AUTO_INCREMENT,
	name CHAR(6) NOT NULL,

	CONSTRAINT Groups_pk PRIMARY KEY (groupId),
	CONSTRAINT Groups_name UNIQUE KEY (name)
);

CREATE TABLE Students (
	personId INT NOT NULL,
	groupId INT NOT NULL,

	CONSTRAINT Students_pk PRIMARY KEY (personId),
	CONSTRAINT Students_personId_groupId UNIQUE KEY (personId, groupId),
	CONSTRAINT StudentPerson FOREIGN KEY (personId) REFERENCES Persons (personId),
	CONSTRAINT StudentGroup FOREIGN KEY (groupId) REFERENCES Groups (groupId)
);

CREATE TABLE Disciplines (
	disciplineId INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(128) NOT NULL,

	CONSTRAINT Disciplines_pk PRIMARY KEY (disciplineId)
);

CREATE TABLE Courses (
	groupId INT NOT NULL,
	disciplineId INT NOT NULL,
	teacherId INT NOT NULL,

	CONSTRAINT Courses_pk PRIMARY KEY (groupId, disciplineId),
	CONSTRAINT GroupCourse FOREIGN KEY (groupId) REFERENCES Groups (groupId),
	CONSTRAINT CourseDiscipline FOREIGN KEY (disciplineId) REFERENCES Disciplines (disciplineId),
	CONSTRAINT CourseTeacher FOREIGN KEY (teacherId) REFERENCES Teachers (personId)
);

CREATE TABLE Grades (
	groupId INT NOT NULL,
	disciplineId INT NOT NULL,
	studentId INT NOT NULL,
	grade CHAR(1) NOT NULL DEFAULT 'F',
	score DECIMAL(5, 2) NOT NULL DEFAULT 0,

	CONSTRAINT Grades_grade CHECK (grade IN ('A', 'B', 'C', 'D', 'E', 'F')),
	CONSTRAINT Grades_score CHECK (score >= 0 AND score <= 100),
	CONSTRAINT Grades_score_and_grade CHECK (
		score < 60 AND grade = 'F' OR
		score >= 60 AND score < 68 AND grade = 'E' OR
		score >= 68 AND score < 75 AND grade = 'D' OR
		score >= 75 AND score < 82 AND grade = 'C' OR
		score >= 82 AND score < 91 AND grade = 'B' OR
		score >= 91 AND grade = 'A'
	),
	CONSTRAINT Grades_pk PRIMARY KEY (groupId, disciplineId, studentId),
	CONSTRAINT CourseGrade FOREIGN KEY (groupId, disciplineId) REFERENCES Courses (groupId, disciplineId),
	CONSTRAINT StudentGrade FOREIGN KEY (studentId) REFERENCES Students (personId),
	CONSTRAINT StudentGradeInGroup FOREIGN KEY (studentId, groupId) REFERENCES Students (personId, groupId)
);