package de.mkg_wegberg.kartenspielsammlung;

import java.util.ArrayList;

/**
 * Created by Felix on 13.10.2017.
 */

public class Bot extends Spieler
{
    private String name;

    public Bot(String pName, Spiel pSpiel)
    {
        super(pName, pSpiel);
        istBot = true;
    }

    public ArrayList<Karte> macheZug()
    {
        if (!hand.isEmpty())
        {
            waehleKarte(0);
            ArrayList<Karte> temp  = super.gibGewaehlteKarten();
            return temp;
        }

        else{
        return null;
    }

    }



}
