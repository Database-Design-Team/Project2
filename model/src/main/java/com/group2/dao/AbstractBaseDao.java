package com.group2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AbstractBaseDao {

    private static final String url = "jdbc:postgresql://ec2-54-165-36-134.compute-1.amazonaws.com:5432/dft5vu9ls8m9g2";
    private static final String user = "zgqjvuamulzrzb";
    private static final String password = "8853622559b8d9f06685388e005b737729ad3175006e14bd266677fcd44ab005";

    protected Connection conn;

    public AbstractBaseDao() throws SQLException {
        conn = DriverManager.getConnection(url, user, password);
    }

    
}
