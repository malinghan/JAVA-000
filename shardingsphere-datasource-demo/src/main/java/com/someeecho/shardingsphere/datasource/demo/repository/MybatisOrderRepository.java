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

package com.someeecho.shardingsphere.datasource.demo.repository;

import com.someecho.dynamic.datasource.demo.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface MybatisOrderRepository{
    
    List<Order> selectLimit();
    
    /**
     * Create table if not exist.
     *
     * @throws SQLException SQL exception
     */
    void createTableIfNotExists();
    
    /**
     * Drop table.
     *
     * @throws SQLException SQL exception
     */
    void dropTable();
    
    /**
     * Truncate table.
     *
     * @throws SQLException SQL exception
     */
    void truncateTable();
    
    /**
     * insert data.
     *
     * @param entity entity
     * @return generated primary key
     * @throws SQLException SQL exception
     */
    void insert(Order entity);
    
    /**
     * Delete data.
     *
     * @param primaryKey primaryKey
     * @throws SQLException SQL exception
     */
    void delete(Long primaryKey);
    
    /**
     * Select all data.
     *
     * @return all data
     * @throws SQLException SQL exception
     */
    List<Order> selectAll();
}
