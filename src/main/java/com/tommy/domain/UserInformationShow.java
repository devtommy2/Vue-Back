package com.tommy.domain;

/**
 * coding and debug by tommy
 */

public class UserInformationShow {

    private Integer id;
    private String st_name;
    private Integer st_mark;
    private String st_mobile;
    private String username;
    private String password;
    private String identity;
    private Integer teacher_id;

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

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Integer getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }

    @Override
    public String toString() {
        return "UserInformationShow{" +
                "id=" + id +
                ", st_name='" + st_name + '\'' +
                ", st_mark=" + st_mark +
                ", st_mobile='" + st_mobile + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", identity='" + identity + '\'' +
                ", teacher_id=" + teacher_id +
                '}';
    }
}