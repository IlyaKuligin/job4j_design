create table type (
    id serial primary key,
    name varchar(255)
);

create table product (
    id serial primary key,
    name varchar(255),
    type_id int references type(id),
    expired_date date,
    price float
);

insert into type (name) values ('СЫР'), ('МОЛОКО'), ('КРУПЫ'), ('РЫБА'), ('МЯСО');

insert into product (name, type_id, expired_date, price)
values
('Сыр плавленный', 1, '2022-08-29', 150.99),
('Сыр моцарелла', 1, '2022-09-13', 260.99),
('Молоко 1,5%', 2, '2022-08-06', 60.99),
('Молоко 2,5%', 2, '2022-08-08', 70.99),
('Молоко 3,5%', 2, '2022-08-05', 80.99),
('Кефир', 2, '2022-08-01', 72.99),
('Снежок', 2, '2022-08-02', 52.99),
('Ряженка', 2, '2022-08-10', 69.99),
('Бифидок', 2, '2022-08-11', 65.98),
('Молочный напиток', 2, '2022-08-15', 90.98),
('Молочный коктейль', 2, '2022-08-15', 110.98),
('Йогур', 2, '2022-08-13', 85.99),
('Рис', 3, '2022-12-15', 70.99),
('Греча', 3, '2022-11-10', 90.99),
('Форель свежая', 4, '2022-08-02', 500.99),
('Мороженное рыбное филе', 4, '2022-11-05', 180.99),
('Сельдь консервированная', 4, '2023-06-25', 166.99),
('Говядина', 5, '2022-08-08', 450.99),
('Курица', 5, '2022-08-09', 250.99);

select p.name as "Наименование продукта", t.name as "Тип продукта" 
from product p 
join type t 
on p.type_id = t.id
where t.name = 'СЫР';

select name as "Наименование продукта"
from product 
where name ilike '%мороженное%';

select name as "Наименование продукта", expired_date as "Срок годности"
from product 
where expired_date < current_date;

select name as "Наименование продукта", price as "Цена"
from product
where price = (select max(price) from product);

select t.name as "Тип продукта", count(p.type_id) as "Количестово" 
from type t 
join product p 
on p.type_id = t.id
group by t.name;

select p.name as "Наименование продукта", t.name as "Тип продукта"
from type t
join product p
on p.type_id = t.id
where (t.name = 'СЫР' or t.name = 'МОЛОКО');

select t.name as "Тип продукта", count(p.type_id) as "Количестово" 
from type t 
join product p 
on p.type_id = t.id
group by t.name
having count(p.type_id) < 10;

select p.name as "Наименование продукта", t.name as "Тип продукта"
from type t
join product p
on p.type_id = t.id;

