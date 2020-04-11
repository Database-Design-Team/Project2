package com.group2.dao;

import com.group2.model.UserCredentials;

import java.sql.*;

public class UserCredentialsDao extends AbstractBaseDao {

    public UserCredentialsDao() throws SQLException {
        super();
    }

    public UserCredentials getUserCredentialsByLoginName(String login_name) throws SQLException {
        String SQL = "SELECT * FROM user_credentials WHERE login_name = ?";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1, login_name);
        ResultSet rs = ps.executeQuery();
        return new UserCredentials(rs.getString("login_name"), rs.getString("password"));
    }

}
