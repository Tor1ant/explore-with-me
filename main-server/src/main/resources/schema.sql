drop table if exists users;
create table users
(
    id    integer generated by default as identity
        constraint users_pk
            primary key,
    email varchar(254) unique not null,
    name  varchar(250) unique not null
);
