use dbmscoffee;

create table if not exists CUSTOMER
(Fname varchar(10) not null,
Lname varchar(10) not null,
num_id int(10) not null,
tier varchar(6) not null,
bday date not null)

create table if not exists EMPLOYEE
(emp_id varchar(10) not null,
pwd varchar(15) not null,
clock_in timestamp not null,
clock_out timestamp not null,
pay_rate decimal(3,2) not null,
SSN int(9) not null)

create table if not exists MANAGER
(emp_id varchar(10) not null,
pwd varchar(15) not null,
clock_in timestamp not null,
clock_out timestamp not null,
pay_rate decimal(3,2) not null,
SSN int(9) not null)

