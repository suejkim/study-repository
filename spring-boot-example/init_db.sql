create table board (
id bigint auto_increment primary key,
title varchar(100) not null,
writer varchar(30) not null,
content varchar(200) not null,
created_at datetime not null,
updated_at datetime not null
);


create table member (
id bigint auto_increment primary key,
login_id varchar(100) not null,
password varchar(100) not null,
username varchar(45) not null
);

create table profile (
id bigint auto_increment primary key,
name varchar(100) not null,
current bit not null,
member_id bigint,
foreign key (member_id) references member(id)
);


create table board_file (
id bigint auto_increment primary key,
name varchar(100) not null,
board_id bigint,
foreign key (board_id) references board(id)
);


create table free_board (
id bigint auto_increment primary key,
title varchar(100) not null,
writer varchar(30) not null,
content varchar(200) not null,
created_at datetime not null,
updated_at datetime not null
);


create table free_board_reply (
id bigint auto_increment primary key,
reply varchar(100) not null,
replyer varchar(30) not null,
created_at datetime not null,
updated_at datetime not null,
free_board_id bigint,
foreign key (free_board_id) references free_board(id)
);
