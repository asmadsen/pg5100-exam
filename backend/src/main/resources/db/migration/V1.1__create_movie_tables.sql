create table movies
(
	id uuid
		constraint movies_pk
			primary key,
	title varchar(255) not null,
	image varchar(255),
	plot varchar not null,
	released timestamp not null,
	runtime int not null,
	director varchar(255) not null
);

create table movie_genres
(
	movie_id uuid not null,
	genres varchar(255) not null,
	constraint movie_genres_pk
		primary key (movie_id, genres)
);

INSERT INTO movies (id, title, image, plot, released, runtime, director) VALUES ('e7be6c09-bf47-4da6-9969-3c55d1e05fad', 'Pulp Fiction', 'https://m.media-amazon.com/images/M/MV5BNGNhMDIzZTUtNTBlZi00MTRlLWFjM2ItYzViMjE3YzI5MjljXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg', 'The lives of two mob hitmen, a boxer, a gangster''s wife, and a pair of diner bandits intertwine in four tales of violence and redemption.', '1994-10-14 00:00:00.000000', 154, 'Quentin Tarantino');
INSERT INTO movies (id, title, image, plot, released, runtime, director) VALUES ('4f58a265-e15c-4207-a5ce-87f7cf3427c2', 'Die Hard', 'https://m.media-amazon.com/images/M/MV5BZjRlNDUxZjAtOGQ4OC00OTNlLTgxNmQtYTBmMDgwZmNmNjkxXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg', 'An NYPD officer tries to save his wife and several others taken hostage by German terrorists during a Christmas party at the Nakatomi Plaza in Los Angeles.', '1988-07-20 00:00:00.000000', 132, 'John McTiernan');
INSERT INTO movies (id, title, image, plot, released, runtime, director) VALUES ('6301af08-aa10-4e76-b9d9-c994a89a3469', 'Die Hard 2', 'https://m.media-amazon.com/images/M/MV5BMzMzYzk3ZTEtZDg0My00MTY5LWE3ZmQtYzNhYjhjN2RhZGRjL2ltYWdlXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SX300.jpg', 'John McClane attempts to avert disaster as rogue military operatives seize control of Dulles International Airport in Washington, D.C.', '1990-07-03 00:00:00.000000', 124, 'Renny Harlin');
INSERT INTO movies (id, title, image, plot, released, runtime, director) VALUES ('94dd3845-523e-4329-8e46-2327d629a3c1', 'Solan og Ludvig - Jul i Flåklypa', 'https://m.media-amazon.com/images/M/MV5BMjIxOTAwMjQwOF5BMl5BanBnXkFtZTgwMTQ5MDkzMzE@._V1_SX300.jpg', 'The small town of Flåklypa is experiencing great lack of snow, which is why the inventor Reodor Felgen is asked to create a snow machine. However, things does not go as planned.', '2013-11-08 00:00:00.000000', 76, 'Rasmus A. Sivertsen');
INSERT INTO movies (id, title, image, plot, released, runtime, director) VALUES ('49ecf5f8-96bf-47c2-bd4f-e463047a3f40', 'The Wave', 'https://m.media-amazon.com/images/M/MV5BMTg5Mjg0MjgxMl5BMl5BanBnXkFtZTgwNjUzNDg0NzE@._V1_SX300.jpg', 'Although anticipated, no one is really ready when the mountain pass above the scenic, narrow Norwegian fjord Geiranger collapses and creates an 85-meter high violent tsunami. A geologist is one of those caught in the middle of it.', '2015-08-28 00:00:00.000000', 105, 'Roar Uthaug');
INSERT INTO movies (id, title, image, plot, released, runtime, director) VALUES ('c02bf81c-4fb1-412b-8d48-6aaf2434130c', 'Flight', 'https://m.media-amazon.com/images/M/MV5BMTUxMjI1OTMxNl5BMl5BanBnXkFtZTcwNjc3NTY1OA@@._V1_SX300.jpg', 'An airline pilot saves almost all his passengers on his malfunctioning airliner which eventually crashed, but an investigation into the accident reveals something troubling.', '2012-11-02 00:00:00.000000', 138, 'Robert Zemeckis');
INSERT INTO movies (id, title, image, plot, released, runtime, director) VALUES ('b05e2f53-1f53-4545-87bd-f44fb3587bb0', 'Forrest Gump', 'https://m.media-amazon.com/images/M/MV5BNWIwODRlZTUtY2U3ZS00Yzg1LWJhNzYtMmZiYmEyNmU1NjMzXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg', 'The presidencies of Kennedy and Johnson, the events of Vietnam, Watergate, and other history unfold through the perspective of an Alabama man with an IQ of 75.', '1994-07-06 00:00:00.000000', 142, 'Robert Zemeckis');
INSERT INTO movies (id, title, image, plot, released, runtime, director) VALUES ('f7ed82f1-d293-44a2-94de-853b0c73db3c', 'The Matrix', 'https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg', 'A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.', '1999-03-31 00:00:00.000000', 136, 'Lana Wachowski, Lilly Wachowski');
INSERT INTO movies (id, title, image, plot, released, runtime, director) VALUES ('4ea4063e-bd33-48f2-b172-3bc066609319', 'WALL·E', 'https://m.media-amazon.com/images/M/MV5BMjExMTg5OTU0NF5BMl5BanBnXkFtZTcwMjMxMzMzMw@@._V1_SX300.jpg', 'In the distant future, a small waste-collecting robot inadvertently embarks on a space journey that will ultimately decide the fate of mankind.', '2008-06-27 00:00:00.000000', 98, 'Andrew Stanton');
INSERT INTO movies (id, title, image, plot, released, runtime, director) VALUES ('b51f462e-59ed-4c30-a3a0-d05b6000f3de', 'Inglourious Basterds', 'https://m.media-amazon.com/images/M/MV5BOTJiNDEzOWYtMTVjOC00ZjlmLWE0NGMtZmE1OWVmZDQ2OWJhXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_SX300.jpg', 'In Nazi-occupied France during World War II, a plan to assassinate Nazi leaders by a group of Jewish U.S. soldiers coincides with a theatre owner''s vengeful plans for the same.', '2009-08-21 00:00:00.000000', 153, 'Quentin Tarantino, Eli Roth');
INSERT INTO movies (id, title, image, plot, released, runtime, director) VALUES ('930d7de3-96bc-41da-ae87-36f1d2b2cb2d', 'Toy Story', 'https://m.media-amazon.com/images/M/MV5BMDU2ZWJlMjktMTRhMy00ZTA5LWEzNDgtYmNmZTEwZTViZWJkXkEyXkFqcGdeQXVyNDQ2OTk4MzI@._V1_SX300.jpg', 'A cowboy doll is profoundly threatened and jealous when a new spaceman figure supplants him as top toy in a boy''s room.', '1995-11-22 00:00:00.000000', 81, 'John Lasseter');
INSERT INTO movies (id, title, image, plot, released, runtime, director) VALUES ('8aa6dde9-5d6d-44db-bacd-ed4a775fc7a2', 'Up', 'https://m.media-amazon.com/images/M/MV5BMTk3NDE2NzI4NF5BMl5BanBnXkFtZTgwNzE1MzEyMTE@._V1_SX300.jpg', 'Seventy-eight year old Carl Fredricksen travels to Paradise Falls in his home equipped with balloons, inadvertently taking a young stowaway.', '2009-05-29 00:00:00.000000', 96, 'Pete Docter, Bob Peterson(co-director)');
INSERT INTO movies (id, title, image, plot, released, runtime, director) VALUES ('fecda8f3-81f5-44cc-b20c-59a85aaf29c0', 'Pirates of the Caribbean: The Curse of the Black Pearl', 'https://m.media-amazon.com/images/M/MV5BNGYyZGM5MGMtYTY2Ni00M2Y1LWIzNjQtYWUzM2VlNGVhMDNhXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg', 'Blacksmith Will Turner teams up with eccentric pirate "Captain" Jack Sparrow to save his love, the governor''s daughter, from Jack''s former pirate allies, who are now undead.', '2003-07-09 00:00:00.000000', 143, 'Gore Verbinski');
INSERT INTO movies (id, title, image, plot, released, runtime, director) VALUES ('bc7e4ee3-5cc1-4dd1-964c-c41c1afa7ecd', 'Saw', 'https://m.media-amazon.com/images/M/MV5BMjE4MDYzNDE1OV5BMl5BanBnXkFtZTcwNDY2OTYwNA@@._V1_SX300.jpg', 'Two strangers, who awaken in a room with no recollection of how they got there, soon discover they''re pawns in a deadly game perpetrated by a notorious serial killer.', '2004-10-29 00:00:00.000000', 103, 'James Wan');
INSERT INTO movies (id, title, image, plot, released, runtime, director) VALUES ('ef0a97cc-ef01-41e5-bddb-7f89c1e8c9ab', 'Cars', 'https://m.media-amazon.com/images/M/MV5BMTg5NzY0MzA2MV5BMl5BanBnXkFtZTYwNDc3NTc2._V1_SX300.jpg', 'A hot-shot race-car named Lightning McQueen gets waylaid in Radiator Springs, where he finds the true meaning of friendship and family.', '2006-06-09 00:00:00.000000', 117, 'John Lasseter, Joe Ranft(co-director)');
INSERT INTO movies (id, title, image, plot, released, runtime, director) VALUES ('a13362aa-b82e-4e95-9a8a-3da1a741113e', 'The Fast and the Furious', 'https://m.media-amazon.com/images/M/MV5BNzlkNzVjMDMtOTdhZC00MGE1LTkxODctMzFmMjkwZmMxZjFhXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg', 'Los Angeles police officer Brian O''Connor must decide where his loyalty really lies when he becomes enamored with the street racing world he has been sent undercover to destroy.', '2001-06-22 00:00:00.000000', 106, 'Rob Cohen');
INSERT INTO movies (id, title, image, plot, released, runtime, director) VALUES ('9224ba07-bdde-4a02-87e4-a5d942b3dd36', 'Mamma Mia! Here We Go Again', 'https://m.media-amazon.com/images/M/MV5BMjEwMTM3OTI1NV5BMl5BanBnXkFtZTgwNDk5NTY0NTM@._V1_SX300.jpg', 'Five years after the events of Mamma Mia! (2008), Sophie prepares for the grand reopening of the Hotel Bella Donna as she learns more about her mother''s past.', '2018-07-20 00:00:00.000000', 114, 'Ol Parker');
INSERT INTO movies (id, title, image, plot, released, runtime, director) VALUES ('a8b03776-22fd-4576-8e22-20fa424863e5', 'The Hobbit: An Unexpected Journey', 'https://m.media-amazon.com/images/M/MV5BMTcwNTE4MTUxMl5BMl5BanBnXkFtZTcwMDIyODM4OA@@._V1_SX300.jpg', 'A reluctant Hobbit, Bilbo Baggins, sets out to the Lonely Mountain with a spirited group of dwarves to reclaim their mountain home, and the gold within it from the dragon Smaug.', '2012-12-14 00:00:00.000000', 169, 'Peter Jackson');
INSERT INTO movies (id, title, image, plot, released, runtime, director) VALUES ('a291ea13-4bc1-4fe9-8670-fdf0c9d18be6', 'The Social Network', 'https://m.media-amazon.com/images/M/MV5BOGUyZDUxZjEtMmIzMC00MzlmLTg4MGItZWJmMzBhZjE0Mjc1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg', 'Harvard student Mark Zuckerberg creates the social networking site. That would become known as Facebook but is later sued by two brothers who claimed he stole their idea, and the co-founder who was later squeezed out of the business.', '2010-10-01 00:00:00.000000', 120, 'David Fincher');
INSERT INTO movies (id, title, image, plot, released, runtime, director) VALUES ('12fc7daa-2b2c-4f17-8268-9fdf1698f282', 'Steve Jobs', 'https://m.media-amazon.com/images/M/MV5BMjE0NTA2MTEwOV5BMl5BanBnXkFtZTgwNzg4NzU2NjE@._V1_SX300.jpg', 'Steve Jobs takes us behind the scenes of the digital revolution, to paint a portrait of the man at its epicenter. The story unfolds backstage at three iconic product launches, ending in 1998 with the unveiling of the iMac.', '2015-10-23 00:00:00.000000', 122, 'Danny Boyle');

