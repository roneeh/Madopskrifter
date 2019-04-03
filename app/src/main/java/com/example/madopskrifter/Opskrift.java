package com.example.madopskrifter;

import net.sourceforge.jtds.jdbc.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Dette er Opskrift klassen.
 * Formålet med denne klasse er, at vi vil kunne instantiere et objekt, som kan indeholde de informationer, som vi se / bruge fra databasen.
 */
public class Opskrift {
    private int id; ///< ID'et på opskriften. Dette bliver tildelt i databasen.
    private String titel; ///< Titlen på opskriften.
    private List<Ingrediens> ingrediensListe; ///< Her lavet vi et List objekt med Ingredienser, den bliver instantieret i constructoren.
    private List<Trin> trinList; ///< Her lavet vi et List objekt med Trin, den bliver instantieret i constructoren.
    private String datoOprettet; ///< Datoen opskriften er oprettet.
    private int oprettetAf; ///< Hvem der har oprettet opskriften.
    private int likes; ///< Hvor mange likes denne opskrift har.
    private byte[] hovedbillede; ///< Billedet der vises med titlen, når man ser den på forsiden og i opskriften.

    /**
     * Herunder har vi lavet en constructor til vores klasse.
     * Meningen med dette er at når vi vil oprette en ny opskrift, vil vi lave en ny instans af denne klasse,
     * Herunder bliver listerne ingrediensListe og trinList instantieret.
     * Derudover vil datoOprettet bliver instantieret med dagens dato, og oprettetAf vil få brugeren, som der er logget på's ID assignet.
     */
    public Opskrift()
    {
        ingrediensListe = new ArrayList<>();
        trinList = new ArrayList<>();
        datoOprettet = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        oprettetAf = MainActivity.currentMainActivity.currentProfile.getBrugerId();
    }

    /**
     * Formålet med denne metode er at tilføje en ingrediens til ingrediensListe ListArray'et.
     * @param ingrediens
     * Denne parameter godtager et objekt af Ingrediens klassen.
     */

    private void AddIngrediens(Ingrediens ingrediens)
    {
        ingrediensListe.add(ingrediens);
    }

    /**
     * Formålet med denne metode er at tilføje et trin til trinList ListArray'et.
     * @param trin
     * Denne parameter godtager et objekt af Trin klassen.
     */

    private void AddTrin(Trin trin)
    {
        trinList.add(trin);
    }




}

