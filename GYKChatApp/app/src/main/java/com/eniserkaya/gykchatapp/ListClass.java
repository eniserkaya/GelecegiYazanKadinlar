package com.eniserkaya.gykchatapp;

/**
 * Created by eniserkaya on 9.07.2017.
 */

public class ListClass {
    private int iconID;
    private String secenek;

    public ListClass(int iconID, String secenek) {
        this.iconID = iconID;
        this.secenek = secenek;
    }

    public int getIconID() {
        return iconID;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }

    public void setSecenek(String secenek) {
        this.secenek = secenek;
    }
    public String getSecenek(){
        return  secenek;
    }
}
