create database myDB;
use myDB;

create table mi_tabla(
	id int primary key auto_increment,
    nombre varchar(30) not null,
    ap_pat varchar(30) not null,
    ap_mat varchar(30) not null,
    color_fav varchar(15) not null
);