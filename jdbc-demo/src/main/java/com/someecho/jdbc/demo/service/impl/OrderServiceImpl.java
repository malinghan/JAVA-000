/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.someecho.jdbc.demo.service.impl;

import com.someecho.jdbc.demo.ExampleService;
import com.someecho.jdbc.demo.entity.Address;
import com.someecho.jdbc.demo.entity.Order;
import com.someecho.jdbc.demo.entity.OrderItem;
import com.someecho.jdbc.demo.core.repository.AddressRepository;
import com.someecho.jdbc.demo.core.repository.OrderItemRepository;
import com.someecho.jdbc.demo.core.repository.OrderRepository;
import com.someecho.jdbc.demo.service.IOrderService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class OrderServiceImpl implements ExampleService,IOrderService {
    
    @Resource
    private OrderRepository orderRepository;
    
    @Resource
    private OrderItemRepository orderItemRepository;
    
    @Resource
    private AddressRepository addressRepository;

    @Override
    public void initEnvironment() throws SQLException {
        //创建表
        orderRepository.createTableIfNotExists();
        orderItemRepository.createTableIfNotExists();
        
        //清空表
        orderRepository.truncateTable();
        orderItemRepository.truncateTable();
        
        //初始化地址表
        initAddressTable();
    }
    
    
    /**
     * 初始化地址表
     * 1. 创建表
     * 2. 清空表数据
     * 3. 初始化10条数据
     * @throws SQLException
     */
    private void initAddressTable() throws SQLException {
        addressRepository.createTableIfNotExists();
        addressRepository.truncateTable();
        for (int i = 1; i <= 10; i++) {
            Address entity = new Address();
            entity.setAddressId((long) i);
            entity.setAddressName("address_" + i);
            addressRepository.insert(entity);
        }
    }
    
    
    /**
     * 删除表
     * @throws SQLException
     */
    @Override
    public void cleanEnvironment() throws SQLException {
        orderRepository.dropTable();
        orderItemRepository.dropTable();
    }
    
    /**
     * 1. 插入数据
     * 2. 查询数据
     * 3. 删除数据
     * 4. 查询数据
     * @throws SQLException
     */
    @Override
    @Transactional
    public void processSuccess() throws SQLException {
        System.out.println("-------------- Process Success Begin ---------------");
        List<Long> orderIds = insertData();
        printData();
        deleteData(orderIds);
        printData();
        System.out.println("-------------- Process Success Finish --------------");
    }
    
    
    /**
     * 1. 插入数据
     * 2. 报错
     * @throws SQLException
     */
    @Override
    @Transactional
    public void processFailure() throws SQLException {
        System.out.println("-------------- Process Failure Begin ---------------");
        insertData();
        System.out.println("-------------- Process Failure Finish --------------");
        throw new RuntimeException("Exception occur for transaction test.");
    }
    
    
    /**
     * 插入数据的方法
     * @return
     * @throws SQLException
     */
    private List<Long> insertData() throws SQLException {
        System.out.println("---------------------------- Insert Data ----------------------------");
        List<Long> result = new ArrayList<>(10);
        for (int i = 1; i <= 10; i++) {
            
            Order order = new Order();
            order.setUserId(i);
            order.setAddressId(i);
            order.setStatus("INSERT_TEST");
            orderRepository.insert(order);
            
            
            OrderItem item = new OrderItem();
            item.setOrderId(order.getOrderId());
            item.setUserId(i);
            item.setStatus("INSERT_TEST");
            orderItemRepository.insert(item);
            result.add(order.getOrderId());
        }
        return result;
    }
    
    /**
     * 删除数据
     * @param orderIds
     * @throws SQLException
     */
    private void deleteData(final List<Long> orderIds) throws SQLException {
        System.out.println("---------------------------- Delete Data ----------------------------");
        for (Long each : orderIds) {
            orderRepository.delete(each);
            orderItemRepository.delete(each);
        }
    }
    
    /**
     * 查询数据
     * @throws SQLException
     */
    @Override
    public void printData() throws SQLException {
        System.out.println("---------------------------- Print Order Data -----------------------");
        for (Object each : orderRepository.selectAll()) {
            System.out.println(each);
        }
        System.out.println("---------------------------- Print OrderItem Data -------------------");
        for (Object each : orderItemRepository.selectAll()) {
            System.out.println(each);
        }
    }
}
