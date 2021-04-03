create database estudiantes;
use estudiantes;
create table estudiante(id int primary key auto_increment,nombre varchar(45),calificacion int);
insert into estudiante(nombre,calificacion) values("Josu Fei",9);
insert into estudiante(nombre,calificacion) values("Paco Sanchez",7);