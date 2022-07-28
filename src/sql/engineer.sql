create table engineer(
    id serial primary key,
    name varchar (255),
    surname varchar (255),
    age int,
    employed boolean,
    comment text
);

insert into engineer(name, surname, age, employed, comment) 
values('Ivan', 'Ivanov', 34, true, 'через год переводится в другое подразделение');

select * from engineer;

update engineer set age = 35, employed = false, comment = 'переведен в другое подразделение';

select * from engineer;

delete from engineer;