package com.eniserkaya.gykchatapp;

/**
 * Created by eniserkaya on 8.07.2017.
 */

public class MessageClass {

    private String userName;
    private String message;
    private String photoUrl;

    public MessageClass(){};
    

    public MessageClass(String userName, String message, String photoUrl) {
        this.userName = userName;
        this.message = message;
        this.photoUrl = photoUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
