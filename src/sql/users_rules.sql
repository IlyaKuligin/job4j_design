create table role(
    id serial primary key,
    name varchar(255)
);

create table users(
    id serial primary key,
    name varchar(255),
    role_id int references role(id)
);

create table rules(
    id serial primary key,
    name varchar(255)
);

create table role_rules(
    id serial primary key,
    role_id int references role(id),
    rules_id int references rules(id)
);

create table category(
    id serial primary key,
    name varchar(255)
);

create table state(
    id serial primary key,
    name varchar(255)
);

create table item(
    id serial primary key,
    name varchar(255),
    users_id int references users(id),
    category_id int references category(id),
    state_id int references state(id)
);

create table comments(
    id serial primary key,
    name text,
    item_id int references item(id)
);

create table attachs(
    id serial primary key,
    name varchar(255),
    item_id int references item(id)
);

insert into role(name) 
values('Простой работник'), ('Начальник');

insert into users(name, role_id) 
values('Иван', 1), ('Михаил', 2);

insert into rules(name) 
values
('принимать задачу в работу, менять статус задачи, оставлять комментарии, передавать задачу на проверку'),
('создавать задачу, оставлять комментарии, закрывать задачу');

insert into role_rules(role_id, rules_id) 
values(1, 1), (2, 2);

insert into category(name) 
values('срочная'), ('обычная'), ('не срочная');

insert into state(name) 
values('в очереди'), ('в работе'), ('на проверке'), ('закрыта');

insert into item(name, users_id, category_id, state_id) 
values('важная задача, купить конфеты', 1, 1, 2);

insert into comments(name, item_id) 
values('сделать в приоритете!', 1);

insert into attachs(name, item_id) 
values('образец конфеты.jpeg', 1);