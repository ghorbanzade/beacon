--
-- CS630: Database Management Systems
-- Copyright 2014 Pejman Ghorbanzade <pejman@ghorbanzade.com>
-- Creative Commons Attribution-ShareAlike 4.0 International License
-- More info: https://github.com/ghorbanzade/beacon
--

DROP TABLE T;

CREATE TABLE T (
	PID integer PRIMARY KEY,
	Ta integer,
	Tb integer
	);

INSERT INTO T (PID, Ta, Tb) VALUES (1,10,20);
INSERT INTO T VALUES (2,NULL,NULL);
INSERT INTO T VALUES (3,NULL,20);
INSERT INTO T VALUES (4,10,NULL);
INSERT INTO T VALUES (5,10,10);
INSERT INTO T VALUES (6,5,8);
INSERT INTO T VALUES (7,12,56);
INSERT INTO T VALUES (8,50,50);

SELECT * FROM T WHERE Ta = 10 OR Tb = 20;
SELECT * FROM T WHERE Ta = 10 AND Tb = 20;
SELECT * FROM T WHERE Ta < 10 OR Ta >= 10;
SELECT * FROM T WHERE Ta = Tb;
