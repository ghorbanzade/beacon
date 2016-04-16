--
-- CS630: Database Management Systems
-- Copyright 2014 Pejman Ghorbanzade <mail@ghorbanzade.com>
-- Creative Commons Attribution-ShareAlike 4.0 International License
-- More info: https://bitbucket.org/ghorbanzade/umb-cs630-2014f
--

-- Query 1
-- Write SQL declarations for creating the schemata. Include
-- necessary key constraints.

CREATE TABLE Employee(
	eid number(6),
	ename varchar(20),
	age number(3),
	salary number(12,2),
	PRIMARY KEY (eid)
	);
CREATE TABLE Department(
	did number(6),
	dname varchar(20),
	budget number(12,2),
	managerid number(10),
	PRIMARY KEY (did)
	);
CREATE TABLE Works(
	eid number(6),
	did number(6),
	pct_time number(5,2),
	PRIMARY KEY (eid,did),
	FOREIGN KEY (eid) REFERENCES Employee (eid),
	FOREIGN KEY (did) REFERENCES Department (did)
	);

-- Query 2
-- Find the salaries of employees that work in a department whose
-- name starts with 'Mar'.

SELECT E.salary
FROM Employee E, Department D, Works W
WHERE D.did = W.did AND E.eid = W.eid AND D.dname LIKE 'Mar%';

-- Query 3
-- Find the ages of employees who work at least 30% of their time
-- in a single department. List each age only once.

SELECT DISTINCT E.age
FROM Employee E, Works W
WHERE E.eid = W.eid AND pct_time >= 30.00;

-- Query 4
-- Find the salaries of employees who work only in departments that
-- have budget more than $500000. List each salary value only once.

SELECT DISTINCT E.salary
FROM Employee E, Department D, Works W
WHERE E.eid = W.eid AND D.did = W.did AND E.eid NOT IN (
SELECT W.eid
FROM Works W, Department D
WHERE W.did = D.did AND D.budget <=500000
);

-- Query 5
-- Find the names of employees who are managers.

SELECT E.ename
FROM Employee E, Department D, Works W
WHERE E.eid = W.eid AND D.did = W.did AND E.eid = D.managerid;

-- Query 6
-- Find the average salary over all employees.

SELECT AVG(E.salary)
FROM Employee E;

-- Query 7
-- Find the ages of employees who work at least 10% of their time in
-- a department called 'Catering' but who do not work in any department
-- with budget higher than $500000.

SELECT E.age
FROM Employee E, Department D, Works W
WHERE E.eid = W.eid AND D.did = W.did AND D.dname = 'Catering' AND W.pct_time >= 10.00 AND W.eid NOT IN (
	SELECT W.eid
	FROM Department D, Works W
	WHERE D.did = W.did AND D.budget > 500000
	);

-- Query 8
-- Find the names of employees who work in all departments with budget
-- higher than $500,000.

SELECT E.ename
FROM Employee E
WHERE NOT EXISTS (
	SELECT D.did
	FROM Department D
	WHERE D.budget >= 500000 AND NOT EXISTS (
		SELECT *
		FROM Works W
		WHERE W.did = D.did AND W.eid = E.eid
		)
	);

-- Query 9
-- Find the name(s) of the department(s) with the highest budget.

SELECT D.dname
FROM Department D
WHERE D.budget = (
	SELECT MAX(D.budget)
	FROM Department D
	);

-- Query 10
-- Find the maximum salary among employees 30 year old or younger
-- for each department with at least 10 employees of any age.

SELECT MAX(E.salary)
FROM Employee E, Department D, Works W
WHERE E.eid = W.eid AND D.did = W.did AND E.age <= 30
GROUP BY D.did
HAVING (SELECT COUNT(*)
	FROM Works W2
	WHERE W2.did = D.did) >= 3;

-- Query 11
-- Find for each manager (listed in the output by eid) the average
-- salary of employees working for that manager.

SELECT D.managerid, AVG(E.salary)
FROM Employee E, Department D, Works W
WHERE W.eid = E.eid AND W.did = D.did
	AND W.did = (
		SELECT D2.did
		FROM Employee E2, Department D2, Works W2
		WHERE W2.eid = E2.eid
			AND W2.did = D2.did
			AND E2.eid = D.managerid
		)
	AND E.eid <> D.managerid
GROUP BY D.managerid;

-- Query 12
-- Find the average age of employees for each department where every
-- employee is 30 years old or younger.

SELECT AVG(E.age)
FROM EMPLOYEE E, DEPARTMENT D, WORKS W
WHERE E.eid = W.eid AND D.did = W.did
GROUP BY D.did
HAVING COUNT(*) = (
	SELECT COUNT(*)
	FROM EMPLOYEE E1, WORKS W1
	WHERE W1.eid = E1.eid
		AND W1.did = D.did
		AND E1.age <= 30
	);

-- Query 13
-- Find the name(s) of department(s) who have the highest average
-- employee age.

SELECT D.dname
FROM Department D, (
	SELECT D.did, D.dname, AVG(E.eid) AS avgage
	FROM Employee E, Department D, WORKS W
	WHERE W.eid = E.eid AND W.did = D.did
	GROUP BY D.did, D.dname
	) TEMP
WHERE D.did = TEMP.did AND TEMP.avgage = (
	SELECT MAX(TEMP.avgage)
	FROM (
		SELECT D.did, D.dname, AVG(E.eid) AS avgage
		FROM Employee E, Department D, WORKS W
		WHERE W.eid = E.eid AND W.did = D.did
		GROUP BY D.did, D.dname
		) TEMP
	);

-- Query 14
-- Find the age(s) that most employees have, i.e., best represented
-- age(s) among employees that work in departments with budget larger
-- than $300,000. If an employee works in multiple such departments,
-- his/her age is only counted once.

SELECT TEMP.age, TEMP.freq
FROM (
	SELECT TEMP2.age, COUNT(TEMP2.age) AS freq
	FROM (
		SELECT *
		FROM EMPLOYEE E, WORKS W, DEPARTMENT D
		WHERE W.eid = E.eid
			AND W.did = D.did
			AND D.budget > 300000
		) TEMP2
	GROUP BY TEMP2.age
	) TEMP
WHERE TEMP.freq = (
	SELECT MAX(TEMP.freq)
	FROM (
		SELECT TEMP2.age, COUNT(TEMP2.age) AS freq
		FROM (
			SELECT *
			FROM EMPLOYEE E, WORKS W, DEPARTMENT D
			WHERE W.eid = E.eid
				AND W.did = D.did
				AND D.budget > 300000
			) TEMP2
		GROUP BY TEMP2.age
		) TEMP
	);

-- Query 15
-- Find the average salary among employees that work in all departments
-- whose names starts with 'Ca'.

SELECT AVG(E.salary)
FROM EMPLOYEE E
WHERE NOT EXISTS (
	SELECT D.did
	FROM DEPARTMENT D
	WHERE D.dname LIKE 'Ca%' AND
		NOT EXISTS (
		SELECT *
		FROM WORKS W
		WHERE W.eid = E.eid AND W.did = D.did
		)
	);
