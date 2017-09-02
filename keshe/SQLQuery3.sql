--触发器
--删除备份
create trigger del on Student
for delete
as
begin
	declare @name varchar(20),@stuid int,@major varchar(20),@sex nchar(1),
	@grade varchar(20),@age int,@school varchar(20),@gratime datetime
	select @name=name,@stuid=stuid,@major=major,@sex=sex,@grade=grade,@age=age,
	@school=school,@gratime=gratime from deleted
	insert into Student2 values(@name,@stuid,@major,@sex,@grade,@age,@school,@gratime)
end


--存储过程
--USE Text
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create PROCEDURE stu_Insert
(
	@name varchar(20),
	@stuid int,
	@major varchar(20),
	@sex nchar(1),
	@grade varchar(20),
	@age int,
	@school varchar(20),
	@gratime varchar(20)
)
as
begin
	insert into Student values (@name,@stuid,@major,@sex,@grade,@age,@school,@gratime);
end
GO
create PROCEDURE stu_update
(
	@name varchar(20),
	@stuid int,
	@major varchar(20),
	@sex nchar(1),
	@grade varchar(20),
	@age int,
	@school varchar(20),
	@gratime varchar(20)
)
as
begin
	update Student set name=@name,stuid=@stuid,major=@major,sex=@sex,grade=@grade,age=@age,school=@school,gratime=@gratime
end
GO
create PROCEDURE stu_delete
(
	@stuid int
)
as
begin
	delete from Student where stuid=@stuid
end
GO

--公司信息存储过程
create PROCEDURE com_Insert
(
	@name varchar(20),
	@city varchar(20),
	@grade varchar(5)
)
as
begin
	insert into compary values (@name,@city,@grade);
end
GO
create PROCEDURE com_update
(
	@name varchar(20),
	@city varchar(20),
	@grade varchar(5)
)
as
begin
	update compary set name=@name,city=@city,grade=@grade
end
GO
create PROCEDURE com_delete
(
	@name varchar(20)
)
as
begin
	delete from compary where name=@name
end
GO

--公司需求信息存储过程
create PROCEDURE com_help_Insert
(
	@cname varchar(20),
	@ccity varchar(20),
	@need varchar(20),
	@num int,
	@ctime varchar(20)
)
as
begin
	insert into comhelp values (@cname,@ccity,@need,@num,@ctime);
end
GO
create PROCEDURE com_help_update
(
	@cname varchar(20),
	@ccity varchar(20),
	@need varchar(20),
	@num int,
	@ctime varchar(20)
)
as
begin
	update comhelp set cname=@cname,ccity=@ccity,need=@need,num=@num,ctime=@ctime
end
GO
create PROCEDURE com_help_delete
(
	@cname varchar(20)
)
as
begin
	delete from comhelp where cname=@cname
end

