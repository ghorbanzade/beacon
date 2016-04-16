--
-- CS630: Database Management Systems
-- Copyright 2014 Pejman Ghorbanzade <mail@ghorbanzade.com>
-- Creative Commons Attribution-ShareAlike 4.0 International License
-- More info: https://bitbucket.org/ghorbanzade/umb-cs630-2014f
--

-- Question 2 Part A
-- Write SQL declarations for creating the schemata. Include necessary key constraints.

CREATE TABLE ACTORS (
	actor_id number(6) NOT NULL,
	name varchar(20),
	nationality varchar(20),
	PRIMARY KEY (actor_id)
	);
CREATE TABLE MOVIES (
	movie_id number(6) NOT NULL,
	title varchar(20),
	year number(4),
	studio varchar(20),
	PRIMARY KEY (movie_id)
	);
CREATE TABLE STARSIN (
	actor_id number(6) NOT NULL,
	movie_id number(6) NOT NULL,
	character varchar(20),
	PRIMARY KEY (actor_id,movie_id),
	FOREIGN KEY (actor_id) REFERENCES ACTORS (actor_id),
	FOREIGN KEY (movie_id) REFERENCES MOVIES (movie_id)
	);
	
-- Question 2 Part B
-- Find the title and studio of movies starring actor 'Tom Hanks'.

SELECT M.title, M.studio
FROM MOVIES M, ACTORS A, STARSIN S
WHERE S.movie_id = M.movie_id AND S.actor_id = A.actor_id AND A.name = 'Tom Hanks';

-- Question 2 Part C
-- Find the names of actors of 'US' nationality.

SELECT A.name
FROM ACTORS A
WHERE A.nationality = 'US';

-- Question 2 Part D
-- Find the nationalities of actors that star in some movie for all producing studios. Another way to phrase this is find the nationalities of actors that worked for all studios. Ensure that a nationality appears only once in the result. 

-- Nationality of actors for whom there is no studio that is not in the list of studios in whose film they have played.
SELECT DISTINCT A.nationality
FROM ACTORS A
WHERE NOT EXISTS (
	SELECT M.studio
	FROM MOVIES M
	WHERE NOT EXISTS (
		SELECT *
		FROM STARSIN S
		WHERE S.actor_id = A.actor_id 
			AND S.movie_id IN (
				SELECT M2.movie_id
				FROM MOVIES M2
				WHERE M2.studio = M.studio
				)
		)
	);

-- Question 2 Part E
-- Find for each year the number of distinct actors that played a character that starts with letter 'G' and has at least three letters in the character name.
	
SELECT M.year, COUNT(A.actor_id)
FROM ACTORS A, MOVIES M, STARSIN S
WHERE S.actor_id = A.actor_id
	AND S.movie_id = M.movie_id
	AND S.character LIKE 'G_%_%'
GROUP BY M.year;

-- Question 2 Part F
-- Find the movie titles that are produced by "Universal" studio and in which there are at least ten actors starring.

SELECT M.title
FROM MOVIES M
WHERE M.studio = 'Universal'
	AND 10 <= (
	SELECT COUNT (*)
	FROM STARSIN S
	WHERE S.movie_id = M.movie_id
	);
	
-- Question 2 Part G
-- Find the nationality(s) best-represented (i.e., nationality of most actors) among actors that starred in movies produced in year 2011.

SELECT TEMP.nationality
FROM (
	SELECT TEMP2.nationality, COUNT(TEMP2.nationality) AS freq
	FROM (
		SELECT *
		FROM ACTORS A, STARSIN S, MOVIES M
		WHERE S.actor_id = A.actor_id
			AND S.movie_id = M.movie_id
			AND M.year = 2011
		) TEMP2
	GROUP BY TEMP2.nationality
	) TEMP
WHERE TEMP.freq = (
	SELECT MAX(TEMP.freq)
	FROM (
		SELECT TEMP2.nationality, COUNT(TEMP2.nationality) AS freq
		FROM (
			SELECT *
			FROM ACTORS A, STARSIN S, MOVIES M
			WHERE S.actor_id = A.actor_id
				AND S.movie_id = M.movie_id
				AND M.year = 2011
			) TEMP2
		GROUP BY TEMP2.nationality
		) TEMP
	);
