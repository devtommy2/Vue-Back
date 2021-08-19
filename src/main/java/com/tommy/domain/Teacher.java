package com.tommy.domain;

/**
 * coding and debug by tommy
 */

public class Teacher {

    private String username;
    private String st_name;
    private String st_mobile;
    private Integer id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSt_name() {
        return st_name;
    }

    public void setSt_name(String st_name) {
        this.st_name = st_name;
    }

    public String getSt_mobile() {
        return st_mobile;
    }

    public void setSt_mobile(String st_mobile) {
        this.st_mobile = st_mobile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "username='" + username + '\'' +
                ", st_name='" + st_name + '\'' +
                ", st_mobile='" + st_mobile + '\'' +
                ", id=" + id +
                '}';
    }
}
