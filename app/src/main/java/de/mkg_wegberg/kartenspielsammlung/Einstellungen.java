package de.mkg_wegberg.kartenspielsammlung;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

public class Einstellungen extends AppCompatActivity implements View.OnClickListener
{

    private boolean einmalGedrueckt = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einstellungen);

    }

    public void onClick (View v)
    {
        switch (v.getId())
        {

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == event.KEYCODE_BACK)
        {
            Intent oeffneHauptmenue = new Intent(getBaseContext(), Hauptmenue.class);
            startActivity(oeffneHauptmenue);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
