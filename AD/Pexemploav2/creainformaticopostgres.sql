drop table if exists informaticos;

drop type if exists public.tipo_fillos;
create type public.tipo_fillos as (
homes numeric,
mulleres numeric
);

create table informaticos (
cinf numeric,
dniinf varchar(4),
fillos tipo_fillos);

insert into informaticos values (1,'361',(0,0));
insert into informaticos values (2,'365',(0,1));
insert into informaticos values (3,'367',(1,1));
insert into informaticos values (4,'3610',(0,0));
insert into informaticos values (5,'3612',(1,2));


