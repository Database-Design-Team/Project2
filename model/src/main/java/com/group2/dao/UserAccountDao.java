package com.group2.dao;

import com.group2.model.UserAccount;
import org.apache.coyote.http11.filters.SavedRequestInputFilter;
import org.jetbrains.annotations.NotNull;

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
        UserAccount uc = new UserAccount(rs.getString("username"), rs.getInt("student_id"), rs.getBoolean("admin"),
                rs.getString("password"), rs.getString("email"), rs.getDate("date_joined"));
        rs.close();
        ps.close();
        return uc;
    }

    public void addUserAccount(@NotNull UserAccount uc) throws SQLException {
        String SQL = "INSERT INTO user_account(username, student_id, admin, password, email) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1, uc.getUsername());
        ps.setInt(2, uc.getStudentID());
        ps.setBoolean(3, uc.isAdmin());
        ps.setString(4, uc.getPassword());
        ps.setString(5, uc.getEmail());
        ps.executeUpdate();
        ps.close();
    }

    public void addUserAccount(@NotNull List<UserAccount> ucList) throws SQLException {
        for (UserAccount u : ucList) {
            addUserAccount(u);
        }
    }
    
    /**
      * This function updates the password of a given user
      * @param username the user who's password it getting changed
      * @param password the user's new password
      * @throws SQLException on errors interacting with the database
      */
      public void updatePassword(String username, String password) throws SQLException {
        String SQL = "UPDATE user_account " +
                "SET password = ? " +
                "WHERE username = ?";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1, password);
        ps.setString(2, username);
        ps.executeQuery();
        ps.close();
    }

    /**
     * This function deleted a specified account
     * @param username The username of the account getting deleted
     * @throws SQLException on errors interacting with the database
     */
    public void deleteAccount(String username) throws SQLException {
        String SQL = "DELETE FROM user_account WHERE username = ?";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1, username);
        ps.executeQuery();
        ps.close();
    }
}
