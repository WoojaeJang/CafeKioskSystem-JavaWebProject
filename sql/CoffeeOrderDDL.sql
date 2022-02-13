-- DDL

DROP TABLE coffee_order;
DROP TABLE pay;
DROP TABLE food;
DROP TABLE customer;

DROP SEQUENCE seq_no;

CREATE TABLE customer(
	cid varchar2(5) PRIMARY KEY,
	cpw varchar2(10),
	cname varchar2(10),
	phone_number varchar2(15),
	point number(6)
);

CREATE TABLE food(
	fid varchar2(5) PRIMARY KEY,
	fname varchar2(10) UNIQUE,
	price number(6)
);

CREATE TABLE pay(
	pid varchar2(5) PRIMARY KEY,
	pname varchar2(15) UNIQUE
);

CREATE TABLE coffee_order(
	order_no number(4) PRIMARY KEY,
	cid varchar2(5) CONSTRAINT order_cid_fk REFERENCES customer(cid),
	fname varchar2(10) CONSTRAINT order_fname_fk REFERENCES food(fname),
	payment number(6),
	pname varchar2(15),
	order_time timestamp,
	order_check varchar(5) CONSTRAINT order_check_ck CHECK(order_check IN ('check', 'done')) 
);

CREATE SEQUENCE seq_no;

COMMIT;