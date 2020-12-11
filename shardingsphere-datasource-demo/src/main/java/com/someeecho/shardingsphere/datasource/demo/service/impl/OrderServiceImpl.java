package com.someeecho.shardingsphere.datasource.demo.service.impl;

import com.someecho.dynamic.datasource.demo.repository.MybatisAddressRepository;
import com.someecho.dynamic.datasource.demo.repository.MybatisOrderItemRepository;
import com.someecho.dynamic.datasource.demo.repository.MybatisOrderRepository;
import com.someecho.dynamic.datasource.demo.service.IOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    
    @Resource
    private MybatisOrderRepository orderRepository;
    
    @Resource
    private MybatisOrderItemRepository orderItemRepository;
    
    @Resource
    private MybatisAddressRepository addressRepository;

    @Override
    public void initEnvironment(){
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
    private void initAddressTable()  {
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
    public void cleanEnvironment()  {
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
    public void processSuccess(){
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
    public void processFailure() {
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
    private List<Long> insertData() {
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
    private void deleteData(final List<Long> orderIds){
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
    public void printData(){
        System.out.println("---------------------------- Print Order Data -----------------------");
        for (Object each : orderRepository.selectAll()) {
            System.out.println(each);
        }
        System.out.println("---------------------------- Print OrderItem Data -------------------");
        for (Object each : orderItemRepository.selectAll()) {
            System.out.println(each);
        }
    }
    
    @Override
    public List<Order> selectLimit() {
        for (Object each : orderRepository.selectLimit()) {
            System.out.println(each);
        }
        return orderRepository.selectLimit();
    }
    
    @Override
    public List<Order> saveLimit() {
        for (Object each : orderRepository.selectLimit()) {
            System.out.println(each);
        }
        return orderRepository.selectLimit();
    }
}
