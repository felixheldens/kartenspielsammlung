package de.mkg_wegberg.kartenspielsammlung;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Hauptmenue extends AppCompatActivity implements View.OnClickListener
{

    private Button bSPstarten;
    private Button bEinstllungen;
    private Button bStatistiken;
    private Button bHilfe;
    private Button bShop;
    private Button bCredits;

    private boolean einmalGedrueckt;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hauptmenue);

        bSPstarten      = (Button) findViewById(R.id.bSPstartenId);
        bEinstllungen   = (Button) findViewById(R.id.bEinstellungenId);
        bStatistiken    = (Button) findViewById(R.id.bStatistikenId);
        bHilfe          = (Button) findViewById(R.id.bHilfeId);
        bShop           = (Button) findViewById(R.id.bShopId);
        bCredits        = (Button) findViewById(R.id.bCreditsId);

        bSPstarten.setOnClickListener(this);
        bEinstllungen.setOnClickListener(this);
        bStatistiken.setOnClickListener(this);
        bHilfe.setOnClickListener(this);
        bShop.setOnClickListener(this);
        bCredits.setOnClickListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {

        if(keyCode == event.KEYCODE_BACK)

        {

            if(!einmalGedrueckt){
                einmalGedrueckt = true;
                Toast.makeText(getApplicationContext(), "Erneut drücken, um zu beenden.", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        einmalGedrueckt = false;
                    }
                }, 3000);
            }
            else
            {
                einmalGedrueckt = false;
                onBackPressed();
            }

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bSPstartenId:
                Intent oeffneModus_Waehlen = new Intent(getBaseContext(), Modus_Waehlen.class);
                startActivity(oeffneModus_Waehlen);
                finish();
                break;
            case R.id.bEinstellungenId:
                Intent oeffneEinstellungen = new Intent(getBaseContext(), Einstellungen.class);
                startActivity(oeffneEinstellungen);
                finish();
                break;
            case R.id.bStatistikenId:
                Intent oeffneStatistiken = new Intent(getBaseContext(), Statistiken.class);
                startActivity(oeffneStatistiken);
                finish();
                break;
            case R.id.bHilfeId:
                Intent oeffneHilfe = new Intent(getBaseContext(), Hilfe.class);
                startActivity(oeffneHilfe);
                finish();
                break;
            case R.id.bShopId:
                Intent oeffneShop = new Intent(getBaseContext(), Shop.class);
                startActivity(oeffneShop);
                finish();
                break;
            case R.id.bCreditsId:
                Intent oeffneCredits = new Intent(getBaseContext(), Credits.class);
                startActivity(oeffneCredits);
                finish();
                break;
            default:break;
        }
    }
}
