--
-- CS630: Database Management Systems
-- Copyright 2014 Pejman Ghorbanzade <pejman@ghorbanzade.com>
-- Creative Commons Attribution-ShareAlike 4.0 International License
-- More info: https://github.com/ghorbanzade/beacon
--

CREATE OR REPLACE FUNCTION getMeanByZip(givenZipcode SUPPLIERS.zipcode%TYPE)
	RETURN PARTS.price%TYPE
IS
	dollar PARTS.price%TYPE;
	sumValue PARTS.price%TYPE;
	numberOfOrders INT;
	meanValue PARTS.price%TYPE;
	CURSOR DOLLARVALUES IS
		SELECT P.price * O.quantity AS dollarValue
		FROM Orders O, Parts P, Suppliers S
		WHERE O.sid = S.sid AND O.pid = P.pid AND S.zipcode = givenZipcode;
BEGIN
	numberOfOrders := 0;
	sumValue := 0;
	OPEN DOLLARVALUES;
	LOOP
		FETCH DOLLARVALUES INTO dollar;
		EXIT WHEN DOLLARVALUES%NOTFOUND;
		numberOfOrders := numberOfOrders + 1;
		sumValue := sumValue + dollar;
	END LOOP;
	CLOSE DOLLARVALUES;
	meanValue := sumValue / numberOfOrders;
	RETURN meanValue;
END;
/
CREATE OR REPLACE FUNCTION getVarianceByZip(givenZipcode SUPPLIERS.zipcode%TYPE)
	RETURN NUMBER
IS
	dollar PARTS.price%TYPE;
	sumValue PARTS.price%TYPE;
	numberOfOrders INT;
	meanValue PARTS.price%TYPE;
	nominator NUMBER(16,4);
	varValue NUMBER(16,4);
	CURSOR DOLLARVALUES IS
		SELECT P.price * O.quantity AS dollarValue
		FROM Orders O, Parts P, Suppliers S
		WHERE O.sid = S.sid AND O.pid = P.pid AND S.zipcode = givenZipcode;
BEGIN
	numberOfOrders := 0;
	sumValue := 0;
	nominator := 0;
	OPEN DOLLARVALUES;
	LOOP
		FETCH DOLLARVALUES INTO dollar;
		EXIT WHEN DOLLARVALUES%NOTFOUND;
		numberOfOrders := numberOfOrders + 1;
		sumValue := sumValue + dollar;
	END LOOP;
	CLOSE DOLLARVALUES;
	meanValue := sumValue / numberOfOrders;
	OPEN DOLLARVALUES;
	LOOP
		FETCH DOLLARVALUES INTO dollar;
		EXIT WHEN DOLLARVALUES%NOTFOUND;
		nominator := nominator + (dollar - meanValue) * (dollar - meanValue);
	END LOOP;
	CLOSE DOLLARVALUES;
	varValue := nominator / numberOfOrders;
	RETURN varValue;
END;
/
CREATE OR REPLACE PROCEDURE Q3(givenZipcode IN SUPPLIERS.zipcode%TYPE)
IS
	meanValue PARTS.price%TYPE;
	varValue NUMBER;
BEGIN
	SELECT getMeanByZip(givenZipcode) INTO meanValue FROM dual;
	SELECT getVarianceByZip(givenZipcode) INTO varValue FROM dual;
	DBMS_OUTPUT.PUT_LINE('Mean = '||meanValue);
	DBMS_OUTPUT.PUT_LINE('Variance =  '||varValue);
END;
/
