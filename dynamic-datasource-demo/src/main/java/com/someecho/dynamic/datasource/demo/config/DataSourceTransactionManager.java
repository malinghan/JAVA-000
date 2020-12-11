package com.someecho.dynamic.datasource.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author : linghan.ma
 * @Package com.someecho.dynamic.datasource.demo.config
 * @Description:
 * @date Date : 2020年12月11日 2:13 AM
 **/
public class DataSourceTransactionManager extends DataSourceTransactionManagerAutoConfiguration {
    
    static Logger logger = LoggerFactory.getLogger(DataSourceTransactionManager.class);
    
    @Resource(name = "master")
    private DataSource dataSource;
    
    /**
     * 自定义事务
     * MyBatis自动参与到spring事务管理中，无需额外配置，只要org.mybatis.spring.SqlSessionFactoryBean引用的数据源与DataSourceTransactionManager引用的数据源一致即可，否则事务管理会不起作用。
     *
     * @return
     */
    @Bean(name = "transactionManager")
    public org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManagers() {
        logger.info("-------------------- transactionManager init ---------------------");
        return new org.springframework.jdbc.datasource.DataSourceTransactionManager(dataSource);
    }
    
}
