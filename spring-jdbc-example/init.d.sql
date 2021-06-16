CREATE USER 'sjkim'@'%' IDENTIFIED BY 'password';
grant all privileges on *.* to sjkim@'%';
flush privileges;
create database school;
use school;
create table Student
(
	id bigint auto_increment,
	name varchar(255) null,
	age int null,
	birth date null,
	phone varchar(255) null,
	constraint student_pk primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;