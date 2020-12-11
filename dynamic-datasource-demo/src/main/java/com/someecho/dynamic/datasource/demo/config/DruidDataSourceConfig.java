package com.someecho.dynamic.datasource.demo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.someecho.dynamic.datasource.demo.enums.DataSourceTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : linghan.ma
 * @Package com.someecho.dynamic.datasource.demo.config
 * @Description:
 * @date Date : 2020年12月11日 2:11 AM
 **/
@Configuration
public class DruidDataSourceConfig {
    
    static Logger logger = LoggerFactory.getLogger(DruidDataSourceConfig.class);
    
    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;
    
    @Bean(name = "master")
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    public DataSource master() {
        logger.info("-------------------- master init ---------------------");
        return DruidDataSourceBuilder.create().build();
    }
    
    @Bean(name = "slave")
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave")
    public DataSource slaveOne() {
        logger.info("-------------------- slave init ---------------------");
        return DruidDataSourceBuilder.create().build();
    }
    
    // slave 多个时，可进行负载（另行处理）
    
    @Bean
    @Primary
    public DataSource multipleDataSource(@Qualifier("master") DataSource master,
                                         @Qualifier("slave") DataSource slave) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceTypeEnum.master.getValue(), master);
        targetDataSources.put(DataSourceTypeEnum.slave.getValue(), slave);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(slave);
        return dynamicDataSource;
    }
    
}
