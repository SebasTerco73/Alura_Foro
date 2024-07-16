create table if not exists topicos (
    id bigint not null auto_increment  primary key,
    titulo varchar(50) not null,
    mensaje varchar(200) not null,
    fecha_creacion DATETIME NOT NULL,
    topico_status BOOLEAN,
    autor VARCHAR(100) NOT NULL,
    curso VARCHAR(100) NOT NULL);
);