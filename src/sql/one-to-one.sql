create table wife(
    id serial primary key,
    name varchar(255)
);

create table husband(
    id serial primary key,
    name varchar(255)
);

create table marriag(
    id serial primary key,
    wife_id int references wife(id) unique,
    husband_id int references husband(id) unique
);