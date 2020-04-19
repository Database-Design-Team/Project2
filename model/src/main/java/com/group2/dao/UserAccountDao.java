package com.group2.dao;

import com.group2.model.UserAccount;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.List;

public class UserAccountDao extends AbstractBaseDao {

    public UserAccountDao() throws SQLException {
        super();
    }

    /**
     * gets an account record from the database
     * @param username the login name of the account being searched for
     * @return the user account with the requested username
     * @throws SQLException on errors interacting with the database
     */
    public JSONArray getUserAccountByLoginName(String username) throws SQLException {
        String SQL = "SELECT * FROM user_account WHERE username = ?";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        return getJsonArray(rs);
    }

    /**
     * adds an account to the database
     * @param uc the {@code user_account} object representing the attributes of the account
     * @throws SQLException on errors interacting with the database
     */
    public void addUserAccount(@NotNull UserAccount uc) throws SQLException {
        String SQL = "INSERT INTO user_account(username, student_id, admin, password, email) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1, uc.getUsername());
        ps.setInt(2, uc.getStudentID());
        ps.setBoolean(3, uc.isAdmin());
        ps.setString(4, uc.getPassword());
        ps.setString(5, uc.getEmail());
        ps.executeUpdate();
    }

    /**
     * this function adds a list of accounts
     * @param ucList a {@code List} of user accounts to be added
     * @throws SQLException on errors interacting with the database
     */
    public void addUserAccount(@NotNull List<UserAccount> ucList) throws SQLException {
        for(UserAccount u : ucList) {
            addUserAccount(u);
        }
    }
}
