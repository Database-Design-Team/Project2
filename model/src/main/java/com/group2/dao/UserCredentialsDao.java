package com.group2.dao;

import com.group2.model.UserCredentials;

import java.sql.*;
import java.util.List;

public class UserCredentialsDao extends AbstractBaseDao {

    public UserCredentialsDao() throws SQLException {
        super();
    }

    public UserCredentials getUserCredentialsByLoginName(String login_name) throws SQLException {
        String SQL = "SELECT * FROM user_credentials WHERE login_name = ?";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1, login_name);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return new UserCredentials(rs.getString("login_name"), rs.getString("password"));
    }

    public void addUserCredentials(UserCredentials uc) throws SQLException {
        String SQL = "INSERT INTO user_credentials(login_name, password) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1, uc.getLogin_name());
        ps.setString(2, uc.getPassword());
        ps.executeUpdate();
    }

    public void addUserCredentials(List<UserCredentials> ucList) throws SQLException {
        for(UserCredentials u : ucList) {
            addUserCredentials(u);
        }
    }



}
