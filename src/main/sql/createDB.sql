create database jdbcrealm;

create table users (
  id int not null primary key,
  username varchar(255) unique not null,
  password varchar(255) unique not null,
  name varchar(255) not null,
  email varchar(255) not null
);

create table groups (
  username varchar(255) not null primary key,
  groupname varchar(255) not null
);

insert into users values (1,'vasouv','vasouv','Vasilis','vasouv@admin.com');
insert into users values (2,'geo','geo','George','geo@admin.com');
insert into users values (3,'maik','maik','Mixalis','maik@maik.com');

insert into groups values ('vasouv','admin');
insert into groups values ('geo','admin');
insert into groups values ('maik','user');

-- Finds the max(id) of the users, usage in registration EJB
select max(id) from APP.USERS;