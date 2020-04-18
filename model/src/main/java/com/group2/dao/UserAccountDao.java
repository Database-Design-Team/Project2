package com.group2.dao;

import com.group2.model.UserAccount;

import java.sql.*;
import java.util.List;

public class UserAccountDao extends AbstractBaseDao {

    public UserAccountDao() throws SQLException {
        super();
    }

    public UserAccount getUserAccountByLoginName(String username) throws SQLException {
        String SQL = "SELECT * FROM user_account WHERE username = ?";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return new UserAccount(rs.getString("username"), rs.getInt("student_id"), rs.getBoolean("admin"), rs.getString("password"), rs.getString("email"));
    }

    public void addUserAccount(UserAccount uc) throws SQLException {
        String SQL = "INSERT INTO user_account(username, student_id, admin, password, email) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1, uc.getUsername());
        ps.setInt(2, uc.getStudent_id());
        ps.setBoolean(3, uc.isAdmin());
        ps.setString(4, uc.getPassword());
        ps.setString(5, uc.getEmail());
        ps.executeUpdate();
    }

    public void addUserAccount(List<UserAccount> ucList) throws SQLException {
        for(UserAccount u : ucList) {
            addUserAccount(u);
        }
    }



}