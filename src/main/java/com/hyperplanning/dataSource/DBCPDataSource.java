package com.hyperplanning.dataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBCPDataSource {
    private static BasicDataSource ds = new BasicDataSource();


    static {

        ds.setUrl("App.getProperties(\"daos.datasource.url\")");
        ds.setUsername("App.getProperties(\"daos.datasource.username\")");
        ds.setPassword("App.getProperties(\"daos.datasource.password\")");
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public DBCPDataSource(){ }
}
