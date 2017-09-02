create database Text
go
use Text
--管理员
create table Users
(
	id int identity primary key,
	name varchar(20) unique not null,
	pwd varchar(20) not null
)
insert into Users values('admin','admin')
--select * from Users
--delete from Users
--大学生就职信息表
create table Student
(
	id int identity primary key,
	name varchar(20) not null,  --姓名
	stuid int not null,  --学号
	major varchar(20) not null,  --专业
	sex nchar(1) check(sex='男'or sex='女') default '男'  not null,  --性别
	grade varchar(20) not null,  --学历
	age int not null,  --年龄
	school varchar(20) not null, --学校
	gratime varchar(20) default '2000-00-00' not null  --毕业时间
)
select * from Student
go
--大学生就职信息删除备份表
create table Student2
(
	id int identity primary key,
	name varchar(20) not null,
	stuid int not null,
	major varchar(20) not null,
	sex nchar(2) check(sex='男'or sex='女') default '男'  not null,
	grade varchar(20) not null,
	age int not null,
	school varchar(20) not null,
	gratime varchar(20) default '2000-00-00' not null
)
go
--公司信息表
create table compary
(
	id int identity primary key,
	name varchar(20) unique not null,  --名称
	city varchar(20) not null,  --城市
	grade varchar(5) not null  --公司级别
)
select * from compary where name='百度'
select * from compary where name=name;
go
--公司需求表
create table comhelp
(
	id int identity primary key,
	cname varchar(20),  --公司名称
	ccity varchar(20),
	need varchar(20) not null,  --需求职位
	num int not null,  --需求数量
	ctime varchar(20) default '2000-00-00'  --发布时间
)
--select * from comhelp where cname='百度'

--联级更新与删除
alter table comhelp add constraint FK_classid foreign key (cname) references compary (name) on update cascade on delete cascade

go
--公司需求统计视图