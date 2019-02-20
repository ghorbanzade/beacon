--
-- CS630: Database Management Systems
-- Copyright 2014 Pejman Ghorbanzade <mail@ghorbanzade.com>
-- Creative Commons Attribution-ShareAlike 4.0 International License
-- More info: https://bitbucket.org/ghorbanzade/umb-cs630-2014f
--

-- Question 1 Part A
-- Write SQL declarations for creating the schemata. Include necessary
-- key constraints.

CREATE TABLE Employee(
    eid number(6) NOT NULL,
    ename varchar(20),
    age number(3),
	salary number(12,2),
	PRIMARY KEY (eid)
	);

CREATE TABLE Department(
    did number(6) NOT NULL,
    dname varchar(20),
	budget number(12,2),
	managerid number(6) NOT NULL,
    PRIMARY KEY (did),
	FOREIGN KEY (managerid) REFERENCES 
	Employee (eid)
    );

CREATE TABLE Works(
    eid number(6) NOT NULL,
    did number(6) NOT NULL,
    pct_time number(5,2),
	PRIMARY KEY (eid,did),
	FOREIGN KEY (eid) REFERENCES Employee,
	FOREIGN KEY (did) REFERENCES Department
	);

-- Question 2 Part A
-- Write SQL declarations for creating the schemata. Include necessary
-- key constraints.

CREATE TABLE ACTORS (
	actor_id number(6) NOT NULL,
	name varchar(20),
	nationality varchar(20),
	PRIMARY KEY (actor_id)
	);

CREATE TABLE MOVIES (
	movie_id number(6) NOT NULL,
	title varchar(20),
	year number(4),
	studio varchar(20),
	PRIMARY KEY (movie_id)
	);

CREATE TABLE STARSIN (
	actor_id number(6) NOT NULL,
	movie_id number(6) NOT NULL,
	character varchar(20),
	PRIMARY KEY (actor_id,movie_id),
	FOREIGN KEY (actor_id) REFERENCES ACTORS (actor_id),
	FOREIGN KEY (movie_id) REFERENCES MOVIES (movie_id)
	);
