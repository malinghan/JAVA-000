package com.someecho.jdbc.demo.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

/**
 * @author : linghan.ma
 * @Package com.someecho.jdbc.demo.config
 * @Description:
 * @date Date : 2020年12月10日 11:53 PM
 **/
public class MyRoutingDataSource extends AbstractRoutingDataSource {
    
    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.get();
    }
    
}
