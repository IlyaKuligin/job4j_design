create database fauna;

create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date)
values ('Белый медведь', 10950, date '1774-01-01');
insert into fauna(name, avg_age, discovery_date)
values ('Обыкновенный ёж', 2190, null);
insert into fauna(name, avg_age, discovery_date)
values ('Lasiognathus dinema fish', null, date '2015-01-01');

select * from fauna where name like '%fish%';
select * from fauna where (avg_age > 10000 AND avg_age < 20000);
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '01.01.1950';