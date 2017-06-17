package com.eniserkaya.gynotdefteri;

/**
 * Created by eniserkaya on 16.06.2017.
 */

public class Not {
    private String baslik;
    private String notIcerik;
    private String konum;
    private String id;
    private byte[] foto;

    public Not(String baslik, String notIcerik, String konum, byte[] foto) {
        this.baslik = baslik;
        this.notIcerik = notIcerik;
        this.konum = konum;
        this.foto = foto;
    }
    public Not(String id,String baslik, String notIcerik, String konum, byte[] foto) {
        this.baslik = baslik;
        this.notIcerik = notIcerik;
        this.konum = konum;
        this.foto = foto;
        this.id=id;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getNotIcerik() {
        return notIcerik;
    }

    public void setNotIcerik(String notIcerik) {
        this.notIcerik = notIcerik;
    }

    public String getKonum() {
        return konum;
    }

    public void setKonum(String konum) {
        this.konum = konum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

}
