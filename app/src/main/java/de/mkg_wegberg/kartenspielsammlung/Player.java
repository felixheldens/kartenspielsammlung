package de.mkg_wegberg.kartenspielsammlung;

/**
 * Created by Felix on 10.11.2017.
 */

public class Player extends Spieler
{
    public Player(String pName, Spiel pSpiel)
    {
        super(pName, pSpiel);
        istBot = false;
    }

}
