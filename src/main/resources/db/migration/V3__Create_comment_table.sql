create table comment
(
	id int auto_increment,
	parentId BIGINT not null,
	type int null,
	commentator int not null comment '评论者id',
	createdAt varchar(200) null comment '评论时间',
	modifiedAt varchar(200) null comment '更新评论时间',
	praiseCount int default 0 null,
	column_8 int null,
	constraint comment_pk
		primary key (id)
);

