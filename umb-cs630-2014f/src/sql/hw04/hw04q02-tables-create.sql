--
-- CS630: Database Management Systems
-- Copyright 2014 Pejman Ghorbanzade <mail@ghorbanzade.com>
-- Creative Commons Attribution-ShareAlike 4.0 International License
-- More info: https://bitbucket.org/ghorbanzade/umb-cs630-2014f
--

CREATE TABLE Students (
	sid number(6) NOT NULL,
	sname varchar(20),
	PRIMARY KEY (sid)
	);

CREATE TABLE Courses (
	cid number(6) NOT NULL,
	cname varchar(20),
	credits number(2),
	PRIMARY KEY (cid)
	);

CREATE TABLE Enrolled (
	sid number(6) NOT NULL,
	cid number(6) NOT NULL,
	PRIMARY KEY (sid,cid),
	FOREIGN KEY (sid) REFERENCES Students (sid),
	FOREIGN KEY (cid) REFERENCES Courses (cid)
	);
