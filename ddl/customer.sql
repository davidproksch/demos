drop table if exists customer;

create table customer (
c_id tinyint not null,
c_d_id smallint not null,
c_w_id smallint not null,
c_first_name  varchar(100),
c_last_name  varchar(100),
c_street varchar(100),
c_city varchar(20),
c_state char(2),
c_zip char(9),
primary key (c_id, c_w_id, c_d_id) ) Engine=InnoDB;

load data local infile '/tmp/customer.txt' into table customer;

select * from customer;
