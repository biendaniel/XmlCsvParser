create database parser;

use  parser;

create table customers(
	id bigint auto_increment primary key,
    name varchar(128) not null,
    surname varchar(128) not null,
    age int
);

create table contacts(
id bigint auto_increment primary key,
contact varchar(64) not null,
type int not null,
id_customer bigint not null,
foreign key (id_customer) references customers(id)
)

create table dictionary(
	id bigint auto_increment primary key,
    name varchar(32) not null,
    value varchar(64) not null
);

insert into dictionary(name, value) values('directory', 'C:\\');

