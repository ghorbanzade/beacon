--
-- CS630: Database Management Systems
-- Copyright 2014 Pejman Ghorbanzade <pejman@ghorbanzade.com>
-- Creative Commons Attribution-ShareAlike 4.0 International License
-- More info: https://github.com/ghorbanzade/beacon
--

CREATE OR REPLACE PROCEDURE Q2(
	givenPid 		IN PARTS.pid%TYPE,
	givenSid 		IN SUPPLIERS.sid%TYPE,
	givenQuantity	IN ORDERS.quantity%TYPE
	)
IS
	AvgQuantity		INT;
	limitQuantity	INT;
	limitValue		NUMBER(8,2);
	part_name			PARTS.pname%TYPE;
	part_year			PARTS.year%TYPE;
	part_price_initial	PARTS.price%TYPE;
	part_price_new		PARTS.price%TYPE;
	part_pid_new		PARTS.pid%TYPE;
BEGIN
	SELECT AVG(O.quantity) INTO AvgQuantity
	FROM ORDERS O, Parts P
	WHERE O.pid = P.pid AND P.pid = givenPid;
	limitQuantity := 0.75 * AvgQuantity;
	IF givenQuantity <= limitQuantity THEN
		INSERT INTO Orders (pid, sid, quantity) VALUES (givenPid, givenSid, givenQuantity);
	ELSE
		SELECT P.pname, P.year, P.price INTO part_name, part_year, part_price_initial
		FROM Parts P
		WHERE P.pid = givenPid;
		SELECT COUNT(P.pid) INTO part_pid_new
		FROM Parts P;
		part_pid_new := part_pid_new + 1;
		limitValue := limitQuantity * part_price_initial;
		part_price_new := limitValue / givenQuantity;
		INSERT INTO Parts (pid, pname, year, price) VALUES (part_pid_new, part_name, part_year, part_price_new);
		INSERT INTO Orders (pid, sid, quantity) VALUES (part_pid_new, givenSid, givenQuantity);
	END IF;
EXCEPTION
	WHEN TOO_MANY_ROWS THEN
	DBMS_OUTPUT.PUT_LINE('Too many rows.');
	WHEN NO_DATA_FOUND THEN
	DBMS_OUTPUT.PUT_LINE('No Data Found.');	
END;
/
