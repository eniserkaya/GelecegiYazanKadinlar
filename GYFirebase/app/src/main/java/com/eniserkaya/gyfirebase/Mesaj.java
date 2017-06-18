package com.eniserkaya.gyfirebase;

/**
 * Created by eniserkaya on 18.06.2017.
 */

public class Mesaj {
    private String mesaj;
    private String gonderenAdi;

    public Mesaj(){}

    public Mesaj(String mesaj, String gonderenAdi) {
        this.mesaj = mesaj;
        this.gonderenAdi = gonderenAdi;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public String getGonderenAdi() {
        return gonderenAdi;
    }

    public void setGonderenAdi(String gonderenAdi) {
        this.gonderenAdi = gonderenAdi;
    }
}
