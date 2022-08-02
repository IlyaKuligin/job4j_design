create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) 
values 
('телефон', 8500.00), 
('планшет', 12900.00), 
('ноутбук', 29900.99), 
('наушники', 1500.00), 
('умная колонка', 4990.99); 

insert into people(name)
values ('Аня'), ('Ваня'), ('Боря');

insert into devices_people(device_id, people_id)
values (1, 1), (2, 3), (3, 2), (4, 1), (5, 3), (1, 2);

select avg(devices.price) from devices;

select p.name Человек, avg(d.price) as "Средняя цена устройства"
from devices_people as dp
join people p
on dp.people_id = p.id
join devices d
on dp.device_id = d.id
group by p.name;

select p.name Человек, avg(d.price) as "Средняя цена устройства"
from devices_people as dp
join people p
on dp.people_id = p.id
join devices d
on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000.00;

