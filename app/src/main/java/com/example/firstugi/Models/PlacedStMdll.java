package com.example.firstugi.Models;

public class PlacedStMdll {
    private String image;
    private String collegename;
    private String name;
    private String course;
    private String companyname;
    private String branch;

    public PlacedStMdll() {
    }

    public PlacedStMdll(String image, String collegename, String name, String course, String companyname, String branch) {
        this.image = image;
        this.collegename = collegename;
        this.name = name;
        this.course = course;
        this.companyname = companyname;
        this.branch = branch;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCollegename() {
        return collegename;
    }

    public void setCollegename(String collegename) {
        this.collegename = collegename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
