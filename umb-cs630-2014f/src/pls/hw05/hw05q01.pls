--
-- CS630: Database Management Systems
-- Copyright 2014 Pejman Ghorbanzade <pejman@ghorbanzade.com>
-- Creative Commons Attribution-ShareAlike 4.0 International License
-- More info: https://github.com/ghorbanzade/beacon
--

CREATE OR REPLACE FUNCTION Q1(specifiedPrice PARTS.price%type)
	RETURN PARTS.pid%TYPE
IS
	Diff PARTS.price%Type;
	Part_pid PARTS.pid%TYPE;
	Part_pname PARTS.pname%TYPE;
	Part_price PARTS.price%TYPE;
	Part_year PARTS.year%TYPE;
	lastDiff PARTS.price%TYPE;
	counter INT;
	numHop INT;
	CURSOR PARTCURSOR IS
		SELECT T.pid, T.pname, T.price, T.year, T.Diff
		FROM (
			SELECT P1.pid, P1.pname, P1.price, P1.year, (P2.price - P1.price) AS Diff
			FROM Parts P1, Parts P2
			WHERE (P2.price - P1.price > 0) AND P2.price = specifiedPrice
			UNION
			SELECT P1.pid, P1.pname, P1.price, P1.year, (P1.price - P2.price) AS Diff
			FROM Parts P1, Parts P2
			WHERE (P2.price - P1.price < 0 ) AND P2.price = specifiedPrice
			) T
		ORDER BY T.Diff ASC, T.year DESC, T.pid DESC;
BEGIN
	counter := 0;
	numHop  := 3;
	lastDiff := 0;
	OPEN PARTCURSOR;
	LOOP
		FETCH PARTCURSOR INTO Part_pid, Part_pname, Part_price, Part_year, Diff;
		IF Diff != lastDiff THEN
			counter := counter + 1;
			EXIT WHEN Counter = numHop;
		END IF;
		lastDiff := Diff;
	END LOOP;
	CLOSE PARTCURSOR;
	RETURN Part_pid;
EXCEPTION
	WHEN TOO_MANY_ROWS THEN
	DBMS_OUTPUT.PUT_LINE('Too many rows.');
	RETURN -1;

	WHEN NO_DATA_FOUND THEN
	DBMS_OUTPUT.PUT_LINE('No Data Found.');
	RETURN -1;
END;
/
