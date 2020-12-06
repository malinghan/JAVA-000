# 用户商品订单的数据表结构

## 创建数据库

CREATE SCHEMA IF NOT EXISTS demo_ds;

# order

CREATE TABLE IF NOT EXISTS t_order
 (
   order_id BIGINT AUTO_INCREMENT comment '订单id',
   user_id INT NOT NULL comment '用户id',
   address_id BIGINT NOT NULL comment '地址id',
   status VARCHAR(50)  comment '订单状态',
   PRIMARY KEY (order_id)
 ) comment '订单表'  charset=utf8mb4;


# order_item

CREATE TABLE IF NOT EXISTS t_order_item
  (
   order_item_id BIGINT AUTO_INCREMENT comment '订单产品id',
   order_id BIGINT, user_id INT NOT NULL comment '订单id',
   status VARCHAR(50) ,
   PRIMARY KEY (order_item_id)
   )
   comment '商品表'  charset=utf8mb4;

# user

 CREATE TABLE IF NOT EXISTS t_user
 (
   user_id INT NOT NULL AUTO_INCREMENT comment '用户id',
   user_name VARCHAR(200) comment '用户姓名',
   user_name_plain VARCHAR(200) comment '用户姓名原文',
   pwd VARCHAR(200) comment '密码',
    assisted_query_pwd VARCHAR(200),
    PRIMARY KEY (user_id)
 )
 comment '用户表'  charset=utf8mb4;

# address

CREATE TABLE IF NOT EXISTS t_address
 (
  address_id BIGINT NOT NULL AUTO_INCREMENT comment '地址id',
  address_name VARCHAR(100) NOT NULL  comment '地址名称',
  PRIMARY KEY (address_id)
  )
  comment '地址表'  charset=utf8mb4;
