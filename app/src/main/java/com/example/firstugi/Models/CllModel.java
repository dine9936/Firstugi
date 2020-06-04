package com.example.firstugi.Models;

public class CllModel {
    private String username;
    private String title;
    private String desc;
    private String imageUrl;
    private String posttext;

    public CllModel() {
    }

    public CllModel(String username, String title, String desc, String imageUrl, String posttext) {
        this.username = username;
        this.title = title;
        this.desc = desc;
        this.imageUrl = imageUrl;
        this.posttext = posttext;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPosttext() {
        return posttext;
    }

    public void setPosttext(String posttext) {
        this.posttext = posttext;
    }
}
