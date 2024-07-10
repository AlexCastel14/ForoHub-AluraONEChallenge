
create table topics (
    id bigint not null auto_increment,
    idUsuario bigint not null,
    mensaje varchar(500) not null,
    nombreCurso varchar(100) not null,
    titulo varchar(100) not null,

    primary key(id)
);
