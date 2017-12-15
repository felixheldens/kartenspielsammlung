package de.mkg_wegberg.kartenspielsammlung;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class MauMau_konfigurieren extends AppCompatActivity implements View.OnClickListener
{
    private Button bStart;
    private Switch sKartenAnzahl;
    private EditText etSpieleranzahl;

    private int kartenanzahl;
    private int spieleranzahl;
    private int anzahlProSpieler;

    private boolean nachMenge = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mau_mau_konfigurieren);

        bStart          = (Button) findViewById(R.id.bStartId);
        sKartenAnzahl   = (Switch) findViewById(R.id.sKartenanzahlId);
        etSpieleranzahl = (EditText) findViewById(R.id.etSpieleranzahlId);

        bStart.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.bStartId:
                Intent oeffneSpiel = new Intent(getBaseContext(),Spiel.class);
                einstellungenUebernehmen();
                oeffneSpiel.putExtra("pKartenAnzahl", kartenanzahl);
                oeffneSpiel.putExtra("pBotanzahl", spieleranzahl);
                oeffneSpiel.putExtra("pAnzahlProSpieler", anzahlProSpieler);
                oeffneSpiel.putExtra("pNachMenge", nachMenge);
                startActivity(oeffneSpiel);
                finish();
                break;
            default: break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == event.KEYCODE_BACK)
        {
            Intent oeffneModus_Waehlen = new Intent(getBaseContext(), Modus_Waehlen.class);
            startActivity(oeffneModus_Waehlen);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void einstellungenUebernehmen()
    {
        if(sKartenAnzahl.isChecked())
        {
            kartenanzahl = 52;
        }
        else
        {
            kartenanzahl = 32;
        }

        spieleranzahl =  Integer.parseInt(etSpieleranzahl.getText().toString());
        // @TODO Zu Testzwecken geforced
        anzahlProSpieler = 6;

    }


}
