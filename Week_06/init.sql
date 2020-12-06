# 1.user
DROP PROCEDURE  IF EXISTS user_init;
delimiter $
CREATE PROCEDURE user_init()
BEGIN
    DECLARE i INT DEFAULT 1;
    set autocommit=0;
    WHILE i < 100 DO
        set autocommit = 1;
        insert into demo_ds.t_user(user_name,user_name_plain,pwd,assisted_query_pwd)
        values (CONCAT("user",i),CONCAT("user_plain",i),"password","password");
        SET i =i + 1;
    END WHILE;
    commit ;
END $
CALL user_init();

# 2.address

DROP PROCEDURE  IF EXISTS address_init;
delimiter $
CREATE PROCEDURE address_init()
BEGIN
    DECLARE i INT DEFAULT 1;
    set autocommit=0;
    WHILE i < 100 DO
        set autocommit = 1;
        insert into demo_ds.t_address(address_name)
        values (CONCAT("address_name",i));
        SET i =i + 1;
    END WHILE;
    commit ;
END $
CALL address_init();

# 3.order_item
DROP PROCEDURE  IF EXISTS order_item_init;
delimiter $
CREATE PROCEDURE order_item_init()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i < 100000 DO
        set autocommit = 1;
        insert into demo_ds.t_order_item(order_id,user_id,status)
        values (i,CEILING(rand()*100),0);
       SET i =i + 1;
    END WHILE;
END $
CALL order_item_init();

# 4.order

DROP PROCEDURE  IF EXISTS order_init;
DELIMITER $
CREATE PROCEDURE order_init()
BEGIN
    DECLARE i INT DEFAULT 1;
    set autocommit=0;
    WHILE i < 1000000 DO
        set autocommit = 1;
        insert into demo_ds.t_order(user_id,address_id,status)
        values (CEILING(rand()*100),CEILING(rand()*100),0);
       SET i =i + 1;
    END WHILE;
    commit ;
END $
CALL order_init();
