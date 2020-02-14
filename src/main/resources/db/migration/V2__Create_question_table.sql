-- auto-generated definition
create table publishquestion
(
    id           int auto_increment
        primary key,
    title        varchar(50)   null,
    detail       text          null,
    tags         varchar(200)  null,
    gmt_created  varchar(100)  null,
    gmt_modified varchar(100)  null,
    author       int           null,
    commentCount int default 0 null,
    readCount    int default 0 null,
    praiseCount  int default 0 null
);

