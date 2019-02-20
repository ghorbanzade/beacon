--
-- CS637: Database Backed Websites
-- Copyleft 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
-- More info: https://bitbucket.org/ghorbanzade/umb-cs637-2015s
--
-- The author has placed this file in the public domain.
-- He makes no warranty and accepts no liability for this file.
--

-- Query 1.2
-- Show who won the 1962 prize for Literature

SELECT winner
FROM nobel N
WHERE N.yr = 1962 AND N.subject = 'Literature';

-- Query 1.4
-- Give the name of the 'Peace' winners since the year 2000,
-- including 2000.

SELECT winner
FROM nobel N
WHERE N.yr >= 2000 AND N.subject='Peace';

-- Query 1.6
-- Show all details of the presidential winners: (Theodore Roosevelt,
-- Woodrow Wilson, Jed Bartlet, Jimmy Carter)

SELECT *
FROM nobel
WHERE winner IN (
	'Theodore Roosevelt',
	'Woodrow Wilson',
	'Jed Bartlet',
	'Jimmy Carter'
	);

-- Query 2.2
-- Show the countries in Europe with a per capita GDP greater than
-- United Kingdom.

SELECT C.name
FROM world C
WHERE C.continent = 'Europe' AND C.gdp/C.population > (
	SELECT C1.gdp/C1.population
	FROM world C1
	WHERE C1.name = 'United Kingdom'
	);

-- Query 2.4
-- Which country has a population that is more than Canada but less
-- than Poland?

SELECT C.name, C.population
FROM world C
WHERE C.population > (
	SELECT C1.population
	FROM world C1
	WHERE C1.name = 'Canada'
	)
	AND C.population < (
	SELECT C2.population
	FROM world C2
	WHERE C2.name = 'Poland'
	);

-- QUery 2.6
-- Find the largest country (by area) in each continent, show the
-- continent, the name and the area.

SELECT C.continent, C.name, C.area
FROM world C
WHERE C.area >= ALL (
	SELECT C1.area
	FROM world C1
	WHERE C1.continent = C.continent
	AND C1.area IS NOT NULL
	);

-- Query 3.2
-- List all the continents, just once each.

SELECT DISTINCT C.continent
FROM world C;

-- Query 3.4
-- How many countries have an area of at least 1000000?

SELECT COUNT(*)
FROM world
WHERE area >= 1000000;

-- Query 3.6
-- For each continent show the continent and number of countries.

SELECT C.continent, COUNT(C.name)
FROM world C
GROUP BY C.continent;

-- Query 4.6
-- What is the id of the film Casablanca?

SELECT id
FROM movie M
WHERE M.title = 'Casablanca';

-- Query 4.7
-- Obtain the cast list for Casablanca.

SELECT A.name
FROM movie M, casting C, actor A
WHERE M.id = C.movieid AND A.id = C.actorid AND M.title = 'Casablanca';

-- Query 4.8
-- Obtain the cast list for the film Alien.

SELECT A.name
FROM movie M, casting C, actor A
WHERE M.id = C.movieid AND A.id = C.actorid AND M.title = 'Alien';

-- Query 4.9
-- List the films in which Harrison Ford has appeared.

SELECT M.title
FROM movie M, actor A, casting C
WHERE C.movieid = M.id AND C.actorid = A.id AND A.name = 'Harrison Ford';
