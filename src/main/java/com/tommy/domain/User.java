package com.tommy.domain;

import java.io.Serializable;

/**
 * 功能描述：用户实体类
 *
 * 其中的必填数据为username和password
 *
 * coding and debug by tommy
 */

public class User implements Serializable {
    private Integer id;
    private String st_name;
    private Integer st_mark;
    private String st_mobile;
    private String username;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSt_name() {
        return st_name;
    }

    public void setSt_name(String st_name) {
        this.st_name = st_name;
    }

    public Integer getSt_mark() {
        return st_mark;
    }

    public void setSt_mark(Integer st_mark) {
        this.st_mark = st_mark;
    }

    public String getSt_mobile() {
        return st_mobile;
    }

    public void setSt_mobile(String st_mobile) {
        this.st_mobile = st_mobile;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", st_name='" + st_name + '\'' +
                ", st_mark=" + st_mark +
                ", st_mobile='" + st_mobile + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
