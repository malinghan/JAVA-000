# 用户商品订单的数据表结构


# order

CREATE TABLE IF NOT EXISTS t_order
 (
   order_id BIGINT AUTO_INCREMENT,
   user_id INT NOT NULL,
   address_id BIGINT NOT NULL,
   status VARCHAR(50),
   PRIMARY KEY (order_id)
 );


# order_item

CREATE TABLE IF NOT EXISTS t_order_item
  (
   order_item_id BIGINT AUTO_INCREMENT,
   order_id BIGINT, user_id INT NOT NULL,
   status VARCHAR(50) ,
   PRIMARY KEY (order_item_id)
   );

# user

 CREATE TABLE IF NOT EXISTS t_user
 (
   user_id INT NOT NULL AUTO_INCREMENT,
   user_name VARCHAR(200),
   user_name_plain VARCHAR(200),
   pwd VARCHAR(200),
    assisted_query_pwd VARCHAR(200),
    PRIMARY KEY (user_id)
 );

# product

# address

CREATE TABLE IF NOT EXISTS t_address
 (
  address_id BIGINT NOT NULL,
  address_name VARCHAR(100) NOT NULL,
  PRIMARY KEY (address_id)
  );
