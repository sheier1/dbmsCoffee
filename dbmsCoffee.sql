use dbmscoffee;

create table if not exists PERSON
(Fname varchar(10) not null,
Lname varchar(10) not null,
num_id int(10) not null,
primary key(num_id));

create table if not exists CUSTOMER
(tier varchar(6) not null,
bday date not null,
cust_id int(10) not null,
foreign key(cust_id) references PERSON(num_id));

create table if not exists EMPLOYEE
(emp_id int(10) not null,
pwd varchar(15) not null,
clock_in timestamp not null,
clock_out timestamp not null,
pay_rate decimal(3,2) not null,
SSN int(9) not null,
foreign key(emp_id) references PERSON(num_id));

create table if not exists MANAGER
(man_id int(10) not null,
pwd varchar(15) not null,
clock_in timestamp not null,
clock_out timestamp not null,
pay_rate decimal(3,2) not null,
SSN int(9) not null,
foreign key(man_id) references PERSON(num_id));

create table if not exists PRODUCTS 
(items varchar(15) not null,
primary key (items));

create table if not exists INVENTORY
(items varchar(15) not null,
qty int(6) not null,
expDate date not null, 
foreign key (items) references PRODUCTS(items));

