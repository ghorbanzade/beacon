--
-- CS637: Database Backed Websites
-- Copyleft 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
-- More info: https://bitbucket.org/ghorbanzade/umb-cs637-2015s
--
-- The author has placed this file in the public domain.
-- He makes no warranty and accepts no liability for this file.
--

CREATE TABLE IF NOT EXISTS t1 (
	id INT NOT NULL PRIMARY KEY,
	message VARCHAR(40)
	);

INSERT INTO t1 (id, message) VALUES (1, 'First Row');
INSERT INTO t1 (id, message) VALUES (2, 'Second Row');

SELECT id, message FROM t1 ORDER BY id ASC;
