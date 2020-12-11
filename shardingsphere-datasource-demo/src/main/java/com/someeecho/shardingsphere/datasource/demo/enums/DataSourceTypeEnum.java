package com.someeecho.shardingsphere.datasource.demo.enums;

/**
 * @author captain
 * @description 数据源枚举
 * @date 2019-12-23 14:55
 */
public enum DataSourceTypeEnum {
    master("master"),
    slave("slave");
    private String value;
    
    DataSourceTypeEnum(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
}