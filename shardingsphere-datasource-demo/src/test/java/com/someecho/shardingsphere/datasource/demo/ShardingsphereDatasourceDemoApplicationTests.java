package com.someecho.shardingsphere.datasource.demo;

import com.someecho.shardingsphere.datasource.demo.service.IOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShardingsphereDatasourceDemoApplicationTests {

    @Test
    void contextLoads() {
    }
    
    @Autowired
    IOrderService orderService;
    
    /**
     * chang to slave
     */
    @Test
    public void print(){
        orderService.selectLimit();
    }
    
    /**
     * change to master
     */
    @Test
    public void save(){
        orderService.saveLimit();
    }
}
