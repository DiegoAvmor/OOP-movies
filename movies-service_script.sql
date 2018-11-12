create database movies_service;

use movies_service;

create table genres (
	id int not null,
    name varchar(20),
    primary key(id)
);

create table movies (
	id int not null,
    title varchar(80),
    overview varchar(1800),
    primary key(id)
);