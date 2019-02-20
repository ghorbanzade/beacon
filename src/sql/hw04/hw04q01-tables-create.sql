--
-- CS630: Database Management Systems
-- Copyright 2014 Pejman Ghorbanzade <mail@ghorbanzade.com>
-- Creative Commons Attribution-ShareAlike 4.0 International License
-- More info: https://bitbucket.org/ghorbanzade/umb-cs630-2014f
--

CREATE TABLE Parts (
	pid number(6) NOT NULL,
	pname varchar(20),
	year number(4),
	price number(8,2),
	PRIMARY KEY (pid)
	);

CREATE TABLE Suppliers (
	sid number(6) NOT NULL,
	sname varchar(20),
	state varchar(20),
	zipcode varchar(10),
	PRIMARY KEY (sid)
	);

CREATE TABLE Orders (
	pid number(6) NOT NULL,
	sid number(6) NOT NULL,
	quantity number(6),
	PRIMARY KEY (pid,sid),
	FOREIGN KEY (pid) REFERENCES Parts (pid),
	FOREIGN KEY (sid) REFERENCES Suppliers (sid)
	);
