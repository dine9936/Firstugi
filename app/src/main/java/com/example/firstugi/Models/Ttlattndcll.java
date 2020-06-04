package com.example.firstugi.Models;

public class Ttlattndcll {
    private String sbjctname;
    private String ttlclass;
    private String ttlpsnt;
    private String ttlabsnt;
    private String prsntprcnt;

    public Ttlattndcll() {
    }

    public String getSbjctname() {
        return sbjctname;
    }

    public void setSbjctname(String sbjctname) {
        this.sbjctname = sbjctname;
    }

    public String getTtlclass() {
        return ttlclass;
    }

    public void setTtlclass(String ttlclass) {
        this.ttlclass = ttlclass;
    }

    public String getTtlpsnt() {
        return ttlpsnt;
    }

    public void setTtlpsnt(String ttlpsnt) {
        this.ttlpsnt = ttlpsnt;
    }

    public String getTtlabsnt() {
        return ttlabsnt;
    }

    public void setTtlabsnt(String ttlabsnt) {
        this.ttlabsnt = ttlabsnt;
    }

    public String getPrsntprcnt() {
        return prsntprcnt;
    }

    public void setPrsntprcnt(String prsntprcnt) {
        this.prsntprcnt = prsntprcnt;
    }

    public Ttlattndcll(String sbjctname, String ttlclass, String ttlpsnt, String ttlabsnt, String prsntprcnt) {
        this.sbjctname = sbjctname;
        this.ttlclass = ttlclass;
        this.ttlpsnt = ttlpsnt;
        this.ttlabsnt = ttlabsnt;
        this.prsntprcnt = prsntprcnt;
    }
}
