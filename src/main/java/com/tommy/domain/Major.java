package com.tommy.domain;

/**
 * coding and debug by tommy
 */

public class Major {

    private Integer id;
    private String name;
    private String code;
    private String is_what;
    private String learn_what;
    private String do_what;
    private String degree;
    private String direction;
    private String celebrity;
    private String course;
    private String type;
    private String type_detail;
    private String level1_name;
    private String rate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIs_what() {
        return is_what;
    }

    public void setIs_what(String is_what) {
        this.is_what = is_what;
    }

    public String getLearn_what() {
        return learn_what;
    }

    public void setLearn_what(String learn_what) {
        this.learn_what = learn_what;
    }

    public String getDo_what() {
        return do_what;
    }

    public void setDo_what(String do_what) {
        this.do_what = do_what;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getCelebrity() {
        return celebrity;
    }

    public void setCelebrity(String celebrity) {
        this.celebrity = celebrity;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType_detail() {
        return type_detail;
    }

    public void setType_detail(String type_detail) {
        this.type_detail = type_detail;
    }

    public String getLevel1_name() {
        return level1_name;
    }

    public void setLevel1_name(String level1_name) {
        this.level1_name = level1_name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Major{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", is_what='" + is_what + '\'' +
                ", learn_what='" + learn_what + '\'' +
                ", do_what='" + do_what + '\'' +
                ", degree='" + degree + '\'' +
                ", direction='" + direction + '\'' +
                ", celebrity='" + celebrity + '\'' +
                ", course='" + course + '\'' +
                ", type='" + type + '\'' +
                ", type_detail='" + type_detail + '\'' +
                ", level1_name='" + level1_name + '\'' +
                ", rate='" + rate + '\'' +
                '}';
    }
}
