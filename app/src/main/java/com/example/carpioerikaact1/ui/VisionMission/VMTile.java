package com.example.carpioerikaact1.ui.VisionMission;

public class VMTile
{
    private String title;
    private  String content;

    public VMTile(String title, String content){
        this.title = title;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