INSERT INTO movie_genres (movie_id, genres) VALUES ('e7be6c09-bf47-4da6-9969-3c55d1e05fad', 'Drama');
INSERT INTO movie_genres (movie_id, genres) VALUES ('e7be6c09-bf47-4da6-9969-3c55d1e05fad', 'Crime');
INSERT INTO movie_genres (movie_id, genres) VALUES ('4f58a265-e15c-4207-a5ce-87f7cf3427c2', 'Action');
INSERT INTO movie_genres (movie_id, genres) VALUES ('4f58a265-e15c-4207-a5ce-87f7cf3427c2', 'Thriller');
INSERT INTO movie_genres (movie_id, genres) VALUES ('6301af08-aa10-4e76-b9d9-c994a89a3469', 'Action');
INSERT INTO movie_genres (movie_id, genres) VALUES ('6301af08-aa10-4e76-b9d9-c994a89a3469', 'Thriller');
INSERT INTO movie_genres (movie_id, genres) VALUES ('94dd3845-523e-4329-8e46-2327d629a3c1', 'Animation');
INSERT INTO movie_genres (movie_id, genres) VALUES ('94dd3845-523e-4329-8e46-2327d629a3c1', 'Family');
INSERT INTO movie_genres (movie_id, genres) VALUES ('49ecf5f8-96bf-47c2-bd4f-e463047a3f40', 'Action');
INSERT INTO movie_genres (movie_id, genres) VALUES ('49ecf5f8-96bf-47c2-bd4f-e463047a3f40', 'Drama');
INSERT INTO movie_genres (movie_id, genres) VALUES ('49ecf5f8-96bf-47c2-bd4f-e463047a3f40', 'Thriller');
INSERT INTO movie_genres (movie_id, genres) VALUES ('c02bf81c-4fb1-412b-8d48-6aaf2434130c', 'Drama');
INSERT INTO movie_genres (movie_id, genres) VALUES ('c02bf81c-4fb1-412b-8d48-6aaf2434130c', 'Thriller');
INSERT INTO movie_genres (movie_id, genres) VALUES ('b05e2f53-1f53-4545-87bd-f44fb3587bb0', 'Drama');
INSERT INTO movie_genres (movie_id, genres) VALUES ('b05e2f53-1f53-4545-87bd-f44fb3587bb0', 'Romance');
INSERT INTO movie_genres (movie_id, genres) VALUES ('f7ed82f1-d293-44a2-94de-853b0c73db3c', 'Action');
INSERT INTO movie_genres (movie_id, genres) VALUES ('f7ed82f1-d293-44a2-94de-853b0c73db3c', 'Sci-Fi');
INSERT INTO movie_genres (movie_id, genres) VALUES ('4ea4063e-bd33-48f2-b172-3bc066609319', 'Sci-Fi');
INSERT INTO movie_genres (movie_id, genres) VALUES ('4ea4063e-bd33-48f2-b172-3bc066609319', 'Adventure');
INSERT INTO movie_genres (movie_id, genres) VALUES ('4ea4063e-bd33-48f2-b172-3bc066609319', 'Animation');
INSERT INTO movie_genres (movie_id, genres) VALUES ('4ea4063e-bd33-48f2-b172-3bc066609319', 'Family');
INSERT INTO movie_genres (movie_id, genres) VALUES ('b51f462e-59ed-4c30-a3a0-d05b6000f3de', 'Adventure');
INSERT INTO movie_genres (movie_id, genres) VALUES ('b51f462e-59ed-4c30-a3a0-d05b6000f3de', 'Drama');
INSERT INTO movie_genres (movie_id, genres) VALUES ('b51f462e-59ed-4c30-a3a0-d05b6000f3de', 'War');
INSERT INTO movie_genres (movie_id, genres) VALUES ('930d7de3-96bc-41da-ae87-36f1d2b2cb2d', 'Adventure');
INSERT INTO movie_genres (movie_id, genres) VALUES ('930d7de3-96bc-41da-ae87-36f1d2b2cb2d', 'Fantasy');
INSERT INTO movie_genres (movie_id, genres) VALUES ('930d7de3-96bc-41da-ae87-36f1d2b2cb2d', 'Animation');
INSERT INTO movie_genres (movie_id, genres) VALUES ('930d7de3-96bc-41da-ae87-36f1d2b2cb2d', 'Family');
INSERT INTO movie_genres (movie_id, genres) VALUES ('930d7de3-96bc-41da-ae87-36f1d2b2cb2d', 'Comedy');
INSERT INTO movie_genres (movie_id, genres) VALUES ('8aa6dde9-5d6d-44db-bacd-ed4a775fc7a2', 'Adventure');
INSERT INTO movie_genres (movie_id, genres) VALUES ('8aa6dde9-5d6d-44db-bacd-ed4a775fc7a2', 'Animation');
INSERT INTO movie_genres (movie_id, genres) VALUES ('8aa6dde9-5d6d-44db-bacd-ed4a775fc7a2', 'Family');
INSERT INTO movie_genres (movie_id, genres) VALUES ('8aa6dde9-5d6d-44db-bacd-ed4a775fc7a2', 'Comedy');
INSERT INTO movie_genres (movie_id, genres) VALUES ('fecda8f3-81f5-44cc-b20c-59a85aaf29c0', 'Action');
INSERT INTO movie_genres (movie_id, genres) VALUES ('fecda8f3-81f5-44cc-b20c-59a85aaf29c0', 'Adventure');
INSERT INTO movie_genres (movie_id, genres) VALUES ('fecda8f3-81f5-44cc-b20c-59a85aaf29c0', 'Fantasy');
INSERT INTO movie_genres (movie_id, genres) VALUES ('bc7e4ee3-5cc1-4dd1-964c-c41c1afa7ecd', 'Horror');
INSERT INTO movie_genres (movie_id, genres) VALUES ('bc7e4ee3-5cc1-4dd1-964c-c41c1afa7ecd', 'Thriller');
INSERT INTO movie_genres (movie_id, genres) VALUES ('bc7e4ee3-5cc1-4dd1-964c-c41c1afa7ecd', 'Crime');
INSERT INTO movie_genres (movie_id, genres) VALUES ('bc7e4ee3-5cc1-4dd1-964c-c41c1afa7ecd', 'Mystery');
INSERT INTO movie_genres (movie_id, genres) VALUES ('ef0a97cc-ef01-41e5-bddb-7f89c1e8c9ab', 'Sport');
INSERT INTO movie_genres (movie_id, genres) VALUES ('ef0a97cc-ef01-41e5-bddb-7f89c1e8c9ab', 'Fantasy');
INSERT INTO movie_genres (movie_id, genres) VALUES ('ef0a97cc-ef01-41e5-bddb-7f89c1e8c9ab', 'Animation');
INSERT INTO movie_genres (movie_id, genres) VALUES ('ef0a97cc-ef01-41e5-bddb-7f89c1e8c9ab', 'Family');
INSERT INTO movie_genres (movie_id, genres) VALUES ('ef0a97cc-ef01-41e5-bddb-7f89c1e8c9ab', 'Comedy');
INSERT INTO movie_genres (movie_id, genres) VALUES ('a13362aa-b82e-4e95-9a8a-3da1a741113e', 'Action');
INSERT INTO movie_genres (movie_id, genres) VALUES ('a13362aa-b82e-4e95-9a8a-3da1a741113e', 'Thriller');
INSERT INTO movie_genres (movie_id, genres) VALUES ('a13362aa-b82e-4e95-9a8a-3da1a741113e', 'Crime');
INSERT INTO movie_genres (movie_id, genres) VALUES ('9224ba07-bdde-4a02-87e4-a5d942b3dd36', 'Romance');
INSERT INTO movie_genres (movie_id, genres) VALUES ('9224ba07-bdde-4a02-87e4-a5d942b3dd36', 'Comedy');
INSERT INTO movie_genres (movie_id, genres) VALUES ('9224ba07-bdde-4a02-87e4-a5d942b3dd36', 'Musical');
INSERT INTO movie_genres (movie_id, genres) VALUES ('a8b03776-22fd-4576-8e22-20fa424863e5', 'Adventure');
INSERT INTO movie_genres (movie_id, genres) VALUES ('a8b03776-22fd-4576-8e22-20fa424863e5', 'Fantasy');
INSERT INTO movie_genres (movie_id, genres) VALUES ('a8b03776-22fd-4576-8e22-20fa424863e5', 'Family');
INSERT INTO movie_genres (movie_id, genres) VALUES ('a291ea13-4bc1-4fe9-8670-fdf0c9d18be6', 'Drama');
INSERT INTO movie_genres (movie_id, genres) VALUES ('a291ea13-4bc1-4fe9-8670-fdf0c9d18be6', 'Biography');
INSERT INTO movie_genres (movie_id, genres) VALUES ('12fc7daa-2b2c-4f17-8268-9fdf1698f282', 'Drama');
INSERT INTO movie_genres (movie_id, genres) VALUES ('12fc7daa-2b2c-4f17-8268-9fdf1698f282', 'Biography');