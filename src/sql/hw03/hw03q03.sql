--
-- CS630: Database Management Systems
-- Copyright 2014 Pejman Ghorbanzade <mail@ghorbanzade.com>
-- Creative Commons Attribution-ShareAlike 4.0 International License
-- More info: https://bitbucket.org/ghorbanzade/umb-cs630-2014f
--

-- Query 1
-- Create a view (ManagerSummary) that lists for every department the
-- department name, manager ID and manager name, manager salary and
-- the number of employees in that department. The view will have five
-- columns with headings: DeptName, MgrID, MgrName, MgrSalary and EmpCount.

CREATE VIEW ManagerSummary(DeptName, MgrID, MgrName, MgrSalary, EmpCount) AS
SELECT D.dname, D.managerid, E.ename, E.salary, COUNT(W2.eid)
FROM Department D, Works W1, Works W2, Employee E
WHERE D.did = W1.did AND E.eid = W1.eid AND W2.did = D.did AND
E.eid = D.managerid
GROUP BY D.did, D.dname, D.managerid, E.ename, E.salary;

-- Query 2
-- Query the view above to retrieve the set of distinct salaries of
-- managers who manage a department called Sales.

SELECT DISTINCT M.MgrSalary
FROM ManagerSummary M
WHERE M.DeptName = 'Sales';

-- Query 3
-- Query the view above to find the name of the manager  who manages
-- most employees. If the same employee works in several departments,
-- that employee is counted once in each  of the departments. The
-- manager is included in the count the same as all other employees,
-- i.e., based on his or her records in the \texttt{Works} table.

-- Query 3, Solution A
-- If we assume employee may not be appointed to manager of multiple
-- departments:

SELECT M1.MgrID
FROM ManagerSummary M1
WHERE M1.EmpCount = (
		SELECT MAX(M2.EmpCount)
		FROM ManagerSummary M2
		)
GROUP BY M1.MgrId;

-- Query 3, Solution B
-- If we assume employees may be appointed to manager of multiple
-- departments:

SELECT TEMP.MgrId, TEMP.EmpCount2
FROM (
	SELECT M1.MgrID, SUM(DISTINCT M2.EmpCount) AS EmpCount2
	FROM ManagerSummary M1, ManagerSummary M2
	WHERE M1.MgrID = M2.MgrID
	GROUP BY M1.MgrID
	) TEMP
WHERE TEMP.EmpCount2 = (
	SELECT MAX(TEMP2.EmpCount2)
	FROM (
		SELECT M1.MgrID, SUM(DISTINCT M2.EmpCount) AS EmpCount2
		FROM ManagerSummary M1, ManagerSummary M2
		WHERE M1.MgrID = M2.MgrID
		GROUP BY M1.MgrID
		) TEMP2
	);
