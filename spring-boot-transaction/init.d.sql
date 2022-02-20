CREATE USER 'sjkim'@'%' IDENTIFIED BY 'password';
grant all privileges on *.* to sjkim@'%';
flush privileges;
create database test;
use test;

create table board (
    id bigint auto_increment primary key,
    title varchar(100) not null,
    writer varchar(30) not null,
    content varchar(200) not null,
    created_at datetime not null,
    updated_at datetime not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table history (
     id bigint auto_increment primary key,
     action varchar(200) not null,
     created_at datetime not null,
     updated_at datetime not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;