create database Text
go
use Text
--����Ա
create table Users
(
	id int identity primary key,
	name varchar(20) unique not null,
	pwd varchar(20) not null
)
insert into Users values('admin','admin')
--select * from Users
--delete from Users
--��ѧ����ְ��Ϣ��
create table Student
(
	id int identity primary key,
	name varchar(20) not null,  --����
	stuid int not null,  --ѧ��
	major varchar(20) not null,  --רҵ
	sex nchar(1) check(sex='��'or sex='Ů') default '��'  not null,  --�Ա�
	grade varchar(20) not null,  --ѧ��
	age int not null,  --����
	school varchar(20) not null, --ѧУ
	gratime varchar(20) default '2000-00-00' not null  --��ҵʱ��
)
select * from Student
go
--��ѧ����ְ��Ϣɾ�����ݱ�
create table Student2
(
	id int identity primary key,
	name varchar(20) not null,
	stuid int not null,
	major varchar(20) not null,
	sex nchar(2) check(sex='��'or sex='Ů') default '��'  not null,
	grade varchar(20) not null,
	age int not null,
	school varchar(20) not null,
	gratime varchar(20) default '2000-00-00' not null
)
go
--��˾��Ϣ��
create table compary
(
	id int identity primary key,
	name varchar(20) unique not null,  --����
	city varchar(20) not null,  --����
	grade varchar(5) not null  --��˾����
)
select * from compary where name='�ٶ�'
select * from compary where name=name;
go
--��˾�����
create table comhelp
(
	id int identity primary key,
	cname varchar(20),  --��˾����
	ccity varchar(20),
	need varchar(20) not null,  --����ְλ
	num int not null,  --��������
	ctime varchar(20) default '2000-00-00'  --����ʱ��
)
--select * from comhelp where cname='�ٶ�'

--����������ɾ��
alter table comhelp add constraint FK_classid foreign key (cname) references compary (name) on update cascade on delete cascade

go
--��˾����ͳ����ͼ