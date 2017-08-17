/*************************************************
     北京启明星辰信息安全技术有限公司版权所有
  Copyright (C), 1996-2017, Venustech.

  File name: DriverName.java  
  Author: Asder(yifei.wu) 
  Version: 1.0 
  Date: 2017-08-16 21:08:27
  Description: 
*************************************************/
package com.asder.tool.generator.mybatisplus;

/**
 * 数据库JDBC驱动名称定义
 *
 * @author asder
 * @since 2017/8/16
 */
public enum DriverName {

    MYSQL("com.mysql.jdbc.Driver"), ORACLE("oracle.jdbc.driver.OracleDriver"), SQL_SERVER("com.microsoft.jdbc.sqlserver.SQLServerDriver"), POSTGRE_SQL("org.postgresql.Driver");

    private final String value;

    DriverName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}