package com.example.firstugi.Models;

public class IntrnlmarkMll {
    private String sbjctname;
    private String ssnlone;
    private String ssnltwo;
    private String ssnlthree;

    public IntrnlmarkMll() {
    }

    public IntrnlmarkMll(String sbjctname, String ssnlone, String ssnltwo, String ssnlthree) {
        this.sbjctname = sbjctname;
        this.ssnlone = ssnlone;
        this.ssnltwo = ssnltwo;
        this.ssnlthree = ssnlthree;
    }

    public String getSbjctname() {
        return sbjctname;
    }

    public void setSbjctname(String sbjctname) {
        this.sbjctname = sbjctname;
    }

    public String getSsnlone() {
        return ssnlone;
    }

    public void setSsnlone(String ssnlone) {
        this.ssnlone = ssnlone;
    }

    public String getSsnltwo() {
        return ssnltwo;
    }

    public void setSsnltwo(String ssnltwo) {
        this.ssnltwo = ssnltwo;
    }

    public String getSsnlthree() {
        return ssnlthree;
    }

    public void setSsnlthree(String ssnlthree) {
        this.ssnlthree = ssnlthree;
    }
}
