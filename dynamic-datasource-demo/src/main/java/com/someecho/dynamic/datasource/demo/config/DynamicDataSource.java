package com.someecho.dynamic.datasource.demo.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author : linghan.ma
 * @Package com.someecho.dynamic.datasource.demo.config
 * @Description:
 * @date Date : 2020年12月11日 2:10 AM
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return  DataSourceHolder.getDbType();
    }
}