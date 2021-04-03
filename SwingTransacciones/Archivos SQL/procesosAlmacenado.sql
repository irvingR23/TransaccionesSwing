use estudiantes;
delimiter $$
create procedure crearEstudiante(
in nombreT varchar(45),
in calificacionT int
)
begin
declare exit handler for sqlexception
begin
get diagnostics condition 1
@texto=message_text;
select @texto;
select 1 as error;
rollback;
end;
declare exit handler for sqlwarning
begin
get diagnostics condition 1
@texto=message_text;
select @texto;
select 0 as error;
rollback;
end;
start transaction;
insert into estudiante(nombre,calificacion) values(nombreT,calificacionT);
commit;
select 0 as error;
end
$$
delimiter ;

delimiter $$
create procedure actualizarEstudiante(
in idT int,
in nombreT varchar(45),
in calificacionT int
)
begin
declare exit handler for sqlexception
begin
get diagnostics condition 1
@texto=message_text;
select @texto;
select 1 as error;
rollback;
end;
declare exit handler for sqlwarning
begin
get diagnostics condition 1
@texto=message_text;
select @texto;
select 1 as error;
rollback;
end;
start transaction;
update estudiante set nombre=nombreT,calificacion=calificacionT where id=idT;
commit;
select 0 as error;
end
$$
delimiter ;

delimiter $$
create procedure seleccionarEstudiante(
in idT int
)
begin
declare exit handler for sqlexception
begin
get diagnostics condition 1
@texto=message_text;
select @texto;
select 1 as error;
rollback;
end;
declare exit handler for sqlwarning
begin
get diagnostics condition 1
@texto=message_text;
select @texto;
select 1 as error;
rollback;
end;
start transaction;
select * from estudiante where id=idT;
commit;
select 0 as error;
end
$$
delimiter ;

delimiter $$
create procedure seleccionarEstudiantes(
)
begin
declare exit handler for sqlexception
begin
get diagnostics condition 1
@texto=message_text;
select @texto;
select 1 as error;
rollback;
end;
declare exit handler for sqlwarning
begin
get diagnostics condition 1
@texto=message_text;
select @texto;
select 1 as error;
rollback;
end;
start transaction;
select * from estudiante;
commit;
select 0 as error;
end
$$
delimiter ;

delimiter $$
create procedure borrarEstudiante(
in idT int
)
begin
declare exit handler for sqlexception
begin
get diagnostics condition 1
@texto=message_text;
select @texto;
select 1 as error;
rollback;
end;
declare exit handler for sqlwarning
begin
get diagnostics condition 1
@texto=message_text;
select @texto;
select 1 as error;
rollback;
end;
start transaction;
delete from estudiante where id=idT;
commit;
select 0 as error;
end
$$
delimiter ;