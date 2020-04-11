package com.group2.model;

/**
 * @author Timothy
 */
public class UserAccount {
    String username;
    int student_id;
    boolean admin;

    /**
     * empty constructor, setting defaults
     */
    public UserAccount() {
        admin = false;
    }

    /**
     * constructor with full parameters
     * @param username unique username
     * @param student_id user's University of Houston ID
     * @param admin indicates if the user is an admin
     */
    public UserAccount(String username, int student_id, boolean admin) {
        this.username = username;
        this.student_id = student_id;
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
