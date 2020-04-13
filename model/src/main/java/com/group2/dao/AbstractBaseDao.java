package com.group2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AbstractBaseDao {

    private static final String url = "jdbc:postgresql://ec2-18-235-20-228.compute-1.amazonaws.com:5432/df53l86ph193k";
    private static final String user = "uzulqijpicwbdd";
    private static final String password = "36980c9e2d2aa70d2fe6e8fc2623b2fa1346bf4bc4c8b2c563c289c72aa7e17d";

    protected Connection conn;

    public AbstractBaseDao() throws SQLException {
        conn = DriverManager.getConnection(url, user, password);
    }
}
