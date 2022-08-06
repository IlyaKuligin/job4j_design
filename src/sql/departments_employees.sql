create table departments (
id serial primary key,
name varchar(255)
);

create table employees (
id serial primary key,
name varchar(255),
departmaents_id int references departments(id)       
);

insert into departments(name)
values ('отдел техподдержки'), ('отдел продаж'), ('отдел кадров'), ('IT отдел'), ('экономический отдел');

insert into employees(name, departmaents_id)
values
('Иван', 1),
('Никита', 1),
('Нина', 1),
('Мария', 2),
('Дарья', 2),
('Светлана', 2),
('Евгения', 3),
('Демид', 4),
('Варвара', 4),
('Степан', 4);

select * from employees em left join departments dp on em.departmaents_id = dp.id;      

select * from employees em right join departments dp on em.departmaents_id = dp.id; 

select * from employees em full join departments dp on em.departmaents_id = dp.id; 

select * from employees cross join departments;

select * from departments dp left join employees em on em.departmaents_id = dp.id where departmaents_id is null;  

select * from employees em left join departments dp on em.departmaents_id = dp.id 
order by em.id asc; 

select * from employees em right join departments dp on em.departmaents_id = dp.id 
where departmaents_id is not null
order by em.id asc;

create table teens (
id serial primary key,
name varchar(255),
gender varchar(255)
);

insert into teens(name, gender )
values ('Иван', 'М'), ('Нина', 'Ж'), ('Евгения', 'Ж'), ('Степан', 'М');

select t1.name, t2.name from teens t1 cross join teens t2 where t1.gender = 'М' AND t2.gender = 'Ж';






