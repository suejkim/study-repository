CREATE USER 'sjkim'@'%' IDENTIFIED BY 'password';
grant all privileges on *.* to sjkim@'%';
flush privileges;
create database singer;
use singer;

create table singer_group (
    id bigint auto_increment,
    name       varchar(50) not null,
    debut_date date null,
    agency     varchar(255) null,
    constraint group_pk primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table singer (
	id bigint auto_increment,
	name varchar(255) not null,
	birth date not null,
	sex varchar(255) not null,
	group_id bigint not null,
	constraint singer_pk primary key (id),
	foreign key singer_group_id_fk (group_id) references singer_group (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table song (
    id bigint auto_increment,
    name varchar(255) not null,
    release_date date not null,
    genre varchar(255) not null,
    group_id bigint not null,
    constraint song_pk primary key (id),
    foreign key song_group_id_fk (group_id) references singer_group (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;