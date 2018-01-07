package de.mkg_wegberg.kartenspielsammlung;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Felix on 06.10.2017.
 */

public class Spielverwaltung
{

    public Spielverwaltung()
    {

    }

    public ArrayList<Karte> erzeugeKartendeck(int pAnzahl)
    {
        ArrayList<Karte> kartendeck = new ArrayList<>();
        int jeFarbe = (pAnzahl / 4);

        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < jeFarbe; j++)
            {
                String pFarbe = holeParam1(i);
                String pNummer = holeParam2(j);
                Karte karte = new Karte(pFarbe, pNummer, true);
                kartendeck.add(karte);
            }
        }
        return kartendeck;
    }

    public ArrayList<Karte> mischeKarten(ArrayList<Karte> pList)
    {
        ArrayList<Karte> gemischteKarten = new ArrayList<Karte>();
        Log.d("vorher", pList.toString());
        while(!pList.isEmpty())
        {
            int zufall = zufallszahl(0, pList.size());
            Karte temp = pList.get(zufall);
            pList.remove(zufall);
            gemischteKarten.add(0, temp);
        }
        Log.d("nachher", gemischteKarten.toString());
        return gemischteKarten;
    }

    private String holeParam1(int pI)
    {
        switch(pI)
        {
            case 0:
                return "pic";
            case 1:
                return "kreuz";
            case 2:
                return "karo";
            case 3:
                return "herz";
            default:
                return "null";
        }
    }

    private String holeParam2(int pJ)
    {
        switch(pJ)
        {
            case 0:
                return "bube";
            case 1:
                return "dame";
            case 2:
                return "koenig";
            case 3:
                return "ass";
            case 4:
                return "10";
            case 5:
                return "9";
            case 6:
                return "8";
            case 7:
                return "7";
            case 8:
                return "6";
            case 9:
                return "5";
            case 10:
                return "4";
            case 11:
                return "3";
            case 12:
                return "2";
            default:
                return "null";
        }
    }

    private int zufallszahl(int pMin, int pMax)
    {
        int zufall = new Random().nextInt(pMax)+pMin;
        return zufall;
    }


    public void delay(int pDauer)
    {
        try {
            Thread.sleep(pDauer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
