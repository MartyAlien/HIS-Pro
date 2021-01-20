package com.hrbu.his.bean;

/**
 * @ClassName: User
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/15 14:53
 */
public class User {
    private String name;
    private String username;
    private String password;


    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
