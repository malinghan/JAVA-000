package com.someecho.shardingsphere.datasource.demo.service;

import com.someecho.shardingsphere.datasource.demo.entity.Order;

import java.sql.SQLException;
import java.util.List;

/**
 * @author : linghan.ma
 * @Package com.someecho.jdbc.demo.service
 * @Description:
 * @date Date : 2020年12月11日 12:00 AM
 **/
public interface IOrderService {
    
    /**
     * Initialize environment.
     *
     * @throws SQLException SQL exception
     */
    void initEnvironment();
    
    /**
     * Clean environment.
     *
     * @throws SQLException SQL exception
     */
    void cleanEnvironment();
    
    /**
     * Process success.
     *
     * @throws SQLException SQL exception
     */
    void processSuccess();
    
    
    /**
     * Process failure.
     *
     * @throws SQLException SQL exception
     */
    void processFailure();
    
    /**
     * Print data.
     *
     * @throws SQLException SQL exception
     */
    void printData();
    
    List<Order> selectLimit();
    
    List<Order> saveLimit();
}
