create table car_bodies (
    id serial primary key,
    name varchar(255) 
);

create table car_engines (
    id serial primary key,
    name varchar(255) 
);

create table car_transmissions (
    id serial primary key,
    name varchar(255) 
);

create table cars  (
    id serial primary key,
    name varchar(255), 
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values ('седан'), ('хэтчбек'), ('пикап'), ('лимузин');
insert into car_engines(name) values ('4 цилиндровый'), ('6 цилиндровый'), ('8 цилиндровый');
insert into car_transmissions(name) values ('Механическая'), ('Автоматическая'), ('Вариатор');
insert into cars(name, body_id, engine_id, transmission_id) 
values 
('TOYOTA', 1, 1, 3),
('FORD', 3, 3, 1),
('HONDA', 2, 1, 1),
('UAZ', null, 1, 1);

select c.name Автомобиль, cb.name Кузов, ce.name Двигатель, ct.name КПП from cars c 
left join car_bodies cb on c.body_id = cb.id
left join car_engines ce on c.engine_id = ce.id
left join car_transmissions ct on c.transmission_id = ct.id;  

select cb.name Кузов from car_bodies cb
left join cars c on c.body_id = cb.id 
where c.body_id is null;

select ce.name Двигатели from car_engines ce
left join cars c on c.engine_id = ce.id 
where c.engine_id is null;

select ct.name КПП from car_transmissions ct
left join cars c on c.transmission_id = ct.id 
where c.transmission_id is null;






