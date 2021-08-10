package com.tommy.domain;

public class Major {

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
     * Physics
     * Chemical
     *
     * Biology
     * Politics
     * History
     *
     * Geography
     */
    private String Name;
    private String Major;
    private String LowestAdmission;
    private String EnrollmentYear;
    private String AdmissionsRestrictions;
    private String Physics;
    private String Chemical;
    private String Biology;
    private String Politics;
    private String History;
    private String Geography;
    private String Manage;               //
    private String Position;               //
    private String Level;               //
    private String Province;
    private String s985;
    private String s211;
    private String FirstClassUniversity;               //
    private String FirstClassDiscipline;               //
    private String Postgraduate;               //

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String Major) {
        this.Major = Major;
    }

    public String getLowestAdmission() {
        return LowestAdmission;
    }

    public void setLowestAdmission(String LowestAdmission) {
        this.LowestAdmission = LowestAdmission;
    }

    public String getEnrollmentYear() {
        return EnrollmentYear;
    }

    public void setEnrollmentYear(String EnrollmentYear) {
        this.EnrollmentYear = EnrollmentYear;
    }

    public String getAdmissionsRestrictions() {
        return AdmissionsRestrictions;
    }

    public void setAdmissionsRestrictions(String AdmissionsRestrictions) {
        this.AdmissionsRestrictions = AdmissionsRestrictions;
    }

    public String getPhysics() {
        return Physics;
    }

    public void setPhysics(String Physics) {
        this.Physics = Physics;
    }

    public String getChemical() {
        return Chemical;
    }

    public void setChemical(String Chemical) {
        this.Chemical = Chemical;
    }

    public String getBiology() {
        return Biology;
    }

    public void setBiology(String Biology) {
        this.Biology = Biology;
    }

    public String getPolitics() {
        return Politics;
    }

    public void setPolitics(String Politics) {
        this.Politics = Politics;
    }

    public String getHistory() {
        return History;
    }

    public void setHistory(String History) {
        this.History = History;
    }

    public String getGeography() {
        return Geography;
    }

    public void setGeography(String Geography) {
        this.Geography = Geography;
    }

    public String getManage() {
        return Manage;
    }

    public void setManage(String Manage) {
        this.Manage = Manage;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String Level) {
        this.Level = Level;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String Province) {
        this.Province = Province;
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

    public void setFirstClassUniversity(String FirstClassUniversity) {
        this.FirstClassUniversity = FirstClassUniversity;
    }

    public String getFirstClassDiscipline() {
        return FirstClassDiscipline;
    }

    public void setFirstClassDiscipline(String FirstClassDiscipline) {
        this.FirstClassDiscipline = FirstClassDiscipline;
    }

    public String getPostgraduate() {
        return Postgraduate;
    }

    public void setPostgraduate(String Postgraduate) {
        this.Postgraduate = Postgraduate;
    }

    @Override
    public String toString() {
        return "Major{" +
                "Name='" + Name + '\'' +
                ", Major='" + Major + '\'' +
                ", LowestAdmission='" + LowestAdmission + '\'' +
                ", EnrollmentYear='" + EnrollmentYear + '\'' +
                ", AdmissionsRestrictions='" + AdmissionsRestrictions + '\'' +
                ", Physics='" + Physics + '\'' +
                ", Chemical='" + Chemical + '\'' +
                ", Biology='" + Biology + '\'' +
                ", Politics='" + Politics + '\'' +
                ", History='" + History + '\'' +
                ", Geography='" + Geography + '\'' +
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
