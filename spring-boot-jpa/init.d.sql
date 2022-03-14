CREATE USER 'sjkim'@'%' IDENTIFIED BY 'password';
grant all privileges on *.* to sjkim@'%';
flush privileges;
create database test;
use test;
