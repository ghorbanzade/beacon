--
-- CS630: Database Management Systems
-- Copyright 2014 Pejman Ghorbanzade <pejman@ghorbanzade.com>
-- Creative Commons Attribution-ShareAlike 4.0 International License
-- More info: https://github.com/ghorbanzade/beacon
--

-- Query 1
-- Find distinct ages of students who took a course with name CS310.

SELECT DISTINCT S.age
FROM Students S, Courses C, Grades G
WHERE G.sid = S.sid AND G.cid = C.cid AND C.cname = 'CS310';

-- Query 2
-- Find the names of students who took only 4-credits course.

SELECT S.sname
FROM Students S
WHERE S.sid NOT IN (
	SELECT S.sid
	FROM Students S, Courses C, Grades G
	WHERE G.sid = S.sid AND G.cid = C.cid AND C.credits != 4
	);

-- Query 3
-- Find the average grade over all students for those courses which
-- enrolled at least 10 students with age greater or equal than 25.

SELECT C.cid, AVG(G.grade)
FROM Grades G, Courses C
WHERE G.cid = C.cid
GROUP BY C.cid
HAVING 10 <= (
	SELECT COUNT(S.sid)
	FROM Grades G, Students S
	WHERE G.sid = S.sid AND G.cid = C.cid AND S.age >= 25
	);

-- Query 4
-- Find the names of students who took every 4-credits course.

SELECT S.sname
FROM Students S
WHERE NOT EXISTS (
	SELECT C.cid
	FROM Courses C
	WHERE C.credits = 4 AND NOT EXISTS (
		SELECT *
		FROM Grades G
		WHERE G.cid = C.cid AND G.sid = S.sid
		)
	);

-- Query 5
-- Find for each course identifier (cid) the sid(s) of the students
-- who got the highest score.

SELECT C.cid, S.sid
FROM Grades G
WHERE G.grade = (
	SELECT MAX(G1.grade)
	FROM Grade G1
	WHERE G1.cid = G.cid
	);
