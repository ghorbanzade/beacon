--
-- CS637: Database Backed Websites
-- Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
-- More info: https://github.com/ghorbanzade/beacon
--

CREATE TABLE IF NOT EXISTS t1 (
	id INT NOT NULL PRIMARY KEY,
	message VARCHAR(40)
	);

INSERT INTO t1 (id, message) VALUES (1, 'First Row');
INSERT INTO t1 (id, message) VALUES (2, 'Second Row');

SELECT id, message FROM t1 ORDER BY id ASC;
