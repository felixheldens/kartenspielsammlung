package de.mkg_wegberg.kartenspielsammlung;

import java.util.ArrayList;

/**
 * Created by Felix on 07.10.2017.
 */

public class Spieler {
    public ArrayList<Karte> hand = new ArrayList<Karte>();
    ArrayList<Karte> temp = new ArrayList<>();
    private  ArrayList<Integer> gewaehlt = new ArrayList<Integer>();

    public String name;
    public boolean istBot;
    public Spiel spiel;


    public Spieler(String pName, Spiel pSpiel)
    {
        name = pName;
        spiel = pSpiel;
    }

    public void nehmeKarte(Karte pKarte)
    {
        hand.add(0, pKarte);
    }

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }

    public void waehleKarte(int pIndex)
    {
        if(!hand.isEmpty())
        {
            if(!hand.get(pIndex).istMarkiert())
            {
                Integer i = new Integer(pIndex);
                gewaehlt.add(i);
                hand.get(pIndex).aendereFarbe();

            }
            else
            {
                gewaehlt.remove(gewaehlt.indexOf(pIndex));
                hand.get(pIndex).aendereFarbe();
            }
        }
    }


    public ArrayList<Karte> gibGewaehlteKarten()
    {
        for(int i = 0; i < gewaehlt.size(); i++)
        {
            temp.add(hand.get(gewaehlt.get(i)));
            hand.get(gewaehlt.get(i)).aendereFarbe();
        }
        while(!gewaehlt.isEmpty())
        {
            int pIndex = gewaehlt.get(0);
            hand.remove(pIndex);
            gewaehlt.remove(0);
        }
        gewaehlt.clear();
        return temp;
    }


    public boolean istBot() {
        return istBot;
    }

    public int getHandzahl()
    {
        return hand.size();
    }

    public ArrayList<Karte> gibHand()
    {
        return hand;
    }

}
