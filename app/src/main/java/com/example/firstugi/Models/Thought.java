package com.example.firstugi.Models;

public class Thought {
    private String thought;
    private String writer;

    public Thought() {
    }

    public Thought(String thought, String writer) {
        this.thought = thought;
        this.writer = writer;
    }

    public String getThought() {
        return thought;
    }

    public void setThought(String thought) {
        this.thought = thought;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
