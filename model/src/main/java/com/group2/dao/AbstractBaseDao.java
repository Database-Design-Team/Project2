package com.group2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AbstractBaseDao {

    private static final String url = "jdbc:postgresql://localhost/cosc3380projectdatabase";
    private static final String user = "postgres";
    private static final String password = "masterpassword";

    protected Connection conn;

    public AbstractBaseDao() throws SQLException {
        conn = DriverManager.getConnection(url, user, password);
    }
}
