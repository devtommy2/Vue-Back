package com.tommy.domain;

public class University {

    /**
     * id
     * Name
     * Manage
     * Position
     * Level
     * Province
     * s985
     * s211
     * FirstClassUniversity
     * FirstClassDiscipline
     * Postgraduate
     * Major
     * LowestAdmission
     * EnrollmentYear
     * AdmissionsRestrictions
     *
     */
    private Integer KEY;
    private String Name;
    private String Manage;
    private String Position;
    private String Level;
    private String Province;
    private String s985;
    private String s211;
    private String FirstClassUniversity;
    private String FirstClassDiscipline;
    private String Postgraduate;

    public Integer getKEY() {
        return KEY;
    }

    public void setKEY(Integer KEY) {
        this.KEY = KEY;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getManage() {
        return Manage;
    }

    public void setManage(String manage) {
        Manage = manage;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getS985() {
        return s985;
    }

    public void setS985(String s985) {
        this.s985 = s985;
    }

    public String getS211() {
        return s211;
    }

    public void setS211(String s211) {
        this.s211 = s211;
    }

    public String getFirstClassUniversity() {
        return FirstClassUniversity;
    }

    public void setFirstClassUniversity(String firstClassUniversity) {
        FirstClassUniversity = firstClassUniversity;
    }

    public String getFirstClassDiscipline() {
        return FirstClassDiscipline;
    }

    public void setFirstClassDiscipline(String firstClassDiscipline) {
        FirstClassDiscipline = firstClassDiscipline;
    }

    public String getPostgraduate() {
        return Postgraduate;
    }

    public void setPostgraduate(String postgraduate) {
        Postgraduate = postgraduate;
    }

    @Override
    public String toString() {
        return "University{" +
                "KEY=" + KEY +
                ", Name='" + Name + '\'' +
                ", Manage='" + Manage + '\'' +
                ", Position='" + Position + '\'' +
                ", Level='" + Level + '\'' +
                ", Province='" + Province + '\'' +
                ", s985='" + s985 + '\'' +
                ", s211='" + s211 + '\'' +
                ", FirstClassUniversity='" + FirstClassUniversity + '\'' +
                ", FirstClassDiscipline='" + FirstClassDiscipline + '\'' +
                ", Postgraduate='" + Postgraduate + '\'' +
                '}';
    }
}
