package com.example.firstugi.Models;

public class CompnyMdll {
    private String image;
    private String name;
    private String date;
    private  String month;
    private String year;

    public CompnyMdll() {
    }

    public CompnyMdll(String image, String name, String date, String month, String year) {
        this.image = image;
        this.name = name;
        this.date = date;
        this.month = month;
        this.year = year;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
