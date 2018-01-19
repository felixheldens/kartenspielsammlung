package de.mkg_wegberg.kartenspielsammlung;

import java.util.ArrayList;

/**
 * Created by Felix on 13.10.2017.
 */

public class Bot extends Spieler
{
    private String name;
    private int schwierigkeit = 1;
    private ArrayList<Karte> gewaehlteKarten = new ArrayList<Karte>();

    private ArrayList<Karte> moeglich = new ArrayList<Karte>();

    public Bot(String pName, Spiel pSpiel)
    {
        super(pName, pSpiel);
        istBot = true;
    }

    public void macheZug()
    {
        if (!hand.isEmpty())
        {
            for(int i = 0; i < hand.size(); i++){
                if(spiel.darfLegen(hand.get(i))){
                    gewaehlteKarten.add(hand.get(i));
                    hand.remove(i);
                }
            }
            if(gewaehlteKarten.size() > 1 && schwierigkeit > 1){
                holeWerte(moeglich);
            }

        }
    }

    private ArrayList legen(int pIndex)
    {
        waehleKarte(pIndex);
        ArrayList<Karte> temp  = super.gibGewaehlteKarten();

        return temp;
    }

    private void holeWerte(){
        for(int i = 0; i < gewaehlteKarten.size(); i++)
        {
            gewaehlteKarten.get(i).setzeWert(spiel.bewertung(gewaehlteKarten.get(i)));
        }

        int bestes = 0;
        for(int i = 0; i < gewaehlteKarten.size(); i++){
            if(gewaehlteKarten.get(i).holeWert() > bestes)
            {

            }
        }
    }






}
