drop table if exists warehouse;

create table warehouse (
w_id smallint not null,
w_name varchar(75), 
w_street_1 varchar(20), 
w_street_2 varchar(20), 
w_city varchar(20), 
w_state char(2), 
w_zip char(9), 
w_tax decimal(4,2), 
w_ytd decimal(12,2),
primary key (w_id) ) Engine=InnoDB;

load data local infile '/tmp/warehouse.txt' into table warehouse;

select * from warehouse;
