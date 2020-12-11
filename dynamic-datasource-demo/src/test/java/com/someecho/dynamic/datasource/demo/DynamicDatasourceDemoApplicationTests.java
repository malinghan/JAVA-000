package com.someecho.dynamic.datasource.demo;

import com.someecho.dynamic.datasource.demo.service.IOrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DynamicDatasourceDemoApplicationTests {

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
