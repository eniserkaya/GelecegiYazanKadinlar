package com.eniserkaya.customlistview;

/**
 * Created by eniserkaya on 10.06.2017.
 */

public class Kullanici {

    String ad;
    int cinsiyet;
    String saat;

    public Kullanici(String ad, int fotoId, String saat) {
        this.ad = ad;
        this.cinsiyet = fotoId;
        this.saat = saat;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public int getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(int fotoId) {
        this.cinsiyet = fotoId;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }
}
