package com.composite.entity;

public class Msg {
    private String title;
    private String content;
    private String extraInfo;

    public Msg() {

    }

    public Msg(String title, String content, String extraInfo) {
        this.title = title;
        this.content = content;
        this.extraInfo = extraInfo;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getExtraInfo() {
        return extraInfo;
    }
}
