create database jdbcrealm;

create table users (
  id int not null primary key,
  username varchar(255) unique not null,
  password varchar(255) unique not null,
  name varchar(255) not null,
  email varchar(255) not null
);

insert into users values (1,'vasouv','fa5c9ed954b7302ec7c368ae06935670f4f2ac7d4f791daf2ba3e2eefdaac9d4',
'Vasilis','vasouv@admin.com');
insert into users values (2,'geo','e81935fb86434cdaaeee21ebe051cca827243ab4cccfdafeee8202004630924e',
'George','geo@admin.com');
insert into users values (3,'maik','f96c7f41614c5900f39425566ff335c42f130dba316d7765159a26e03c82e182',
'Mixalis','maik@maik.com');

create table groups (
  username varchar(255) not null primary key,
  groupname varchar(255) not null
);

insert into groups values ('vasouv','admin');
insert into groups values ('geo','admin');
insert into groups values ('maik','user');

create table courses (
    id int not null primary key,
    title varchar(50) unique not null,
    description clob(1024),
    price int not null,
    image varchar(255) not null
);

insert into courses values (1,'Learning Java EE 7','This tutorial is intended for 
novice Java EE developers. You should be familiar with
the Java programming language, particularly the features introduced in Java Platform,
Standard Edition 7. While familiarity with enterprise development and Java EE
technologies is helpful, this tutorial assumes you are new to developing Java EE
applications.',55,'courses\javaee7\javaee7pic.png');

insert into courses values (2,'Developing Applications with NetBeans 8',
'NetBeans IDE is a free, open source, integrated development environment (IDE) that enables you to develop desktop, 
mobile and web applications. The IDE supports application development in various languages, including Java, 
HTML5, PHP and C++. The IDE provides integrated support for the complete development cycle, 
from project creation through debugging, profiling and deployment. The IDE runs on Windows, Linux, 
Mac OS X, and other UNIX-based systems.',0,'courses\netbeans\netbeans.png');

create table lectures (
    id int not null primary key,
    title varchar(50) not null,
    youtube varchar(100) not null,
    material varchar(200)
);

insert into lectures values(1,'Getting Started with JAX-WS','http://youtu.be/BEYnlh78TQ4','courses\netbeans\lec01\Code.txt');
insert into lectures values(2,'Create RESTful Web Services and a Test Client','http://youtu.be/l08u2L-8psk','courses\netbeans\lec02\ZippedCode.zip');

create table usercourses (
    user_id int not null,
    course_id int not null,
    primary key (user_id,course_id),
    foreign key (user_id) references users (id),
    foreign key (course_id) references courses (id)
);

insert into usercourses values(1,1);
insert into usercourses values(1,2);
insert into usercourses values(2,1);
insert into usercourses values(3,2);

create table courselectures (
    course_id int not null,
    lecture_id int not null,
    primary key (course_id,lecture_id),
    foreign key (course_id) references courses (id),
    foreign key (lecture_id) references lectures (id)
);

insert into courselectures values(2,1);
insert into courselectures values(2,2);

-- Finds the max(id) of the users, usage in registration EJB
select max(id) from APP.USERS;

-- Displays the name of the user and which courses they've enrolled to
select u."NAME",c.TITLE 
from users u, courses c, usercourses uc 
where u.ID=uc.user_id and c.ID=uc.COURSE_ID