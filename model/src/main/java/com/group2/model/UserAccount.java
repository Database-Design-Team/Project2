package com.group2.model;

/**
 * @author Timothy
 */
public class UserAccount {
    String username;
    int studentID;
    boolean isAdmin;

    /**
     * empty constructor, setting defaults
     */
    public UserAccount() {
        isAdmin = false;
    }

    /**
     * constructor with full parameters
     * @param username unique username
     * @param studentID user's University of Houston ID
     * @param isAdmin indicates if the user is an admin
     */
    public UserAccount(String username, int studentID, boolean isAdmin, String email) {
        this.username = username;
        this.studentID = studentID;
        this.isAdmin = isAdmin;
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
}
