package de.mkg_wegberg.kartenspielsammlung;


/**
 * Created by Felix on 06.10.2017.
 */

public class Karte
{
    private String name;
    private String aktuellerName;
    private String farbe;
    private String nummer;

    private boolean zeigeVorderseite;
    private boolean markiert;

    private int wert = 0;

    public Karte(String pFarbe, String pNummer, boolean pZeigeVorderseite)
    {
        farbe = pFarbe;
        nummer = pNummer;
        name = farbe + nummer;
        aktuellerName = name;
        zeigeVorderseite = pZeigeVorderseite;
    }

    public String getBilder()
    {
       if (zeigeVorderseite == true)
       {
           return aktuellerName;
       }
       else
       {
           return "rueckseite";
       }
    }

    public void aendereFarbe()
    {
        if (markiert)
        {
            aktuellerName =  name;
            markiert = false;

        }
        else
        {
            aktuellerName = "m" + name;
            markiert = true;
        }
    }

    public void setVorderseite()
    {
        zeigeVorderseite = true;
    }

    public void setRueckseite()
    {
        zeigeVorderseite = false;
    }

    public String getName()
    {
        return name;
    }

    public String getFarbe()
    {
        return farbe;
    }

    public String getNummer()
    {
        return nummer;
    }

    public boolean istMarkiert()
    {
        return markiert;
    }

    public void setzeWert(int pWert)
    {
        wert = pWert;
    }

    public int holeWert()
    {
        return wert;
    }


}
