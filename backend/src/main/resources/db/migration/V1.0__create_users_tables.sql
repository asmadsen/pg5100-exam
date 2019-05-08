create table users
(
	id uuid
		constraint users_pk
			primary key,
	email varchar(255) not null,
	password varchar(255) not null,
	enabled boolean default false not null,
	alias varchar(255) default null,
	first_name varchar(255) not null,
	last_name varchar(255) not null
);

create unique index users_email_uindex
	on users (email);

create table user_roles
(
	user_id uuid not null,
	roles varchar(255) not null,
	constraint user_roles_pk
        primary key (user_id, roles)
);

INSERT INTO users (id, email, password, enabled, alias, first_name, last_name) VALUES ('4bfe8afa-3ef8-46e4-975f-9e940ee2b6af', 'post@example.com', '$2a$10$gHeeWXTVs24DBgAdOPijhOuZ7QHrNxdve1aK1YobqM8JAYyS37TyW', true, 'example', 'John', 'Doe');
INSERT INTO users (id, email, password, enabled, alias, first_name, last_name) VALUES ('91f3bcbc-01f5-403d-ac2c-cf64594c1be5', 'sheena.ward@yahoo.com', '$2a$10$AlY3.Dd4yBH1m8jXJv4tkuy3Xc4cdS/jt6/HF2AWfVOtUvhYFhvSa', true, 'normand.howell', 'Karrie', 'Osinski');
INSERT INTO users (id, email, password, enabled, alias, first_name, last_name) VALUES ('6b23575d-117e-47b1-a963-246e031a83bf', 'miquel.kuhic@gmail.com', '$2a$10$00r2ZXVVhDYKSIZp.H1PEuCBSNiz5XO2As.3o3vLYbCF9yyNr2BP2', true, 'rosamond.wehner', 'Lou', 'Lesch');
INSERT INTO users (id, email, password, enabled, alias, first_name, last_name) VALUES ('b7a201ea-2363-4106-8c6e-fdff0a960c60', 'gianna.okeefe@yahoo.com', '$2a$10$WhLvXsS5LZV7CrDsUUF1neawrihkUdnj7nlbKlGbfuQ8yHfMHAE4S', true, 'shanna.mohr', 'Martin', 'Stracke');
INSERT INTO users (id, email, password, enabled, alias, first_name, last_name) VALUES ('b5d31036-b700-43b4-a181-5ce9eea31659', 'deshawn.reichert@hotmail.com', '$2a$10$t8ltqhnwcm2Nu1p4XHG19urz36.IVWfAJJEW5nQ2.2R/6lbCuJR8O', true, 'oralee.turcotte', 'Wilford', 'Medhurst');
INSERT INTO users (id, email, password, enabled, alias, first_name, last_name) VALUES ('8d2361d0-ece7-4621-9c91-7eba6a22d241', 'herman.huels@hotmail.com', '$2a$10$qZZkUlYX81S.aPmMvuDgruSdxqxflq6lyFBD011Ve8q6aJQ7zDACu', true, 'riley.schaefer', 'Ryan', 'Mertz');
INSERT INTO users (id, email, password, enabled, alias, first_name, last_name) VALUES ('45faa051-05f4-40da-89f3-1196188403c6', 'young.heller@gmail.com', '$2a$10$khEO5FLa9uT.ACmQhhV51uSrzuxr3R6paRCY1qElpTKhxEwJOY2ga', true, 'delbert.dickinson', 'Michael', 'Ankunding');
INSERT INTO users (id, email, password, enabled, alias, first_name, last_name) VALUES ('eff9b544-e922-4f1d-bf37-ec0638fe28f0', 'elbert.zieme@hotmail.com', '$2a$10$WEG9tgkWWQ44UesMp2ljR.N81i2ady8idWdKRPfRNXmPuYrc4bEc6', true, 'kenneth.schamberger', 'Oswaldo', 'Ruecker');
INSERT INTO users (id, email, password, enabled, alias, first_name, last_name) VALUES ('dc909217-0191-4232-9483-070bae642941', 'abram.brekke@gmail.com', '$2a$10$0BVvEWDtuLuZdv6UQ0c41.2brPH0ADyPKf64/whwj6wZ3vlfc6oUa', true, 'donnetta.moen', 'Thurman', 'Russel');
INSERT INTO users (id, email, password, enabled, alias, first_name, last_name) VALUES ('e980ac6d-5df7-4147-8b25-2a356e5e0265', 'alfreda.pollich@yahoo.com', '$2a$10$OnQHg2ToHcDH/EZrnKrmjO4sgVwmhO7BPOjuxuZtlqmMDt9siMetG', true, 'mireya.kozey', 'Damien', 'Metz');
INSERT INTO users (id, email, password, enabled, alias, first_name, last_name) VALUES ('19314804-7bfa-44dd-8ed1-1e60370e0b32', 'edwardo.bauch@yahoo.com', '$2a$10$G8kuS57/4HCvtp3TgoYQNu.gJ9XM3f0Vnf/LNsyBJayMPftzsjlIW', true, 'lucia.schroeder', 'Ismael', 'Swaniawski');

INSERT INTO user_roles (user_id, roles) VALUES ('4bfe8afa-3ef8-46e4-975f-9e940ee2b6af', 'USER');
INSERT INTO user_roles (user_id, roles) VALUES ('91f3bcbc-01f5-403d-ac2c-cf64594c1be5', 'USER');
INSERT INTO user_roles (user_id, roles) VALUES ('6b23575d-117e-47b1-a963-246e031a83bf', 'USER');
INSERT INTO user_roles (user_id, roles) VALUES ('b7a201ea-2363-4106-8c6e-fdff0a960c60', 'USER');
INSERT INTO user_roles (user_id, roles) VALUES ('b5d31036-b700-43b4-a181-5ce9eea31659', 'USER');
INSERT INTO user_roles (user_id, roles) VALUES ('8d2361d0-ece7-4621-9c91-7eba6a22d241', 'USER');
INSERT INTO user_roles (user_id, roles) VALUES ('45faa051-05f4-40da-89f3-1196188403c6', 'USER');
INSERT INTO user_roles (user_id, roles) VALUES ('eff9b544-e922-4f1d-bf37-ec0638fe28f0', 'USER');
INSERT INTO user_roles (user_id, roles) VALUES ('dc909217-0191-4232-9483-070bae642941', 'USER');
INSERT INTO user_roles (user_id, roles) VALUES ('e980ac6d-5df7-4147-8b25-2a356e5e0265', 'USER');
INSERT INTO user_roles (user_id, roles) VALUES ('19314804-7bfa-44dd-8ed1-1e60370e0b32', 'USER');