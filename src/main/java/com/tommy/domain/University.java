package com.tommy.domain;

public class University {

    private Integer KEY;
    private String U_NAME;
    private String ADMIN_DEPARTMENT;
    private String U_CITY;
    private String U_LEVEL;
    private String U_PROVINCE;
    private String _985;
    private String _211;
    private String 一流大学建设高校;
    private String 一流学科建设高校;
    private String 研究生院;

    public Integer getKEY() {
        return KEY;
    }

    public void setKEY(Integer KEY) {
        this.KEY = KEY;
    }

    public String getU_NAME() {
        return U_NAME;
    }

    public void setU_NAME(String u_NAME) {
        U_NAME = u_NAME;
    }

    public String getADMIN_DEPARTMENT() {
        return ADMIN_DEPARTMENT;
    }

    public void setADMIN_DEPARTMENT(String ADMIN_DEPARTMENT) {
        this.ADMIN_DEPARTMENT = ADMIN_DEPARTMENT;
    }

    public String getU_CITY() {
        return U_CITY;
    }

    public void setU_CITY(String u_CITY) {
        U_CITY = u_CITY;
    }

    public String getU_LEVEL() {
        return U_LEVEL;
    }

    public void setU_LEVEL(String u_LEVEL) {
        U_LEVEL = u_LEVEL;
    }

    public String getU_PROVINCE() {
        return U_PROVINCE;
    }

    public void setU_PROVINCE(String u_PROVINCE) {
        U_PROVINCE = u_PROVINCE;
    }

    public String get_985() {
        return _985;
    }

    public void set_985(String _985) {
        this._985 = _985;
    }

    public String get_211() {
        return _211;
    }

    public void set_211(String _211) {
        this._211 = _211;
    }

    public String get一流大学建设高校() {
        return 一流大学建设高校;
    }

    public void set一流大学建设高校(String 一流大学建设高校) {
        this.一流大学建设高校 = 一流大学建设高校;
    }

    public String get一流学科建设高校() {
        return 一流学科建设高校;
    }

    public void set一流学科建设高校(String 一流学科建设高校) {
        this.一流学科建设高校 = 一流学科建设高校;
    }

    public String get研究生院() {
        return 研究生院;
    }

    public void set研究生院(String 研究生院) {
        this.研究生院 = 研究生院;
    }

    @Override
    public String toString() {
        return "University{" +
                "KEY=" + KEY +
                ", U_NAME='" + U_NAME + '\'' +
                ", ADMIN_DEPARTMENT='" + ADMIN_DEPARTMENT + '\'' +
                ", U_CITY='" + U_CITY + '\'' +
                ", U_LEVEL='" + U_LEVEL + '\'' +
                ", U_PROVINCE='" + U_PROVINCE + '\'' +
                ", _985='" + _985 + '\'' +
                ", _211='" + _211 + '\'' +
                ", 一流大学建设高校='" + 一流大学建设高校 + '\'' +
                ", 一流学科建设高校='" + 一流学科建设高校 + '\'' +
                ", 研究生院='" + 研究生院 + '\'' +
                '}';
    }
}
