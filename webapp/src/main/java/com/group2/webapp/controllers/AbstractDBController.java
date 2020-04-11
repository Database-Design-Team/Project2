package com.group2.webapp.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AbstractDBController {
    private static final String url = "jdbc:postgresql://localhost/cosc3380projectdatabase";
    private static final String user = "postgres";
    private static final String password = "masterpassword";

    protected Connection conn;

    public AbstractDBController() {
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch(SQLException e) {
            e.getMessage();
        }
    }
}
