package com.example.firstugi.Models;

public class SubjectMdll {
    private String tchrname;
    private String sbjctname;

    public SubjectMdll() {
    }

    public SubjectMdll(String tchrname, String sbjctname) {
        this.tchrname = tchrname;
        this.sbjctname = sbjctname;
    }

    public String getTchrname() {
        return tchrname;
    }

    public void setTchrname(String tchrname) {
        this.tchrname = tchrname;
    }

    public String getSbjctname() {
        return sbjctname;
    }

    public void setSbjctname(String sbjctname) {
        this.sbjctname = sbjctname;
    }
}
