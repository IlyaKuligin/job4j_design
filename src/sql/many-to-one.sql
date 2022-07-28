create table student_group(
    id serial primary key,
    name varchar(255)
);

create table students(
    id serial primary key,
    name varchar(255),
    surname varchar(255),
    student_group_id int references student_group(id)
);