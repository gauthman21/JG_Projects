create database test_projects;
use test_projects;
create table test_project_status
(ProjectID INT,
Project_Name varchar(24),
Assigned varchar(24),
Priority varchar(24),
Status varchar(10));

create table pUser
(UserID INT,
UserLogin varchar(18),
UserPass varchar(18),
UserName varchar(24));

create table pAdmin
(AdminID INT,
AdminLogin varchar(18),
AdminPass varchar(18));

alter table pAdmin
add constraint pk_pAdmin primary key (AdminID);

alter table pUser
add constraint pk_pUser primary key (UserID);

insert into pUser(UserID, UserLogin, UserPass, UserName) values (1, "john_breen", "root", "John Breen");

insert into pUser(UserID, UserLogin, UserPass, UserName)
values (1000, "justin_gauthier", "notroot", "Justin Gauthier");