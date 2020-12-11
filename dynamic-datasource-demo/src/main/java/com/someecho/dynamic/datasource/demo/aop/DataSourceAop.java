package com.someecho.dynamic.datasource.demo.aop;

import com.someecho.dynamic.datasource.demo.config.DataSourceHolder;
import com.someecho.dynamic.datasource.demo.enums.DataSourceTypeEnum;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author : linghan.ma
 * @Package com.someecho.dynamic.datasource.demo.aop
 * @Description:
 * @date Date : 2020年12月11日 2:08 AM
 **/
@Aspect
@Component
public class DataSourceAop {
    static Logger logger = LoggerFactory.getLogger(DataSourceAop.class);
    
    @Before("execution(* com.someecho.dynamic.datasource.demo.service.*.insert*(..)) ||" +
            " execution(* com.someecho.dynamic.datasource.demo.service.*.update*(..)) || " +
            "execution(* com.someecho.dynamic.datasource.demo.service.*.delete*(..))")
    public void setWriteDataSourceType() {
        DataSourceHolder.setDbType(DataSourceTypeEnum.master);
        logger.info("change -------- write ------------");
    }
    
    @Before("execution(* com.someecho.dynamic.datasource.demo.service.*.select*(..)) ||" +
            " execution(* com.someecho.dynamic.datasource.demo.service.*.count*(..))")
    public void setReadDataSourceType() {
        DataSourceHolder.setDbType(DataSourceTypeEnum.slave);
        logger.info("change -------- read ------------");
    }
    
}
