create table if not exists tb_item (
    id bigint generated by default as identity,
    content varchar(255),
    create_time timestamp,
    status integer,
    title varchar(255),
    update_time timestamp,
    primary key (id)
)