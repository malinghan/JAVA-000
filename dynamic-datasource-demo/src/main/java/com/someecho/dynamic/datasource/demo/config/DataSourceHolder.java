package com.someecho.dynamic.datasource.demo.config;

import com.someecho.dynamic.datasource.demo.enums.DataSourceTypeEnum;

/**
 * @author : linghan.ma
 * @Package com.someecho.dynamic.datasource.demo.config
 * @Description:
 * @date Date : 2020年12月11日 2:08 AM
 **/
public class DataSourceHolder {
    
    private static final ThreadLocal contextHolder = new ThreadLocal<>();
    
    /**
     * 设置数据源
     *
     * @param dbTypeEnum
     */
    public static void setDbType(DataSourceTypeEnum dbTypeEnum) {
        contextHolder.set(dbTypeEnum.getValue());
    }
    
    /**
     * 取得当前数据源
     *
     * @return
     */
    public static String getDbType() {
        return (String) contextHolder.get();
    }
    
    /**
     * 清除上下文数据
     */
    public static void clearDbType() {
        contextHolder.remove();
    }
    
}
