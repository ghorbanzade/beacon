/**
 * CS240: Programming in C
 * Copyleft 2014 Pejman Ghorbanzade <mail@ghorbanzade.com>
 * More info: https://github.com/ghorbanzade/UMB-CS240-2016S
 *
 * The author has placed this file in the public domain.
 * He makes no warranty and accepts no liability for this file.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <oraca.h>
#define programAuth 0
#define programRequ 1
#define programPart 2
#define programSupp 3
#define programMatc 4
#define programTabl 5
#define programDele 6
EXEC SQL INCLUDE SQLCA;
/*	-----------------------------------------
	Start of main();
	-----------------------------------------*/
int main()
{
/*	-----------------------------------------
	Variable Declaration Block
	-----------------------------------------*/
	int programState[10];
	int rowNumber = 0;
	int row;
	char message[200];
	EXEC SQL BEGIN DECLARE SECTION;
	char login[100];
	char password[100];
	int  requestedPartID;
	char requestedSuppZip[10];
	int  rowToDelete = 0;
	int  Part_pid;
	char Part_pname[20];
	int  Part_price;
	int  Orde_quantity;
	int  Supp_sid;
	char Supp_sname[20];
	char Supp_state[20];
	char Supp_zipcode[10];
	int  dollarAmount;
	char SQLSTATE[6];
	EXEC SQL END DECLARE SECTION;
/*	-----------------------------------------
	Error Handler
	-----------------------------------------*/
	EXEC SQL WHENEVER SQLERROR goto ERRORREPORT;
	EXEC SQL WHENEVER NOT FOUND goto NOTFOUND;
/*	-----------------------------------------
	Welcoming
	-----------------------------------------*/
	statusShow(0,"",5);
	statusShow(0,"-----------------------------------------",1);
	statusShow(0,"Solution to Assignment 4, Part B",1);
	statusShow(0,"Course CS630: Database Management Systems",1);
	statusShow(0,"Department of Computer Science",1);
	statusShow(0,"University of Massachusetts Boston",1);
	statusShow(0,"Developed By",1);
	statusShow(0,"Pejman Ghorbanzade",1);
	statusShow(0,"pejman@cs.umb.edu",1);
	statusShow(0,"-----------------------------------------",2);
/*	-----------------------------------------
	Authentication
	-----------------------------------------*/
	programState[programAuth] = 0;
	statusShow(0,"Authenticating...",1);
	statusShow(1,"Please type your username and click enter.",1);
	statusShow(1,"Username = ",0);
	gets(login);
	statusShow(1,"Thank you.",1);
	statusShow(1,"Please type your password and click enter.",1);
	statusShow(1,"For security reasons your password would not be shown.",1);
	statusShow(1,"Password = ",0);
	char *pass_string = malloc(128);
	pass_string=getpass("");
	strcpy(password, pass_string);
	statusShow(1,"Thank you.",1);
	EXEC SQL CONNECT :login IDENTIFIED BY :password;
	programState[programAuth] = 1;
	statusShow(1,"You are now connected to database.",1);
	statusShow(0,"Authentication Successful.",1);
/*	-----------------------------------------
	Request Input (Part ID & Supplier Zipcode)
	-----------------------------------------*/
	programState[programRequ] = 0;
	programState[programPart] = 0;
	statusShow(0,"Demanding Part Information...",1);
	statusShow(1,"Please input desired part number.",1);
	statusShow(1,"Part ID = ",0);
	scanf("%d", &requestedPartID);
	EXEC SQL
		SELECT P.pid, P.pname
		INTO :Part_pid, :Part_pname
		FROM Parts P
		WHERE P.pid = :requestedPartID;
	programState[programPart] = 1;
	statusShow(1,"Thank you.",1);
	statusShow(0,"Part ID found.",1);
	programState[programSupp] = 0;
	statusShow(0,"Demanding Supplier Information...",1);
	statusShow(1,"Please input desired zipcode.",1);
	statusShow(1,"Supplier Zipcode = ",0);
	scanf("%s",&requestedSuppZip);
	EXEC SQL WHENEVER NOT FOUND goto NOTFOUND2;
	EXEC SQL DECLARE cursorSupps CURSOR FOR
		SELECT S.sid, S.sname, S.state, S.zipcode
		FROM Suppliers S
		WHERE S.zipcode = :requestedSuppZip;
	EXEC SQL OPEN cursorSupps;
	while(1)
	{
		EXEC SQL
			FETCH cursorSupps
			INTO :Supp_sid, :Supp_sname, :Supp_state, :Supp_zipcode;
		rowNumber++;
	}
NOTFOUND2:
	EXEC SQL CLOSE cursorSupps;
	if (rowNumber == 0)
	{
		goto NOTFOUND;
	}
	programState[programSupp] = 1;
	sprintf(message,"%d supplier(s) found in specified zipcode.",rowNumber);
	statusShow(1,message,1);
	programState[programMatc] = 0;
	EXEC SQL WHENEVER NOT FOUND goto NOTFOUND3;
	EXEC SQL DECLARE cursorMatch CURSOR FOR
		SELECT S.sid, S.sname, O.quantity, P.price
		FROM Suppliers S, Parts P, Orders O
		WHERE O.sid = S.sid AND
			O.pid = P.pid AND
			P.pid = :requestedPartID AND
			S.zipcode = :requestedSuppZip;

	EXEC SQL OPEN cursorMatch;
	rowNumber = 0;
	while(1)
	{
		EXEC SQL
			FETCH cursorMatch
			INTO :Supp_sid, :Supp_sname, :Orde_quantity, :Part_price;
		rowNumber++;
	}
NOTFOUND3:
	EXEC SQL CLOSE cursorMatch;
	if (rowNumber == 0)
	{
		goto NOTFOUND;
	}
	sprintf(message,"%d supplier(s) in specified zipcode supply specified part.",rowNumber);
	statusShow(1,message,1);
	programState[programMatc] = 1;
	statusShow(0,"Suppliers recognized.",1);
	programState[programRequ] = 1;
/*	-----------------------------------------
	Deletion
	-----------------------------------------*/
	programState[programDele] = 0;
	while (!programState[programDele])
	{
		if (rowNumber == 0)
		{
			sprintf(message,"All orders for part ID %d from suppliers in %s are removed.",requestedPartID,requestedSuppZip);
			statusShow(1,message,1);
			programState[programDele] = 1;
			continue;
		}
		else
		{
			programState[programTabl] = 0;
			statusShow(0,"",1);
			statusShow(2,"Row	SID	Total	Supplier",1);
			statusShow(2,"---	---	-----	--------",1);
			EXEC SQL OPEN cursorMatch;
			for (row = 1; row <= rowNumber; row++)
			{
				EXEC SQL
					FETCH cursorMatch
					INTO :Supp_sid, :Supp_sname, :Orde_quantity, :Part_price;
				dollarAmount = Orde_quantity*Part_price;
				sprintf(message,"%d\t%d\t%d\t%s", row, Supp_sid, dollarAmount, Supp_sname);
				statusShow(2,message,1);
			}
			EXEC SQL CLOSE cursorMatch;
			statusShow(0,"",1);
			programState[programTabl] = 1;
			statusShow(1,"You may remove orders placed to suppliers.",1);
			statusShow(1,"Please input the row number you want to remove.",1);
			statusShow(1,"You may insert 0 to quit.",1);
			sprintf(message,"Row to Remove [0-%d] = ",rowNumber);
			statusShow(1,message,0);
			scanf("%d", &rowToDelete);
			if (rowToDelete == 0)
			{
				programState[programDele] = 1;
			}
			else if (rowToDelete > rowNumber)
			{
				statusShow(1,"Invalid row number.",1);
			}
			else
			{
				EXEC SQL OPEN cursorMatch;
				for (row = 1; row <= rowToDelete; row++)
				{
					EXEC SQL
						FETCH cursorMatch
						INTO :Supp_sid, :Supp_sname, :Orde_quantity, :Part_price;
				}
				EXEC SQL CLOSE cursorMatch;
				EXEC SQL DELETE
					FROM Orders O
					WHERE O.pid = :requestedPartID AND O.sid = :Supp_sid;
				rowNumber--;
				sprintf(message,"Row %d removed.",rowToDelete);
				EXEC SQL COMMIT;
				statusShow(1,message,1);
			}
		}
	}
	statusShow(0,"",2);
	statusShow(0,"Thank you for using this program.",5);
/*	-----------------------------------------
	Finalization
	-----------------------------------------*/
	return 0;
ERRORREPORT:
	if (!programState[programAuth])
	{
		statusShow(0,"Authentication Failed.",1);
	}
	else if (!programState[programRequ])
	{
		if (!programState[programPart])
		{
			statusShow(0,"Failure in Getting Part Info from User.",1);
		}
		if (!programState[programSupp])
		{
			statusShow(0,"Failure in Getting Supplier Info from User.",1);
		}
		if (!programState[programMatc])
		{
			statusShow(0,"None of suppliers supply the specified part.",1);
		}
		statusShow(0,"Failure in Getting Info from User.",1);
	}
	sprintf(message,"Error %s occured.", SQLSTATE);
	statusShow(0,message,1);
	statusShow(0,"Program Aborted.",2);
	return 1;
NOTFOUND:
	if (!programState[programRequ])
	{
		if (!programState[programPart])
		{
			sprintf(message,"Part ID %d does not exist.", requestedPartID);
		}
		if (!programState[programSupp])
		{
			sprintf(message,"No supplier found in specified zipcode.");
		}
		if (!programState[programMatc])
		{
			sprintf(message,"No supplier in zipcode %s supplies Part ID %d.", requestedSuppZip, requestedPartID);
		}
	}
	statusShow(0,"",1);
	statusShow(0,message,1);
	sprintf(message,"Error %s occured.", SQLSTATE);
	statusShow(0,message,1);
	statusShow(0,"Program Aborted.",2);
	return 1;
}
/*	-----------------------------------------
	End of main();
	-----------------------------------------*/
int statusShow(int indention, char *status, int lineSpace)
{
	int i;
	for (i = 0; i < indention ; i++)
	{
		printf("\t");
	}
	printf("%s",status);
	for (i = 0; i < lineSpace ; i++)
	{
		printf("\n");
	}
}
