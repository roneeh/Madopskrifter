package com.example.madopskrifter;

import java.util.Date;
import java.util.List;

public class Opskrift {
    private int id;
    private String titel;
    private List<Ingrediens> ingrediensListe;
    private Date datoOprettet;
    private String oprettetAf;
    private int likes;
    private byte[] hovedbillede;
}
