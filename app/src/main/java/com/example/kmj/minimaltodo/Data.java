package com.example.kmj.minimaltodo;


public class Data {
    String maintext;
    String subtext;
    String extra;


    public Data(String maintext, String subtext, String extra) {
        this.maintext = maintext;
        this.subtext = subtext;
        this.extra = extra;
    }

    public String getMaintext() {
        return maintext;
    }

    public String getSubtext() {
        return subtext;
    }

    public String getExtra() {
        return extra;
    }

    public void setMaintext(String maintext) {



        this.maintext = maintext;
    }

    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
