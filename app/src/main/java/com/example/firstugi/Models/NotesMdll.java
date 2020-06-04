package com.example.firstugi.Models;

public class NotesMdll {
    private String image;
    private String name;
    private String info;

    public NotesMdll() {
    }

    public NotesMdll(String image, String name, String info) {
        this.image = image;
        this.name = name;
        this.info = info;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
