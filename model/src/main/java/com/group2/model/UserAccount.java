package com.group2.model;

import org.jetbrains.annotations.Contract;
import java.sql.Date;
import java.util.Objects;

/**
 * @author Timothy
 */
public class UserAccount {
    String username;
    int studentID;
    boolean isAdmin;
    String password;
    String email;
    Date dateJoined;

    /**
     * empty constructor, setting defaults
     */
    @Contract(pure = true)
    public UserAccount() {
        isAdmin = false;
    }

    /**
     * constructor with full parameters
     * @param username unique username
     * @param student_id user's University of Houston ID
     * @param admin indicates if the user is an admin
     * @param password user's password
     * @param email user's email
     */
    @Contract(pure = true)
    public UserAccount(String username, int student_id, boolean admin, String password, String email) {
        this.username = username;
        this.studentID = student_id;
        this.isAdmin = admin;
        this.password = password;
        this.email = email;
    }

    /**
     * constructor with non-default parameters
     * @param username the username of the user's account
     * @param studentID the user's UH student ID number
     * @param password the user's password
     * @param email the user's email
     */
    @Contract(pure = true)
    public UserAccount(String username, int studentID, String password, String email) {
        this.username = username;
        this.studentID = studentID;
        this.password = password;
        this.email = email;
    }

    /**
     * constructor with full parameters
     * @param username the username of the user's account
     * @param studentID the user's UH student ID number
     * @param isAdmin specifies if the account belongs to an administrator
     * @param password the user's password
     * @param email the user's email
     * @param dateJoined the date the account was created
     */
    @Contract(pure = true)
    public UserAccount(String username, int studentID, boolean isAdmin, String password, String email, Date dateJoined) {
        this.username = username;
        this.studentID = studentID;
        this.isAdmin = isAdmin;
        this.password = password;
        this.email = email;
        this.dateJoined = dateJoined;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        this.isAdmin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDate(Date date_joined) {
        this.dateJoined = date_joined;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return getUsername().equals(that.getUsername()) &&
                getPassword().equals(that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPassword());

    }
}
