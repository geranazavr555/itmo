CREATE TABLE Students (
	studentId INT NOT NULL,
	studentName VARCHAR(64) NOT NULL,

	CONSTRAINT Students_pk PRIMARY KEY (studentId)
);

CREATE TABLE Groups (
	groupId INT NOT NULL,
	groupName CHAR(6) NOT NULL,

	CONSTRAINT Groups_pk PRIMARY KEY (groupId),
	CONSTRAINT Groups_unique_groupName UNIQUE KEY (groupName)
);

CREATE TABLE StudentGroups (
	studentId INT NOT NULL,
	groupId INT NOT NULL,

	CONSTRAINT StudentGroups_pk PRIMARY KEY (studentId),
	CONSTRAINT StudentGroups_Students FOREIGN KEY (studentId) REFERENCES Students (studentId),
	CONSTRAINT StudentGroups_Groups FOREIGN KEY (groupId) REFERENCES Groups (groupId)
);

CREATE TABLE Lecturers (
	lecturerId INT NOT NULL,
	lecturerName VARCHAR(64) NOT NULL,

	CONSTRAINT Lecturers_pk PRIMARY KEY (lecturerId)
);

CREATE TABLE Courses (
	courseId INT NOT NULL,
	courseName VARCHAR(64) NOT NULL,

	CONSTRAINT Courses_pk PRIMARY KEY (courseId)
);

CREATE TABLE GroupCourseLecturers (
	groupId INT NOT NULL,
	courseId INT NOT NULL,
	lecturerId INT NOT NULL,

	CONSTRAINT GroupCourseLecturers_pk PRIMARY KEY (groupId, courseId),
	CONSTRAINT GroupCourseLecturers_Groups FOREIGN KEY (groupId) REFERENCES Groups (groupId),
	CONSTRAINT GroupCourseLecturers_Courses FOREIGN KEY (courseId) REFERENCES Courses (courseId),
	CONSTRAINT GroupCourseLecturers_Lecturers FOREIGN KEY (lecturerId) REFERENCES Lecturers (lecturerId)
);

CREATE TABLE Marks (
	studentId INT NOT NULL,
	courseId INT NOT NULL,
	mark INT NOT NULL,

	CONSTRAINT Marks_pk PRIMARY KEY (studentId, courseId),
	CONSTRAINT Marks_Students FOREIGN KEY (studentId) REFERENCES Students (studentId),
	CONSTRAINT Marks_Courses FOREIGN KEY (courseId) REFERENCES Courses (courseId)
);