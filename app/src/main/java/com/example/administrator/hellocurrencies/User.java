package com.example.administrator.hellocurrencies;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/7/12.
 */

public class User extends DataSupport {
    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
