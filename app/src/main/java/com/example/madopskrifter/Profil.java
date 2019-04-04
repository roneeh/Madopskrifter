package com.example.madopskrifter;

import java.util.ArrayList;
import java.util.List;

/**
 * En klasse til profil
 * Formåle i denne klasse er, at brugen kan redigere og andre brugen kan instentierer på
 */
public class Profil {
    private int brugerId;
    private String brugerNavn;
    private String brugerEmail;
    private String brugerPassword;
    private boolean admin;
    private int likes;
    private List<Opskrift> egenOpskrift;
    private List<Opskrift> likeOpskrift;

    public Profil()
    {
    }

    public Profil(int brugerId, String brugerNavn, String brugerEmail, String brugerPassword, boolean admin)
    {
        this.brugerId = brugerId;
        this.brugerNavn = brugerNavn;
        this.brugerPassword = brugerPassword;
        this.brugerEmail = brugerEmail;
        this.admin = admin;
        egenOpskrift = new ArrayList<>();
        likeOpskrift = new ArrayList<>();
    }


    public int getBrugerId() {
        return brugerId;
    }

    public void setBrugerId(int brugerId) {
        this.brugerId = brugerId;
    }

    public String getBrugerNavn() {
        return brugerNavn;
    }

    public void setBrugerNavn(String brugerNavn) {
        this.brugerNavn = brugerNavn;
    }

    public String getBrugerEmail() {
        return brugerEmail;
    }

    public void setBrugerEmail(String brugerEmail) {
        this.brugerEmail = brugerEmail;
    }

    public String getBrugerPassword() {
        return brugerPassword;
    }

    public void setBrugerPassword(String brugerPassword) {
        this.brugerPassword = brugerPassword;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<Opskrift> getEgenOpskrift() {
        return egenOpskrift;
    }

    public void addToEgenOpskrift(Opskrift egenOpskrift) {
        this.egenOpskrift.add(egenOpskrift);
    }

    public List<Opskrift> getLikeOpskrift() {
        return likeOpskrift;
    }

    public void addToLikeOpskrift(Opskrift likeOpskrift) {
        this.likeOpskrift.add(likeOpskrift);
    }

}
