package com.example.demothigiuaky;

import android.media.Image;

import java.io.Serializable;
import java.time.Year;
import java.util.Date;

public class Player implements Serializable {
    String name;
    int year,avt,flag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getAvt() {
        return avt;
    }

    public void setAvt(int avt) {
        this.avt = avt;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Player(String name, int year, int avt, int flag) {
        this.name = name;
        this.year = year;
        this.avt = avt;
        this.flag = flag;
    }
}
