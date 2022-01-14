package com.hyperplanning.dataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBCPDataSource {
    private static BasicDataSource ds = new BasicDataSource();

    static {

        //ds.setUrl(App.getProperty("daos.datasource.url"));
        ds.setUrl("jdbc:mariadb://localhost:3306/hyperplanning");
        ds.setUsername("root");
        //ds.setUsername(App.getProperty("daos.datasource.username"));
        ds.setPassword("");
        //ds.setPassword(App.getProperty("daos.datasource.password"));
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }

    public static Connection getConnection() throws SQLException {
       
        return ds.getConnection();
    }

    private DBCPDataSource(){ }


}
