package com.example.madopskrifter;

import java.util.List;

public class Profil {

    private int brugerId;
    private String brugerNavn;
    private String brugerEmail;
    private String brugerPassword;
    private String admin;
    private int likes;
    private List<Opskrift> egenOpskrift;
    private List<Opskrift> likeOpskrift;
}
