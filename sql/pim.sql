DROP DATABASE IF EXISTS pim2;

CREATE DATABASE pim2;
USE pim2;
#用户表
CREATE TABLE t_user(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`username` VARCHAR(30) NOT NULL,
	`password` VARCHAR(32) NOT NULL,
	`email` VARCHAR(200) 
);
INSERT INTO t_user(`username`,`password`,`email`) VALUES('admin','admin','admin@a163.com'); 
SELECT * FROM t_user;


#日常管理
DROP TABLE IF EXISTS t_daily;
CREATE TABLE t_daily(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`date` DATE,
	`place` VARCHAR(255),
	`description` VARCHAR(255),
	`user_id` INT,
	FOREIGN KEY(user_id) REFERENCES t_user(id)
);

INSERT INTO t_daily(`date`,`place`,`description`,`user_id`) VALUES('2020-1-2','天河南路1号','开会',1);
INSERT INTO t_daily(`date`,`place`,`description`,`user_id`) VALUES('2020-1-3','天河南路1号','聚餐',1);
INSERT INTO t_daily(`date`,`place`,`description`,`user_id`) VALUES('2020-1-4','天河南路1号','跑步',1);
INSERT INTO t_daily(`date`,`place`,`description`,`user_id`) VALUES('2020-1-5','天河南路1号','旅游',1);
INSERT INTO t_daily(`date`,`place`,`description`,`user_id`) VALUES('2020-1-6','天河南路1号','去图书馆',1);
INSERT INTO t_daily(`date`,`place`,`description`,`user_id`) VALUES('2020-1-7','天河南路1号','出差',1);

INSERT INTO t_daily(`date`,`place`,`description`,`user_id`) VALUES('2020-1-2','天河南路1号','开会',2);
INSERT INTO t_daily(`date`,`place`,`description`,`user_id`) VALUES('2020-1-3','天河南路1号','聚餐',2);
INSERT INTO t_daily(`date`,`place`,`description`,`user_id`) VALUES('2020-3-4','天河南路1号','跑步',2);
INSERT INTO t_daily(`date`,`place`,`description`,`user_id`) VALUES('2020-4-5','天河南路1号','旅游',2);
INSERT INTO t_daily(`date`,`place`,`description`,`user_id`) VALUES('2020-6-6','天河南路1号','去图书馆',2);
INSERT INTO t_daily(`date`,`place`,`description`,`user_id`) VALUES('2020-8-7','天河南路1号','出差',2);
INSERT INTO t_daily(`date`,`place`,`description`,`user_id`) VALUES('2020-8-7','天河南路1号','出差',3);

# 账务分类表
DROP TABLE IF EXISTS t_sort;
CREATE TABLE t_sort(
	id INT PRIMARY KEY AUTO_INCREMENT,   -- 分类ID
	sname VARCHAR(100),                   -- 分类名称
	parent CHAR(2),                       -- 所属父类
	uid INT,
	FOREIGN KEY(uid) REFERENCES t_user(id)	
);

INSERT INTO t_sort(sname,parent,uid) 
VALUES('服装支出','支出',1),
('吃饭支出','支出',1),
('交通支出','支出',1),
('住房支出','支出',1),
('工资收入','收入',1),
('股票收入','收入',1),
('礼金支出','支出',1),
('其它支出','支出',1);




# 账务表
DROP TABLE IF EXISTS t_bill;
CREATE TABLE t_bill(
	id INT PRIMARY KEY AUTO_INCREMENT,     -- 账务ID
	description VARCHAR(1000),             -- 账务描述
	money DOUBLE,                           -- 金额
	source VARCHAR(100),                    -- 来源  
	sid INT,                                -- 分类ID                                           
	parent CHAR(2),                         -- 所属父类                       
	ctime DATE,                             -- 创建时间
	uid INT,                                -- 用户ID
	FOREIGN KEY(sid) REFERENCES t_sort(id) ON DELETE CASCADE,
	FOREIGN KEY(uid) REFERENCES t_user(id)                   	
);

INSERT INTO t_bill(description,money,source,sid,parent,ctime,uid)
VALUES('家庭聚餐','247','交通银行',2,'支出','2019-03-15','1'),
('发工资','247','现金',5,'收入','2019-03-15','1'),
('买衣服','247','现金',1,'支出','2019-03-15','1'),
('朋友聚餐','247','现金',2,'支出','2019-03-15','1'),
('股票大涨','247','工商银行',6,'收入','2019-07-15','1'),
('股票又大涨','247','工商银行',6,'收入','2019-05-15','1'),
('发工资','247','交通银行',5,'支出','2019-8-14','1'),
('朋友结婚','247','建设银行',7,'支出','2019-06-10','1'),
('丢钱','247','现金',8,'支出','2019-07-10','1'),
('油价上涨','247','建设银行',3,'支出','2019-08-15','1'),
('吃饭','247','建设银行',2,'支出','2019-06-15','1'),
('发工资','247','建设银行',5,'收入','2019-05-15','1'),
('买机票','247','现金',3,'支出','2019-04-15','1'),
('发工资','247','现金',5,'收入','2019-01-15','1');

SELECT * FROM t_sort;
SELECT * FROM t_bill;

# 密码管理表
DROP TABLE IF EXISTS t_password;
CREATE TABLE t_password(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`pname` VARCHAR(50),           -- 密码对应的网站/App等
	`pass` VARCHAR(50),           -- 对应的密码
	`uid` INT,                    -- 所属用户
	FOREIGN KEY(uid) REFERENCES t_user(id)

);

INSERT INTO t_password(`pname`,`pass`,`uid`) 
VALUES('b站','12345678','1'),
('facebook','12345678','1'),
('ins','12345678','1'),
('淘宝','12345678','1');

SELECT * FROM t_password;

