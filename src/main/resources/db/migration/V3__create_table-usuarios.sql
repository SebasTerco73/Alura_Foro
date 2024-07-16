create table if not exists usuarios (
    id bigint not null auto_increment  primary key,
    login varchar(100) not null unique,
    clave varchar(300) not null
);