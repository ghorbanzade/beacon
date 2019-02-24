--
-- CS630: Database Management Systems
-- Copyright 2014 Pejman Ghorbanzade <pejman@ghorbanzade.com>
-- Creative Commons Attribution-ShareAlike 4.0 International License
-- More info: https://github.com/ghorbanzade/beacon
--

INSERT INTO Students (sid, sname) VALUES (1, 'Pejman');
INSERT INTO Students (sid, sname) VALUES (2, 'John');
INSERT INTO Students (sid, sname) VALUES (3, 'Bob');
INSERT INTO Students (sid, sname) VALUES (4, 'Mariam');
INSERT INTO Students (sid, sname) VALUES (5, 'Sara');
INSERT INTO Students (sid, sname) VALUES (6, 'Gabriel');
INSERT INTO Students (sid, sname) VALUES (7, 'Brian');
INSERT INTO Students (sid, sname) VALUES (8, 'Elizabeth');
INSERT INTO Students (sid, sname) VALUES (9, 'Billy');
INSERT INTO Students (sid, sname) VALUES (10, 'Sandy');
INSERT INTO Courses (cid, cname, credits) VALUES (1, 'Math', 2);
INSERT INTO Courses (cid, cname, credits) VALUES (2, 'Physics', 2);
INSERT INTO Courses (cid, cname, credits) VALUES (3, 'Database', 3);
INSERT INTO Courses (cid, cname, credits) VALUES (4, 'Algorithm', 3);
INSERT INTO Courses (cid, cname, credits) VALUES (5, 'Compiler', 3);
INSERT INTO Courses (cid, cname, credits) VALUES (6, 'Network', 3);
INSERT INTO Courses (cid, cname, credits) VALUES (7, 'Object Oriented', 3);
INSERT INTO Courses (cid, cname, credits) VALUES (8, 'Independent Study', 4);
INSERT INTO Courses (cid, cname, credits) VALUES (9, 'Dissertation', 6);
INSERT INTO Courses (cid, cname, credits) VALUES (10, 'Sociology', 3);
INSERT INTO Enrolled (sid, cid) VALUES (1, 1);
INSERT INTO Enrolled (sid, cid) VALUES (2, 2);
INSERT INTO Enrolled (sid, cid) VALUES (3, 3);
INSERT INTO Enrolled (sid, cid) VALUES (4, 4);
INSERT INTO Enrolled (sid, cid) VALUES (5, 5);
