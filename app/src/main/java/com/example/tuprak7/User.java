package com.example.tuprak7;

public class User {
    String username;
    String password;
    boolean isLogin;
    boolean rememberMe;

    public User(String username, String password, boolean isLogin, boolean rememberMe) {
        this.username = username;
        this.password = password;
        this.isLogin = isLogin;
        this.rememberMe = rememberMe;
    }

    User(String username, String password) {
        this.username = username;
        this.password = password;
        this.isLogin = true;
        this.rememberMe = true;
    }

    User() {}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean checkUser(User user) {
        if (user == null) {
            return false;
        }
        return this.username.equals(user.getUsername()) && this.password.equals(user.getPassword());
    }

    public void setLogin(boolean login) {
        this.isLogin = login;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", login=" + isLogin +
                ", rememberMe=" + rememberMe +
                '}';
    }
}
