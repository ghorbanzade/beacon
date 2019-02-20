--
-- CS630: Database Management Systems
-- Copyright 2014 Pejman Ghorbanzade <mail@ghorbanzade.com>
-- Creative Commons Attribution-ShareAlike 4.0 International License
-- More info: https://bitbucket.org/ghorbanzade/umb-cs630-2014f
--

CREATE VIEW TopStudents(sid, sname, GPA) AS
SELECT TMP.sid, TMP.sname, TMP.gpa
FROM (
	SELECT S.sid, S.sname, SUM(C.credits*G.grade)/COUNT(*) AS gpa
	FROM Students S, Courses C, Grades G
	WHERE G.sid = S.sid AND G.cid = C.cid
	GROUP BY S.sid, S.name
	) TMP
WHERE TMP.gpa > 3.0;
