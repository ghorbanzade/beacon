--
-- CS630: Database Management Systems
-- Copyright 2014 Pejman Ghorbanzade <mail@ghorbanzade.com>
-- Creative Commons Attribution-ShareAlike 4.0 International License
-- More info: https://bitbucket.org/ghorbanzade/umb-cs630-2014f
--

INSERT INTO Parts (pid, pname, year, price) VALUES (1,'iPhone',2013,100);
INSERT INTO Parts (pid, pname, year, price) VALUES (2,'iPod',2010,200);
INSERT INTO Parts (pid, pname, year, price) VALUES (3,'iPad',2011,300);
INSERT INTO Parts (pid, pname, year, price) VALUES (4,'iWatch',2014,300);
INSERT INTO Parts (pid, pname, year, price) VALUES (5,'iMac',2013,300);
INSERT INTO Parts (pid, pname, year, price) VALUES (6,'iCloud',2013,300);
INSERT INTO Parts (pid, pname, year, price) VALUES (7,'iGlass',2014,400);
INSERT INTO Parts (pid, pname, year, price) VALUES (8,'iBag',2013,500);
INSERT INTO Parts (pid, pname, year, price) VALUES (9,'iBox',2013,600);
INSERT INTO Parts (pid, pname, year, price) VALUES (10,'iVoice',2013,700);
INSERT INTO Parts (pid, pname, year, price) VALUES (11,'iMatch',2013,800);
INSERT INTO Parts (pid, pname, year, price) VALUES (12,'iPatch',2013,900);
INSERT INTO Parts (pid, pname, year, price) VALUES (13,'iLatch',2013,900);
INSERT INTO Parts (pid, pname, year, price) VALUES (14,'iCatch',2014,900);
INSERT INTO Parts (pid, pname, year, price) VALUES (15,'iBatch',2013,1000);

INSERT INTO Suppliers (sid, sname, state, zipcode) VALUES (1,'Amazon','MA','02125');
INSERT INTO Suppliers (sid, sname, state, zipcode) VALUES (2,'BestBuy','MA','02139');
INSERT INTO Suppliers (sid, sname, state, zipcode) VALUES (3,'Ebay','MA','02171');
INSERT INTO Suppliers (sid, sname, state, zipcode) VALUES (4,'Walmart','MA','02169');
INSERT INTO Suppliers (sid, sname, state, zipcode) VALUES (5,'AliExpress','MA','02135');
INSERT INTO Suppliers (sid, sname, state, zipcode) VALUES (6,'Amazon Prime','MA','02139');

INSERT INTO Orders (pid, sid, quantity) VALUES (1,1,100);
INSERT INTO Orders (pid, sid, quantity) VALUES (1,2,250);
INSERT INTO Orders (pid, sid, quantity) VALUES (1,6,10);
INSERT INTO Orders (pid, sid, quantity) VALUES (2,4,50);
INSERT INTO Orders (pid, sid, quantity) VALUES (2,1,10);
INSERT INTO Orders (pid, sid, quantity) VALUES (3,1,100);
INSERT INTO Orders (pid, sid, quantity) VALUES (3,4,300);
INSERT INTO Orders (pid, sid, quantity) VALUES (4,2,60);
INSERT INTO Orders (pid, sid, quantity) VALUES (4,1,40);
INSERT INTO Orders (pid, sid, quantity) VALUES (5,3,20);
INSERT INTO Orders (pid, sid, quantity) VALUES (5,5,10);
