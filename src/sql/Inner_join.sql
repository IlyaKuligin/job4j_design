create table books(
    id serial primary key,
    name varchar(255)
);

create table readers(
    id serial primary key,
    name varchar(255),
    books_id int references books(id)
);

insert into books(name) values ('Преступление и наказание');
insert into books(name) values ('Мастер и маргарита');
insert into books(name) values ('Война и мир');
insert into books(name) values ('Стихотворения Пушкина');

select * from books;

insert into readers(name, books_id) values ('Иван', 1); 
insert into readers(name, books_id) values ('Петр', 1); 
insert into readers(name, books_id) values ('Семен', 2);
insert into readers(name, books_id) values ('Геннадий', 3);

select r.name, b.name from readers as r
join books as b on r.books_id = b.id;

select r.name as Читатель, b.name as Книга from readers as r
join books as b on r.books_id = b.id;

select r.name as "Имя читателя", b.name Книга from readers as r
join books as b on r.books_id = b.id;