drop table customer if exists;
create table customer (
	customer_id varchar(6) not null primary key,
	last_name varchar(20) not null,
	first_name varchar(20) not null,
	mail_address varchar(256)
)