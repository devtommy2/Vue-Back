package com.tommy.domain;

public class T_Major {

    /**
     * major_id   专业的唯一标识
     * school_id    学校id
     * Name   学校名
     * AdmissionCode     招生代码
     * AdmissionDirection
     * Province   招生省份
     * Direction
     * Major    专业名
     * MethodCode   专业代码
     * Batch       段位
     * enrollments   招生人数
     * LowScore   最低分
     * LowLevel  最低位次
     * Request   选课要求描述
     * Physics       T/F
     * Chemical       T/F
     * Biology       T/F
     * Politics       T/F
     * History       T/F
     * Geography       T/F
     */
    /**
     * coding and debug by tommy
     */

    private Integer major_id;
    private Integer school_id;
    private String Name;
    private String AdmissionCode;
    private String AdmissionDirection;
    private String Province;
    private String Direction;
    private String Major;
    private String MethodCode;
    private String Batch;
    private String enrollments;
    private Integer LowScore;
    private Integer LowLevel;
    private String Request;
    private String Physics;
    private String Chemical;
    private String Biology;
    private String Politics;
    private String History;
    private String Geography;



    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAdmissionCode() {
        return AdmissionCode;
    }

    public void setAdmissionCode(String admissionCode) {
        AdmissionCode = admissionCode;
    }

    public String getAdmissionDirection() {
        return AdmissionDirection;
    }

    public void setAdmissionDirection(String admissionDirection) {
        AdmissionDirection = admissionDirection;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getDirection() {
        return Direction;
    }

    public void setDirection(String direction) {
        Direction = direction;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String major) {
        Major = major;
    }

    public String getMethodCode() {
        return MethodCode;
    }

    public void setMethodCode(String methodCode) {
        MethodCode = methodCode;
    }

    public String getBatch() {
        return Batch;
    }

    public void setBatch(String batch) {
        Batch = batch;
    }

    public String getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(String enrollments) {
        this.enrollments = enrollments;
    }

    public Integer getLowScore() {
        return LowScore;
    }

    public void setLowScore(Integer lowScore) {
        LowScore = lowScore;
    }

    public Integer getLowLevel() {
        return LowLevel;
    }

    public void setLowLevel(Integer lowLevel) {
        LowLevel = lowLevel;
    }

    public String getRequest() {
        return Request;
    }

    public void setRequest(String request) {
        Request = request;
    }

    public String getPhysics() {
        return Physics;
    }

    public void setPhysics(String physics) {
        Physics = physics;
    }

    public String getChemical() {
        return Chemical;
    }

    public void setChemical(String chemical) {
        Chemical = chemical;
    }

    public String getBiology() {
        return Biology;
    }

    public void setBiology(String biology) {
        Biology = biology;
    }

    public String getPolitics() {
        return Politics;
    }

    public void setPolitics(String politics) {
        Politics = politics;
    }

    public String getHistory() {
        return History;
    }

    public void setHistory(String history) {
        History = history;
    }

    public String getGeography() {
        return Geography;
    }

    public void setGeography(String geography) {
        Geography = geography;
    }

    public Integer getMajor_id() {
        return major_id;
    }

    public void setMajor_id(Integer major_id) {
        this.major_id = major_id;
    }

    public Integer getSchool_id() {
        return school_id;
    }

    public void setSchool_id(Integer school_id) {
        this.school_id = school_id;
    }

    @Override
    public String toString() {
        return "T_Major{" +
                "major_id=" + major_id +
                ", school_id=" + school_id +
                ", Name='" + Name + '\'' +
                ", AdmissionCode='" + AdmissionCode + '\'' +
                ", AdmissionDirection='" + AdmissionDirection + '\'' +
                ", Province='" + Province + '\'' +
                ", Direction='" + Direction + '\'' +
                ", Major='" + Major + '\'' +
                ", MethodCode='" + MethodCode + '\'' +
                ", Batch='" + Batch + '\'' +
                ", enrollments='" + enrollments + '\'' +
                ", LowScore='" + LowScore + '\'' +
                ", LowLevel='" + LowLevel + '\'' +
                ", Request='" + Request + '\'' +
                ", Physics='" + Physics + '\'' +
                ", Chemical='" + Chemical + '\'' +
                ", Biology='" + Biology + '\'' +
                ", Politics='" + Politics + '\'' +
                ", History='" + History + '\'' +
                ", Geography='" + Geography + '\'' +
                '}';
    }
}
