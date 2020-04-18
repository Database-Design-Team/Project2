package com.group2.model;

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

    /**
     * empty constructor, setting defaults
     */
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
    public UserAccount(String username, int student_id, boolean admin, String password, String email) {
        this.username = username;
        this.studentID = student_id;
        this.isAdmin = admin;
        this.password = password;
        this.email = email;
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
