package com.example.carpioerikaact1.ui.game;

public class GuessTile {
    private String info;
    private int number;

    public  GuessTile(String info, int number){
        this.info = info;
        this.number = number;
    }
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
