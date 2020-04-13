package com.group2.model;

import java.util.Objects;

/**
 * @author Timothy
 */
public class UserCredentials {
    String login_name;
    String password;
    String email;

    /**
     * empty constructor
     */
    public UserCredentials() {
    }

    /**
     * constructor with full parameters
     * @param login_name username
     * @param password user's password
     * @param email user's email
     */
    public UserCredentials(String login_name, String password, String email) {
        this.login_name = login_name;
        this.password = password;
        this.email = email;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
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
        UserCredentials that = (UserCredentials) o;
        return getLogin_name().equals(that.getLogin_name()) &&
                getPassword().equals(that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPassword());

    }
}
