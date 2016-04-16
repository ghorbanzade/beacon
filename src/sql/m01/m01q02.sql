--
-- CS630: Database Management Systems
-- Copyright 2014 Pejman Ghorbanzade <mail@ghorbanzade.com>
-- Creative Commons Attribution-ShareAlike 4.0 International License
-- More info: https://bitbucket.org/ghorbanzade/umb-cs630-2014f
--

-- Query 1
-- Write a statement to create the table Works.

CREATE TABLE WORKS(
eid number(6),
did number(6),
pct_time number(5,2),
PRIMARY KEY (eid,did),
FOREIGN KEY (eid) REFERENCES Employee (eid),
FOREIGN KEY (did) REFERENCES Department (did)
);

-- Query 2
-- Find the names of employees who work under the supervision of a
-- manager named Steve Smith.

SELECT E.ename
FROM Employee E, Department D, Works W
WHERE W.eid = E.eid AND W.did = D.did AND D.managerid IN (
	SELECT E2.eid
	FROM Employee E2
	WHERE E2.ename = 'Steve Smith'
	);

-- Query 3
-- Find the ages of employees who do not work in any department with
-- budget below 20000.

SELECT E.age
FROM Employee E
WHERE E.eid NOT IN (
	SELECT E.eid
	FROM Employee E2, Works W, Department D
	WHERE W.did = D.did AND W.eid = E2.eid AND D.budget > 20000
	);

-- Query 4
-- Find the age(s) of the employee(s) with the highest salary.

SELECT E1.age
FROM Employee E1
WHERE E1.salary = (
	SELECT MAX(E2.salary)
	FROM EMPLOYEE E2
	);

-- Query 5
-- Find the did and average salary over employees younger than 45 years
-- old for each department with at least 10 employees of any age.

SELECT D1.did, AVG(E1.salary)
FROM Employee E1, Department D1, Works W1 
WHERE W1.did = D1.did AND W1.eid = E1.eid AND E1.age < 45
GROUP BY D1.did
HAVING 10 <= (
	SELECT COUNT(*)
	FROM Works W2
	WHERE W2.did = D1.did
	);

-- Query 6
-- Find the names of employees who work in all departments.

SELECT E.ename
FROM Employee E
WHERE NOT EXISTS (
	SELECT D.did
	FROM Department D
	WHERE NOT EXISTS (
		SELECT *
		FROM Works W
			WHERE W.did = D.did and W.eid = E.eid
			)
	);

-- Query 7
-- Find the name(s) of the department(s) with the highest average salary.

SELECT Temp.dname
FROM (
	SELECT D.did, D.dname, AVG(E.salary) AS AvgSalary
	FROM Department D, Works W, Employee E
	WHERE W.did = D.did AND W.eid = E.eid
	GROUP BY D.did, D.dname
	) TEMP;
WHERE Temp.AvgSalary = (
	SELECT MAX(TEMP2.AvgSalary)
	FROM TEMP TEMP2
	);
