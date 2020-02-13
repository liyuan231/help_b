-- auto-generated definition
create table githubuser
(
    id         int          null,
    avatar_url varchar(200) null,
    bio        varchar(200) null,
    created_at varchar(200) null,
    updated_at varchar(200) null,
    username   varchar(100) null
);
-- auto-generated definition
create table sysuser
(
    id         int auto_increment
        primary key,
    username   varchar(100) null,
    avatar_url varchar(200) null,
    bio        varchar(200) null,
    created_at varchar(200) null,
    updated_at varchar(200) null,
    password   varchar(200) null
);


