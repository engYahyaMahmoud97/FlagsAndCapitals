package com.example.flagsandcapitals.model;

public class AUser {
    private String username ;
    private String whatsappNumber ;
    private String pubgUid ;
    private String imageProfile ;

    public AUser(String username,String whatsappNumber, String pubgUid) {
        this.username = username;
        this.whatsappNumber = whatsappNumber;
        this.pubgUid = pubgUid;
    }

    public AUser() {
    }

    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }

    public void setWhatsappNumber(String whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

    public String getPubgUid() {
        return pubgUid;
    }

    public void setPubgUid(String pubgUid) {
        this.pubgUid = pubgUid;
    }
}
